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
    return this.http
    .put(
      `${environment.apiUrl}/v1/resources/${this.resourceId}`,
      this.resource
    )
    .toPromise()
    .then(
      () => { this.syncingWithVAR = false; },
    );
  }

}
