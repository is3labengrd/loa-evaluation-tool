import { Component, OnInit } from '@angular/core';
import {CookieService} from '../cookie.service';

@Component({
  selector: 'app-main-analysis',
  templateUrl: './main-analysis.component.html',
  styleUrls: ['./main-analysis.component.css']
})
export class MainAnalysisComponent implements OnInit {

  constructor(private _processListService: CookieService) { }

  cookie: any;

   ngOnInit() {
      this.cookie = this._processListService.getCookie('selectedSubprocess');
  }

}


