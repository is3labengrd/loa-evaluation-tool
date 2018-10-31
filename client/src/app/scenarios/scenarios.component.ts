import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';

@Component({
  selector: 'app-scenarios',
  templateUrl: './scenarios.component.html',
  styleUrls: ['./scenarios.component.css']
})
export class ScenariosComponent implements OnInit {

  constructor(private http: HttpClient, private _processListService: CookieService) { }

  resSubSceList:Array<any> = [];
  subSceList:Array<any> = [];
  cookie: any;
  objlist:Array<any> = [];
  subProc:any;
  scenNumber:any;

  ngOnInit() {
    this.cookie = this._processListService.getCookie("selectedSubprocess");
    var waitresponse = this.getScenariosList();
    waitresponse.then((x)=>{
        this.creteTable();
        console.log(this.subSceList);
    });
  }

  creteTable(){
    for(let i in this.resSubSceList[0]){
      this.objlist= [];
      if(this.resSubSceList[0][i].fkTbAceProSeq === this.cookie.mainProcessId){
      for(let j in this.resSubSceList[0]){
          if(this.resSubSceList[0][i].fkTbAceSubProLev === this.resSubSceList[0][j].fkTbAceSubProLev){
            if(this.resSubSceList[0][j].scenarioNumber === 1){
              this.scenNumber = {"scenNumber1": this.resSubSceList[0][j]}
              this.objlist.push(this.scenNumber);
            }
            if(this.resSubSceList[0][j].scenarioNumber === 2){
              this.scenNumber = {"scenNumber2": this.resSubSceList[0][j]}
              this.objlist.push(this.scenNumber);
            }
            if(this.resSubSceList[0][j].scenarioNumber === 3){
              this.scenNumber = {"scenNumber3": this.resSubSceList[0][j]}
              this.objlist.push(this.scenNumber);
            }
          }
      }
        this.subProc = {"subProc": this.resSubSceList[0][i], "objList": this.objlist};
        this.subSceList.push(this.subProc);
      }
    }
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
