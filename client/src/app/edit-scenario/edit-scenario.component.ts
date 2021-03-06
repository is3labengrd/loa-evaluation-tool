import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-scenario',
  templateUrl: './edit-scenario.component.html',
  styleUrls: ['./edit-scenario.component.css']
})
export class EditScenarioComponent implements OnInit {

  constructor(private http: HttpClient,
    public router: Router,
    private route: ActivatedRoute,
    private _processListService: CookieService) { }

  resSubSceList: Array<any> = [];
  subSceList: Array<any> = [];
  cookie: any;
  objlist: any = {};
  subProc: any;
  scenNumber: any;
  stepResult: Array<any> = [];
  processSegmentList: Array<any> = [];
  allProcessSegmentCAM: Array<any> = [];
  showedList: Array<any> = [];
  phyLoaTotal: any;
  cogLoaTotal: any;
  TotalCost: any;
  TotalAssemblyCost: any;
  CostperPiece: any;
  AssemblyCostPerPiece: any;
  bodyPost: any = {};
  avgPhy: any;
  avgCog: any;
  totalProcessTime: any;
  id: any;
  tmpPost: any;
  scenarios: any = {};
  scenResList: Array<any> = [];
  resToBeRemoved: Array<any> = [];
  enableSave: boolean;
  syncingWithVAR = false;
  allAlreadySelectedOnce = new Map<string, boolean>();
  penultimateSubprocess: any;



  ngOnInit() {
    this.enableSave = false;

    this.cookie = this._processListService.getCookie("selectedSubprocess");
    var penultimateLevel = this.cookie.maxDepth - 1;
    this.penultimateSubprocess = this.cookie['level' + penultimateLevel];
    this.id = this.route.snapshot.params['id'];
    this.syncingWithVAR = true;

    var waitGetScen = this.getScenarios();
    var waitCAMProcTree = this.fetchFromCAM();
    var waitScenarioList = this.getScenariosList();
    var waitScenarioRESList = this.getScenariosRes();

    waitGetScen.then((x) => {
      waitScenarioRESList.then((x) => {
        waitCAMProcTree.then((x) => {
          this.findMissingSegments();
          this.checkduplicate();
        });

        waitScenarioList.then((x) => {
          this.creteTable();
          this.deleteDupicate();

        });
      });
    });

  }


  creteTable() {
    for (let i in this.resSubSceList[0]) {
      this.objlist = {};
      if (this.resSubSceList[0][i].fkTbAceProSeq === this.cookie.mainProcessId) {
        for (let j in this.resSubSceList[0]) {
          if (this.resSubSceList[0][i].subprocessLevel.pkTbId === this.resSubSceList[0][j].subprocessLevel.pkTbId) {
            if (this.resSubSceList[0][j].scenarioNumber === 1 /*&&  this.resSubSceList[0][j].resSorting*/) {
              this.objlist['scenNumber1'] = this.resSubSceList[0][j];
            }
            if (this.resSubSceList[0][j].scenarioNumber === 2 /*&&  this.resSubSceList[0][j].resSorting*/) {
              this.objlist['scenNumber2'] = this.resSubSceList[0][j];
            }
            if (this.resSubSceList[0][j].scenarioNumber === 3 /*&&  this.resSubSceList[0][j].resSorting*/) {
              this.objlist['scenNumber3'] = this.resSubSceList[0][j];
            }
          }
        }
        this.subProc = { "subProc": this.resSubSceList[0][i], "objList": this.objlist };
        //console.log(this.subProc);
        this.subSceList.push(this.subProc);
      }
    }
  }

  deleteDupicate() {
    var filteredResult = [];
    var result = this.subSceList.filter(function (a) {
      return !this[a.subProc.subprocessLevel.pkTbId] && (this[a.subProc.subprocessLevel.pkTbId] = true);
    }, Object.create(null));
    result.forEach((element) => {
      var parentSubprocessId = element.subProc.subprocessLevel.pkTbId - 1;
      this.http
        .get(environment.apiUrl + '/v1/subprocess-levels/' + parentSubprocessId)
        .toPromise()
        .then((parentSubprocess: any) => {
          if (parentSubprocess.name == this.penultimateSubprocess.name) {
            element.hidden = false;
          } else {
            element.hidden = true;
          }
        })
    })
    this.subSceList = result;
    this.findSelection();
  }

