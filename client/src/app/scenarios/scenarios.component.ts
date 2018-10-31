import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';

@Component({
  selector: 'app-scenarios',
  templateUrl: './scenarios.component.html',
  styleUrls: ['./scenarios.component.css']
})
export class ScenariosComponent implements OnInit {

  constructor(private http: HttpClient, private _processListService: CookieService) { }

  cookie: any;

  ngOnInit() {
    this.cookie = this._processListService.getCookie("selectedSubprocess");
    this.getScenariosList();
  }


  getScenariosList() {
    return this.http
      .get(environment.apiUrl + '/v1/subscenarios')
      .toPromise()
      .then(
        (res:any) => {
          console.log(res);

        }
      )
  }

}
