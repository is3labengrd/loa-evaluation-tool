import { Component, OnInit } from '@angular/core';
import {ProcessListService} from '../process-list/process-list.service';

@Component({
  selector: 'app-main-analysis',
  templateUrl: './main-analysis.component.html',
  styleUrls: ['./main-analysis.component.css']
})
export class MainAnalysisComponent implements OnInit {

  constructor(private _processListService: ProcessListService) { }

  cookie: any;

   ngOnInit() {
      this.cookie = this._processListService.getCookie("selectedSubprocess");
  }

}


