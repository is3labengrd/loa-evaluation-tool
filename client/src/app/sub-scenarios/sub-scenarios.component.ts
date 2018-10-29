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


  scenario1: {
    resourceName: any, loaPhysical: number, loaCognitive: number, processTime: number,
    numberShiftsPerDay: number, hoursPerShift: number, workingDaysPerYear: number,
    hoursPerYear: number, numProducedPiecesHour: number, numProducedPiecesYear: number,
    propWageCostsPerHour: number, numberOperationsPerMachine: number, ratioOfParticipPerHour: number,
    labourCosts: number, electrConsumption: number, electrConsumptionStandby: number,
    electrPrice: number, energyCosts: number, annualMaintenanceCostsDirect: number, annualMaintenanceCostsEstimate: number,
    maintenanceCosts: number, installSurface: number, costsM3PerMonth: number,
    annualSpaceCosts: number, machinePurchase: number, machineSales: number, economicUsefulLife: number,
    imputedDeprecation: number, interestRate: number, accruedInterestCosts: number,
    variableCostsTotal: number, fixedCostsTotal: number,
    assemblyCostsPerPiece: number, assemblyCostsTotal: number
  } = {
    resourceName: null, loaPhysical: null, loaCognitive: null, processTime: null,
      numberShiftsPerDay: null, hoursPerShift: null, workingDaysPerYear: null,
      hoursPerYear: null, numProducedPiecesHour: null, numProducedPiecesYear: null,
      propWageCostsPerHour: null, numberOperationsPerMachine: null, ratioOfParticipPerHour: null,
      labourCosts: null, electrConsumption: null, electrConsumptionStandby: null,
      electrPrice: null, energyCosts: null, annualMaintenanceCostsDirect: null, annualMaintenanceCostsEstimate: null,
      maintenanceCosts: null, installSurface: null, costsM3PerMonth: null,
      annualSpaceCosts: null, machinePurchase: null, machineSales: null, economicUsefulLife: null,
      imputedDeprecation: null, interestRate: null, accruedInterestCosts: null,
      variableCostsTotal: null, fixedCostsTotal: null,
      assemblyCostsPerPiece: null, assemblyCostsTotal: null
    };


  scenario2: {
    resourceName: any, loaPhysical: number, loaCognitive: number, processTime: number,
    numberShiftsPerDay: number, hoursPerShift: number, workingDaysPerYear: number,
    hoursPerYear: number, numProducedPiecesHour: number, numProducedPiecesYear: number,
    propWageCostsPerHour: number, numberOperationsPerMachine: number, ratioOfParticipPerHour: number,
    labourCosts: number, electrConsumption: number, electrConsumptionStandby: number,
    electrPrice: number, energyCosts: number, annualMaintenanceCostsDirect: number, annualMaintenanceCostsEstimate: number,
    maintenanceCosts: number, installSurface: number, costsM3PerMonth: number,
    annualSpaceCosts: number, machinePurchase: number, machineSales: number, economicUsefulLife: number,
    imputedDeprecation: number, interestRate: number, accruedInterestCosts: number,
    variableCostsTotal: number, fixedCostsTotal: number,
    assemblyCostsPerPiece: number, assemblyCostsTotal: number
  } = {
    resourceName: null, loaPhysical: null, loaCognitive: null, processTime: null,
      numberShiftsPerDay: null, hoursPerShift: null, workingDaysPerYear: null,
      hoursPerYear: null, numProducedPiecesHour: null, numProducedPiecesYear: null,
      propWageCostsPerHour: null, numberOperationsPerMachine: null, ratioOfParticipPerHour: null,
      labourCosts: null, electrConsumption: null, electrConsumptionStandby: null,
      electrPrice: null, energyCosts: null, annualMaintenanceCostsDirect: null, annualMaintenanceCostsEstimate: null,
      maintenanceCosts: null, installSurface: null, costsM3PerMonth: null,
      annualSpaceCosts: null, machinePurchase: null, machineSales: null, economicUsefulLife: null,
      imputedDeprecation: null, interestRate: null, accruedInterestCosts: null,
      variableCostsTotal: null, fixedCostsTotal: null,
      assemblyCostsPerPiece: null, assemblyCostsTotal: null
    };

  scenario3: {
    resourceName: any, loaPhysical: number, loaCognitive: number, processTime: number,
    numberShiftsPerDay: number, hoursPerShift: number, workingDaysPerYear: number,
    hoursPerYear: number, numProducedPiecesHour: number, numProducedPiecesYear: number,
    propWageCostsPerHour: number, numberOperationsPerMachine: number, ratioOfParticipPerHour: number,
    labourCosts: number, electrConsumption: number, electrConsumptionStandby: number,
    electrPrice: number, energyCosts: number, annualMaintenanceCostsDirect: number, annualMaintenanceCostsEstimate: number,
    maintenanceCosts: number, installSurface: number, costsM3PerMonth: number,
    annualSpaceCosts: number, machinePurchase: number, machineSales: number, economicUsefulLife: number,
    imputedDeprecation: number, interestRate: number, accruedInterestCosts: number,
    variableCostsTotal: number, fixedCostsTotal: number,
    assemblyCostsPerPiece: number, assemblyCostsTotal: number
  } = {
    resourceName: null, loaPhysical: null, loaCognitive: null, processTime: null,
      numberShiftsPerDay: null, hoursPerShift: null, workingDaysPerYear: null,
      hoursPerYear: null, numProducedPiecesHour: null, numProducedPiecesYear: null,
      propWageCostsPerHour: null, numberOperationsPerMachine: null, ratioOfParticipPerHour: null,
      labourCosts: null, electrConsumption: null, electrConsumptionStandby: null,
      electrPrice: null, energyCosts: null, annualMaintenanceCostsDirect: null, annualMaintenanceCostsEstimate: null,
      maintenanceCosts: null, installSurface: null, costsM3PerMonth: null,
      annualSpaceCosts: null, machinePurchase: null, machineSales: null, economicUsefulLife: null,
      imputedDeprecation: null, interestRate: null, accruedInterestCosts: null,
      variableCostsTotal: null, fixedCostsTotal: null,
      assemblyCostsPerPiece: null, assemblyCostsTotal: null
    };

  procSpecInfoObj: { shiftsPerDay: number, hoursPerShift: number, workingDayPerY: number, propWageCostsPerH: number } =
    { shiftsPerDay: null, hoursPerShift: null, workingDayPerY: null, propWageCostsPerH: null };

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
    this.getMainProc(1);
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
    if (this.nprodPiecePerHours != null && this.procSpecInfoObj.shiftsPerDay != null && this.procSpecInfoObj.hoursPerShift != null && this.procSpecInfoObj.workingDayPerY != null && this.procSpecInfoObj.propWageCostsPerH != null) {
      this.disableButton = true;
    } else {
      this.disableButton = false;
    }
  }

  productInfo(): void {

    this.produtsPiece.nprodPiecePerHours = this.nprodPiecePerHours;
    this.produtsPiece.fkTbAceProSeq = this.mainProcess.pkTbId;
    //console.log(this.mainProcess);
    if (this.nprodPiecePerHours != null) {
      this.http.post(environment.apiUrl + '/v1/product-planning', this.produtsPiece)
        .subscribe(res =>
          console.log('Done nprodPiecePerHours'));
    }



    this.checkMandatoryData();
    this.scenario1.numProducedPiecesHour = this.nprodPiecePerHours;
    this.scenario2.numProducedPiecesHour = this.nprodPiecePerHours;
    this.scenario3.numProducedPiecesHour = this.nprodPiecePerHours;
  }

  procSpecInfo(): void {
    this.checkMandatoryData();
    this.scenario1.numberShiftsPerDay = this.procSpecInfoObj.shiftsPerDay;
    this.scenario1.hoursPerShift = this.procSpecInfoObj.hoursPerShift;
    this.scenario1.workingDaysPerYear = this.procSpecInfoObj.workingDayPerY;
    this.scenario1.propWageCostsPerHour = this.procSpecInfoObj.propWageCostsPerH;

    this.scenario2.numberShiftsPerDay = this.procSpecInfoObj.shiftsPerDay;
    this.scenario2.hoursPerShift = this.procSpecInfoObj.hoursPerShift;
    this.scenario2.workingDaysPerYear = this.procSpecInfoObj.workingDayPerY;
    this.scenario2.propWageCostsPerHour = this.procSpecInfoObj.propWageCostsPerH;

    this.scenario3.numberShiftsPerDay = this.procSpecInfoObj.shiftsPerDay;
    this.scenario3.hoursPerShift = this.procSpecInfoObj.hoursPerShift;
    this.scenario3.workingDaysPerYear = this.procSpecInfoObj.workingDayPerY;
    this.scenario3.propWageCostsPerHour = this.procSpecInfoObj.propWageCostsPerH;

  }

  createSubScenarios(): void {
    this.scenario1.processTime = this.selRes1ProcTime;
    this.scenario2.processTime = this.selRes2ProcTime;
    this.scenario3.processTime = this.selRes3ProcTime;
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
    this.scenario1.resourceName = this.firstdropdown.name;
    this.scenario1.loaPhysical = this.firstdropdown.loaPhysical;
    this.scenario1.loaCognitive = this.firstdropdown.loaCognitive;

    this.scenario1.numberOperationsPerMachine = this.firstdropdown.lcNOperMachine;
    this.scenario1.electrConsumption = this.firstdropdown.ecAEleConsumFun;
    this.scenario1.electrConsumptionStandby = this.firstdropdown.ecAEleConsumSb;
    this.scenario1.electrPrice = this.firstdropdown.ecElePrice;
    this.scenario1.annualMaintenanceCostsDirect = this.firstdropdown.mcAMaintCosts;
    this.scenario1.annualMaintenanceCostsEstimate = this.firstdropdown.mcAMaintCostsPerc;
    this.scenario1.installSurface = this.firstdropdown.rcInstSurface;
    this.scenario1.costsM3PerMonth = this.firstdropdown.rcCostsMMonth;
    this.scenario1.machinePurchase = this.firstdropdown.idMacPurhValue;
    this.scenario1.machineSales = this.firstdropdown.idMacSalesValue;
    this.scenario1.economicUsefulLife = this.firstdropdown.idEcoUsefullLife;
    this.scenario1.interestRate = this.firstdropdown.icInterRate;

    this.subscenario1.fkTbAceRes = this.firstdropdown.pkTbId;

    // console.log(this.selRes1ProcTime);
  }

  secondDropDownChanged(val: any) {
    this.seconddropdown = this.findObj(this.resourcesList[0], val);
    this.scenario2.resourceName = this.seconddropdown.name;
    this.scenario2.loaPhysical = this.seconddropdown.loaPhysical;
    this.scenario2.loaCognitive = this.seconddropdown.loaCognitive;

    this.scenario2.numberOperationsPerMachine = this.seconddropdown.lcNOperMachine;
    this.scenario2.electrConsumption = this.seconddropdown.ecAEleConsumFun;
    this.scenario2.electrConsumptionStandby = this.seconddropdown.ecAEleConsumSb;
    this.scenario2.electrPrice = this.seconddropdown.ecElePrice;
    this.scenario2.annualMaintenanceCostsDirect = this.seconddropdown.mcAMaintCosts;
    this.scenario2.annualMaintenanceCostsEstimate = this.seconddropdown.mcAMaintCostsPerc;
    this.scenario2.installSurface = this.seconddropdown.rcInstSurface;
    this.scenario2.costsM3PerMonth = this.seconddropdown.rcCostsMMonth;
    this.scenario2.machinePurchase = this.seconddropdown.idMacPurhValue;
    this.scenario2.machineSales = this.seconddropdown.idMacSalesValue;
    this.scenario2.economicUsefulLife = this.seconddropdown.idEcoUsefullLife;
    this.scenario2.interestRate = this.seconddropdown.icInterRate;

  }

  thirdDropDownChanged(val: any) {
    this.thirddropdown = this.findObj(this.resourcesList[0], val);
    this.scenario3.resourceName = this.thirddropdown.name;
    this.scenario3.loaPhysical = this.thirddropdown.loaPhysical;
    this.scenario3.loaCognitive = this.thirddropdown.loaCognitive;

    this.scenario3.numberOperationsPerMachine = this.thirddropdown.lcNOperMachine;
    this.scenario3.electrConsumption = this.thirddropdown.ecAEleConsumFun;
    this.scenario3.electrConsumptionStandby = this.thirddropdown.ecAEleConsumSb;
    this.scenario3.electrPrice = this.thirddropdown.ecElePrice;
    this.scenario3.annualMaintenanceCostsDirect = this.thirddropdown.mcAMaintCosts;
    this.scenario3.annualMaintenanceCostsEstimate = this.thirddropdown.mcAMaintCostsPerc;
    this.scenario3.installSurface = this.thirddropdown.rcInstSurface;
    this.scenario3.costsM3PerMonth = this.thirddropdown.rcCostsMMonth;
    this.scenario3.machinePurchase = this.thirddropdown.idMacPurhValue;
    this.scenario3.machineSales = this.thirddropdown.idMacSalesValue;
    this.scenario3.economicUsefulLife = this.thirddropdown.idEcoUsefullLife;
    this.scenario3.interestRate = this.thirddropdown.icInterRate;
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
    this.scenario1.hoursPerYear = this.scenario1.numberShiftsPerDay * this.scenario1.hoursPerShift * this.scenario1.workingDaysPerYear;
    this.scenario2.hoursPerYear = this.scenario2.numberShiftsPerDay * this.scenario2.hoursPerShift * this.scenario2.workingDaysPerYear;
    this.scenario3.hoursPerYear = this.scenario3.numberShiftsPerDay * this.scenario3.hoursPerShift * this.scenario3.workingDaysPerYear;

    this.subscenario1.hoursPerYears = this.scenario1.hoursPerYear;
    this.subscenario2.hoursPerYears = this.scenario2.hoursPerYear;
    this.subscenario3.hoursPerYears = this.scenario3.hoursPerYear;

  }

  updateNumbersNumYear(): void {
    this.scenario1.numProducedPiecesYear = this.scenario1.hoursPerYear * this.scenario1.numProducedPiecesHour;
    this.scenario2.numProducedPiecesYear = this.scenario2.hoursPerYear * this.scenario2.numProducedPiecesHour;
    this.scenario3.numProducedPiecesYear = this.scenario3.hoursPerYear * this.scenario3.numProducedPiecesHour;

    this.subscenario1.nprodPieces = this.scenario1.numProducedPiecesYear;
    this.subscenario2.nprodPieces = this.scenario2.numProducedPiecesYear;
    this.subscenario3.nprodPieces = this.scenario3.numProducedPiecesYear;


  }

  updateRateParticipationPerHour(): void {
    this.scenario1.ratioOfParticipPerHour = this.roundValue((this.scenario1.numProducedPiecesHour * this.scenario1.processTime) / 3600);
    this.scenario2.ratioOfParticipPerHour = this.roundValue((this.scenario2.numProducedPiecesHour * this.scenario2.processTime) / 3600);
    this.scenario3.ratioOfParticipPerHour = this.roundValue((this.scenario3.numProducedPiecesHour * this.scenario3.processTime) / 3600);

    this.subscenario1.rateOfPart = this.scenario1.ratioOfParticipPerHour;
    this.subscenario2.rateOfPart = this.scenario2.ratioOfParticipPerHour;
    this.subscenario3.rateOfPart = this.scenario3.ratioOfParticipPerHour;

  }

  updateLabourCost(): void {

    this.scenario1.labourCosts = this.roundValue(this.scenario1.propWageCostsPerHour * this.scenario1.numberOperationsPerMachine * this.scenario1.ratioOfParticipPerHour / this.scenario1.workingDaysPerYear);
    this.scenario2.labourCosts = this.roundValue(this.scenario2.propWageCostsPerHour * this.scenario2.numberOperationsPerMachine * this.scenario2.ratioOfParticipPerHour / this.scenario2.workingDaysPerYear);
    this.scenario3.labourCosts = this.roundValue(this.scenario3.propWageCostsPerHour * this.scenario3.numberOperationsPerMachine * this.scenario3.ratioOfParticipPerHour / this.scenario3.workingDaysPerYear);

    this.subscenario1.labourCost = this.scenario1.labourCosts;
    this.subscenario2.labourCost = this.scenario2.labourCosts;
    this.subscenario3.labourCost = this.scenario3.labourCosts;
  }

  updateEnergyCosts(): void {

    //TODO check if the calculation of energyCosts if correct: the results is always the same in all scenarios
    this.scenario1.energyCosts = this.roundValue((this.scenario1.electrConsumption * this.scenario1.electrPrice * this.scenario1.processTime) / 3600 + ((3600 - this.scenario1.processTime * this.scenario1.numProducedPiecesHour) / (this.scenario1.numProducedPiecesHour * 3600)) * this.scenario1.electrConsumptionStandby * this.scenario1.electrPrice);
    this.scenario2.energyCosts = this.roundValue((this.scenario2.electrConsumption * this.scenario2.electrPrice * this.scenario2.processTime) / 3600 + ((3600 - this.scenario2.processTime * this.scenario2.numProducedPiecesHour) / (this.scenario2.numProducedPiecesHour * 3600)) * this.scenario2.electrConsumptionStandby * this.scenario2.electrPrice);
    this.scenario3.energyCosts = this.roundValue((this.scenario3.electrConsumption * this.scenario3.electrPrice * this.scenario3.processTime) / 3600 + ((3600 - this.scenario3.processTime * this.scenario3.numProducedPiecesHour) / (this.scenario3.numProducedPiecesHour * 3600)) * this.scenario3.electrConsumptionStandby * this.scenario3.electrPrice);

    this.subscenario1.energyCost = this.scenario1.energyCosts;
    this.subscenario2.energyCost = this.scenario2.energyCosts;
    this.subscenario3.energyCost = this.scenario3.energyCosts;
  }

  updateMaintenanceCosts(): void {
    if (this.scenario1.annualMaintenanceCostsDirect == null) {
      this.scenario1.maintenanceCosts = this.scenario1.annualMaintenanceCostsEstimate * this.scenario1.machinePurchase;
    } else {
      this.scenario1.maintenanceCosts = this.scenario1.annualMaintenanceCostsDirect;
    }

    if (this.scenario2.annualMaintenanceCostsDirect == null) {
      this.scenario2.maintenanceCosts = this.scenario2.annualMaintenanceCostsEstimate * this.scenario2.machinePurchase;
    } else {
      this.scenario2.maintenanceCosts = this.scenario2.annualMaintenanceCostsDirect;
    }


    if (this.scenario3.annualMaintenanceCostsDirect == null) {
      this.scenario3.maintenanceCosts = this.scenario3.annualMaintenanceCostsEstimate * this.scenario3.machinePurchase;
    } else {
      this.scenario3.maintenanceCosts = this.scenario3.annualMaintenanceCostsDirect;
    }

    this.subscenario1.maintCost = this.scenario1.annualMaintenanceCostsDirect;
    this.subscenario2.maintCost = this.scenario2.annualMaintenanceCostsDirect;
    this.subscenario3.maintCost = this.scenario3.annualMaintenanceCostsDirect;
  }


  updateAnnualSpaceCosts(): void {

    this.scenario1.annualSpaceCosts = this.scenario1.costsM3PerMonth * 12 * this.scenario1.installSurface;
    this.scenario2.annualSpaceCosts = this.scenario2.costsM3PerMonth * 12 * this.scenario2.installSurface;
    this.scenario3.annualSpaceCosts = this.scenario3.costsM3PerMonth * 12 * this.scenario3.installSurface;

    this.subscenario1.annualSpaceCost = this.scenario1.annualSpaceCosts;
    this.subscenario2.annualSpaceCost = this.scenario2.annualSpaceCosts;
    this.subscenario3.annualSpaceCost = this.scenario3.annualSpaceCosts;
  }

  updateImputedDepreciation(): void {
    var num1, num2, num3;

    num1 = (this.scenario1.machinePurchase - this.scenario1.machineSales) / this.scenario1.economicUsefulLife;
    num2 = (this.scenario2.machinePurchase - this.scenario2.machineSales) / this.scenario2.economicUsefulLife;
    num3 = (this.scenario3.machinePurchase - this.scenario3.machineSales) / this.scenario3.economicUsefulLife;

    if (!isNaN(num1)) {
      this.scenario1.imputedDeprecation = this.roundValue(num1);
    } else {
      this.scenario1.imputedDeprecation = 0;
    }

    if (!isNaN(num2)) {
      this.scenario2.imputedDeprecation = this.roundValue(num2);
    } else {
      this.scenario2.imputedDeprecation = 0;
    }

    if (!isNaN(num3)) {
      this.scenario3.imputedDeprecation = this.roundValue(num3);
    } else {
      this.scenario3.imputedDeprecation = 0;
    }

    this.subscenario1.inputedDepreciation = this.scenario1.imputedDeprecation;
    this.subscenario2.inputedDepreciation = this.scenario2.imputedDeprecation;
    this.subscenario3.inputedDepreciation = this.scenario3.imputedDeprecation;
  }

  updateAccruedInterestCosts(): void {
    this.scenario1.accruedInterestCosts = this.roundValue(((this.scenario1.machinePurchase + this.scenario1.machineSales) / 2) * this.scenario1.interestRate);
    this.scenario2.accruedInterestCosts = this.roundValue(((this.scenario2.machinePurchase + this.scenario2.machineSales) / 2) * this.scenario2.interestRate);
    this.scenario3.accruedInterestCosts = this.roundValue(((this.scenario3.machinePurchase + this.scenario3.machineSales) / 2) * this.scenario3.interestRate);

    this.subscenario1.accruedIntCosts = this.scenario1.accruedInterestCosts;
    this.subscenario2.accruedIntCosts = this.scenario2.accruedInterestCosts;
    this.subscenario3.accruedIntCosts = this.scenario3.accruedInterestCosts;
  }

  updateVariableCostsTotal(): void {
    this.scenario1.variableCostsTotal = this.roundValue(this.scenario1.labourCosts * this.scenario1.energyCosts);
    this.scenario2.variableCostsTotal = this.roundValue(this.scenario2.labourCosts * this.scenario2.energyCosts);
    this.scenario3.variableCostsTotal = this.roundValue(this.scenario3.labourCosts * this.scenario3.energyCosts);

    this.subscenario1.varCostTotal = this.scenario1.variableCostsTotal;
    this.subscenario2.varCostTotal = this.scenario2.variableCostsTotal;
    this.subscenario3.varCostTotal = this.scenario3.variableCostsTotal;
  }

  updateFixedCostsTotal(): void {
    this.scenario1.fixedCostsTotal = this.roundValue((this.scenario1.maintenanceCosts + this.scenario1.annualSpaceCosts + this.scenario1.imputedDeprecation + this.scenario1.accruedInterestCosts) / this.scenario1.numProducedPiecesYear);
    this.scenario2.fixedCostsTotal = this.roundValue((this.scenario2.maintenanceCosts + this.scenario2.annualSpaceCosts + this.scenario2.imputedDeprecation + this.scenario2.accruedInterestCosts) / this.scenario2.numProducedPiecesYear);
    this.scenario3.fixedCostsTotal = this.roundValue((this.scenario3.maintenanceCosts + this.scenario3.annualSpaceCosts + this.scenario3.imputedDeprecation + this.scenario3.accruedInterestCosts) / this.scenario3.numProducedPiecesYear);

    this.subscenario1.fixedCostTotal = this.scenario1.fixedCostsTotal;
    this.subscenario2.fixedCostTotal = this.scenario2.fixedCostsTotal;
    this.subscenario3.fixedCostTotal = this.scenario3.fixedCostsTotal;
  }

  updateAssemblyCostsPerPiece(): void {
    this.scenario1.assemblyCostsPerPiece = this.scenario1.variableCostsTotal + this.scenario1.fixedCostsTotal;
    this.scenario2.assemblyCostsPerPiece = this.scenario2.variableCostsTotal + this.scenario2.fixedCostsTotal;
    this.scenario3.assemblyCostsPerPiece = this.scenario3.variableCostsTotal + this.scenario3.fixedCostsTotal;

    this.subscenario1.assemblyCostPerPiece = this.scenario1.assemblyCostsPerPiece;
    this.subscenario2.assemblyCostPerPiece = this.scenario2.assemblyCostsPerPiece;
    this.subscenario3.assemblyCostPerPiece = this.scenario3.assemblyCostsPerPiece;
  }

  updateAssemblyCostsTotal(): void {
    this.scenario1.assemblyCostsTotal = this.scenario1.assemblyCostsPerPiece + this.scenario1.numProducedPiecesYear;
    this.scenario2.assemblyCostsTotal = this.scenario2.assemblyCostsPerPiece + this.scenario2.numProducedPiecesYear;
    this.scenario3.assemblyCostsTotal = this.scenario3.assemblyCostsPerPiece + this.scenario3.numProducedPiecesYear;

    this.subscenario1.assemblyCosts = this.scenario1.assemblyCostsTotal;
    this.subscenario2.assemblyCosts = this.scenario2.assemblyCostsTotal;
    this.subscenario3.assemblyCosts = this.scenario3.assemblyCostsTotal;
  }


  roundValue(number): number {
    return Math.round(number * 100) / 100;
  }

  setAll = (obj, val) => Object.keys(obj).forEach(k => obj[k] = val);

  cancelScenariosValues(): void {
    this.setAll(this.scenario1, null);
    this.setAll(this.scenario2, null);
    this.setAll(this.scenario3, null);
  }

  saveSubscenarios(): void {

    this.http
      .post(environment.apiUrl + '/v1/subscenarios', this.subscenario1)
      .subscribe(res =>
        console.log('Done subscanario 1'));


    this.http
      .post(environment.apiUrl + '/v1/subscenarios', this.subscenario2)
      .subscribe(res =>
        console.log('Done subscanario 2'));


    this.http
      .post(environment.apiUrl + '/v1/subscenarios', this.subscenario3)
      .subscribe(res =>
        console.log('Done subscanario 3'));
  }

}
function Subscenario(scenarioNumber) {
  this.scenarioNumber = scenarioNumber;
}

function ProdutsPiece() { }
