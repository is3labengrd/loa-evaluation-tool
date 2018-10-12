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

  productsNum:number;
  shiftsPerDay:number;
  hoursPerShift:number;
  workingDayPerY:number;
  propWageCostsPerH:number;
  disableButton:boolean;
  resources:any;
  selectedRes1:any = null;



  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.checkMandatoryData();

    this.http
      .get(environment.apiUrl + '/v1/resources')
      .toPromise()
      .then(
        (res:any) => {
          this.resources = res;
          console.log(res);
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
    console.log(val);
   //this.resources = val;
  }

}
