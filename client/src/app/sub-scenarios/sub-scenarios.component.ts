import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';


@Component({
  selector: 'app-sub-scenarios',
  templateUrl: './sub-scenarios.component.html',
  styleUrls: ['./sub-scenarios.component.css']
})
export class SubScenariosComponent implements OnInit {

  nprodPiecePerHours: number;
  subscenario1 = new Subscenario("1");
  subscenario2 = new Subscenario("2");
  subscenario3 = new Subscenario("3");
  produtsPiece = new ProdutsPiece();

  resourceInfo1: any;
  resourceInfo2: any;
  resourceInfo3: any;


  productPlanningID: number = null;
  procSpecInfoID: number = null;
  subScenarioID1: number = null;
  subScenarioID2: number = null;
  subScenarioID3: number = null;

 procSpecInfoObj: any = { nshiptsDay: null, hoursShift: null, workingDaysYear: null, propWCPerHours: null, fkTbAceSubProLev: null};

  sub: { sub1: any, sub2: any, sub3: any } =
    { sub1: null, sub2: null, sub3: null };


  bestLoaCog: Object = {};
  bestLoaPhy: Object = {};

  //ProcTime: {selRes1ProcTime: number, selRes2ProcTime: number, selRes3ProcTime: number}=
  //     {selRes1ProcTime: null, selRes2ProcTime:null, selRes3ProcTime: null};

  //TODO: These three variables should be inserted into a object
  selRes1ProcTime: number;
  selRes2ProcTime: number;
  selRes3ProcTime: number;


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


  hoursPerYear: number;
  productsNumYear: number;


  constructor(private http: HttpClient, private _processListService: CookieService) { }


  cookie: any;

  ngOnInit() {
    this.cookie = this._processListService.getCookie("selectedSubprocess");
    this.productPlanningGET();
    this.procSpecInfoGET();
    this.subScenarioGET();
   // this.getMainProc(1);
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
      )
  }

