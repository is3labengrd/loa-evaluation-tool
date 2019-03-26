import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';
declare var $:any;



@Component({
  selector: 'app-sub-scenarios',
  templateUrl: './sub-scenarios.component.html',
  styleUrls: ['./sub-scenarios.component.css']
})
export class SubScenariosComponent implements OnInit {



  enableProductAndProcessInfo: boolean;

  resRecal: boolean = false;

  nprodPiecePerHours: number;
  nprodPiecePerHoursGUI1: number;
  nprodPiecePerHoursGUI2: number;
  nprodPiecePerHoursGUI3: number;

  isProductAndProcessInfoFilled = false;


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

  procSpecInfoObjGUI1: any;
  procSpecInfoObjGUI2: any;
  procSpecInfoObjGUI3: any;



  perform: boolean;

  sub: { sub1: any, sub2: any, sub3: any } =
    { sub1: null, sub2: null, sub3: null };


  bestLoaCog: Object = {};
  bestLoaPhy: Object = {};

  //ProcTime: {selRes1ProcTime: number, selRes2ProcTime: number, selRes3ProcTime: number}=
  //     {selRes1ProcTime: null, selRes2ProcTime:null, selRes3ProcTime: null};

  //TODO: These three variables should be inserted into a object
  selRes1ProcTime: number = null;
  selRes2ProcTime: number = null;
  selRes3ProcTime: number = null;

  selRes1ProcTimeGUI: number = null;
  selRes2ProcTimeGUI: number = null;
  selRes3ProcTimeGUI: number = null;


  disableButton: boolean;
  resources: Array<any> = [];
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

  isSubScenario1Present: boolean = false;
  isSubScenario2Present: boolean = false;
  isSubScenario3Present: boolean = false;

  physicalProcessLoaInfo = {};
  cognitiveProcessLoaInfo = {};

  constructor(private http: HttpClient, private _processListService: CookieService) { }



  cookie: any;

  ngOnInit() {
    this.enableProductAndProcessInfo=false;



    this.cookie = this._processListService.getCookie("selectedSubprocess");
    var subprocessId = this.cookie[
      'level' + this.cookie.maxDepth
    ].id;

    this
      .http
      .get(environment.apiUrl + '/v1/process-loa-info-by-subprocess-id/' + subprocessId)
      .toPromise()
      .then((physicalLoa) => {
        this.physicalProcessLoaInfo = physicalLoa;
      });

    this
      .http
      .get(environment.apiUrl + '/v1/cognitive-process-loa-info-by-subprocess-id/' + subprocessId)
      .toPromise()
      .then((cognitiveLoa) => {
        this.cognitiveProcessLoaInfo = cognitiveLoa;
      });

    var promise1 = this.productPlanningGET();
    promise1.then((x) => {
         var promise2 = this.procSpecInfoGET();
         promise2.then((x) => {
            var promise3 = this.getResource();
                promise3.then((x) => {
                   var promise4 = this.subScenarioGET();
                      promise4.then((x) => {
                             if(this.resRecal){
                              $("#resourceValueChange").modal('show');
                             }

                             this.saveSubscenarios();
                             this.enableProductAndProcessInfo = true;
                             this.checkMandatoryData();



                      });
                 });
         });

    });

  }

  getResource() {
     return this.http
           .get(environment.apiUrl + '/v1/subprocess-level-resources-by-subprocess-id/'+this.getFkAceSubProLevId(this.cookie))
           .toPromise()
           .then(
             (res: any) => {
               res.forEach((element:any)=>  {
                    this.resources.push(element.resource);
                    this.resourcesList.push(element.resource);
               });
              }
           )
   }



checkMandatoryData() {
    if (this.nprodPiecePerHours != null && this.procSpecInfoObj.nshiptsDay != null &&
        this.procSpecInfoObj.hoursShift != null && this.procSpecInfoObj.workingDaysYear != null
        && this.procSpecInfoObj.propWCPerHours != null) {
      this.disableButton = true;
      this.isProductAndProcessInfoFilled = true;
    } else {
      this.disableButton = false;
      this.isProductAndProcessInfoFilled = false;
    }
  }

