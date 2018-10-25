import { Component, OnInit } from '@angular/core';
import {ProcessListService} from '../process-list/process-list.service';

@Component({
  selector: 'app-scenarios',
  templateUrl: './scenarios.component.html',
  styleUrls: ['./scenarios.component.css']
})
export class ScenariosComponent implements OnInit {

  constructor(private _processListService: ProcessListService) { }

  cookie:any;

  ngOnInit() {
    this.cookie = this._processListService.getCookie("selectedSubprocess");
  }

}
