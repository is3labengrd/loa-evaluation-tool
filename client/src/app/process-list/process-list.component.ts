import { StateHolderService } from '../state-holder.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subscription, Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import {ProcessListService} from './process-list.service';


@Component({
  selector: 'app-process-list',
  templateUrl: './process-list.component.html',
  styleUrls: ['./process-list.component.css']
})
export class ProcessListComponent implements OnInit {

  processSegmentList: Array<any> = [];
  private processSegments: Array<any>;
  private subprocesses: Array<any>;
  numberOfItems = 'all';
  page = 0;
  lastPage = 0;

  private getAllPageIterator = () => {
    let i = 1;
    return {
      next: function() {
        return {
          'value': '0',
          'done': !(i--)
        }
      }
    };
  }

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
    [Symbol.iterator]: this.getAllPageIterator
  };

  constructor(
    private http: HttpClient,
    private stateHolder: StateHolderService,
    private _processListService: ProcessListService
  ) {}

  ngOnInit() {
    //this._processListService.setCookie("loa_test","100",1,"");
    this.populateProcessSegmentList();
  }

  private populateProcessSegmentList() {
    switch (this.numberOfItems) {
      case 'all':
        this.pageIterable[Symbol.iterator] = this.getAllPageIterator;
        this.initateProcessSegmentListPopulationWithoutPagination();
        break;
      default:
        this.initiateProcessSegmentListPopulationWithPagination();
        break;
    }
  }

  private initateProcessSegmentListPopulationWithoutPagination() {
    let processSegmentsSubscription: Subscription = this.http
      .get(environment.apiUrl + '/v1/process-segments')
      .subscribe(
        (processSegments: Array<any>) => {
          this.processSegments = processSegments;
          processSegmentsSubscription.unsubscribe();
          this.triggerProcessSegmentListPopulation();
        }
      );
    let subprocessLevelsSubscription: Subscription = this.http
      .get(environment.apiUrl + '/v1/subprocess-levels')
      .subscribe(
        (subprocesses: Array<any>) => {
          this.subprocesses = subprocesses;
          subprocessLevelsSubscription.unsubscribe();
          this.triggerProcessSegmentListPopulation();
        }
      );
  }

  private initiateProcessSegmentListPopulationWithPagination() {
    let processSegmentsSubscription: Subscription = this.http
      .get(environment.apiUrl + '/v1/process-segments')
      .subscribe(
        (processSegments: Array<any>) => {
          this.processSegments = processSegments;
          processSegmentsSubscription.unsubscribe();
          this.triggerProcessSegmentListPopulation();
        }
      );
    let subprocessLevelsSubscription: Subscription = this.http
      .get(
        environment.apiUrl + '/v1/subprocess-levels?' +
        'page=' + this.page + '&' +
        'size=' + this.numberOfItems
      )
      .subscribe(
        (subprocesses: any) => {
          this.pageIterable[Symbol.iterator] = this
            .getPageIteratorGetter(subprocesses.totalPages);
          this.lastPage = subprocesses.totalPages - 1;
          this.subprocesses = subprocesses.content;
          subprocessLevelsSubscription.unsubscribe();
          this.triggerProcessSegmentListPopulation();
        }
      );
  }

  private triggerProcessSegmentListPopulation = (() => {
    let i = 0;
    return () => {
      i++;
      if (i == 2) {
        i = 0;
        this.populateProcessSegmentListFromSubprocesses();
      }
    }
  })();

  private populateProcessSegmentListFromSubprocesses() {
    this.processSegmentList = this.subprocesses.reduce(
      (accumulator: Array<any>, subprocess) => {
        let currentDisplaySubprocess;
        let relatedProcess = this.processSegments[subprocess.fkTbAceProSeq - 1] || {
          name: 'error',
          nlowerLevelSubPro: 'error',
          pkTbId: NaN
        };
        if (subprocess.proLevel == 1 || accumulator.length == 0) {
          currentDisplaySubprocess = Object.create(null);
          currentDisplaySubprocess.name = relatedProcess.name;
          currentDisplaySubprocess.sublevels = relatedProcess.nlowerLevelSubPro;
          currentDisplaySubprocess.sub1 = { name: '-' };
          currentDisplaySubprocess.sub2 = { name: '-' };
          currentDisplaySubprocess.sub3 = { name: '-' };
          currentDisplaySubprocess.route = 'main-analysis';
          currentDisplaySubprocess.editRoute = (
            Number.isNaN(relatedProcess.pkTbId) ?
              '/error'
            :
              `/edit-process/${relatedProcess.pkTbId}`
          );
          currentDisplaySubprocess.actions = 'Analysis';
          accumulator.push(currentDisplaySubprocess);
        } else {
          currentDisplaySubprocess = accumulator[accumulator.length - 1];
        }
        switch (subprocess.proLevel) {
          case 1:
            currentDisplaySubprocess.sub1 = subprocess;
            break;
          case 2:
            currentDisplaySubprocess.sub2 = subprocess;
            break;
          case 3:
            currentDisplaySubprocess.sub3 = subprocess;
            break;
        }
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

  private saveAsIsInfo(info: Array<any>) {
    this.stateHolder.storeData(info);
  }

}