  productInfo(): void {

    this.produtsPiece.nprodPiecePerHours = this.nprodPiecePerHours;
    this.produtsPiece.fkTbAceProSeq = this.cookie.mainProcessId;


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

    if(this.disableButton==true){
       this.createSubScenarios();
     }

     this.checkMandatoryData();

  }

    procSpecInfo(): void {

     // this.checkMandatoryData();
     this.procSpecInfoObj.fkTbAceSubProLev=this.getFkAceSubProLevId(this.cookie);

     if(this.procSpecInfoID==null){
      this.http.post(environment.apiUrl + '/v1/process-specific-info', this.procSpecInfoObj)
               .toPromise().then((res:any) => {
                this.procSpecInfoID=res.pkTbId;
                },
                err => {
                this.procSpecInfoID=null;
                });
      }else{
        this.http.put(environment.apiUrl + '/v1/process-specific-info/'+ this.procSpecInfoID, this.procSpecInfoObj)
                      .subscribe(res =>
                        console.log('Done put'));

      }

      if(this.disableButton==true){
            this.createSubScenarios();
      }

      this.checkMandatoryData();

  }

  createSubScenarios(): void {

    if(this.firstdropdown!=null){
    this.resourceInfo1 = Object.assign({}, this.firstdropdown);
    this.subscenario1.fkTbAceRes = this.firstdropdown.pkTbId;
    }

    if(this.seconddropdown!=null){
    this.resourceInfo2 = Object.assign({}, this.seconddropdown);
    this.subscenario2.fkTbAceRes=this.seconddropdown.pkTbId;
    }

    if(this.thirddropdown!=null){
    this.resourceInfo3 = Object.assign({}, this.thirddropdown);
    this.subscenario3.fkTbAceRes=this.thirddropdown.pkTbId;
    }

    if(this.subscenario1.fkTbAceRes!=null)
        this.isSubScenario1Present=true;

    if(this.subscenario2.fkTbAceRes!=null)
        this.isSubScenario2Present=true;

    if(this.subscenario3.fkTbAceRes!=null)
       this.isSubScenario3Present=true;

    if(this.isSubScenario1Present==true){
      this.selRes1ProcTime=this.selRes1ProcTimeGUI;
      this.subscenario1.processTime=this.selRes1ProcTime;
    }

    if(this.isSubScenario2Present==true){
      this.selRes2ProcTime=this.selRes2ProcTimeGUI;
      this.subscenario2.processTime=this.selRes2ProcTime;
    }

    if(this.isSubScenario3Present==true){
      this.selRes3ProcTime=this.selRes3ProcTimeGUI;
      this.subscenario3.processTime=this.selRes3ProcTime;
    }

    if(this.isSubScenario1Present==true)
      this.nprodPiecePerHoursGUI1 = this.nprodPiecePerHours;

    if(this.isSubScenario2Present==true)
      this.nprodPiecePerHoursGUI2 = this.nprodPiecePerHours;

    if(this.isSubScenario3Present==true)
      this.nprodPiecePerHoursGUI3 = this.nprodPiecePerHours;

    if(this.isSubScenario1Present==true)
    this.procSpecInfoObjGUI1 = Object.assign({}, this.procSpecInfoObj);

    if(this.isSubScenario2Present==true)
    this.procSpecInfoObjGUI2 = Object.assign({}, this.procSpecInfoObj);

    if(this.isSubScenario3Present==true)
    this.procSpecInfoObjGUI3 = Object.assign({}, this.procSpecInfoObj);

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
    this.updateAssemblyCostsTotal();



    this.checkMandatoryData();



  }

  firstDropDownChanged(val: any) {
    this.firstdropdown = Object.assign({},this.findObj(this.resourcesList, val));
   }

  secondDropDownChanged(val: any) {
   this.seconddropdown = Object.assign({}, this.findObj(this.resourcesList, val));
  }

