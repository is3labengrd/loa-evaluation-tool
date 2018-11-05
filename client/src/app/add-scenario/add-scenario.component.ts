import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';

@Component({
  selector: 'app-add-scenario',
  templateUrl: './add-scenario.component.html',
  styleUrls: ['./add-scenario.component.css']
})
export class AddScenarioComponent implements OnInit {

  constructor(private http: HttpClient, private _processListService: CookieService) { }

  resSubSceList:Array<any> = [];
  subSceList:Array<any> = [];
  cookie: any;
  objlist:any={};
  subProc:any;
  scenNumber:any;

  ngOnInit() {
    this.cookie = this._processListService.getCookie("selectedSubprocess");
    var waitresponse = this.getScenariosList();
    waitresponse.then((x)=>{
        this.creteTable();
        console.log(this.subSceList);
        this.deleteDupicate();
    });
  }

  creteTable(){
    for(let i in this.resSubSceList[0]){
      this.objlist= {};
      if(this.resSubSceList[0][i].fkTbAceProSeq === this.cookie.mainProcessId){
          for(let j in this.resSubSceList[0]){
              if(this.resSubSceList[0][i].subprocessLevel.pkTbId === this.resSubSceList[0][j].subprocessLevel.pkTbId){
                if(this.resSubSceList[0][j].scenarioNumber === 1){
                  this.objlist['scenNumber1'] = this.resSubSceList[0][j];
                }
                if(this.resSubSceList[0][j].scenarioNumber === 2){
                  this.objlist['scenNumber2'] = this.resSubSceList[0][j];
                }
                if(this.resSubSceList[0][j].scenarioNumber === 3){
                  this.objlist['scenNumber3'] = this.resSubSceList[0][j];
                }
              }
          }
            this.subProc = {"subProc": this.resSubSceList[0][i], "objList": this.objlist};
            this.subSceList.push(this.subProc);
      }
    }
  }

  deleteDupicate(){
    var result = this.subSceList.filter(function (a) {
          return !this[a.subProc.subprocessLevel.pkTbId] && (this[a.subProc.subprocessLevel.pkTbId] = true);
      }, Object.create(null));
    this.subSceList = result;
  }

  getScenariosList() {
    return this.http
      .get(environment.apiUrl + '/v1/subscenarios')
      .toPromise()
      .then(
        (res:any) => {
            this.resSubSceList.push(res);
        }
      )
  }

}
