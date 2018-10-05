import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';


@Component({
  selector: 'app-add-process',
  templateUrl: './add-process.component.html',
  styleUrls: ['./add-process.component.css']
})
export class AddProcessComponent implements OnInit {

  processSegmentList:Array<any> = [];
  countries:any[];
  main:Array<any> = [];
  subL1:Array<any> = [];
  subL2:Array<any> = [];
  subL3:Array<any> = [];
  lvl2selection:string;

  private _values1 = [];
  private _values2 = [];
  private _values3 = [];

  constructor(private http: HttpClient) {}

  async ngOnInit() {

    var processSegmentsSubscription:Subscription = this.http
      .get(environment.apiUrl + '/v1/var/process-segments')
      .subscribe(
        (processSegments:Array<any>) => {
          this.processSegmentList.push(processSegments);
          processSegmentsSubscription.unsubscribe();
          this._values1.push(this.subProcessL1('latchValveProduction'));
        }
      )


  }


  firstDropDownChanged(val: any) {
   const obj = this._values1[0];
   //console.log(val, obj);
   if (!obj) return;
   this._values2 = this.subProcessL2('latchValveProduction',obj[val]));
   this.lvl2selection = obj[val];
 }


   secondDropDownChanged(val2: any) {
    const obj2 = this._values2;
    //console.log(val, obj);
    if (!obj2) return;
    this._values3 = this.subProcessL3('latchValveProduction',this.lvl2selection,obj2[val2]);
  }

  mainProcess(){
    this.main = [];

   for (let entry of this.processSegmentList[0]) {
       //console.log(entry.name);
       //console.log(entry.processSegmentId);
       //console.log(entry.subProcLowerLevel);
       this.main.push(entry.name);
     }
     return this.main;
  }


  subProcessL1(mainProc){
    this.subL1 = [];

     for (let entry of this.processSegmentList[0]) {
       if (mainProc === entry.name){
         for (let sub of entry.subProcesses) {
           //console.log(sub.name);
           //console.log(sub.processSegmentId);
            this.subL1.push(sub.name);
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
                 //console.log(sub2.name);
                 //console.log(sub2.processSegmentId);
                 this.subL2.push(sub2.name);
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
                   //console.log(sub3.name);
                   //console.log(sub3.processSegmentId);
                   this.subL3.push(sub3.name);

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
