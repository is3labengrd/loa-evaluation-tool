import { Component, OnInit, NgZone } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { Router, ActivatedRoute} from '@angular/router';
import {CookieService} from '../cookie.service';
import { AngularWaitBarrier } from 'blocking-proxy/built/lib/angular_wait_barrier';
import { allowSanitizationBypass } from '@angular/core/src/sanitization/bypass';

@Component({
  selector: 'app-add-process',
  templateUrl: './add-process.component.html',
  styleUrls: ['./add-process.component.css']
})
export class AddProcessComponent implements OnInit {

  processSegmentList: Array<any> = [];
  mainProcess: Array<any> = [];
  subL1: Array<any> = [];
  subL2: Array<any> = [];
  subL3: Array<any> = [];
  lvl1selection: any;
  lvl2selection: any;
  lvl3selection: any;
  private _values1 = [];
  private _values2 = [];
  private _values3 = [];
  id: number;
  lowerLevel: any;
  pks: Array<any> = [];
  cookie: any;
  processAdded: Array<any> = [];

  constructor(
    private http: HttpClient,
    public router: Router,
    private route: ActivatedRoute,
    private zone: NgZone,
    private _processListService: CookieService
  ) {}

  ngOnInit() {
    this.cookie = this._processListService.getCookie('selectedSubprocess');
    this.id  = this.route.snapshot.params['id'];

    return this.http
    .get(environment.apiUrl + '/v1/process-segments/' + this.id)
    .toPromise()
    .then(
      (main: any) => {
        this.mainProcess.push(main);
        this.fetchFromCAM(main);
      }
    );
  }

  fetchFromCAM(mainP) {

    return this.http
    .get(environment.apiUrl + '/v1/var/process-segments')
    .toPromise()
    .then(
      (processSegments: Array<any>) => {
        this.processSegmentList.push(processSegments);
        this._values1.push(this.subProcessL1(mainP.name));
        this.lowerLevel = this.findObj(this.processSegmentList[0], this.mainProcess[0].name);
      }
    );
  }

  findObj(obj: any, name: any) {
    // tslint:disable-next-line:prefer-const
    for (let k in obj) {
      if (obj[k].name === name) {
        return obj[k];
      }
    }
  }

  refresh(): void {
    this._values1 = [];
    this._values2 = [];
    this._values3 = [];
    this.lvl1selection = null;
    this.lvl2selection = null;
    this.lvl3selection = null;

     this.http
    .get(environment.apiUrl + '/v1/process-segments/' + this.id)
    .toPromise()
    .then(
      (main: any) => {
        this.mainProcess.push(main);
        this.fetchFromCAM(main);
      }
    );
  }

  away() {
    if(this.opSuc){
      this.router.navigate(['process-list']);
    }
    if(!this.opSuc){
      this.refresh();
    }
  }

  // tslint:disable-next-line:member-ordering
  opSuc: boolean;

  addProcess() {

    let enablePost = false;

    let promise = this.checkAlreadyAddedProcSegm();
    promise.then((x) => {
      for(let i in this.processAdded[0]){
        if(this.processAdded[0][i].mainProcess.pkTbId === this.cookie.mainProcessId){
          if(this.lvl3selection === undefined || this.lvl3selection === null){
            if(this.lvl2selection === undefined || this.lvl2selection === null){
              if(this.lvl1selection === undefined || this.lvl1selection === null){
                break;
              }else{
                if(this.processAdded[0][i].subProcessLevel1 !=null){
                  if (this.lvl1selection.name === this.processAdded[0][i].subProcessLevel1.name){
                    enablePost = true;
                    // alert("This process segment already exist!");
                    this.opSuc = false;
                    break;
                  }
                }
              }
            }
            else{
              if(this.processAdded[0][i].subProcessLevel1 !=null && this.processAdded[0][i].subProcessLevel2 !=null){
                if (this.lvl1selection.name === this.processAdded[0][i].subProcessLevel1.name && this.lvl2selection.name === this.processAdded[0][i].subProcessLevel2.name){
                  enablePost = true;
                  // alert("This process segment already exist!");
                  this.opSuc = false;
                  break;
                }
              }
            }
          }else{
            if(this.processAdded[0][i].subProcessLevel1 !=null && this.processAdded[0][i].subProcessLevel2 !=null && this.processAdded[0][i].subProcessLevel3 !=null){
              if (this.lvl1selection.name === this.processAdded[0][i].subProcessLevel1.name && this.lvl2selection.name === this.processAdded[0][i].subProcessLevel2.name && this.lvl3selection.name === this.processAdded[0][i].subProcessLevel3.name){
                enablePost = true;
                // alert("This process segment already exist!");
                this.opSuc = false;
                break;
              }
            }
          }
        }
      }

      if(!enablePost){
        this.opSuc = true;
        this.addSubProcessL1(this.id);
      }
    });
  }

