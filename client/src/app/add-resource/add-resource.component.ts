import { Router } from '@angular/router';
import { environment } from './../../environments/environment';
import { CookieService } from './../cookie.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-resource',
  templateUrl: './add-resource.component.html',
  styleUrls: ['./add-resource.component.css']
})
export class AddResourceComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    public router: Router
  ) { }

  selectedSubprocess = this
  .cookieService
  .getCookie('selectedSubprocess');

  resourceInitialState = {
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
    'varClass':'WorkUnit'
  };

  resource = Object.seal(
    Object.assign({}, this.resourceInitialState)
  );

  syncingWithVAR = false;
  ngOnInit() {
    this.syncingWithVAR = true;
  }

  cancel() {
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

  // tslint:disable-next-line:member-ordering
  opSuc: boolean;

  save = () => {

    return this.http
    .post(
      `${environment.apiUrl}/v1/resources`,
      this.resource
    )
    .toPromise()
    .then(() => {
      this.syncingWithVAR = false;
      this.opSuc = true;
    })
    .catch((err) => {
      this.opSuc = false;
    });


  }

}
