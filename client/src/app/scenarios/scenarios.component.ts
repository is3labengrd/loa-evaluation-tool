import { Component, OnInit, NgZone } from '@angular/core';
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
  processSegmentList:Array<any> = [];
  allProcessSegmentCAM:Array<any> = [];
  scenarios:any={};
  showAddEdit:any={};
  scenario1:Array<any> = [];
  scenario2:Array<any> = [];
  scenario3:Array<any> = [];
  resourceTable:Array<any> = [];
  syncingWithVAR = false;

  ngOnInit() {
    this.cookie = this._processListService.getCookie("selectedSubprocess");
    this.syncingWithVAR = true;

    var waitCAMProcTree = this.fetchFromCAM();
    var waitScenarioList = this.getScenariosList();
    var waitScen = this.getScenarios();
    waitScenarioList.then((x)=>{
      waitScen.then((x)=>{
        this.creteTable();
        waitCAMProcTree.then((x)=>{
          this.createResTable();
        });
      });
    });
  }

  creteTable(){
    for(let i in this.resSubSceList[0]){
      if(this.resSubSceList[0][i].subprocessLevel.fkTbAceProSeq === this.cookie.mainProcessId){

        if(this.scenarios.scen1 != null){
          if(this.resSubSceList[0][i].fkTbAceScenarios === this.scenarios.scen1.pkTbId){
            this.scenario1.push(this.resSubSceList[0][i]);
          }
        }

        if(this.scenarios.scen2 != null){
          if(this.resSubSceList[0][i].fkTbAceScenarios === this.scenarios.scen2.pkTbId){
            this.scenario2.push(this.resSubSceList[0][i]);
          }
        }

        if(this.scenarios.scen3 != null){
          if(this.resSubSceList[0][i].fkTbAceScenarios === this.scenarios.scen3.pkTbId){
            this.scenario3.push(this.resSubSceList[0][i]);
          }
        }
      }
    }
  }

  getScenariosList() {
    return this.http
    .get(environment.apiUrl + '/v1/scenario-resources')
    .toPromise()
    .then(
      (res:any) => {
        this.resSubSceList.push(res);
      }
    )
  }

  fetchFromCAM() {

    return this.http
    .get(environment.apiUrl + '/v1/var/process-segments')
    .toPromise()
    .then(
      (processSegments:any) => {
        this.processSegmentList.push(processSegments);
        this.syncingWithVAR = false;
      }
    );
  }

  createResTable(){
    for(let i in this.processSegmentList[0]){
      if (this.processSegmentList[0][i].name === this.cookie.mainProcessName){
        for (let j in this.processSegmentList[0][i].subProcesses){
          this.loop(this.processSegmentList[0][i].subProcesses[j].subProcesses);
        }
      }
    }
    var obj = {}
    for (let k in this.allProcessSegmentCAM){
      obj = {"name": this.allProcessSegmentCAM[k],
      "res1": this.findRes(this.scenario1, this.allProcessSegmentCAM[k]),
      "res2": this.findRes(this.scenario2, this.allProcessSegmentCAM[k]),
      "res3": this.findRes(this.scenario3, this.allProcessSegmentCAM[k])}
      this.resourceTable.push(obj);
      obj = {};
    }
  }

  findRes(list, name){
    for(let i in list){
      if(list[i].subprocessLevel.name === name){
        return list[i].resource.name;
      }
    }
  }

  loop(camList:any){
    if (camList.length === 0){
      return;
    }

    for (let j in camList){
      this.allProcessSegmentCAM.push(camList[j].name);
      this.loop(camList[j].subProcesses);
    }
  }

  getScenarios() {
    return this.http
    .get(environment.apiUrl + '/v1/scenarios')
    .toPromise()
    .then(
      (res:any) => {

        for (let i in res){
          if(res[i].fkTbAceProSeq === this.cookie.mainProcessId ){
            if(res[i].scenarioNumber === 1){
              this.scenarios['scen1'] = res[i];
            }
            if(res[i].scenarioNumber === 2){
              this.scenarios['scen2'] = res[i];
            }
            if(res[i].scenarioNumber === 3){
              this.scenarios['scen3'] = res[i];
            }
          }
        }

        if(this.scenarios.scen1 != null){
          this.showAddEdit['show1'] = true;
        }else{
          this.showAddEdit['show1'] = false;
        }

        if(this.scenarios.scen2 != null){
          this.showAddEdit['show2'] = true;
        }else{
          this.showAddEdit['show2'] = false;
        }

        if(this.scenarios.scen3 != null){
          this.showAddEdit['show3'] = true;
        }else{
          this.showAddEdit['show3'] = false;
        }

      });
    }

  }
