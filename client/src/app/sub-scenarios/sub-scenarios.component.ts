import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import {ProcessListService} from '../process-list/process-list.service';

@Component({
  selector: 'app-sub-scenarios',
  templateUrl: './sub-scenarios.component.html',
  styleUrls: ['./sub-scenarios.component.css']
})
export class SubScenariosComponent implements OnInit {

  productsNum:number;
  shiftsPerDay:number;
  hoursPerShift:number;
  workingDayPerY:number;
  propWageCostsPerH:number;
  disableButton:boolean;
  resources:any;
  selectedRes1:any;
  selectedRes2:any;
  selectedRes3:any;

  selectedtest:any;
  sub1:any;
  sub2:any;
  sub3:any;
  mainProcess:any;
  lowerLevel:any;
  resourcesList:Array<any> = [];


  constructor(private http: HttpClient, private _processListService: ProcessListService) { }

  ngOnInit() {
//    console.log(this._processListService.getCookie("loa_test"));
    this.getMainProc(3);
    this.checkMandatoryData();
    this.getResource();
  }

  getResource(){
    this.http
      .get(environment.apiUrl + '/v1/resources')
      .toPromise()
      .then(
        (res:any) => {
          this.resources = res;
          this.resourcesList.push(res);
        }
      )
  }

  checkMandatoryData(){
    if(this.productsNum != null && this.shiftsPerDay != null && this.hoursPerShift != null && this.workingDayPerY != null && this.propWageCostsPerH != null){
      this.disableButton=true;}else{
        this.disableButton=false;
      }
  }

  productInfo(): void{
    this.checkMandatoryData();
  }

  procSpecInfo(): void{
    this.checkMandatoryData();
  }

  firstDropDownChanged(val: any) {
    this.selectedtest = this.findObj(this.resourcesList[0], val);
  }

  getMainProc(id){
      this.http
        .get(environment.apiUrl + '/v1/process-segments/'+id)
        .toPromise()
        .then(
          (main:any) => {
            this.getSubProc(main);
            this.fetchFromCAM(main);
            this.mainProcess = main;
          }
        )
  }

  fetchFromCAM(mainP) {
    this.http
      .get(environment.apiUrl + '/v1/var/process-segments')
      .toPromise()
      .then(
        (processSegments:Array<any>) => {
          let LL = this.findObj(processSegments,mainP.name);
          this.lowerLevel = LL.subProcLowerLevel;
        }
      )

  }

  findObj(obj: any, name: any){
    for (let k in obj) {
      if (obj[k].name === name){
        return obj[k];
       }
    }
  }

  getSubProc(mainP) {

    this.http.get(environment.apiUrl + '/v1/subprocess-levels')
      .toPromise()
      .then(
        (subProcessesList:any) => {

          for (let i in subProcessesList) {
            if (subProcessesList[i].fkTbAceProSeq === mainP.pkTbId){
              this.sub1 = subProcessesList[i];
             }
          }

          for (let j in subProcessesList) {
            if (subProcessesList[j].fkTbAceProSeq === this.sub1.pkTbId){
              this.sub2 = subProcessesList[j];
             }
          }
          for (let k in subProcessesList) {
            if (subProcessesList[k].fkTbAceProSeq === this.sub2.pkTbId){
              this.sub3 = subProcessesList[k];
             }
          }
        }
      )
  }

}