  addProSecOrdered() {

    const addProSecOrderedUrl = environment.apiUrl + '/v1/process-segment-list-elements';

    // tslint:disable-next-line:max-line-length
    return this.http.post(addProSecOrderedUrl, {'fkTbAceProSeq': this.id , 'fkTbAceSubProLev1': this.pks[0], 'fkTbAceSubProLev2': this.pks[1], 'fkTbAceSubProLev3': this.pks[2]})
    .toPromise()
    .then((res: any) => {
      if (res.pkTbId != null) {
      }
    },
    (err) => alert('Something went wrong. \nStatus: ' +  err.error.status)
  );
}


addSubProcessL1(pkTbId) {

  const subProcUrl = environment.apiUrl + '/v1/subprocess-levels';
  // tslint:disable-next-line:max-line-length
  return this.http.post(subProcUrl, {'fkTbAceProSeq': this.id, 'name': this.lvl1selection.name, 'varProSeqId': this.lvl1selection.processSegmentId, 'proLevel': this.lvl1selection.level})
  .toPromise()
  .then((res: any) => {
    if (res.pkTbId != null) {
      this.pks.push(res.pkTbId);
      // tslint:disable-next-line:prefer-const
      let response = this.addSubProcessL2(res.pkTbId);
      response.then((x) => {

      });
    }
  },
  (err) => {alert('Something went wrong. \nStatus: ' +  err.error.status); }
);
}

addSubProcessL2(pkTbId) {
  const subProcUrl = environment.apiUrl + '/v1/subprocess-levels';
  if (this.lvl2selection != null) {
    // tslint:disable-next-line:max-line-length
    return this.http.post(subProcUrl, {'fkTbAceProSeq': this.id, 'name': this.lvl2selection.name, 'varProSeqId': this.lvl2selection.processSegmentId, 'proLevel': this.lvl2selection.level})
    .toPromise()
    .then((res: any) => {
      if (res.pkTbId != null) {
        this.pks.push(res.pkTbId);
        // tslint:disable-next-line:prefer-const
        let promise2 = this.addSubProcessL3(res.pkTbId);
        promise2.then((x) => { this.addProSecOrdered(); });
      }
    },
    (err) => {alert('Something went wrong. \nStatus: ' +  err.error.status); }); } else {
      this.addProSecOrdered();
      return Promise.resolve();
    }
  }

  addSubProcessL3(pkTbId) {
    const subProcUrl = environment.apiUrl + '/v1/subprocess-levels';

    if (this.lvl3selection != null) {
      // tslint:disable-next-line:max-line-length
      return this.http.post(subProcUrl, {'fkTbAceProSeq': this.id, 'name': this.lvl3selection.name, 'varProSeqId': this.lvl3selection.processSegmentId, 'proLevel': this.lvl3selection.level})
      .toPromise()
      .then((res: any) => {
        if (res.pkTbId != null) {
          this.pks.push(res.pkTbId);
        }
      },
      (err) => {alert('Something went wrong. \nStatus: ' +  err.error.status); });
    } else {
      return Promise.resolve();
    }
  }

  firstDropDownChanged(val: any) {
    const obj = this._values1[0];
    if (!obj) { return; }
    this._values2 = this.subProcessL2(this.mainProcess[0].name, obj[val].name);
    this.lvl1selection = obj[val];
  }

  secondDropDownChanged(val2: any) {
    const obj2 = this._values2;
    if (!obj2) { return; }
    this._values3 = this.subProcessL3(this.mainProcess[0].name, this.lvl1selection.name, obj2[val2].name);
    this.lvl2selection = obj2[val2];
  }

  thirdDropDownChanged(val3: any) {
    const obj3 = this._values3;
    if (!obj3) { return; }
    this.lvl3selection = obj3[val3];
  }

  subProcessL1(mainProc) {
    this.subL1 = [];
    // tslint:disable-next-line:prefer-const
    for (let entry of this.processSegmentList[0]) {
      if (mainProc === entry.name) {
        // tslint:disable-next-line:prefer-const
        for (let sub of entry.subProcesses) {
          this.subL1.push(sub);
        }
      }
    }
    return this.subL1;
  }

  subProcessL2(mainProc, subProcessL1) {
    this.subL2 = [];
    // tslint:disable-next-line:prefer-const
    for (let entry of this.processSegmentList[0]) {
      if (mainProc === entry.name) {
        // tslint:disable-next-line:prefer-const
        for (let sub of entry.subProcesses) {
          if (subProcessL1 === sub.name) {
            // tslint:disable-next-line:prefer-const
            for (let sub2 of sub.subProcesses) {
              this.subL2.push(sub2);
            }
          }
        }
      }
    }
    return this.subL2;
  }

  subProcessL3(mainProc, subProcessL1, subProcessL2) {
    this.subL3 = [];
    // tslint:disable-next-line:prefer-const
    for (let entry of this.processSegmentList[0]) {
      if (mainProc === entry.name) {
        // tslint:disable-next-line:prefer-const
        for (let sub of entry.subProcesses) {
          if (subProcessL1 === sub.name) {
            // tslint:disable-next-line:prefer-const
            for (let sub2 of sub.subProcesses) {
              if (subProcessL2 === sub2.name) {
                // tslint:disable-next-line:prefer-const
                for (let sub3 of sub2.subProcesses) {
                  this.subL3.push(sub3);
                }
              }
            }
          }
        }
      }
    }
    return this.subL3;
  }

  checkAlreadyAddedProcSegm() {

    return this.http
    .get(environment.apiUrl + '/v1/process-segment-list-elements')
    .toPromise()
    .then(
      (res: any) => {
        this.processAdded.push(res);
      }
    );
  }
}
