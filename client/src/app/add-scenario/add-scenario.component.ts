import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';
import { Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-scenario',
  templateUrl: './add-scenario.component.html',
  styleUrls: ['./add-scenario.component.css']
})
export class AddScenarioComponent implements OnInit {

  constructor(private http: HttpClient,
              public router: Router,
              private route: ActivatedRoute,
              private _processListService: CookieService) { }

  resSubSceList:Array<any> = [];
  subSceList:Array<any> = [];
  cookie: any;
  objlist:any={};
  subProc:any;
  scenNumber:any;
  stepResult:Array<any> = [];
  processSegmentList: Array<any> = [];
  allProcessSegmentCAM: Array<any> = [];
  showedList:Array<any> = [];
  phyLoaTotal:any;
  cogLoaTotal:any;
  TotalCost:any;
  CostperPiece:any;
  bodyPost:any={};
  avgPhy:any;
  avgCog:any;
  totalProcessTime:any;
  id:any;

  ngOnInit() {

    this.cookie = this._processListService.getCookie("selectedSubprocess");
    this.id  = this.route.snapshot.params['id'];

    var waitCAMProcTree = this.fetchFromCAM();
    waitCAMProcTree.then((x)=>{
      this.findMissingSegments();
      this.checkduplicate();
    });

    var waitScenarioList = this.getScenariosList();
    waitScenarioList.then((x)=>{
        this.creteTable();
        this.deleteDupicate();
    });

  }

  creteTable(){
    for(let i in this.resSubSceList[0]){
      this.objlist= {};
      if(this.resSubSceList[0][i].fkTbAceProSeq === this.cookie.mainProcessId){
          for(let j in this.resSubSceList[0]){
              if(this.resSubSceList[0][i].subprocessLevel.pkTbId === this.resSubSceList[0][j].subprocessLevel.pkTbId){
                if(this.resSubSceList[0][j].scenarioNumber === 1){
                  this.objlist['scenNumber1'] = this.resSubSceList[0][j];
                }
                if(this.resSubSceList[0][j].scenarioNumber === 2){
                  this.objlist['scenNumber2'] = this.resSubSceList[0][j];
                }
                if(this.resSubSceList[0][j].scenarioNumber === 3){
                  this.objlist['scenNumber3'] = this.resSubSceList[0][j];
                }
              }
          }
            this.subProc = {"subProc": this.resSubSceList[0][i], "objList": this.objlist};
            this.subSceList.push(this.subProc);
      }
    }
  }

