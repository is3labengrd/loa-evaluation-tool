import { environment } from './../../environments/environment';
import { Router, ActivatedRoute } from '@angular/router';
import { CookieService } from './../cookie.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-resource',
  templateUrl: './edit-resource.component.html',
  styleUrls: ['./edit-resource.component.css']
})
export class EditResourceComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    public router: Router,
    private route: ActivatedRoute
  ) { }

  resourceId = this.route.snapshot.params['resourceId'];
  syncingWithVAR = false;

  selectedSubprocess = this
  .cookieService
  .getCookie('selectedSubprocess');

  resource = {
    'name': null,
    'loaPhysical': null,
    'loaCognitive': null,
    'lcNOperMachine': null,
    'mcAMaintCosts': null,
    'mcAMaintCostsPerc': null,
    'rcInstSurface': null,
    'rcCostsMMonth': null,
    'idMacPurhValue': null,
    'idMacSalesValue': null,
    'idEcoUsefullLife': null,
    'icInterRate': null,
    'ecAEleConsumFun': null,
    'ecAEleConsumSb': null,
    'ecElePrice': null,
    'varClass':null
  };

  resourceInitialState;

  ngOnInit() {

    this.syncingWithVAR = true;

    this.http
    .get(`${environment.apiUrl}/v1/resources/${this.resourceId}`)
    .toPromise()
    .then(
      (resource) => {
        this.resourceInitialState = resource;
        Object.assign(this.resource, this.resourceInitialState);
      }
    );
  }

  cancel = () => {
    Object.assign(this.resource, this.resourceInitialState);
  }

  resourceIsIncomplete = () => {
    return Object.entries(this.resource).some(
      (keyValuePair) => {
        if (keyValuePair[0] == "varClass") return false;
        return keyValuePair[1] == null;
      }
    );
  }

  // tslint:disable-next-line:member-ordering
  performed: boolean;

  away() {
    this.router.navigate(['resource-list']);
  }

  save = () => {
    this.editVAR();
    return this.http
    .put(
      `${environment.apiUrl}/v1/resources/${this.resourceId}`,
      this.resource
    )
    .toPromise()
    .then(
      () => {
      },
      (err) => {}
    );
  }

  editVAR = () => {
    var varResObj = {
      "assetName": this.resource.name,
      "className": this.resource.varClass,
      "loAPhysical": this.resource.loaPhysical,
      "loACognitive": this.resource.loaCognitive,
      "numberOfOperators": this.resource.lcNOperMachine,
      "annualMaintenanceCost": this.resource.mcAMaintCosts,
      "annualMaintenanceCostPercent": this.resource.mcAMaintCostsPerc,
      "installationSurface": this.resource.rcInstSurface,
      "costPerSurfacePerMonth": this.resource.rcCostsMMonth,
      "machinePurchaseValue": this.resource.idMacPurhValue,
      "machineSalesValue": this.resource.idMacSalesValue,
      "economicUsefulLife": this.resource.idEcoUsefullLife,
      "annualElectricityConsumptionWhileWorking" : this.resource.ecAEleConsumFun,
      "annualElectricityConsumptionStandBy" : this.resource.ecAEleConsumSb,
      "equipmentId": Math.random().toString(36).substring(2).slice(-2).toUpperCase() + Math.floor(Math.random() * 99),
      "equipmentLevel": "",
      "interestRate": this.resource.icInterRate,
      "electricityPrice": this.resource.ecElePrice
    };


    return this.http
    .put(
      `${environment.apiUrl}/v1/var/editResource`,
      varResObj
    )
    .toPromise()
    .then(() => {this.syncingWithVAR = false;
    },
    (err) => {}
  );
}

}
