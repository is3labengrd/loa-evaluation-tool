import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { Router, ActivatedRoute} from '@angular/router';
import {CookieService} from '../cookie.service';


@Component({
  selector: 'app-edit-process',
  templateUrl: './edit-process.component.html',
  styleUrls: ['./edit-process.component.css']
})
export class EditProcessComponent implements OnInit {

  selectedModule1: any = null;
  selectedModule2: any = null;
  selectedModule3: any = null;
  processSegmentList:Array<any> = [];
  subProcessesList:Array<any> = [];
  subProcessesListToEdit:Array<any> = [];
  checkEdit1:any =[];
  checkEdit2:any =[];
  mainProcess:Array<any> = [];
  subL1:Array<any> = [];
  subL2:Array<any> = [];
  subL3:Array<any> = [];
  lvl1selection:any;
  lvl2selection:any;
  lvl3selection:any;
  private _values1 = [];
  private _values2 = [];
  private _values3 = [];
  fk:any;
  fk2:any;
  id:number;
  lowerLevel:any;
  SegmentList:Array<any> = [];
  pks:Array<any> = [];
  cookie: any;
  disable:boolean;
  processAdded: Array<any> = [];
  syncingWithVAR = false;


  constructor(private http: HttpClient, public router: Router, private route:ActivatedRoute,private _processListService: CookieService) {}

  ngOnInit() {
    this.disable = false;
    this.id  = this.route.snapshot.params['id'];
    this.cookie = this._processListService.getCookie("selectedSubprocess");
    this.syncingWithVAR = true;

    this.getCriMatrix(this.findsubprocid());

    var promise2 = this.getSegmentList()
    promise2.then((x) => {
      this.http
      .get(environment.apiUrl + '/v1/process-segments/'+this.SegmentList[0].mainProcess.pkTbId)
      .toPromise()
      .then(
        (main:any) => {
          this.fetchFromCAM(main);
          this.mainProcess.push(main);
          this.getResourceList();
        }
      )
    });
  }

  findsubprocid(): string{
    if(this.cookie.level3 != null){
      return this.cookie.level3.id.toString();
    }else{
      if(this.cookie.level2 != null){
        return this.cookie.level2.id.toString();
      }
      else{
        if(this.cookie.level1 != null){
          return this.cookie.level1.id.toString();
        }else{
          return"";
        }
      }
    }
  }

  getResourceList(){
    return this.http
    .get(environment.apiUrl + '/v1/subprocess-level-resources')
    .toPromise()
    .then(
      (res:any) => {
        if(this.SegmentList[0].subProcessLevel3 != null){
          for(let i in res){
            if(res[i].subprocessLevel.pkTbId === this.SegmentList[0].subProcessLevel3.pkTbId){
              this.disable = true;
            }
          }
        }else{
          if(this.SegmentList[0].subProcessLevel2 != null){
            for(let i in res){
              if(res[i].subprocessLevel.pkTbId === this.SegmentList[0].subProcessLevel2.pkTbId){
                this.disable = true;
              }
            }
          }else{
            if(this.SegmentList[0].subProcessLevel1 != null){
              for(let i in res){
                if(res[i].subprocessLevel.pkTbId === this.SegmentList[0].subProcessLevel1.pkTbId){
                  this.disable = true;
                }
              }
            }
          }
        }
      }
    )
  }

  getCriMatrix(id){
    this.http.get(environment.apiUrl + '/v1/criteria-matrices-by-subprocess-id/'+id)
    .toPromise()
    .then(
      (data) => {this.disable = true;},
      (err) => {});
    }


    getSegmentList() {
      return this.http
      .get(environment.apiUrl + '/v1/process-segment-list-elements/'+this.id)
      .toPromise()
      .then(
        (res:any) => {
          this.SegmentList.push(res);
        }
      )
    }

    addProSecOrdered() {

      const addProSecOrderedUrl = environment.apiUrl + '/v1/process-segment-list-elements/'+this.id;
      this.http.put(addProSecOrderedUrl, {"fkTbAceProSeq": this.SegmentList[0].mainProcess.pkTbId ,"fkTbAceSubProLev1": this.pks[0], "fkTbAceSubProLev2": this.pks[1],"fkTbAceSubProLev3": this.pks[2]})
      .toPromise()
      .then((res:any) => {
        if (res.pkTbId != null) {
        }
      },
      (err) => alert('Something went wrong. \nStatus: ' +  err.error.status)
    )
  }

