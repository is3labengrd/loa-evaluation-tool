import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AssaignService {

  constructor(private http: Http, private route: ActivatedRoute) { }

  assaignRes(data) {
    const url = environment.apiUrl + '/v1/subprocess-level-resources';
    const header = new Headers({'Content-type': 'application/json'});

    this.http.post(url, data).subscribe();
  }

}
