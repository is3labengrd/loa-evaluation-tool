import { CookieService } from './../cookie.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-criteria-matrix',
  templateUrl: './criteria-matrix.component.html',
  styleUrls: ['./criteria-matrix.component.css']
})
export class CriteriaMatrixComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private cookieService: CookieService
  ) { }

  selectedSubprocess = this.cookieService
    .getCookie('selectedSubprocess');
  subprocessId = this.selectedSubprocess[
    'level' + this.selectedSubprocess.maxDepth
  ].id;

  matrixId;

  criteriaMatrixBody = {
    fkTbAceSubProLev: this.subprocessId,
    tcH: 0,
    tcM: 0,
    tcL: 0,
    tcN: 0,
    reN: 0,
    reL: 0,
    reM: 0,
    reH: 0,
    fcH: 0,
    fcM: 0,
    fcL: 0,
    fcN: 0,
    lvH: 0,
    lvM: 0,
    lvL: 0,
    lvN: 0,
    rdH: 0,
    rdM: 0,
    rdL: 0,
    rdN: 0,
    adL: 0,
    adA: 0,
    adS: 0,
    adN: 0,
    asL: 0,
    asA: 0,
    asS: 0,
    asN: 0,
    rmN: 0,
    rmS: 0,
    rmM: 0,
    rmL: 0,
    usN: 0,
    usOc: 0,
    usR: 0,
    usOf: 0
  }

  ngOnInit() {
    this.http
      .get(environment.apiUrl + '/v1/cognitive-criteria-matrices-by-subprocess-id/' + this.subprocessId)
      .toPromise()
      .catch(
        () => this.http
          .post(environment.apiUrl + '/v1/cognitive-criteria-matrices', this.criteriaMatrixBody)
          .toPromise()
      )
      .then(
        (matrix: any) => {
          this.matrixId = matrix.pkTbId;
          this.criteriaMatrixBody = matrix;
        }
      );
  }

  updateMatrix() {
    const subscription: Subscription = this.http
      .put(
        `${environment.apiUrl}/v1/cognitive-criteria-matrices/${this.matrixId}`,
        this.criteriaMatrixBody
      )
      .subscribe(
        () => {
          subscription.unsubscribe();
        }
      );
  }

}
