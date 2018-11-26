import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';
import { $ } from 'protractor';


@Component({
  selector: 'app-process-list',
  templateUrl: './process-list.component.html',
  styleUrls: ['./process-list.component.css']
})
export class ProcessListComponent implements OnInit {

  baseImg = environment.production == true ?
       '/' + environment.hostUrl + '/assets/':
       '/assets/';

  rawProcessSegmentList: Array<any> = [];
  processSegmentList: Array<any> = Array(12);
  numberOfItems = 12;
  page = 0;
  lastPage = 0;
  searchText = '';
  syncingWithVAR = false;

  private getPageIterator(maxNumberOfPages) {
    return () => {
      let i = 0;
      return {
        next: () => ({
          value: i.toString(),
          done: (i++ == maxNumberOfPages)
        })
      };
    };
  }

  // tslint:disable-next-line:member-ordering
  pageIterable: any = (function * () {}());

  // tslint:disable-next-line:member-ordering
  modal = document.getElementById('errorModal');

  private deleteProcess(processId) {
    return this
      .http
      .delete(environment.apiUrl + '/v1/process-segments/' + processId)
      .toPromise();
  }

  private deleteListElement(elementId) {
    return this
      .http
      .delete(environment.apiUrl + '/v1/process-segment-list-elements/' + elementId)
      .toPromise();
  }

  constructor(
    private http: HttpClient,
    private _processListService: CookieService
  ) { }

  ngOnInit() {
    this.populateProcessSegmentList();
    this.syncingWithVAR = true;
    this.http
      .post(environment.apiUrl + '/v1/var/populate-process-segments', {})
      .toPromise()
      .then(
        () => {
          this.syncingWithVAR = false;
          this.populateProcessSegmentList();
        }
      )
      .catch(
        (err) => {
          this.syncingWithVAR = false;
          // alert('CAM is not working.');
          console.log('CAM is not working');
        }
      );
  }

  populateProcessSegmentList = () => {
    this.initiateProcessSegmentListPopulationWithPagination();
  }

  initiateProcessSegmentListPopulationWithPagination = () => {
    let url;
    if (this.searchText.length) {
      url = environment.apiUrl +
        `/v1/process-segment-list-elements-like/${this.searchText}?` +
        'page=' + this.page + '&' +
        'size=' + this.numberOfItems;
    } else {
      url = environment.apiUrl +
        '/v1/process-segment-list-elements?' +
        'page=' + this.page + '&' +
        'size=' + this.numberOfItems;
    }
    this.http
      .get(url)
      .toPromise()
      .then(
        (processSegmentListElements: any) => {
          this.pageIterable[Symbol.iterator] = this
            .getPageIterator(processSegmentListElements.totalPages);
          this.lastPage = processSegmentListElements.totalPages - 1;
          this.rawProcessSegmentList = processSegmentListElements.content;
          this.adaptProcessSegmentList();
        }
      );
  }

  adaptProcessSegmentList = () => {
    this.processSegmentList = this.rawProcessSegmentList.reduce(
      (accumulator: Array<any>, listElement) => {
        let currentListElement = Object.create(null);
        let id = listElement.mainProcess ? listElement.mainProcess.pkTbId : "error";
        currentListElement.name = listElement.mainProcess ? listElement.mainProcess.name : "error";
        currentListElement.sub1 = listElement.subProcessLevel1 ? listElement.subProcessLevel1.name : "-";
        currentListElement.sub2 = listElement.subProcessLevel2 ? listElement.subProcessLevel2.name : "-";
        currentListElement.sub3 = listElement.subProcessLevel3 ? listElement.subProcessLevel3.name : "-";
        currentListElement.sublevels = " ";
        currentListElement.editRoute = `/edit-process/${listElement.pkTbId}`;
        if (
          currentListElement.sub1 == '-' &&
          currentListElement.sub2 == '-' &&
          currentListElement.sub3 == '-'
        ) {
          currentListElement.route = `/add-process/${id}`;
          currentListElement.action = "Add";
          currentListElement.deleteAction = "Delete Process";
          currentListElement.delete = () => this.deleteProcess(
            listElement.mainProcess.pkTbId
          )
          currentListElement.editRoute = null;
          currentListElement.sublevels = listElement.mainProcess ?
            listElement.mainProcess.nlowerLevelSubPro:
            "error";
        } else {
          currentListElement.route = '/main-analysis';
          currentListElement.action = 'Analysis';
          currentListElement.deleteAction = "Delete Segment";
          currentListElement.delete = () => this.deleteListElement(listElement.pkTbId)
          let subprocessCount = 0;
          for (let property in listElement) {
            if (property.match(/subProcessLevel\d/)) {
              if (listElement[property]) {
                subprocessCount++
              }
            }
          }
          currentListElement.sublevels = subprocessCount;
        }
        currentListElement.rawElementReference = listElement;
        accumulator.push(currentListElement);
        return accumulator;
      },
      []
    );
  }

  private previousPage() {
    this.page = Math.max(0, --this.page);
  }

  private nextPage() {
    this.page = Math.min(this.lastPage, ++this.page);
  }

  saveAnalysisData = (data, subProcessData) => {
    var actualSubProcessInfo = {};
    actualSubProcessInfo['mainProcessName'] = subProcessData.name;
    actualSubProcessInfo['mainProcessId'] = subProcessData.rawElementReference.mainProcess.pkTbId;
    actualSubProcessInfo['nLowerLevelSubProcs'] = data.mainProcess.nlowerLevelSubPro;
    actualSubProcessInfo['subLevels'] = subProcessData.sublevels;
    actualSubProcessInfo['totalNumberSubprocs'] = data.mainProcess.subprocessLevels.length;
    actualSubProcessInfo['maxDepth'] = 0;
    for (var i = 1; i <= subProcessData.sublevels; i++) {
      if (!('undefined' === typeof (subProcessData.rawElementReference['subProcessLevel' + i])) && subProcessData.rawElementReference['subProcessLevel' + i] != null) {
        actualSubProcessInfo['level' + i] = new SubProcess(subProcessData.rawElementReference['subProcessLevel' + i].pkTbId, subProcessData.rawElementReference['subProcessLevel' + i].name);
        actualSubProcessInfo['maxDepth'] = i;
      }
    }
    this._processListService.setCookie('selectedSubprocess', JSON.stringify(actualSubProcessInfo), 1, '');
  }

}

function SubProcess(id, name) {
  this.id = id;
  this.name = name;
}


