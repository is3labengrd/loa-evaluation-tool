import { Component, OnInit } from '@angular/core';
import { CookieService } from '../cookie.service';

@Component({
  selector: 'app-scenarios',
  templateUrl: './scenarios.component.html',
  styleUrls: ['./scenarios.component.css']
})
export class ScenariosComponent implements OnInit {

  constructor(private _processListService: CookieService) { }

  cookie: any;

  ngOnInit() {
    this.cookie = this._processListService.getCookie("selectedSubprocess");
  }

}