  fetchFromCAM(mainP) {
    this.http
    .get(environment.apiUrl + '/v1/var/process-segments')
    .toPromise()
    .then(
      (processSegments:Array<any>) => {
        this.syncingWithVAR = false;
        this.processSegmentList.push(processSegments);
        this._values1.push(this.subProcessL1(mainP.name));
        this.startDropDownMenu(this._values1[0],mainP);
        this.lowerLevel = this.findObj(this.processSegmentList[0],this.mainProcess[0].name);

      }
    )

  }

  startDropDownMenu(objList,mainP) {

    this.http.get(environment.apiUrl + '/v1/subprocess-levels')
    .toPromise()
    .then(
      (subProcessesList:any) => {

        for (let i in subProcessesList) {
          if (subProcessesList[i].pkTbId === this.SegmentList[0].subProcessLevel1.pkTbId){
            this.fk = subProcessesList[i];
            this.subProcessesList.push(subProcessesList[i]);
            this.selectedModule1 = subProcessesList[i].name;
            this.lvl1selection = this.findObj(objList,this.selectedModule1);
          }
        }

        for (let j in subProcessesList) {
          if (subProcessesList[j].pkTbId === this.SegmentList[0].subProcessLevel2.pkTbId){
            this.fk2 = subProcessesList[j];
            this._values2 = this.subProcessL2(mainP.name,this.selectedModule1);
            this.subProcessesList.push(subProcessesList[j]);
            this.selectedModule2 = subProcessesList[j].name;
            this.lvl2selection = this.findObj(this._values2,this.selectedModule2);
          }
        }
        for (let k in subProcessesList) {
          if (subProcessesList[k].pkTbId === this.SegmentList[0].subProcessLevel3.pkTbId){
            this._values3 = this.subProcessL3(mainP.name,this.selectedModule1,this.selectedModule2);
            this.subProcessesList.push(subProcessesList[k]);
            this.selectedModule3 = subProcessesList[k].name;
            this.lvl3selection = this.findObj(this._values3,this.selectedModule2);
          }
        }
      }
    )
  }

  refresh(): void {
    this._values2 = [];
    this._values3 = [];
    this.lvl1selection = null;
    this.lvl2selection = null;
    this.lvl3selection = null;
  }

  opSuc: boolean;

