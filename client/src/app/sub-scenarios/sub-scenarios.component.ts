import { CookieService } from './../cookie.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-sub-scenarios',
  templateUrl: './sub-scenarios.component.html',
  styleUrls: ['./sub-scenarios.component.css']
})
export class SubScenariosComponent implements OnInit {

  productsNum: number;
  procSpecInfoObj: { shiftsPerDay: number, hoursPerShift: number, workingDayPerY: number, propWageCostsPerH: number} =
                   { shiftsPerDay: null, hoursPerShift: null, workingDayPerY: null, propWageCostsPerH: null};
  sub: { sub1: any, sub2: any, sub3: any} =
       { sub1: null, sub2: null, sub3: null};
  bestLoaCog: Object = {};
  bestLoaPhy: Object = {};
  ProcTime: Object = {};
  disableButton: boolean;
  resources: any;
  selectedRes1: any;
  selectedRes2: any;
  selectedRes3: any;
  firstdropdown: any;
  seconddropdown: any;
  thirddropdown: any;
  mainProcess: any;
  lowerLevel: any;
  lowerLevelObj: any;
  resourcesList: Array<any> = [];

  // tslint:disable-next-line:no-shadowed-variable
  constructor(private http: HttpClient, private CookieService: CookieService) { }

  ngOnInit() {
    this.getMainProc(3);
    this.checkMandatoryData();
    this.getResource();
  }

  getResource() {
    this.http
      .get(environment.apiUrl + '/v1/resources')
      .toPromise()
      .then(
        (res: any) => {
          this.resources = res;
          this.resourcesList.push(res);
        }
      );
  }

  checkMandatoryData() {
    if (this.productsNum != null && this.procSpecInfoObj.shiftsPerDay != null && this.procSpecInfoObj.hoursPerShift != null
         && this.procSpecInfoObj.workingDayPerY != null && this.procSpecInfoObj.propWageCostsPerH != null) {
      this.disableButton = true; } else {
        this.disableButton = false;
      }
  }

  productInfo(): void {
    this.checkMandatoryData();
  }

  procSpecInfo(): void {
    this.checkMandatoryData();
  }

  createSubScenarios(): void {
    this.checkMandatoryData();
  }

  firstDropDownChanged(val: any) {
    this.firstdropdown = this.findObj(this.resourcesList[0], val);
  }

  secondDropDownChanged(val: any) {
    this.seconddropdown = this.findObj(this.resourcesList[0], val);
  }

  thirdDropDownChanged(val: any) {
    this.thirddropdown = this.findObj(this.resourcesList[0], val);
  }

  getMainProc(id) {
      this.http
        .get(environment.apiUrl + '/v1/process-segments/' + id)
        .toPromise()
        .then(
          (main: any) => {
            this.getSubProc(main);
            this.fetchFromCAM(main);
            this.mainProcess = main;
          }
        );
  }

  fetchFromCAM(mainP) {
    this.http
      .get(environment.apiUrl + '/v1/var/process-segments')
      .toPromise()
      .then(
        (processSegments: Array<any>) => {
          this.lowerLevelObj = this.findObj(processSegments, mainP.name);
          this.lowerLevel = this.lowerLevelObj.subProcLowerLevel;
          console.log(this.lowerLevel);
        }
      );

  }

  findObj(obj: any, name: any) {
    for (const k in obj) {
      if (obj[k].name === name) {
        return obj[k];
       }
    }
  }

  getSubProc(mainP) {

    this.http.get(environment.apiUrl + '/v1/subprocess-levels')
      .toPromise()
      .then(
        (subProcessesList: any) => {

          for (const i in subProcessesList) {
            if (subProcessesList[i].fkTbAceProSeq === mainP.pkTbId) {
              this.sub.sub1 = subProcessesList[i];
             }
          }

          for (const j in subProcessesList) {
            if (subProcessesList[j].fkTbAceProSeq === this.sub.sub1.pkTbId) {
              this.sub.sub2 = subProcessesList[j];
             }
          }
          for (const k in subProcessesList) {
            if (subProcessesList[k].fkTbAceProSeq === this.sub.sub2.pkTbId) {
              this.sub.sub3 = subProcessesList[k];
             }
          }
        }
      );
  }
}
