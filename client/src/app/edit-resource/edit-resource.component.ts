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
    'ecElePrice': null
  };

  resourceInitialState;

  ngOnInit() {
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
      (keyValuePair) => (keyValuePair[1] == null)
    );
  }

  // tslint:disable-next-line:member-ordering
  performed: boolean;

  away() {
    this.router.navigate(['resource-list']);
  }

  save = () => {


    return this.http
      .put(
        `${environment.apiUrl}/v1/resources/${this.resourceId}`,
        this.resource
      )
      .toPromise()
      .then(
        () => {
          this.saveVAR();
        }
      );
  }

  saveVAR = () => {
    var varResObj = {
                "assetName": this.resource.name,
                "loAPhysical": this.resource.loaPhysical,
                "loACognitive": this.resource.loaCognitive,
                "numberOfOperators": this.resource.lcNOperMachine,
                "annualMaintenanceCost": this.resource.mcAMaintCosts,
                "installationSurface": this.resource.rcInstSurface,
                "costPerSurfacePerMonth": this.resource.rcCostsMMonth,
                "machinePurchaseValue": this.resource.idMacPurhValue,
                "machineSalesValue": this.resource.idMacSalesValue,
                "economicUsefulLife": this.resource.idEcoUsefullLife,
                "annualElectricityConsumptionWhileWorking" : this.resource.ecAEleConsumFun,
                "annualElectricityConsumptionStandBy" : this.resource.ecAEleConsumSb,
                "equipmentId": "",
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
      .then(() => {
      })
  }

}