  thirdDropDownChanged(val: any) {
    this.thirddropdown = Object.assign({},this.findObj(this.resourcesList, val));
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

    if(this.isSubScenario1Present==true)
    this.subscenario1.hoursPerYears = this.procSpecInfoObj.nshiptsDay * this.procSpecInfoObj.hoursShift * this.procSpecInfoObj.workingDaysYear;

    if(this.isSubScenario2Present==true)
    this.subscenario2.hoursPerYears = this.procSpecInfoObj.nshiptsDay * this.procSpecInfoObj.hoursShift * this.procSpecInfoObj.workingDaysYear;

    if(this.isSubScenario3Present==true)
    this.subscenario3.hoursPerYears = this.procSpecInfoObj.nshiptsDay * this.procSpecInfoObj.hoursShift * this.procSpecInfoObj.workingDaysYear;
  }

  updateNumbersNumYear(): void {

    if(this.isSubScenario1Present==true)
    this.subscenario1.nprodPieces = this.subscenario1.hoursPerYears * this.nprodPiecePerHours;

    if(this.isSubScenario2Present==true)
    this.subscenario2.nprodPieces = this.subscenario2.hoursPerYears * this.nprodPiecePerHours;

    if(this.isSubScenario3Present==true)
    this.subscenario3.nprodPieces = this.subscenario3.hoursPerYears * this.nprodPiecePerHours;
  }

  updateRateParticipationPerHour(): void {

    if(this.isSubScenario1Present==true)
    this.subscenario1.rateOfPart = this.roundValue((this.nprodPiecePerHours * this.selRes1ProcTime) / 3600);

    if(this.isSubScenario2Present==true)
    this.subscenario2.rateOfPart = this.roundValue((this.nprodPiecePerHours * this.selRes2ProcTime) / 3600);

    if(this.isSubScenario3Present==true)
    this.subscenario3.rateOfPart = this.roundValue((this.nprodPiecePerHours * this.selRes3ProcTime) / 3600);
  }

  updateLabourCost(): void {

    if(this.isSubScenario1Present==true)
    this.subscenario1.labourCost = this.roundValue(this.procSpecInfoObj.propWCPerHours * this.resourceInfo1.lcNOperMachine * this.subscenario1.rateOfPart / this.nprodPiecePerHours);

    if(this.isSubScenario2Present==true)
    this.subscenario2.labourCost = this.roundValue(this.procSpecInfoObj.propWCPerHours * this.resourceInfo2.lcNOperMachine * this.subscenario2.rateOfPart / this.nprodPiecePerHours);

    if(this.isSubScenario3Present==true)
    this.subscenario3.labourCost = this.roundValue(this.procSpecInfoObj.propWCPerHours * this.resourceInfo3.lcNOperMachine * this.subscenario3.rateOfPart / this.nprodPiecePerHours);

  }

  updateEnergyCosts(): void {

    //TODO check if the calculation of energyCosts if correct: the results is always the same in all scenarios
    if(this.isSubScenario1Present==true)
    this.subscenario1.energyCost = this.roundValue((this.resourceInfo1.ecAEleConsumFun * this.resourceInfo1.ecElePrice * this.selRes1ProcTime) / 3600 + ((3600 - this.selRes1ProcTime * this.nprodPiecePerHours) / (this.nprodPiecePerHours * 3600)) * this.resourceInfo1.ecAEleConsumSb * this.resourceInfo1.ecElePrice);

    if(this.isSubScenario2Present==true)
    this.subscenario2.energyCost = this.roundValue((this.resourceInfo2.ecAEleConsumFun * this.resourceInfo2.ecElePrice * this.selRes2ProcTime) / 3600 + ((3600 - this.selRes2ProcTime * this.nprodPiecePerHours) / (this.nprodPiecePerHours * 3600)) * this.resourceInfo2.ecAEleConsumSb * this.resourceInfo2.ecElePrice);

    if(this.isSubScenario3Present==true)
    this.subscenario3.energyCost = this.roundValue((this.resourceInfo3.ecAEleConsumFun * this.resourceInfo3.ecElePrice * this.selRes3ProcTime) / 3600 + ((3600 - this.selRes3ProcTime * this.nprodPiecePerHours) / (this.nprodPiecePerHours * 3600)) * this.resourceInfo3.ecAEleConsumSb * this.resourceInfo3.ecElePrice);
    }