checkMandatoryData() {
    //console.log(this.ProcTime.selRes1ProcTime)
    if (this.nprodPiecePerHours != null && this.procSpecInfoObj.nshiptsDay != null && this.procSpecInfoObj.hoursShift != null && this.procSpecInfoObj.workingDaysYear != null && this.procSpecInfoObj.propWCPerHours != null) {
      this.disableButton = true;
    } else {
      this.disableButton = false;
    }
  }

  productInfo(): void {

    this.produtsPiece.nprodPiecePerHours = this.nprodPiecePerHours;
    this.produtsPiece.fkTbAceProSeq = this.cookie.mainProcessId;

    console.log("PRODOTTO: "+this.productPlanningID);

    if (this.productPlanningID == null) {
     this.http.post(environment.apiUrl + '/v1/product-planning', this.produtsPiece)
                        .toPromise().then((res:any) => {
                          this.productPlanningID=res.pkTbId;
                        });

    }else{
       this.http.put(environment.apiUrl + '/v1/product-planning/'+this.productPlanningID, this.produtsPiece)
                .subscribe(res =>
                  console.log('Done put'));
    }


    this.checkMandatoryData();


  }

    procSpecInfo(): void {


     this.checkMandatoryData();
     this.procSpecInfoObj.fkTbAceSubProLev=this.getFkAceSubProLevId(this.cookie);

     if(this.procSpecInfoID==null){
      this.http.post(environment.apiUrl + '/v1/process-specific-info', this.procSpecInfoObj)
               .toPromise().then((res:any) => {
                this.procSpecInfoID=res.pkTbId;
                });



      }else{
        this.http.put(environment.apiUrl + '/v1/process-specific-info/'+ this.procSpecInfoID, this.procSpecInfoObj)
                      .subscribe(res =>
                        console.log('Done put'));
      }




  }

  createSubScenarios(): void {

    this.subscenario1.processTime=this.selRes1ProcTime;
    this.subscenario2.processTime=this.selRes2ProcTime;
    this.subscenario3.processTime=this.selRes3ProcTime;

    this.updateHoursPerYear();
    this.updateNumbersNumYear();
    this.updateRateParticipationPerHour();
    this.updateLabourCost();
    this.updateEnergyCosts();
    this.updateMaintenanceCosts();
    this.updateAnnualSpaceCosts();
    this.updateImputedDepreciation();
    this.updateAccruedInterestCosts();
    this.updateVariableCostsTotal();
    this.updateFixedCostsTotal();
    this.updateAssemblyCostsPerPiece();
    this.updateAssemblyCostsTotal()




    this.checkMandatoryData();

  }

  firstDropDownChanged(val: any) {
    this.firstdropdown = this.findObj(this.resourcesList[0], val);
    this.resourceInfo1 = this.firstdropdown;

    this.subscenario1.fkTbAceRes = this.firstdropdown.pkTbId;

    // console.log(this.selRes1ProcTime);
  }

  secondDropDownChanged(val: any) {
   this.seconddropdown = this.findObj(this.resourcesList[0], val);
   this.resourceInfo2 = this.seconddropdown;

   this.subscenario2.fkTbAceRes=this.seconddropdown.pkTbId;

  }

  thirdDropDownChanged(val: any) {
    this.thirddropdown = this.findObj(this.resourcesList[0], val);
    this.resourceInfo3 = this.thirddropdown;
    this.subscenario3.fkTbAceRes=this.thirddropdown.pkTbId;

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
      )
  }

  fetchFromCAM(mainP) {
    this.http
      .get(environment.apiUrl + '/v1/var/process-segments')
      .toPromise()
      .then(
        (processSegments: Array<any>) => {
          this.lowerLevelObj = this.findObj(processSegments, mainP.name);
          this.lowerLevel = this.lowerLevelObj.subProcLowerLevel;
          //console.log(this.lowerLevel);
        }
      )

  }

  findObj(obj: any, name: any) {
    for (let k in obj) {
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

          for (let i in subProcessesList) {
            if (subProcessesList[i].fkTbAceProSeq === mainP.pkTbId) {
              this.sub.sub1 = subProcessesList[i];
            }
          }

          for (let j in subProcessesList) {
            if (subProcessesList[j].fkTbAceProSeq === this.sub.sub1.pkTbId) {
              this.sub.sub2 = subProcessesList[j];
            }
          }
          for (let k in subProcessesList) {
            if (subProcessesList[k].fkTbAceProSeq === this.sub.sub2.pkTbId) {
              this.sub.sub3 = subProcessesList[k];
            }
          }
        }
      )
  }


  updateHoursPerYear(): void {

    //TODO: add checks if these values are not null
    this.subscenario1.hoursPerYears = this.procSpecInfoObj.nshiptsDay * this.procSpecInfoObj.hoursShift * this.procSpecInfoObj.workingDaysYear;
    this.subscenario2.hoursPerYears = this.procSpecInfoObj.nshiptsDay * this.procSpecInfoObj.hoursShift * this.procSpecInfoObj.workingDaysYear;
    this.subscenario3.hoursPerYears = this.procSpecInfoObj.nshiptsDay * this.procSpecInfoObj.hoursShift * this.procSpecInfoObj.workingDaysYear;
  }

  updateNumbersNumYear(): void {
    this.subscenario1.nprodPieces = this.subscenario1.hoursPerYears * this.nprodPiecePerHours;
    this.subscenario2.nprodPieces = this.subscenario2.hoursPerYears * this.nprodPiecePerHours;
    this.subscenario3.nprodPieces = this.subscenario3.hoursPerYears * this.nprodPiecePerHours;
  }

  updateRateParticipationPerHour(): void {
    this.subscenario1.rateOfPart = this.roundValue((this.nprodPiecePerHours * this.selRes1ProcTime) / 3600);
    this.subscenario2.rateOfPart = this.roundValue((this.nprodPiecePerHours * this.selRes2ProcTime) / 3600);
    this.subscenario3.rateOfPart = this.roundValue((this.nprodPiecePerHours * this.selRes3ProcTime) / 3600);
  }

  updateLabourCost(): void {

    this.subscenario1.labourCost = this.roundValue(this.procSpecInfoObj.propWCPerHours * this.resourceInfo1.lcNOperMachine * this.subscenario1.rateOfPart / this.procSpecInfoObj.workingDaysYear);
    this.subscenario2.labourCost = this.roundValue(this.procSpecInfoObj.propWCPerHours * this.resourceInfo2.lcNOperMachine * this.subscenario2.rateOfPart / this.procSpecInfoObj.workingDaysYear);
    this.subscenario3.labourCost = this.roundValue(this.procSpecInfoObj.propWCPerHours * this.resourceInfo3.lcNOperMachine * this.subscenario3.rateOfPart / this.procSpecInfoObj.workingDaysYear);

  }

  updateEnergyCosts(): void {

    //TODO check if the calculation of energyCosts if correct: the results is always the same in all scenarios
    this.subscenario1.energyCost = this.roundValue((this.resourceInfo1.ecAEleConsumFun * this.resourceInfo1.ecElePrice * this.selRes1ProcTime) / 3600 + ((3600 - this.selRes1ProcTime * this.nprodPiecePerHours) / (this.nprodPiecePerHours * 3600)) * this.resourceInfo1.ecAEleConsumSb * this.resourceInfo1.ecElePrice);
    this.subscenario2.energyCost = this.roundValue((this.resourceInfo2.ecAEleConsumFun * this.resourceInfo2.ecElePrice * this.selRes2ProcTime) / 3600 + ((3600 - this.selRes2ProcTime * this.nprodPiecePerHours) / (this.nprodPiecePerHours * 3600)) * this.resourceInfo2.ecAEleConsumSb * this.resourceInfo2.ecElePrice);
    this.subscenario3.energyCost = this.roundValue((this.resourceInfo3.ecAEleConsumFun * this.resourceInfo3.ecElePrice * this.selRes3ProcTime) / 3600 + ((3600 - this.selRes3ProcTime * this.nprodPiecePerHours) / (this.nprodPiecePerHours * 3600)) * this.resourceInfo3.ecAEleConsumSb * this.resourceInfo3.ecElePrice);
    }

  updateMaintenanceCosts(): void {
    if (this.resourceInfo1.mcAMaintCosts == null) {
      this.subscenario1.maintCost = this.resourceInfo1.mcAMaintCostsPerc * this.resourceInfo1.idMacPurhValue;
    } else {
      this.subscenario1.maintCost = this.resourceInfo1.mcAMaintCosts;
    }

    if (this.resourceInfo2.mcAMaintCosts == null) {
      this.subscenario2.maintCost = this.resourceInfo2.mcAMaintCostsPerc * this.resourceInfo2.idMacPurhValue;
    } else {
      this.subscenario2.maintCost = this.resourceInfo2.mcAMaintCosts;
    }


    if (this.resourceInfo3.mcAMaintCosts == null) {
      this.subscenario3.maintCost = this.resourceInfo3.mcAMaintCostsPerc * this.resourceInfo3.idMacPurhValue;
    } else {
      this.subscenario3.maintCost = this.resourceInfo3.mcAMaintCosts;
    }



  }

  updateAnnualSpaceCosts(): void {

    this.subscenario1.annualSpaceCost = this.resourceInfo1.rcCostsMMonth * 12 * this.resourceInfo1.rcInstSurface;
    this.subscenario2.annualSpaceCost = this.resourceInfo2.rcCostsMMonth * 12 * this.resourceInfo2.rcInstSurface;
    this.subscenario3.annualSpaceCost = this.resourceInfo3.rcCostsMMonth * 12 * this.resourceInfo3.rcInstSurface;

  }

  updateImputedDepreciation(): void {
    var num1, num2, num3;

    num1 = (this.resourceInfo1.idMacPurhValue - this.resourceInfo1.idMacSalesValue) / this.resourceInfo1.idEcoUsefullLife;
    num2 = (this.resourceInfo2.idMacPurhValue - this.resourceInfo2.idMacSalesValue) / this.resourceInfo2.idEcoUsefullLife;
    num3 = (this.resourceInfo3.idMacPurhValue - this.resourceInfo3.idMacSalesValue) / this.resourceInfo3.idEcoUsefullLife;

    if (!isNaN(num1)) {
      this.subscenario1.inputedDepreciation = this.roundValue(num1);
    } else {
      this.subscenario1.inputedDepreciation = 0;
    }

    if (!isNaN(num2)) {
      this.subscenario2.inputedDepreciation = this.roundValue(num2);
    } else {
      this.subscenario2.inputedDepreciation = 0;
    }

    if (!isNaN(num3)) {
      this.subscenario3.inputedDepreciation = this.roundValue(num3);
    } else {
      this.subscenario3.inputedDepreciation = 0;
    }

  }

  updateAccruedInterestCosts(): void {
    this.subscenario1.accruedIntCosts = this.roundValue(((this.resourceInfo1.idMacPurhValue + this.resourceInfo1.idMacSalesValue) / 2) * this.resourceInfo1.icInterRate);
    this.subscenario2.accruedIntCosts = this.roundValue(((this.resourceInfo2.idMacPurhValue + this.resourceInfo2.idMacSalesValue) / 2) * this.resourceInfo2.icInterRate);
    this.subscenario3.accruedIntCosts = this.roundValue(((this.resourceInfo3.idMacPurhValue + this.resourceInfo3.idMacSalesValue) / 2) * this.resourceInfo3.icInterRate);
 }

  updateVariableCostsTotal(): void {
    this.subscenario1.varCostTotal = this.roundValue(this.subscenario1.labourCost * this.subscenario1.energyCost);
    this.subscenario2.varCostTotal = this.roundValue(this.subscenario2.labourCost * this.subscenario2.energyCost);
    this.subscenario3.varCostTotal = this.roundValue(this.subscenario3.labourCost * this.subscenario3.energyCost);

   }

  updateFixedCostsTotal(): void {
    this.subscenario1.fixedCostTotal = this.roundValue((this.subscenario1.maintCost + this.subscenario1.annualSpaceCost + this.subscenario1.inputedDepreciation + this.subscenario1.inputedDepreciation) / this.subscenario1.nprodPieces);
    this.subscenario2.fixedCostTotal = this.roundValue((this.subscenario2.maintCost + this.subscenario2.annualSpaceCost + this.subscenario2.inputedDepreciation + this.subscenario2.inputedDepreciation) / this.subscenario2.nprodPieces);
    this.subscenario3.fixedCostTotal = this.roundValue((this.subscenario3.maintCost + this.subscenario3.annualSpaceCost + this.subscenario3.inputedDepreciation + this.subscenario3.inputedDepreciation) / this.subscenario3.nprodPieces);
  }

  updateAssemblyCostsPerPiece(): void {
    this.subscenario1.assemblyCostPerPiece = this.roundValue(this.subscenario1.varCostTotal + this.subscenario1.fixedCostTotal);
    this.subscenario2.assemblyCostPerPiece = this.roundValue(this.subscenario2.varCostTotal + this.subscenario2.fixedCostTotal);
    this.subscenario3.assemblyCostPerPiece = this.roundValue(this.subscenario3.varCostTotal + this.subscenario3.fixedCostTotal);

  }

  updateAssemblyCostsTotal(): void {
    this.subscenario1.assemblyCosts = this.roundValue(this.subscenario1.assemblyCostPerPiece + this.subscenario1.nprodPieces);
    this.subscenario2.assemblyCosts = this.roundValue(this.subscenario2.assemblyCostPerPiece + this.subscenario2.nprodPieces);
    this.subscenario3.assemblyCosts = this.roundValue(this.subscenario3.assemblyCostPerPiece + this.subscenario3.nprodPieces);
   }


  roundValue(number): number {
    return Math.round(number * 100) / 100;
  }

  setAll = (obj, val) => Object.keys(obj).forEach(k => obj[k] = val);

  cancelScenariosValues(): void {
    this.setAll(this.procSpecInfoObj, null);
    this.nprodPiecePerHours=null;
    this.selRes1ProcTime=null;
    this.selRes2ProcTime=null;
    this.selRes3ProcTime=null;
    this.setAll(this.subscenario1, null);
    this.setAll(this.resourceInfo1, null);
    this.setAll(this.subscenario2, null);
    this.setAll(this.resourceInfo2, null);
    this.setAll(this.subscenario3, null);
    this.setAll(this.resourceInfo3, null);

  }

 getFkAceSubProLevId(cookie): number {
     if(!("undefined" === typeof(cookie['level3']))){
            return cookie.level3.id;
        }else{
         if(!("undefined" === typeof(cookie['level2']))){
         return cookie.level2.id;
         }else{
          return cookie.level1.id;
          }
        }

    }

  saveSubscenarios(): void{

  this.subscenario1.fkTbAceProSeq=this.cookie.mainProcessId;
  this.subscenario1.fkTbAceSubProLev=this.getFkAceSubProLevId(this.cookie);

  this.subscenario2.fkTbAceProSeq=this.cookie.mainProcessId;
  this.subscenario2.fkTbAceSubProLev=this.getFkAceSubProLevId(this.cookie);

  this.subscenario3.fkTbAceProSeq=this.cookie.mainProcessId;
  this.subscenario3.fkTbAceSubProLev=this.getFkAceSubProLevId(this.cookie);

  console.log(this.subscenario1);

  if(this.subScenarioID1==null){
  this.http
        .post(environment.apiUrl + '/v1/subscenarios', this.subscenario1)
        .toPromise().then((res:any) => {
            this.subScenarioID1=res.pkTbId;
          });
    }else{
    //delete this.subscenario1.createDate;
    //delete this.subscenario1.updateDate;
    //console.log(this.subscenario1);
    this.http
            .put(environment.apiUrl + '/v1/subscenarios/'+this.subScenarioID1, this.subscenario1)
            .toPromise().then((res:any) => {
               this.subScenarioID1=res.pkTbId;
              });
    }

    if(this.subScenarioID2==null){
    this.http
      .post(environment.apiUrl + '/v1/subscenarios', this.subscenario2)
      .toPromise().then((res:any) => {
           this.subScenarioID2=res.pkTbId;
        });
    }else{
     this.http
          .put(environment.apiUrl + '/v1/subscenarios/'+this.subScenarioID2, this.subscenario2)
          .toPromise().then((res:any) => {
               this.subScenarioID2=res.pkTbId;
            });
    }

    if(this.subScenarioID3==null){
    this.http
      .post(environment.apiUrl + '/v1/subscenarios', this.subscenario3)
      .toPromise().then((res:any) => {
           this.subScenarioID3=res.pkTbId;
         });
     }else{
      this.http
           .put(environment.apiUrl + '/v1/subscenarios/'+this.subScenarioID3, this.subscenario3)
           .toPromise().then((res:any) => {
              this.subScenarioID3=res.pkTbId;
          });
     }
  }



    productPlanningGET() {
          return this.http
            .get(environment.apiUrl + '/v1/product-planning-by-process-segment-id/'+this.cookie.mainProcessId)
            .toPromise()
            .then(
            (result:any) => {

                 this.nprodPiecePerHours=result.nprodPiecePerHours;
                 this.productPlanningID=result.pkTbId;
                 console.log("FOUND: "+this.productPlanningID);

             },
             err => {
               //product not found
               this.productPlanningID=null;
               console.log("NOT FOUND: "+this.productPlanningID);
             })
      }

      procSpecInfoGET(){
          this.http
                .get(environment.apiUrl + '/v1/process-specific-info-by-subprocess-id/'+this.getFkAceSubProLevId(this.cookie))
                .toPromise()
                .then(
                (result:any) => {
                     this.procSpecInfoObj=result;
                     this.procSpecInfoID=result.pkTbId;
                },
                  err => {
                  this.procSpecInfoID=null;
                })
            }



      subScenarioGET(){
              let fkTbAceResID;
              let fkTbAceSubProLevID;
              let subscenarios: Array<any> ;

              //var postObj = new PostObj();
              var postObj = {
                              subprocessLevel: {
                                         pkTbId:{}
                                  }
                            };

              postObj.subprocessLevel.pkTbId = this.getFkAceSubProLevId(this.cookie);
              console.log(postObj);
              this.http
              .post(environment.apiUrl + '/v1/subscenarios/search',  postObj )
              .toPromise()
              .then((result:any) => {
                 subscenarios = result;
                 subscenarios.forEach((element:any)=>  {
                    //console.log(element);
                    switch(element.scenarioNumber){
                    case 1:
                        this.resourceInfo1=element.resource;
                        this.firstDropDownChanged(element.resource.name);
                        this.subScenarioID1=element.pkTbId
                        this.selRes1ProcTime=element.processTime;
                        fkTbAceResID = element.resource.pkTbId;
                        fkTbAceSubProLevID = element.subprocessLevel.pkTbId;
                        delete element['resource'];
                        delete element['subprocessLevel'];
                        element.fkTbAceRes=fkTbAceResID;
                        element.fkTbAceSubProLev=fkTbAceSubProLevID;
                        this.subscenario1=element;
                        break;
                    case 2:
                       this.resourceInfo2=element.resource;
                       this.secondDropDownChanged(element.resource.name);
                       this.subScenarioID2=element.pkTbId
                       this.selRes2ProcTime=element.processTime;
                       fkTbAceResID = element.resource.pkTbId;
                       fkTbAceSubProLevID = element.subprocessLevel.pkTbId;
                       delete element['resource'];
                       delete element['subprocessLevel'];
                       element.fkTbAceRes=fkTbAceResID;
                       element.fkTbAceSubProLev=fkTbAceSubProLevID;
                       this.subscenario2=element;
                       break;
                    case 3:
                       this.resourceInfo2=element.resource;
                       this.thirdDropDownChanged(element.resource.name);
                       this.subScenarioID3=element.pkTbId
                       this.selRes3ProcTime=element.processTime;
                       fkTbAceResID = element.resource.pkTbId;
                       fkTbAceSubProLevID = element.subprocessLevel.pkTbId;
                       delete element['resource'];
                       delete element['subprocessLevel'];
                       element.fkTbAceRes=fkTbAceResID;
                       element.fkTbAceSubProLev=fkTbAceSubProLevID;
                       this.subscenario3=element;
                       break;
                  }

                 });
              },
              err => { })
      }

      resourceInfoGET(primaryKey, scenarioNumber) {
      this.http.get(environment.apiUrl + '/v1/resources/'+ primaryKey)
              .toPromise()
              .then((result:any) => {
               switch(scenarioNumber){
               case 1:
                   this.resourceInfo1=result;
                   this.firstDropDownChanged(result.name);
                   break;
               case 2:
                   this.resourceInfo2=result;
                   this.secondDropDownChanged(result.name);
                   break;
               case 3:
                   this.resourceInfo3=result;
                   this.thirdDropDownChanged(result.name);
                   break;
               }
              },
              err => {})
       }

       cancelUserSatisfaction(){
          this.subscenario1.usPhysicalLoa=null;
          this.subscenario2.usPhysicalLoa=null;
          this.subscenario3.usPhysicalLoa=null;

          this.subscenario1.usCognitiveLoa=null;
          this.subscenario2.usCognitiveLoa=null;
          this.subscenario3.usCognitiveLoa=null;
       }





}

function Subscenario(scenarioNumber)  {
  this.scenarioNumber = scenarioNumber;
}

function ProdutsPiece() { }

function PostObj(){}

