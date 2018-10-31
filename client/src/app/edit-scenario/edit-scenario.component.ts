import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Subscription } from 'rxjs';
import { environment } from '../../environments/environment';
import { CookieService } from '../cookie.service';


@Component({
  selector: 'app-edit-scenario',
  templateUrl: './edit-scenario.component.html',
  styleUrls: ['./edit-scenario.component.css']
})
export class EditScenarioComponent implements OnInit {

  constructor(private http: HttpClient, private _processListService: CookieService) { }

  cookie: any;

  ngOnInit() {
    this.cookie = this._processListService.getCookie("selectedSubprocess");
  }
}