  getScenariosList() {
    return this.http
      .get(environment.apiUrl + '/v1/subscenarios')
      .toPromise()
      .then(
        (res: any) => {
          this.resSubSceList.push(res);
        }
      )
  }

  calculate() {
    var valueList = [];
    var objlist = {};
    var calculatedList = [];
    var procTime = 0;
    var optCost = 0;
    var totAssemblyCostPerPiece = 0;

    for (var i = 0; i < this.stepResult.length; i++) {
      if (!(i in this.stepResult)) {
        valueList.push("");
      } else {
        objlist = {};
        var fields = this.stepResult[i].split('-');
        objlist['optC'] = parseInt(fields[0]);
        if (this.subSceList[parseInt(fields[1])].objList.scenNumber1 != undefined && parseInt(fields[2]) === 0) {
          objlist['phy'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.resource.loaPhysical;
          objlist['cog'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.resource.loaCognitive;
          objlist['costPerPiece'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.fixedCostTotal;
          objlist['fkTbAceProSeq'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.fkTbAceProSeq;
          objlist['scenarioNumber'] = parseInt(this.id);
          objlist['optionCost'] = this.subSceList[(fields[1])].objList.scenNumber1.assemblyCosts;
          objlist['hoursYear'] = parseInt(this.subSceList[parseInt(fields[1])].objList.scenNumber1.hoursPerYears);
          objlist['labourCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.labourCost;
          let keySubProcessFixedCost1: string = this.subSceList[parseInt(fields[1])].objList.scenNumber1.subprocessLevel.name + "-" + this.subSceList[parseInt(fields[1])].objList.scenNumber1.subprocessLevel.pkTbId;
          if (this.allAlreadySelectedOnce.has(keySubProcessFixedCost1) && this.allAlreadySelectedOnce.get(keySubProcessFixedCost1)) {
            objlist['fixedCost'] = true;
            objlist['maintCost'] = 0;
            objlist['annualSpaceCost'] = 0;
            objlist['inputedDepreciation'] = 0;
            objlist['accruedInterestCost'] = 0;
          } else {
            objlist['fixedCost'] = false;
            objlist['maintCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.maintCost;
            objlist['annualSpaceCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.annualSpaceCost;
            objlist['inputedDepreciation'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.inputedDepreciation;
            objlist['accruedInterestCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.accruedIntCosts;
          }
          objlist['energyCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.energyCost;
          objlist['varCostsPerUnit'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.varCostTotal;
          objlist['macCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.resource.mcAMaintCosts;
          objlist['prodUnitsPerYears'] = parseInt(this.subSceList[parseInt(fields[1])].objList.scenNumber1.nprodPieces);
          objlist['assCostsPerUnits'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.assemblyCostPerPiece;
          objlist['totalAssCosts'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.assemblyCosts;
          objlist['fkTbAceSubProLev'] = parseInt(this.subSceList[parseInt(fields[1])].objList.scenNumber1.subprocessLevel.pkTbId);
          objlist['fkTbAceRes'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.resource.pkTbId;
          objlist['numberSelected'] = 1;
          objlist['usCognitiveLoa'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.usCognitiveLoa;
          objlist['usPhysicalLoa'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.usPhysicalLoa;
          objlist['physicalTime'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.physicalTime;
          objlist['cognitiveTime'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.cognitiveTime;


        }
        if (this.subSceList[parseInt(fields[1])].objList.scenNumber2 != undefined && parseInt(fields[2]) === 1) {
          objlist['phy'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.resource.loaPhysical;
          objlist['cog'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.resource.loaCognitive;
          objlist['costPerPiece'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.fixedCostTotal;
          objlist['fkTbAceProSeq'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.fkTbAceProSeq;
          objlist['scenarioNumber'] = parseInt(this.id);
          objlist['optionCost'] = this.subSceList[(fields[1])].objList.scenNumber2.assemblyCosts;
          objlist['hoursYear'] = parseInt(this.subSceList[parseInt(fields[1])].objList.scenNumber2.hoursPerYears);
          objlist['labourCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.labourCost;
          let keySubProcessFixedCost2: string = this.subSceList[parseInt(fields[1])].objList.scenNumber2.subprocessLevel.name + "-" + this.subSceList[parseInt(fields[1])].objList.scenNumber2.subprocessLevel.pkTbId;
          if (this.allAlreadySelectedOnce.has(keySubProcessFixedCost2) && this.allAlreadySelectedOnce.get(keySubProcessFixedCost2)) {
            objlist['fixedCost'] = true;
            objlist['maintCost'] = 0;
            objlist['annualSpaceCost'] = 0;
            objlist['inputedDepreciation'] = 0;
            objlist['accruedInterestCost'] = 0;
          } else {
            objlist['fixedCost'] = false;
            objlist['maintCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.maintCost;
            objlist['annualSpaceCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.annualSpaceCost;
            objlist['inputedDepreciation'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.inputedDepreciation;
            objlist['accruedInterestCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.accruedIntCosts;
          }
          objlist['energyCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.energyCost;
          objlist['varCostsPerUnit'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.varCostTotal;
          objlist['macCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.resource.mcAMaintCosts;
          objlist['prodUnitsPerYears'] = parseInt(this.subSceList[parseInt(fields[1])].objList.scenNumber2.nprodPieces);
          objlist['assCostsPerUnits'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.assemblyCostPerPiece;
          objlist['totalAssCosts'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.assemblyCosts;
          objlist['fkTbAceSubProLev'] = parseInt(this.subSceList[parseInt(fields[1])].objList.scenNumber2.subprocessLevel.pkTbId);
          objlist['fkTbAceRes'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.resource.pkTbId;
          objlist['numberSelected'] = 2;
          objlist['usCognitiveLoa'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.usCognitiveLoa;
          objlist['usPhysicalLoa'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.usPhysicalLoa;
          objlist['physicalTime'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.physicalTime;
          objlist['cognitiveTime'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.cognitiveTime;

        }
        if (this.subSceList[parseInt(fields[1])].objList.scenNumber3 != undefined && parseInt(fields[2]) === 2) {
          objlist['phy'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.resource.loaPhysical;
          objlist['cog'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.resource.loaCognitive;
          objlist['costPerPiece'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.fixedCostTotal;
          objlist['fkTbAceProSeq'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.fkTbAceProSeq;
          objlist['scenarioNumber'] = parseInt(this.id);
          objlist['optionCost'] = this.subSceList[(fields[1])].objList.scenNumber3.assemblyCosts;
          objlist['hoursYear'] = parseInt(this.subSceList[parseInt(fields[1])].objList.scenNumber3.hoursPerYears);
          objlist['labourCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.labourCost;
          let keySubProcessFixedCost3: string = this.subSceList[parseInt(fields[1])].objList.scenNumber3.subprocessLevel.name + "-" + this.subSceList[parseInt(fields[1])].objList.scenNumber3.subprocessLevel.pkTbId;
          if (this.allAlreadySelectedOnce.has(keySubProcessFixedCost3) && this.allAlreadySelectedOnce.get(keySubProcessFixedCost3)) {
            objlist['fixedCost'] = true;
            objlist['maintCost'] = 0;
            objlist['annualSpaceCost'] = 0;
            objlist['inputedDepreciation'] = 0;
            objlist['accruedInterestCost'] = 0;
          } else {
            objlist['fixedCost'] = false;
            objlist['maintCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.maintCost;
            objlist['annualSpaceCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.annualSpaceCost;
            objlist['inputedDepreciation'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.inputedDepreciation;
            objlist['accruedInterestCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.accruedIntCosts;
          }
          objlist['energyCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.energyCost;
          objlist['varCostsPerUnit'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.varCostTotal;
          objlist['macCost'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.resource.mcAMaintCosts;
          objlist['prodUnitsPerYears'] = parseInt(this.subSceList[parseInt(fields[1])].objList.scenNumber3.nprodPieces);
          objlist['assCostsPerUnits'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.assemblyCostPerPiece;
          objlist['totalAssCosts'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.assemblyCosts;
          objlist['fkTbAceSubProLev'] = parseInt(this.subSceList[parseInt(fields[1])].objList.scenNumber3.subprocessLevel.pkTbId);
          objlist['fkTbAceRes'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.resource.pkTbId;
          objlist['numberSelected'] = 3;
          objlist['usCognitiveLoa'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.usCognitiveLoa;
          objlist['usPhysicalLoa'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.usPhysicalLoa;
          objlist['physicalTime'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.physicalTime;
          objlist['cognitiveTime'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.cognitiveTime;
        }
        objlist['procTime'] = this.subSceList[parseInt(fields[1])].subProc.processTime;
        valueList.push(objlist);
      }
    }

    if (valueList.length < this.subSceList.length) {
      var add = this.subSceList.length - valueList.length;
      for (var j = 0; j < add; j++) {
        valueList.push("");
      }
    }

    var countAvg = 0;
    var phyLoa = 0;
    var cogLoa = 0;
    var costPerPiece = 0;
    var fkTbAceProSeq = 0;
    var scenarioNumber = 0;
    var optionCost = 0;
    var hoursYear = 0;
    var labourCost = 0;
    var maintCost = 0;
    var annualSpaceCost = 0;
    var inputedDepreciation = 0;
    var accruedInterestCost = 0;
    var energyCost = 0;
    var varCostsPerUnit = 0;
    var macCost = 0;
    var prodUnitsPerYears = 0;
    var assCostsPerUnits = 0;
    var totalAssCosts = 0;
    var phy = 0;
    var cog = 0;
    var poundphyLoa = 0;
    var poundcogLoa = 0;
    var physicalTimeTotal = 0;
    var cognitiveTimeTotal = 0;

    objlist = {};

    for (var k = 0; k < valueList.length; k++) {
      if (valueList[k] != "") {
        countAvg += 1;

        phy += valueList[k].phy;
        cog += valueList[k].cog;
        costPerPiece += valueList[k].costPerPiece;
        fkTbAceProSeq = valueList[k].fkTbAceProSeq;
        scenarioNumber = parseInt(this.id);
        optionCost += valueList[k].optionCost;
        hoursYear += valueList[k].hoursYear;
        labourCost += valueList[k].labourCost;
        maintCost += valueList[k].maintCost;
        annualSpaceCost += valueList[k].annualSpaceCost;
        inputedDepreciation += valueList[k].inputedDepreciation;
        accruedInterestCost += valueList[k].accruedInterestCost;
        energyCost += valueList[k].energyCost;
        varCostsPerUnit += valueList[k].varCostsPerUnit;
        macCost += valueList[k].macCost;
        prodUnitsPerYears += valueList[k].prodUnitsPerYears;
        assCostsPerUnits += valueList[k].assCostsPerUnits;
        totalAssCosts += valueList[k].totalAssCosts;

        phyLoa += valueList[k].phy;
        cogLoa += valueList[k].cog;

        poundphyLoa += valueList[k].phy * valueList[k].procTime;
        poundcogLoa += valueList[k].cog * valueList[k].procTime;

        procTime += valueList[k].procTime;
        optCost += valueList[k].optionCost;
        totAssemblyCostPerPiece += valueList[k].costPerPiece;
      }
    }

    this.avgPhy = 0;
    this.avgCog = 0;
    for (var k = 0; k < valueList.length; k++) {
      if (valueList[k] != "") {
        this.avgPhy += (valueList[k].physicalTime / physicalTimeTotal) * valueList[k].phy;
        this.avgCog += (valueList[k].cognitiveTime / cognitiveTimeTotal) * valueList[k].cog;
      }
    }

    Math.round(this.avgPhy);
    Math.round(this.avgCog);

    objlist['phy'] = phy / countAvg;
    objlist['cog'] = cog / countAvg;
    objlist['costPerPiece'] = costPerPiece ;
    objlist['fkTbAceProSeq'] = fkTbAceProSeq;
    objlist['scenarioNumber'] = scenarioNumber;
    objlist['optionCost'] = optionCost ;
    objlist['hoursYear'] = hoursYear ;
    objlist['labourCost'] = labourCost;
    objlist['maintCost'] = maintCost;
    objlist['annualSpaceCost'] = annualSpaceCost ;
    objlist['inputedDepreciation'] = inputedDepreciation;
    objlist['accruedInterestCost'] = accruedInterestCost;
    objlist['energyCost'] = energyCost;
    objlist['varCostsPerUnit'] = varCostsPerUnit ;
    // objlist['macCost'] = macCost / countAvg;
    objlist['macCost'] = macCost;
    objlist['prodUnitsPerYears'] = prodUnitsPerYears ;
    objlist['assCostsPerUnits'] = assCostsPerUnits ;
    // objlist['totalAssCosts'] = totalAssCosts ;
    objlist['totalAssCosts'] = (prodUnitsPerYears * assCostsPerUnits).toFixed(5);

    this.tmpPost = objlist;
    //this.avgPhy = phyLoa/countAvg;
    //this.avgCog = cogLoa/countAvg;

    for (var j = 0; j < valueList.length; j++) {
      objlist = {};
      if (valueList[j] != "") {
        objlist['optC'] = (valueList[j].optC).toFixed(5);
        objlist['optionCost'] = (valueList[j].optionCost).toFixed(5);
        objlist['phy'] = valueList[j].phy;
        objlist['cog'] = valueList[j].cog;
        objlist['poundPhy'] = valueList[j].phy * valueList[j].procTime;
        objlist['poundCog'] = valueList[j].cog * valueList[j].procTime;
        objlist['fkTbAceRes'] = valueList[j].fkTbAceRes;
        objlist['fkTbAceSubProLev'] = valueList[j].fkTbAceSubProLev;
        objlist['numberSelected'] = valueList[j].numberSelected;
        objlist['fixedCost'] = valueList[j].fixedCost;
      } else {
        objlist['optC'] = "-";
        objlist['phy'] = "-";
        objlist['cog'] = "-";
        objlist['poundPhy'] = "-";
        objlist['poundCog'] = "-";
        objlist['fkTbAceRes'] = "-";
        objlist['fkTbAceSubProLev'] = "-";
        objlist['numberSelected'] = "-";
      }

      calculatedList.push(objlist);
    }

    this.showedList = calculatedList;

    if (optCost != 0) {
      this.phyLoaTotal = poundphyLoa / procTime;
    } else {
      this.phyLoaTotal = 0;
    }

    if (optCost != 0) {
      this.cogLoaTotal = poundcogLoa / procTime;
    } else {
      this.cogLoaTotal = 0;
    }

    if (totAssemblyCostPerPiece != 0) {
      this.CostperPiece = (costPerPiece).toFixed(5);
    } else {
      this.CostperPiece = 0;
    }
    this.TotalCost = (optCost).toFixed(5);
    this.totalProcessTime = (procTime).toFixed(5);


    for (let n in this.showedList) {
      if (this.showedList[n].cog != "-" && this.showedList[n].fkTbAceRes != "-" && this.showedList[n].fkTbAceSubProLev != "-" && this.showedList[n].numberSelected != "-" && this.showedList[n].optC != "-" && this.showedList[n].poundCog != "-" && this.showedList[n].poundPhy != "-") {
        this.enableSave = true;
        break;
      }
      else {
        this.enableSave = false;
      }

    }
    this.createScenarioBody();

  }

  cancel() {
    this.stepResult = [];
    this.showedList = [];
    this.phyLoaTotal = 0;
    this.cogLoaTotal = 0;
    this.TotalCost = 0;
    this.CostperPiece = 0;
    this.bodyPost = {};
    this.allAlreadySelectedOnce.clear();
  }

  fetchFromCAM() {

    return this.http
      .get(environment.apiUrl + '/v1/var/process-segments')
      .toPromise()
      .then(
        (processSegments: any) => {
          this.processSegmentList.push(processSegments);
          this.syncingWithVAR = false;
        }
      );
  }

  findMissingSegments() {
    for (let i in this.processSegmentList[0]) {
      if (this.processSegmentList[0][i].name === this.cookie.mainProcessName) {
        for (let j in this.processSegmentList[0][i].subProcesses) {
          if (
            this.processSegmentList[0][i].subProcesses[j].name == this.penultimateSubprocess.name
          ) {
            this.loop(this.processSegmentList[0][i].subProcesses[j].subProcesses);
          }
        }
      }
    }
    setTimeout(() => {
      // @ts-ignore
      tsorter.create('sortable', 0);
      // @ts-ignore
      jQuery('#sort-trigger').click();
      // @ts-ignore
      jQuery('#sort-trigger').click();
    }, 0)
  }

  loop(camList: any) {
    if (camList.length === 0) {
      return;
    }

    for (let j in camList) {
      if (camList[j].subProcesses.length === 0) {
        this.allProcessSegmentCAM.push(camList[j].name);
      }
      else {
        this.loop(camList[j].subProcesses);
      }
    }
  }

  checkduplicate() {
    var tmpList = [];
    var tmpList2 = [];

    for (let i in this.subSceList) {
      tmpList.push(this.subSceList[i].subProc.subprocessLevel.name);
    }

    for (let i in this.allProcessSegmentCAM) {
      if (!tmpList.includes(this.allProcessSegmentCAM[i])) {
        tmpList2.push(this.allProcessSegmentCAM[i]);
      }
    }
    this.allProcessSegmentCAM = tmpList2;
  }

  createScenarioBody() {

    this.bodyPost['fkTbAceProSeq'] = this.tmpPost.fkTbAceProSeq;
    this.bodyPost['scenarioNumber'] = parseInt(this.id);
    this.bodyPost['optionCost'] = this.tmpPost.optionCost;
    this.bodyPost['totalCost'] = (parseFloat(this.TotalCost)).toFixed(5);
    this.bodyPost['costPerPiece'] = (parseFloat(this.CostperPiece)).toFixed(5);
    this.bodyPost['weightedPhysicalLoa'] = parseInt(this.avgPhy);
    this.bodyPost['weightedCognitiveLoa'] = parseInt(this.avgCog);
    this.bodyPost['totalProcessTime'] = parseInt(this.totalProcessTime);
    this.bodyPost['hoursYear'] = parseInt(this.tmpPost.hoursYear);
    this.bodyPost['labourCost'] = (parseFloat(this.tmpPost.labourCost)).toFixed(5);
    this.bodyPost['maintCost'] = (parseFloat(this.tmpPost.maintCost)).toFixed(5);
    this.bodyPost['annualSpaceCost'] = (parseFloat(this.tmpPost.annualSpaceCost)).toFixed(5);
    this.bodyPost['inputedDepreciation'] = (parseFloat(this.tmpPost.inputedDepreciation)).toFixed(5);
    this.bodyPost['accruedInterestCost'] = (parseFloat(this.tmpPost.accruedInterestCost)).toFixed(5);
    this.bodyPost['energyCost'] = (parseFloat(this.tmpPost.energyCost)).toFixed(5);
    this.bodyPost['varCostsPerUnit'] = (parseFloat(this.tmpPost.varCostsPerUnit)).toFixed(5);
    this.bodyPost['macCost'] = (parseFloat(this.tmpPost.macCost)).toFixed(5);
    this.bodyPost['totWeightedPhysicalLoa'] = (parseFloat(this.phyLoaTotal)).toFixed(5);
    this.bodyPost['totWeightedCognitiveLoa'] = (parseFloat(this.cogLoaTotal)).toFixed(5);
    this.bodyPost['prodUnitsPerYears'] = parseInt(this.tmpPost.prodUnitsPerYears);
    this.bodyPost['assCostsPerUnits'] = (parseFloat(this.tmpPost.assCostsPerUnits)).toFixed(5);
    this.bodyPost['totalAssCosts'] = (parseFloat(this.tmpPost.totalAssCosts)).toFixed(5);
    this.TotalAssemblyCost = this.bodyPost['totalAssCosts'];
    this.AssemblyCostPerPiece = this.bodyPost['assCostsPerUnits'];
  }

  // tslint:disable-next-line:member-ordering
  opSuc: boolean;
  // tslint:disable-next-line:member-ordering
  status: any;

  save() {

    for (let i in this.resToBeRemoved) {
      this.deleteScenatio(this.resToBeRemoved[i].pkTbId)
    }
    this.bodyPost = {};
    this.createScenarioBody();

    const addProSecOrderedUrl = environment.apiUrl + '/v1/scenarios/' + this.scenarios.scen.pkTbId;

    return this.http.put(addProSecOrderedUrl, this.bodyPost)
      .toPromise()
      .then((res: any) => {
        var bodyPostScenRes = {};
        var tmp = 0;
        for (let i in this.showedList) {
          this.opSuc = true;
          bodyPostScenRes = {};
          bodyPostScenRes['fkTbAceSubProLev'] = this.showedList[i].fkTbAceSubProLev;
          bodyPostScenRes['fkTbAceRes'] = this.showedList[i].fkTbAceRes;
          bodyPostScenRes['fkTbAceScenarios'] = res.pkTbId;
          bodyPostScenRes['optionalCost'] = this.showedList[i].optC;
          bodyPostScenRes['weightedPhysicalLoa'] = this.showedList[i].phy;
          bodyPostScenRes['weightedCognitiveLoa'] = this.showedList[i].cog;
          bodyPostScenRes['numberSelected'] = this.showedList[i].numberSelected;
          bodyPostScenRes['fixedCost'] = this.showedList[i].fixedCost;
          tmp += 1;
          if (tmp.toString() === this.showedList.length.toString()) {
            this.saveScenRes(bodyPostScenRes, true);
          }
          else {
            this.saveScenRes(bodyPostScenRes, false);
          }
        }

      },
        (err) => {
          this.status = err.error.status;
        });
  }

  saveScenRes(bodyPost, last) {

    const addProSecOrderedUrl = environment.apiUrl + '/v1/scenario-resources';

    return this.http.post(addProSecOrderedUrl, bodyPost)
      .toPromise()
      .then((res: any) => {
        if (last) {
          this.opSuc = true;
        }
      },
        (err) => {
          this.opSuc = false;
          this.status = err.error.status;
        });
  }


  getScenarios() {
    return this.http
      .get(environment.apiUrl + '/v1/scenarios')
      .toPromise()
      .then(
        (res: any) => {
          for (let i in res) {
            if (res[i].fkTbAceProSeq === this.cookie.mainProcessId) {
              if (res[i].scenarioNumber === parseInt(this.id)) {
                this.scenarios['scen'] = res[i];
              }
            }
          }
        });
  }

  getScenariosRes() {
    return this.http
      .get(environment.apiUrl + '/v1/scenario-resources')
      .toPromise()
      .then(
        (res: any) => {
          this.scenResList.push(res);
        });
  }

  findSelection() {
    this.resToBeRemoved = [];

    for (let i in this.scenResList[0]) {

      if (this.scenResList[0][i].fkTbAceScenarios === this.scenarios.scen.pkTbId) {
        this.resToBeRemoved.push(this.scenResList[0][i]);
        if (this.scenResList[0][i].fixedCost == true) {
          this.allAlreadySelectedOnce.set(this.scenResList[0][i].subprocessLevel.name + "-" + this.scenResList[0][i].subprocessLevel.pkTbId, true);
        }
      }
    }
    //console.log(this.scenResList);
    for (let k in this.subSceList) {
      for (let j in this.resToBeRemoved) {
        if (this.resToBeRemoved[j].subprocessLevel.name === this.subSceList[k].subProc.subprocessLevel.name) {
          if (this.subSceList[k].objList.scenNumber1 != null) {
            if (this.subSceList[k].objList.scenNumber1.resource.name === this.resToBeRemoved[j].resource.name && this.resToBeRemoved[j].numberSelected === 1) {
              this.stepResult.push(this.subSceList[k].objList.scenNumber1.assemblyCosts + "-" + k + "-" + "0");
            }
          }

          if (this.subSceList[k].objList.scenNumber2 != null) {
            if (this.subSceList[k].objList.scenNumber2.resource.name === this.resToBeRemoved[j].resource.name && this.resToBeRemoved[j].numberSelected === 2) {
              this.stepResult.push(this.subSceList[k].objList.scenNumber2.assemblyCosts + "-" + k + "-" + "1");
            }
          }

          if (this.subSceList[k].objList.scenNumber3 != null) {
            if (this.subSceList[k].objList.scenNumber3.resource.name === this.resToBeRemoved[j].resource.name && this.resToBeRemoved[j].numberSelected === 3) {
              this.stepResult.push(this.subSceList[k].objList.scenNumber3.assemblyCosts + "-" + k + "-" + "2");
            }
          }
        }
      }
    }

    this.calculate();
    this.enableSave = false;
  }

  deleteScenatio(pkTbId: string) {
    const scenarioUrl = environment.apiUrl + '/v1/scenario-resources/' + pkTbId;
    this.http.delete(scenarioUrl).subscribe();
  }

  away() {
    if (this.opSuc) {
      this.router.navigate(['scenarios']);
    }
  }

  checkedFixedCost(temp: Object) {
    let objlist: any = temp;
    let key: string = objlist.subProc.subprocessLevel.name + "-" + objlist.subProc.subprocessLevel.pkTbId;
    if (this.allAlreadySelectedOnce.has(key)) {
      return this.allAlreadySelectedOnce.get(key);
    } else {
      return false;
    }
  }

  isFixedCostChecked(event: any, obj: any) {
    let key: string = obj.subProc.subprocessLevel.name + "-" + obj.subProc.subprocessLevel.pkTbId;
    if (event.target.checked) {
      this.allAlreadySelectedOnce.set(key, true);
    } else {
      this.allAlreadySelectedOnce.delete(key);
    }

  }

}
