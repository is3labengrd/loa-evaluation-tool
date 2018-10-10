import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { Router, ActivatedRoute} from '@angular/router';


@Component({
selector: 'app-add-process',
templateUrl: './add-process.component.html',
styleUrls: ['./add-process.component.css']
})
export class AddProcessComponent implements OnInit {

processSegmentList:Array<any> = [];
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
id:number;
lowerLevel:any;

constructor(private http: HttpClient, public router: Router, private route:ActivatedRoute) {}

ngOnInit() {

  this.id  = this.route.snapshot.params['id'];

  this.http
    .get(environment.apiUrl + '/v1/process-segments/'+this.id)
    .toPromise()
    .then(
      (main:any) => {
          this.mainProcess.push(main);
          this.fetchFromCAM(main);
      }
    )
}

fetchFromCAM(mainP) {

  this.http
    .get(environment.apiUrl + '/v1/var/process-segments')
    .toPromise()
    .then(
      (processSegments:Array<any>) => {
        this.processSegmentList.push(processSegments);
        this._values1.push(this.subProcessL1(mainP.name));
        this.lowerLevel = this.findObj(this.processSegmentList[0],this.mainProcess[0].name);
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

refresh(): void {
  this._values2 = [];
  this._values3 = [];
  this.lvl1selection = null;
  this.lvl2selection = null;
  this.lvl3selection = null;
}

addProcess() {

 const mainProcUrl = environment.apiUrl + '/v1/process-segments';
 this.http.post(mainProcUrl, {"name": this.mainProcess[0].name,"varProSeqId": this.mainProcess[0].varProSeqId,"nlowerLevelSubPro": this.lowerLevel.subProcLowerLevel})
        .toPromise()
        .then((res:any) => {
          if (res.pkTbId != null) {
            alert('Process segment added');
            this.addSubProcessL1(res.pkTbId);
            }
            this.router.navigate(['process-list']);
 },
  (err) => alert('Something went wrong. \nStatus: ' +  err.error.status))

}

addSubProcessL1(pkTbId) {

const subProcUrl = environment.apiUrl + '/v1/subprocess-levels';
this.http.post(subProcUrl, {"fkTbAceProSeq": pkTbId,"name": this.lvl1selection.name,"varProSeqId": this.lvl1selection.processSegmentId, "proLevel": this.lvl1selection.level})
        .toPromise()
        .then((res:any) => {
          if (res.pkTbId != null) {
            this.addSubProcessL2(res.pkTbId);}
          },
  (err) => {alert('Something went wrong. \nStatus: ' +  err.error.status);});

}

addSubProcessL2(pkTbId) {
const subProcUrl = environment.apiUrl + '/v1/subprocess-levels';

this.http.post(subProcUrl, {"fkTbAceProSeq": pkTbId,"name": this.lvl2selection.name,"varProSeqId": this.lvl2selection.processSegmentId, "proLevel": this.lvl2selection.level})
       .toPromise()
       .then((res:any) => {
         if (res.pkTbId != null) {
           this.addSubProcessL3(res.pkTbId);}
         },

 (err) => {alert('Something went wrong. \nStatus: ' +  err.error.status);});
}

addSubProcessL3(pkTbId) {
const subProcUrl = environment.apiUrl + '/v1/subprocess-levels';

this.http.post(subProcUrl, {"fkTbAceProSeq": pkTbId,"name": this.lvl3selection.name,"varProSeqId": this.lvl3selection.processSegmentId, "proLevel": this.lvl3selection.level})
       .toPromise()
       .then((res:any) => {
         if (res.pkTbId != null) {
       }},
 (err) => {alert('Something went wrong. \nStatus: ' +  err.error.status);})

}



firstDropDownChanged(val: any) {
 const obj = this._values1[0];
 //console.log(val, obj);
 if (!obj) return;
 this._values2 = this.subProcessL2(this.mainProcess[0].name,obj[val].name);
 this.lvl1selection = obj[val];
}


 secondDropDownChanged(val2: any) {
  const obj2 = this._values2;
  //console.log(val, obj);
  if (!obj2) return;
  this._values3 = this.subProcessL3(this.mainProcess[0].name,this.lvl1selection.name,obj2[val2].name);
  this.lvl2selection = obj2[val2];
}

thirdDropDownChanged(val3: any) {
 const obj3 = this._values3;
 //console.log(val, obj);
 if (!obj3) return;
 this.lvl3selection = obj3[val3];
}
/*
mainProcess(){
  this.main = [];
 for (let entry of this.processSegmentList[0]) {
     this.main.push(entry.name);
   }
   return this.main;
}*/

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

}
