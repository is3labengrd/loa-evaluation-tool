import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subscription, Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-process-list',
  templateUrl: './process-list.component.html',
  styleUrls: ['./process-list.component.css']
})
export class ProcessListComponent implements OnInit {

  processSegmentList:Array<any> = []

  private processSegments:Array<any>;
  private subprocesses:Array<any>;
  numberOfItems = "all";
  page = 0;
  private paginate = false;

  private getAllPageIterator = () => {
    var i = 1;
    return {
      next: function() {
        return {
          "value": "0",
          "done": !(i--)
        }
      }
    };
  }

  private getPageIteratorGetter(maxNumberOfPages) {
    return () => {
      var i = 0;
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

  constructor(private http:HttpClient) { }

  ngOnInit() {
    this.populateProcessSegmentList();
  }

  private populateProcessSegmentList() {
    switch (this.numberOfItems) {
      case "all":
        this.pageIterable[Symbol.iterator] = this.getAllPageIterator;
        this.initateProcessSegmentListPopulationWithoutPagination();
        break;
      default:
        this.initiateProcessSegmentListPopulationWithPagination();
        break;
    }
  }

  private initateProcessSegmentListPopulationWithoutPagination() {
    var processSegmentsSubscription:Subscription = this.http
      .get(environment.apiUrl + '/v1/process-segments')
      .subscribe(
        (processSegments:Array<any>) => {
          this.processSegments = processSegments;
          processSegmentsSubscription.unsubscribe();
          this.triggerProcessSegmentListPopulation();
        }
      );
    var subprocessLevelsSubscription:Subscription = this.http
      .get(environment.apiUrl + '/v1/subprocess-levels')
      .subscribe(
        (subprocesses:Array<any>) => {
          this.subprocesses = subprocesses;
          subprocessLevelsSubscription.unsubscribe();
          this.triggerProcessSegmentListPopulation();
        }
      );
  }

  private initiateProcessSegmentListPopulationWithPagination() {
    var processSegmentsSubscription:Subscription = this.http
      .get(environment.apiUrl + '/v1/process-segments')
      .subscribe(
        (processSegments:Array<any>) => {
          this.processSegments = processSegments;
          processSegmentsSubscription.unsubscribe();
          this.triggerProcessSegmentListPopulation();
        }
      );
    var subprocessLevelsSubscription:Subscription = this.http
      .get(
        environment.apiUrl + '/v1/subprocess-levels?' +
        'page=' + this.page + '&' +
        'size=' + this.numberOfItems
      )
      .subscribe(
        (subprocesses:any) => {
          this.pageIterable[Symbol.iterator] = this
            .getPageIteratorGetter(subprocesses.totalPages);
          console.log(subprocesses.totalPages);
          this.subprocesses = subprocesses.content;
          subprocessLevelsSubscription.unsubscribe();
          this.triggerProcessSegmentListPopulation();
        }
      );
  }

  private triggerProcessSegmentListPopulation = (() => {
    var i = 0;
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
      (accumulator:Array<any>, subprocess) => {
        var currentDisplaySubprocess;
        var relatedProcess = this.processSegments[subprocess.fkTbAceProSeq-1];
        if (subprocess.proLevel == 1) {
          currentDisplaySubprocess = Object.create(null);
          currentDisplaySubprocess.name = relatedProcess.name;
          currentDisplaySubprocess.sublevels = relatedProcess.nlowerLevelSubPro;
          currentDisplaySubprocess.sub1 = "-";
          currentDisplaySubprocess.sub2 = "-";
          currentDisplaySubprocess.sub3 = "-";
          currentDisplaySubprocess.route = "main-analysis";
          currentDisplaySubprocess.actions = "Analysis";
          accumulator.push(currentDisplaySubprocess);
        } else {
          currentDisplaySubprocess = accumulator[accumulator.length-1];
        }
        switch (subprocess.proLevel) {
          case 1:
            currentDisplaySubprocess.sub1=subprocess.name;
            break;
          case 2:
            currentDisplaySubprocess.sub2=subprocess.name;
            break;
          case 3:
            currentDisplaySubprocess.sub3=subprocess.name;
            break;
        }
        return accumulator;
      },
      []
    );
  }

}