  save() {
    this.checkEdit1 = [];
    this.checkEdit2 = [];
    var enablePost = false;
    var promise = this.checkAlreadyAddedProcSegm();

    this.subProcessesListToEdit = [];
    if (this.lvl1selection != null)
    this.subProcessesListToEdit.push(this.lvl1selection);
    if (this.lvl2selection != null)
    this.subProcessesListToEdit.push(this.lvl2selection);
    if (this.lvl3selection != null)
    this.subProcessesListToEdit.push(this.lvl3selection);

    if (this.subProcessesList.length === this.subProcessesListToEdit.length){
      for(let i in this.subProcessesList){
        this.checkEdit1.push(this.subProcessesList[i].name);
        this.checkEdit2.push(this.subProcessesListToEdit[i].name);
      }

      if (this.checkEdit1.toString() === this.checkEdit2.toString()){
        alert("Nothing to modify");
      }else{

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
            for(let i in this.subProcessesList){
              this.deleteSubProccess((this.subProcessesList[i].pkTbId).toString());
            }

            this.addSubProcessL1(this.SegmentList[0].mainProcess.pkTbId);
            //this.router.navigate(['process-list']);
            // alert("Process edited successfully");
            this.opSuc = true;
          }
        });
      }
    }
  }

  deleteSubProccess (pkTbId:string){
    const subProcUrl = environment.apiUrl + '/v1/subprocess-levels/'+pkTbId;
    this.http.delete(subProcUrl).subscribe();
  }

  addSubProcessL1(pkTbId) {

    const subProcUrl = environment.apiUrl + '/v1/subprocess-levels';
    this.http.post(subProcUrl, {"fkTbAceProSeq": this.cookie.mainProcessId,"name": this.lvl1selection.name,"varProSeqId": this.lvl1selection.processSegmentId, "proLevel": this.lvl1selection.level})
    .toPromise()
    .then((res:any) => {
      if (res.pkTbId != null) {
        this.pks.push(res.pkTbId);
        this.addSubProcessL2(res.pkTbId);}
      },
      (err) => {alert('Something went wrong. \nStatus: ' +  err.error.status);});

    }

    addSubProcessL2(pkTbId) {
      const subProcUrl = environment.apiUrl + '/v1/subprocess-levels';

      if(this.lvl2selection != null){
        this.http.post(subProcUrl, {"fkTbAceProSeq": this.cookie.mainProcessId,"name": this.lvl2selection.name,"varProSeqId": this.lvl2selection.processSegmentId, "proLevel": this.lvl2selection.level})
        .toPromise()
        .then((res:any) => {
          if (res.pkTbId != null) {
            this.pks.push(res.pkTbId);
            this.addSubProcessL3(res.pkTbId);}
          },

          (err) => {alert('Something went wrong. \nStatus: ' +  err.error.status);})
        } else{
          this.addProSecOrdered();
        }
      }

      addSubProcessL3(pkTbId) {
        const subProcUrl = environment.apiUrl + '/v1/subprocess-levels';
        if(this.lvl3selection != null){
          this.http.post(subProcUrl, {"fkTbAceProSeq": this.cookie.mainProcessId,"name": this.lvl3selection.name,"varProSeqId": this.lvl3selection.processSegmentId, "proLevel": this.lvl3selection.level})
          .toPromise()
          .then((res:any) => {
            this.pks.push(res.pkTbId);
            if (res.pkTbId != null) {
            }},
            (err) => {alert('Something went wrong. \nStatus: ' +  err.error.status);})}
            else{
              this.addProSecOrdered();
            }

          }

          findObj(obj: any, name: any){
            for (let k in obj) {
              if (obj[k].name === name){
                return obj[k];
              }
            }
          }


          firstDropDownChanged(val: any) {
            const obj = this._values1[0];
            if (!obj) return;

            this._values2 = [];
            this.lvl2selection=null;
            this._values3 = [];
            this.lvl3selection=null;
            this._values2 = this.subProcessL2(this.mainProcess[0].name,this.selectedModule1);
            this.lvl1selection = this.findObj(obj,this.selectedModule1);
          }


          secondDropDownChanged(val2: any) {
            this._values3 = [];
            this.lvl3selection=null;
            const obj2 = this._values2;
            if (!obj2) return;
            this._values3 = this.subProcessL3(this.mainProcess[0].name,this.selectedModule1,this.selectedModule2);
            this.lvl2selection = this.findObj(obj2,this.selectedModule2);
          }

          thirdDropDownChanged(val3: any) {

            const obj3 = this._values3;
            if (!obj3) return;
            this.lvl3selection = this.findObj(obj3,this.selectedModule3);
          }


          subProcessL1(mainProc){
            this.subL1 = [];
            for (let entry of this.processSegmentList[0]) {
              if (mainProc === entry.name){
                for (let sub of entry.subProcesses) {
                  this.subL1.push(sub);
                }
              }
            }
            return this.subL1;
          }


          subProcessL2(mainProc,subProcessL1){
            this.subL2 = [];
            for (let entry of this.processSegmentList[0]) {
              if (mainProc === entry.name){
                for (let sub of entry.subProcesses) {
                  if (subProcessL1 === sub.name){
                    for (let sub2 of sub.subProcesses) {
                      this.subL2.push(sub2);
                    }
                  }
                }
              }
            }
            return this.subL2;
          }

          subProcessL3(mainProc,subProcessL1,subProcessL2){
            this.subL3 = [];
            for (let entry of this.processSegmentList[0]) {
              if (mainProc === entry.name){
                for (let sub of entry.subProcesses) {
                  if (subProcessL1 === sub.name){
                    for (let sub2 of sub.subProcesses) {
                      if (subProcessL2 === sub2.name){
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

          away() {
            this.router.navigate(['process-list']);
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

           disableSaveSubProcess(){

                 if(this.lvl1selection!=null && this._values2.length==0 && this._values3.length==0){
                       return false;
                 }

                 if(this.lvl1selection!=null && this.lvl2selection=="undefined" && this._values3.length==0){
                      true;
                 }
                 if(this.lvl1selection!=null && this.lvl2selection!=null && this._values3.length==0){
                       return false;
                 }
                 if(this.lvl1selection!=null && this.lvl2selection!=null && this.lvl3selection!=null){
                       return false;
                 }

                 return true;
             }
        }