  updateMaintenanceCosts(): void {

    if(this.isSubScenario1Present==true){
    if (this.resourceInfo1.mcAMaintCosts == null) {
      this.subscenario1.maintCost = this.resourceInfo1.mcAMaintCostsPerc * this.resourceInfo1.idMacPurhValue;
    } else {
      this.subscenario1.maintCost = this.resourceInfo1.mcAMaintCosts;
    }
    }

    if(this.isSubScenario2Present==true){
    if (this.resourceInfo2.mcAMaintCosts == null) {
      this.subscenario2.maintCost = this.resourceInfo2.mcAMaintCostsPerc * this.resourceInfo2.idMacPurhValue;
    } else {
      this.subscenario2.maintCost = this.resourceInfo2.mcAMaintCosts;
    }
    }

    if(this.isSubScenario3Present==true){
    if (this.resourceInfo3.mcAMaintCosts == null) {
      this.subscenario3.maintCost = this.resourceInfo3.mcAMaintCostsPerc * this.resourceInfo3.idMacPurhValue;
    } else {
      this.subscenario3.maintCost = this.resourceInfo3.mcAMaintCosts;
    }
    }


  }

  updateAnnualSpaceCosts(): void {

    if(this.isSubScenario1Present==true)
    this.subscenario1.annualSpaceCost = this.roundValue(this.resourceInfo1.rcCostsMMonth * 12 * this.resourceInfo1.rcInstSurface);

    if(this.isSubScenario2Present==true)
    this.subscenario2.annualSpaceCost = this.roundValue(this.resourceInfo2.rcCostsMMonth * 12 * this.resourceInfo2.rcInstSurface);

    if(this.isSubScenario3Present==true)
    this.subscenario3.annualSpaceCost = this.roundValue(this.resourceInfo3.rcCostsMMonth * 12 * this.resourceInfo3.rcInstSurface);

  }

  updateImputedDepreciation(): void {
    var num1, num2, num3;

    if(this.isSubScenario1Present==true)
    num1 = (this.resourceInfo1.idMacPurhValue - this.resourceInfo1.idMacSalesValue) / this.resourceInfo1.idEcoUsefullLife;

    if(this.isSubScenario2Present==true)
    num2 = (this.resourceInfo2.idMacPurhValue - this.resourceInfo2.idMacSalesValue) / this.resourceInfo2.idEcoUsefullLife;

    if(this.isSubScenario3Present==true)
    num3 = (this.resourceInfo3.idMacPurhValue - this.resourceInfo3.idMacSalesValue) / this.resourceInfo3.idEcoUsefullLife;

    if(this.isSubScenario1Present==true){
    if (!isNaN(num1)) {
      this.subscenario1.inputedDepreciation = this.roundValue(num1);
    } else {
      this.subscenario1.inputedDepreciation = 0;
    }
    }

    if(this.isSubScenario2Present==true){
    if (!isNaN(num2)) {
      this.subscenario2.inputedDepreciation = this.roundValue(num2);
    } else {
      this.subscenario2.inputedDepreciation = 0;
    }
    }

    if(this.isSubScenario3Present==true){
    if (!isNaN(num3)) {
      this.subscenario3.inputedDepreciation = this.roundValue(num3);
    } else {
      this.subscenario3.inputedDepreciation = 0;
    }
    }

  }

  updateAccruedInterestCosts(): void {

    if(this.isSubScenario1Present==true)
    this.subscenario1.accruedIntCosts = this.roundValue(((this.resourceInfo1.idMacPurhValue + this.resourceInfo1.idMacSalesValue) / 2) * (this.resourceInfo1.icInterRate/100));

    if(this.isSubScenario2Present==true)
    this.subscenario2.accruedIntCosts = this.roundValue(((this.resourceInfo2.idMacPurhValue + this.resourceInfo2.idMacSalesValue) / 2) * (this.resourceInfo2.icInterRate/100));

    if(this.isSubScenario3Present==true)
    this.subscenario3.accruedIntCosts = this.roundValue(((this.resourceInfo3.idMacPurhValue + this.resourceInfo3.idMacSalesValue) / 2) * (this.resourceInfo3.icInterRate/100));
 }

