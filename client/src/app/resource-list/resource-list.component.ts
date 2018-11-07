import { AssaignService } from './../assaign.service';
import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/map';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Http } from '@angular/http';

import { CookieService } from '../cookie.service';

@Component({
  selector: 'app-resource-list',
  templateUrl: './resource-list.component.html',
  styleUrls: ['./resource-list.component.css']
})
export class ResourceListComponent implements OnInit {

  pagination = {
    size: 12,
    page: 0,
    lastPage: 0,
    totalPages: []
  };

  resetPage() {
    this.pagination.page = 0;
  }

  private nextPage() {
    this.pagination.page = Math.min(this.pagination.lastPage, ++this.pagination.page);
  }

  private previousPage() {
    this.pagination.page = Math.max(0, --this.pagination.page);
  }

  // tslint:disable-next-line:member-ordering
  searchTerm = '';
  // tslint:disable-next-line:member-ordering
  resources = [];

  constructor(
    private http: HttpClient,
    private _processListService: CookieService,
    private assaignService: AssaignService
  ) { }

  // tslint:disable-next-line:member-ordering
  selectedSubprocess: any;

  ngOnInit() {
    this.selectedSubprocess = this._processListService.getCookie('selectedSubprocess');
    this.updateResourceList();
  }

  // tslint:disable-next-line:member-ordering
  assigned: boolean;
  // tslint:disable-next-line:member-ordering
  deassigned: boolean;

  assi() {
    this.assigned = true;

    setTimeout(() => {
      this.assigned = false;
    }, 2000);
  }

  deassi() {
    this.deassigned = true;

    setTimeout(() => {
      this.deassigned = false;
    }, 2000);
  }

  updateResourceList = () => {
    // tslint:disable-next-line:prefer-const
    let subProcessId = this
      .selectedSubprocess[`level${this.selectedSubprocess.maxDepth}`].id;
    // tslint:disable-next-line:whitespace
    const url = this.searchTerm.length?
      `${environment.apiUrl}/v1/resource-items-by-subprocess-id/${subProcessId}` +
      `/like/${this.searchTerm}?` +
      'page=' + this.pagination.page + '&' +
      'size=' + this.pagination.size
    :
      `${environment.apiUrl}/v1/resource-items-by-subprocess-id/${subProcessId}?` +
      'page=' + this.pagination.page + '&' +
      'size=' + this.pagination.size
    ;
    this.http.get(url)
    .subscribe((resources: any) => {
      this.resources = resources.content;
      this.pagination.lastPage = resources.totalPages - 1;
      this.pagination.totalPages = Array(this.pagination.lastPage + 1)
        .fill(0).map((x, y) => x + y);
    });

  }


  assign = (resource) => {
    // tslint:disable-next-line:prefer-const
    let subProcessLevelId = this
      .selectedSubprocess[
        `level${this.selectedSubprocess.maxDepth}`
      ].id;
    return this.http.post(
      `${environment.apiUrl}/v1/subprocess-level-resources`,
      {
        'fkTbAceRes': resource.resourceId,
        'fkTbAceSubProLev': subProcessLevelId
      }
    ).toPromise();

  }

  deassign = (resource) => {
    return this.http
      .delete(
        `${environment.apiUrl}/v1/subprocess-level-resources/${resource.assignmentId}`
      ).toPromise();
  }

}