  deleteDupicate(){
    var result = this.subSceList.filter(function (a) {
          return !this[a.subProc.subprocessLevel.pkTbId] && (this[a.subProc.subprocessLevel.pkTbId] = true);
      }, Object.create(null));
    this.subSceList = result;
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

  calculate(){
    var valueList = [];
    var objlist= {};
    var calculatedList = [];
    var procTime = 0;
    var optCost = 0;
    var totAssemblyCostPerPiece = 0;

    for (var i=0; i<this.stepResult.length; i++){
        if (!(i in this.stepResult)){
          valueList.push("");
        }else{
          objlist= {};
          var fields = this.stepResult[i].split('-');
          objlist['optC'] = parseInt(fields[0]);
          if(this.subSceList[parseInt(fields[1])].objList.scenNumber1 != undefined && parseInt(fields[2]) === 0){
            objlist['phy'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.resource.loaPhysical;
            objlist['cog'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.resource.loaCognitive;
            objlist['costPerPiece'] = this.subSceList[parseInt(fields[1])].objList.scenNumber1.assemblyCostPerPiece;
          }
          if(this.subSceList[parseInt(fields[1])].objList.scenNumber2 != undefined && parseInt(fields[2]) === 1){
            objlist['phy'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.resource.loaPhysical;
            objlist['cog'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.resource.loaCognitive;
            objlist['costPerPiece'] = this.subSceList[parseInt(fields[1])].objList.scenNumber2.assemblyCostPerPiece;
          }
          if(this.subSceList[parseInt(fields[1])].objList.scenNumber3 != undefined && parseInt(fields[2]) === 2){
            objlist['phy'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.resource.loaPhysical;
            objlist['cog'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.resource.loaCognitive;
            objlist['costPerPiece'] = this.subSceList[parseInt(fields[1])].objList.scenNumber3.assemblyCostPerPiece;
          }
          objlist['procTime'] = this.subSceList[parseInt(fields[1])].subProc.processTime;
          valueList.push(objlist);
      }
    }

    if(valueList.length < this.subSceList.length){
      var add = this.subSceList.length-valueList.length;
      for (var j=0; j<add; j++){
        valueList.push("");
      }
    }

    var countAvg = 0;
    var phyLoa = 0;
    var cogLoa = 0;
    for (var k=0; k<valueList.length; k++){
      if(valueList[k] != ""){
        countAvg+=1;
        phyLoa += valueList[k].phy;
        cogLoa += valueList[k].cog;
        procTime += valueList[k].procTime;
        optCost += valueList[k].optC;
        totAssemblyCostPerPiece += valueList[k].costPerPiece;
      }
    }

    this.avgPhy = phyLoa/countAvg;
    this.avgCog = cogLoa/countAvg;

    for (var j=0; j<valueList.length; j++){
      objlist= {};
      if(valueList[j] != ""){
        objlist['optC'] = (valueList[j].optC).toFixed(2);
      }else{
        objlist['optC'] = "-";
      }

      if(valueList[j] != ""){
        objlist['phy'] = (valueList[j].phy * valueList[j].procTime + this.avgPhy).toFixed(2);

      }else{
        objlist['phy'] = "-";
      }

      if(valueList[j] != ""){
        objlist['cog'] = (valueList[j].cog * valueList[j].procTime + this.avgCog).toFixed(2);
      }else{
        objlist['cog'] = "-";
      }
      calculatedList.push(objlist);
    }

    this.showedList = calculatedList;

    if(optCost != 0){
      this.phyLoaTotal = (this.avgPhy/procTime).toFixed(2);
    }else{
      this.phyLoaTotal = 0;
    }

    if(optCost != 0){
      this.cogLoaTotal = (this.avgCog/procTime).toFixed(2);
    }else{
      this.cogLoaTotal = 0;
    }

    if(totAssemblyCostPerPiece != 0){
      this.CostperPiece = (optCost/totAssemblyCostPerPiece).toFixed(2);
    }else{
      this.CostperPiece = 0;
    }
    this.TotalCost = (optCost).toFixed(2);
    this.totalProcessTime = (procTime).toFixed(2);
}

  cancel(){
    this.stepResult = [];
    this.showedList = [];
    this.phyLoaTotal = 0;
    this.cogLoaTotal = 0;
    this.TotalCost = 0;
    this.CostperPiece = 0;
    this.bodyPost = {};
  }

  fetchFromCAM() {

    return this.http
      .get(environment.apiUrl + '/v1/var/process-segments')
      .toPromise()
      .then(
        (processSegments:any) => {
          this.processSegmentList.push(processSegments);
        }
      );
  }

  findMissingSegments(){
    for(let i in this.processSegmentList[0]){
      if (this.processSegmentList[0][i].name === this.cookie.mainProcessName){
        for (let j in this.processSegmentList[0][i].subProcesses){
          this.loop(this.processSegmentList[0][i].subProcesses[j].subProcesses);
        }
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

  checkduplicate(){
    var tmpList = [];
    var tmpList2 = [];

    for(let i in this.subSceList){
        tmpList.push(this.subSceList[i].subProc.subprocessLevel.name);
      }

    for(let i in this.allProcessSegmentCAM){
        if(!tmpList.includes(this.allProcessSegmentCAM[i])){
            tmpList2.push(this.allProcessSegmentCAM[i]);
        }
      }
      this.allProcessSegmentCAM = tmpList2;
    }

    findsubProc(){
      var pkSubProc;
      if(this.cookie.level3 != null){
        pkSubProc = this.cookie.level3.id;
      }else{
        if(this.cookie.level2 != null){
          pkSubProc = this.cookie.level2.id;
        }else{
          pkSubProc = this.cookie.level1.id;
        }
      }

      for (let i in this.resSubSceList[0]){

        if(this.resSubSceList[0][i].subprocessLevel.pkTbId === pkSubProc){
          this.bodyPost['fkTbAceProSeq'] = this.resSubSceList[0][i].fkTbAceProSeq;
          this.bodyPost['fkTbAceSubProLev'] = this.resSubSceList[0][i].subprocessLevel.pkTbId;
          this.bodyPost['fkTbAceRes'] = this.resSubSceList[0][i].resource.pkTbId;
          this.bodyPost['scenarioNumber'] = parseInt(this.id);
          this.bodyPost['optionCost'] = this.resSubSceList[0][i].assemblyCosts;
          this.bodyPost['totalCost'] = this.TotalCost;
          this.bodyPost['costPerPiece'] = this.CostperPiece;
          this.bodyPost['weightedPhysicalLoa'] = parseInt(this.avgPhy);
          this.bodyPost['weightedCognitiveLoa'] = parseInt(this.avgCog);
          this.bodyPost['totalProcessTime'] = parseInt(this.totalProcessTime);
          this.bodyPost['hoursYear'] = parseInt(this.resSubSceList[0][i].hoursPerYears);
          this.bodyPost['labourCost'] = this.resSubSceList[0][i].labourCost;
          this.bodyPost['maintCost'] = this.resSubSceList[0][i].maintCost;
          this.bodyPost['annualSpaceCost'] = this.resSubSceList[0][i].annualSpaceCost;
          this.bodyPost['inputedDepreciation'] = this.resSubSceList[0][i].inputedDepreciation;
          this.bodyPost['accruedInterestCost'] = this.resSubSceList[0][i].accruedIntCosts;
          this.bodyPost['energyCost'] = this.resSubSceList[0][i].energyCost;
          this.bodyPost['varCostsPerUnit'] = this.resSubSceList[0][i].varCostTotal;
          this.bodyPost['macCost'] = this.resSubSceList[0][i].resource.mcAMaintCosts;
          this.bodyPost['totWeightedPhysicalLoa'] = parseInt(this.phyLoaTotal);
          this.bodyPost['totWeightedCognitiveLoa'] = parseInt(this.cogLoaTotal);
          this.bodyPost['prodUnitsPerYears'] = parseInt(this.resSubSceList[0][i].nprodPieces);
          this.bodyPost['assCostsPerUnits'] = this.resSubSceList[0][i].assemblyCostPerPiece;
          this.bodyPost['totalAssCosts'] = this.resSubSceList[0][i].assemblyCosts;
        }
      }
    }

    // tslint:disable-next-line:member-ordering
    opSuc: boolean;
    // tslint:disable-next-line:member-ordering
    status: any;

    save(){

      this.bodyPost = {};
      this.findsubProc();
      const addProSecOrderedUrl = environment.apiUrl + '/v1/scenarios';

      return this.http.post(addProSecOrderedUrl, this.bodyPost)
             .toPromise()
             .then((res: any) => {
                    this.opSuc = true;
                    this.router.navigate(['scenarios']);
                    },
                   (err) => {
                     this.opSuc = false;
                     this.status = err.error.status;
                   });
    }
}