  updateVariableCostsTotal(): void {

    if(this.isSubScenario1Present==true)
    this.subscenario1.varCostTotal = this.roundValue(this.subscenario1.labourCost + this.subscenario1.energyCost);

    if(this.isSubScenario2Present==true)
    this.subscenario2.varCostTotal = this.roundValue(this.subscenario2.labourCost + this.subscenario2.energyCost);

    if(this.isSubScenario3Present==true)
    this.subscenario3.varCostTotal = this.roundValue(this.subscenario3.labourCost + this.subscenario3.energyCost);

   }

  updateFixedCostsTotal(): void {

    if(this.isSubScenario1Present==true)
    this.subscenario1.fixedCostTotal = this.roundValue((this.subscenario1.maintCost + this.subscenario1.annualSpaceCost + this.subscenario1.inputedDepreciation + this.subscenario1.accruedIntCosts) / this.subscenario1.nprodPieces);

    if(this.isSubScenario2Present==true)
    this.subscenario2.fixedCostTotal = this.roundValue((this.subscenario2.maintCost + this.subscenario2.annualSpaceCost + this.subscenario2.inputedDepreciation + this.subscenario2.accruedIntCosts) / this.subscenario2.nprodPieces);

    if(this.isSubScenario3Present==true)
    this.subscenario3.fixedCostTotal = this.roundValue((this.subscenario3.maintCost + this.subscenario3.annualSpaceCost + this.subscenario3.inputedDepreciation + this.subscenario3.accruedIntCosts) / this.subscenario3.nprodPieces);
  }

  updateAssemblyCostsPerPiece(): void {

    if(this.isSubScenario1Present==true)
    this.subscenario1.assemblyCostPerPiece = this.roundValue(this.subscenario1.varCostTotal + this.subscenario1.fixedCostTotal);

    if(this.isSubScenario2Present==true)
    this.subscenario2.assemblyCostPerPiece = this.roundValue(this.subscenario2.varCostTotal + this.subscenario2.fixedCostTotal);

    if(this.isSubScenario3Present==true)
    this.subscenario3.assemblyCostPerPiece = this.roundValue(this.subscenario3.varCostTotal + this.subscenario3.fixedCostTotal);

  }

  updateAssemblyCostsTotal(): void {

    if(this.isSubScenario1Present==true)
    this.subscenario1.assemblyCosts = this.roundValue(this.subscenario1.assemblyCostPerPiece + this.subscenario1.nprodPieces);

    if(this.isSubScenario2Present==true)
    this.subscenario2.assemblyCosts = this.roundValue(this.subscenario2.assemblyCostPerPiece + this.subscenario2.nprodPieces);

    if(this.isSubScenario3Present==true)
    this.subscenario3.assemblyCosts = this.roundValue(this.subscenario3.assemblyCostPerPiece + this.subscenario3.nprodPieces);
   }


  roundValue(number): number {
    return Math.round(number * 100) / 100;
  }

  setAll = (obj, val) => Object.keys(obj).forEach(k => obj[k] = val);

