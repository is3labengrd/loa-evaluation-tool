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
    size: 1,
    page: 0,
    lastPage: 0,
    totalPages: []
  };
  searchTerm: string;
  resources = [];
  resources1 = [
    {
      id: 1,
      name: 'Manual tool trolley',
      loaPhysical: 4,
      loaCognitive: 6
    },
    {
      id: 2,
      name: 'Semi automated tool trolley',
      loaPhysical: 2,
      loaCognitive: 3
    },
    {
      id: 3,
      name: 'Automated tool trolley',
      loaPhysical: 4,
      loaCognitive: 6
    },
    {
      id: 4,
      name: 'Resource 4',
      loaPhysical: 1,
      loaCognitive: 1
    },
    {
      id: 8,
      name: 'Resource 5',
      loaPhysical: 2,
      loaCognitive: 6
    }
  ];

  linkChange = false;

  constructor(private http: HttpClient, private _processListService: CookieService, private assignService: AssaignService) { }

  cookie: any;
  ngOnInit() {
    this.updateResourceList();
    this.cookie = this._processListService.getCookie('selectedSubprocess');
  }

  updateResourceList() {
    this.http.get(
      environment.apiUrl + '/v1/resources?' +
      'page=' + this.pagination.page + '&' +
      'size=' + this.pagination.size
    ).subscribe((resources: any) => {
      this.resources = resources.content;
      this.pagination.lastPage = resources.totalPages - 1;
      this.pagination.totalPages = Array(this.pagination.lastPage + 1)
        .fill(0).map((x, y) => x + y);
    });
  }

  assignRes(
    name: string,
    loaPhysical: number,
    loaCognitive: number
  ) {
      const data = {
        name: name,
        loaPhysical: loaPhysical,
        loaCognitive: loaCognitive
      };

      this.assignService.assaignRes(data);
      console.log(data);
  }

  deassignRes(id) {
    console.log(id);

    this.assignService.deassignRes(id);
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

  isChecked(e, name, loaF, loaC, id) {

    if (e.target.checked) {
      console.log('obelezeno');
      this.assignRes(name, loaF, loaC);
    } else {
      console.log('neobelezeno');
    }
    console.log(name, loaF, loaC);
    this.deassignRes(id);
  }

  promena() {
    this.linkChange = !this.linkChange;
    console.log(this.linkChange);
    if (this.linkChange === false) {
      alert('Assign successful!');
    } else {
      alert('Deassaign successful!');
    }
  }


}
