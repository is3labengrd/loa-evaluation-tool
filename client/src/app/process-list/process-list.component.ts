import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subscription, Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';


@Component({
  selector: 'app-process-list',
  templateUrl: './process-list.component.html',
  styleUrls: ['./process-list.component.css']
})
export class ProcessListComponent implements OnInit {

  rawProcessSegmentList: Array<any> = [];
  processSegmentList: Array<any> = Array(12);
  numberOfItems = 12;
  page = 0;
  lastPage = 0;

  private getPageIteratorGetter(maxNumberOfPages) {
    return () => {
      let i = 0;
      return {
        next: () => ({
          value: i.toString(),
          done: (i++ == maxNumberOfPages)
        })
      }
    }
  }

  pageIterable = {
    [Symbol.iterator]: () => { }
  };

  constructor(
    private http: HttpClient,
    private _processListService: CookieService
  ) { }

  ngOnInit() {
    this.populateProcessSegmentList();
    this.http
      .post(environment.apiUrl + '/v1/var/populate-process-segments', {})
      .toPromise()
      .then(
        () => { this.populateProcessSegmentList(); }
      )
  }

  private populateProcessSegmentList() {
    this.initiateProcessSegmentListPopulationWithPagination();
  }

  private initiateProcessSegmentListPopulationWithPagination() {
    let subprocessLevelsSubscription: Subscription = this.http
      .get(
        environment.apiUrl + '/v1/process-segment-list-elements?' +
        'page=' + this.page + '&' +
        'size=' + this.numberOfItems
      )
      .subscribe(
        (processSegmentListElements: any) => {
          this.pageIterable[Symbol.iterator] = this
            .getPageIteratorGetter(processSegmentListElements.totalPages);
          this.lastPage = processSegmentListElements.totalPages - 1;
          this.rawProcessSegmentList = processSegmentListElements.content;
          subprocessLevelsSubscription.unsubscribe();
          this.adaptProcessSegmentList();
        }
      );
  }

  private adaptProcessSegmentList() {
    this.processSegmentList = this.rawProcessSegmentList.reduce(
      (accumulator: Array<any>, listElement) => {
        let currentListElement = Object.create(null);
        let id = listElement.mainProcess ? listElement.mainProcess.pkTbId : "error"
        currentListElement.name = listElement.mainProcess ? listElement.mainProcess.name : "error";
        currentListElement.sublevels = listElement.mainProcess ? listElement.mainProcess.nlowerLevelSubPro : "error";
        currentListElement.sub1 = listElement.subProcessLevel1 ? listElement.subProcessLevel1.name : "-";
        currentListElement.sub2 = listElement.subProcessLevel2 ? listElement.subProcessLevel2.name : "-";
        currentListElement.sub3 = listElement.subProcessLevel3 ? listElement.subProcessLevel3.name : "-";
        currentListElement.editRoute = `/edit-process/${listElement.pkTbId}`;
        if (
          currentListElement.sub1 == "-" &&
          currentListElement.sub2 == "-" &&
          currentListElement.sub3 == "-"
        ) {
          currentListElement.route = `/add-process/${id}`;
          currentListElement.actions = "Add";
        } else {
          currentListElement.route = "/main-analysis";
          currentListElement.actions = "Analysis";
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

  saveAnalysisData(data, subProcessData) {
    var actualSubProcessInfo = {};
    actualSubProcessInfo['mainProcessName'] = subProcessData.name;
    actualSubProcessInfo['mainProcessId'] = subProcessData.rawElementReference.mainProcess.pkTbId;
    actualSubProcessInfo['subLevels'] = subProcessData.sublevels;
    actualSubProcessInfo['totalNumberSubprocs'] = data.mainProcess.subprocessLevels.length;
    actualSubProcessInfo['maxDepth'] = 0;
    for (var i = 1; i <= subProcessData.sublevels; i++) {
      if (!("undefined" === typeof (subProcessData.rawElementReference['subProcessLevel' + i])) && subProcessData.rawElementReference['subProcessLevel' + i] != null) {
        actualSubProcessInfo['level' + i] = new SubProcess(subProcessData.rawElementReference['subProcessLevel' + i].pkTbId, subProcessData.rawElementReference['subProcessLevel' + i].name);
        actualSubProcessInfo['maxDepth'] = i;
      }
    }
    this._processListService.setCookie("selectedSubprocess", JSON.stringify(actualSubProcessInfo), 1, "");
  }

}

function SubProcess(id, name) {
  this.id = id;
  this.name = name;
}