  cancelScenariosValues(): void {
    this.nprodPiecePerHoursGUI1=null;
    this.nprodPiecePerHoursGUI2=null;
    this.nprodPiecePerHoursGUI3=null;
    this.procSpecInfoObjGUI1=null;
    this.procSpecInfoObjGUI2=null;
    this.procSpecInfoObjGUI3=null;
    this.selRes1ProcTime=null;
    this.selRes2ProcTime=null;
    this.selRes3ProcTime=null;
    this.selectedRes1=null;
    this.selectedRes2=null;
    this.selectedRes3=null;

    if(this.subscenario1!=null)
    this.setAll(this.subscenario1, null);
    if(this.subscenario2!=null)
    this.setAll(this.subscenario2, null);
    if(this.subscenario3!=null)
    this.setAll(this.subscenario3, null);

    if(this.resourceInfo1!=null)
    this.setAll(this.resourceInfo1, null);
    if(this.resourceInfo2!=null)
    this.setAll(this.resourceInfo2, null);
    if(this.resourceInfo3!=null)
    this.setAll(this.resourceInfo3, null);

    if(this.firstdropdown!=null)
    this.setAll(this.firstdropdown, null);
    if(this.seconddropdown!=null)
    this.setAll(this.seconddropdown, null);
    if(this.thirddropdown!=null)
    this.setAll(this.thirddropdown, null);


    this.disableButton=false;
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

  getNameSubProLev(cookie): number {
       if(!("undefined" === typeof(cookie['level3']))){
              return cookie.level3.name;
          }else{
           if(!("undefined" === typeof(cookie['level2']))){
           return cookie.level2.name;
           }else{
            return cookie.level1.name;
            }
          }

      }

  saveSubscenarios(): void{

  if(this.isSubScenario1Present==true){
  this.subscenario1.fkTbAceProSeq=this.cookie.mainProcessId;
  this.subscenario1.fkTbAceSubProLev=this.getFkAceSubProLevId(this.cookie);
  }

  if(this.isSubScenario2Present==true){
  this.subscenario2.fkTbAceProSeq=this.cookie.mainProcessId;
  this.subscenario2.fkTbAceSubProLev=this.getFkAceSubProLevId(this.cookie);
  }

  if(this.isSubScenario3Present==true){
  this.subscenario3.fkTbAceProSeq=this.cookie.mainProcessId;
  this.subscenario3.fkTbAceSubProLev=this.getFkAceSubProLevId(this.cookie);
  }


  if(this.isSubScenario1Present==true){
  if(this.subScenarioID1==null){
  this.http
        .post(environment.apiUrl + '/v1/subscenarios', this.subscenario1)
        .toPromise().then((res:any) => {
            this.subScenarioID1=res.pkTbId;
            this.perform=true;
          });
    }else{
    this.http
            .put(environment.apiUrl + '/v1/subscenarios/'+this.subScenarioID1, this.subscenario1)
            .toPromise().then((res:any) => {
               this.subScenarioID1=res.pkTbId;
               this.perform=true;
              });
    }
    }

    if(this.isSubScenario2Present==true){
    if(this.subScenarioID2==null){
    this.http
      .post(environment.apiUrl + '/v1/subscenarios', this.subscenario2)
      .toPromise().then((res:any) => {
           this.subScenarioID2=res.pkTbId;
           this.perform=true;
        });
    }else{
     this.http
          .put(environment.apiUrl + '/v1/subscenarios/'+this.subScenarioID2, this.subscenario2)
          .toPromise().then((res:any) => {
               this.subScenarioID2=res.pkTbId;
               this.perform=true;
            });
    }
    }

    if(this.isSubScenario3Present==true){
    if(this.subScenarioID3==null){
    this.http
      .post(environment.apiUrl + '/v1/subscenarios', this.subscenario3)
      .toPromise().then((res:any) => {
           this.subScenarioID3=res.pkTbId;
           this.perform=true;
         });
     }else{
      this.http
           .put(environment.apiUrl + '/v1/subscenarios/'+this.subScenarioID3, this.subscenario3)
           .toPromise().then((res:any) => {
              this.subScenarioID3=res.pkTbId;
              this.perform=true;
          });
     }
     }

    this.saveVARProductPlanning();
    this.saveVARProcessSpecificInformation();
    this.saveVARProportionalWageCost();
    }


    productPlanningGET() {
          return this.http
            .get(environment.apiUrl + '/v1/product-planning-by-process-segment-id/'+this.cookie.mainProcessId)
            .toPromise()
            .then(
            (result:any) => {
                 this.nprodPiecePerHours=result.nprodPiecePerHours;
                 this.productPlanningID=result.pkTbId;
                 this.checkMandatoryData();

             },
             err => {
                this.productPlanningID=null;

             })
      }

      procSpecInfoGET(){
         return this.http
                .get(environment.apiUrl + '/v1/process-specific-info-by-subprocess-id/'+this.getFkAceSubProLevId(this.cookie))
                .toPromise()
                .then(
                (result:any) => {
                     this.procSpecInfoObj=result;
                     this.procSpecInfoID=result.pkTbId;
                     this.checkMandatoryData();
                }, (err) => {
                 this.procSpecInfoID=null;
                });
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
              return this.http
              .post(environment.apiUrl + '/v1/subscenarios/search',  postObj )
              .toPromise()
              .then((result:any) => {
                 subscenarios = result;
                 subscenarios.forEach((element:any)=>  {
                    switch(element.scenarioNumber){
                    case 1:
                        this.nprodPiecePerHoursGUI1 = this.nprodPiecePerHours;
                        this.procSpecInfoObjGUI1 = Object.assign({}, this.procSpecInfoObj);
                        this.resourceInfo1=element.resource;
                        this.selectedRes1=this.resourceInfo1.name;
                        this.firstDropDownChanged(element.resource.name);
                        this.subScenarioID1=element.pkTbId;
                        this.selRes1ProcTimeGUI=element.processTime;
                        fkTbAceResID = element.resource.pkTbId;
                        fkTbAceSubProLevID = element.subprocessLevel.pkTbId;
                        delete element['resource'];
                        delete element['subprocessLevel'];
                        element.fkTbAceRes=fkTbAceResID;
                        element.fkTbAceSubProLev=fkTbAceSubProLevID;
                        this.subscenario1=element;
                        this.isSubScenario1Present=true;

                        if(element.resRecal!=null && element.resRecal==true){
                          this.resRecal=true;
                          this.subscenario1.resRecal=false;
                        }

                        break;
                    case 2:
                       this.nprodPiecePerHoursGUI2 = this.nprodPiecePerHours;
                       this.procSpecInfoObjGUI2 = Object.assign({}, this.procSpecInfoObj);
                       this.resourceInfo2=element.resource;
                       this.selectedRes2=this.resourceInfo2.name;
                       this.secondDropDownChanged(element.resource.name);
                       this.subScenarioID2=element.pkTbId
                       this.selRes2ProcTimeGUI=element.processTime;
                       fkTbAceResID = element.resource.pkTbId;
                       fkTbAceSubProLevID = element.subprocessLevel.pkTbId;
                       delete element['resource'];
                       delete element['subprocessLevel'];
                       element.fkTbAceRes=fkTbAceResID;
                       element.fkTbAceSubProLev=fkTbAceSubProLevID;
                       this.subscenario2=element;
                       this.isSubScenario2Present=true;

                        if(element.resRecal!=null && element.resRecal==true){
                           this.resRecal=true;
                           this.subscenario2.resRecal=false;
                        }
                       break;
                    case 3:
                       this.nprodPiecePerHoursGUI3 = this.nprodPiecePerHours;
                       this.procSpecInfoObjGUI3 = Object.assign({}, this.procSpecInfoObj);
                       this.resourceInfo3=element.resource;
                       this.selectedRes3=this.resourceInfo3.name;
                       this.thirdDropDownChanged(element.resource.name);
                       this.subScenarioID3=element.pkTbId
                       this.selRes3ProcTimeGUI=element.processTime;
                       fkTbAceResID = element.resource.pkTbId;
                       fkTbAceSubProLevID = element.subprocessLevel.pkTbId;
                       delete element['resource'];
                       delete element['subprocessLevel'];
                       element.fkTbAceRes=fkTbAceResID;
                       element.fkTbAceSubProLev=fkTbAceSubProLevID;
                       this.subscenario3=element;
                       this.isSubScenario3Present=true;

                       if(element.resRecal!=null && element.resRecal==true){
                         this.resRecal=true;
                         this.subscenario3.resRecal=false;
                       }
                       break;
                  }


                 });
               this.createSubScenarios();
               this.disableButton=true;
              },
              err => { })
      }

      //THIS METHOD IS NOT USED
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
          if(this.subscenario1!=null){
            this.subscenario1.usPhysicalLoa=null;
            this.subscenario1.usCognitiveLoa=null;
          }

          if(this.subscenario2!=null){
            this.subscenario2.usPhysicalLoa=null;
            this.subscenario2.usCognitiveLoa=null;
          }

          if(this.subscenario3!=null){
            this.subscenario3.usPhysicalLoa=null;
            this.subscenario3.usCognitiveLoa=null;
          }


       }

