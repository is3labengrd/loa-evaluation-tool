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
  searchTerm: string;
  resources = [];

  constructor(
    private http: HttpClient,
    private _processListService: CookieService,
    private assaignService: AssaignService
  ) { }

  selectedSubprocess: any;

  ngOnInit() {
    this.selectedSubprocess = this._processListService.getCookie('selectedSubprocess');
    this.updateResourceList();
  }

  updateResourceList() {
    this.http.get(
      environment.apiUrl +
      '/v1/resource-items-by-subprocess-id/' +
      this.selectedSubprocess[`level${this.selectedSubprocess.maxDepth}`].id + "?" +
      'page=' + this.pagination.page + '&' +
      'size=' + this.pagination.size
    ).subscribe((resources: any) => {
      this.resources = resources.content;
      this.pagination.lastPage = resources.totalPages - 1;
      this.pagination.totalPages = Array(this.pagination.lastPage + 1)
        .fill(0).map((x, y) => x + y);
    });
  }

  resetPage() {
    this.pagination.page = 0;
  }

  private nextPage() {
    this.pagination.page = Math.min(this.pagination.lastPage, ++this.pagination.page);
  }

  private previousPage() {
    this.pagination.page = Math.max(0, --this.pagination.page);
  }

}