       procSpecInfoIsIncomplete = () => {
           if (this.procSpecInfoObj.nshiptsDay == null && this.procSpecInfoObj.hoursShift == null ||
                  this.procSpecInfoObj.workingDaysYear == null || this.procSpecInfoObj.propWCPerHours == null){
                  return true;
           }else{
              return false;
           }
         }

       productInfoIsIncomplete = () => {
          if(this.nprodPiecePerHours==null){
              return true;
          }else{
              return false;
          }
        }

       createSubScenariosIsIncomplete = () => {

          var retDisabled = true;

          if(this.firstdropdown == null && this.seconddropdown==null && this.thirddropdown==null){
                return retDisabled;
          }

         if((this.firstdropdown!=null && this.selRes1ProcTimeGUI==null) || this.seconddropdown!=null && this.selRes2ProcTimeGUI==null
           || this.thirddropdown!=null && this.selRes3ProcTimeGUI==null ){
             retDisabled=false;
           }

          return !retDisabled;
        }

        cancelDropDown = () => {
            if(this.disableButton==true){
            this.selectedRes1=null;
            this.selectedRes2=null;
            this.selectedRes3=null;

            if(this.firstdropdown!=null)
            this.setAll(this.firstdropdown, null);
            if(this.seconddropdown!=null)
            this.setAll(this.seconddropdown, null);
            if(this.thirddropdown!=null)
            this.setAll(this.thirddropdown, null);

            if(this.resourceInfo1!=null){
            this.setAll(this.resourceInfo1, null);
            this.selRes1ProcTime=null;
            }
            if(this.resourceInfo2!=null){
            this.selRes2ProcTime=null;
            this.setAll(this.resourceInfo2, null);
            }
            if(this.resourceInfo3!=null){
            this.setAll(this.resourceInfo3, null);
            this.selRes3ProcTime=null;
            }
            }
         }

         saveVARProductPlanning = () => {

         var varProductPlanningObj = {
                          "assetName": this.cookie.mainProcessName+"-"+this.cookie.mainProcessId,
                          "productionVolume": this.produtsPiece.nprodPiecePerHours
         };

         return this.http.put(environment.apiUrl + '/v1/var/editProductPlanning', varProductPlanningObj)
                                  .toPromise().then((res:any) => { });

         }

         saveVARProcessSpecificInformation = () => {

         var varProcessSpecificInformationObj = {
                                      "assetName": this.getNameSubProLev(this.cookie),
                                      "NumberOfShiftsPerDay": this.procSpecInfoObj.nshiptsDay,
                                      "HoursPerShift": this.procSpecInfoObj.hoursShift,
                                      "WorkingDaysPerYear": this.procSpecInfoObj.workingDaysYear
                                   }

         return this.http.put(environment.apiUrl + '/v1/var/editProcessSpecificInformation', varProcessSpecificInformationObj)
                      .toPromise().then((res:any) => { });
         }


         saveVARProportionalWageCost = () => {

                  //Todo Check the values
                  var varProportionalWageCostObj = {
                                                 "assetName": this.getNameSubProLev(this.cookie)+"-"+this.getFkAceSubProLevId(this.cookie),
                                                 "valueString": this.procSpecInfoObj.propWCPerHours,
                                                 "unitOfMeasure": "€/h",
                                                 "propertyID": ""
                                           }

                  return this.http.put(environment.apiUrl + '/v1/var/editProportionalWageCost', varProportionalWageCostObj)
                               .toPromise().then((res:any) => { });
                  }



  }

function Subscenario(scenarioNumber)  {
  this.scenarioNumber = scenarioNumber;
}

function ProdutsPiece() { }

function PostObj(){}

