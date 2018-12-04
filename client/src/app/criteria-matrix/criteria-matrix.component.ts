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
  infoId;
  cognitiveLoaArray;
  physicalLoaArray;

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

  actualLoaInfoRequest = {
    'fkTbAcePhyLoa': '1',
    'fkTbAceCogLoa': '1',
    'possibility': false,
    'bestRange': '1-1',
    'fkTbAceSubProLev': this.subprocessId,
    'loaType': 'c'
  };

  loaInfoRequest: any = new Proxy(
    this.actualLoaInfoRequest,
    {
      set: function (obj, prop, value) {
        switch (prop) {
          case 'bestRangeMin':
            if (Number.isNaN(Number(value))) {
              // tslint:disable-next-line:no-string-throw
              throw 'Invalid parameter';
            }
            obj.bestRange = obj.bestRange
              .replace(/^(\d+)/, Number(value).toString());
            return value;
          case 'bestRangeMax':
            if (Number.isNaN(Number(value))) {
              // tslint:disable-next-line:no-string-throw
              throw 'Invalid parameter';
            }
            obj.bestRange = obj.bestRange
              .replace(/(\d+)$/, Number(value).toString());
            return value;
          case 'bestRange':
            if (/^(\d+)-(\d+)$/.test(value)) {
              obj.bestRange = obj.bestRange;
            } else {
              obj.bestRange = '1-1';
            }
            return value;
          default:
            return Reflect.set(obj, prop, value);
        }
      },
      get: function (obj, prop) {
        switch (prop) {
          case 'bestRangeMin':
            return /^(\d+)/.exec(obj.bestRange)[0].toString();
          case 'bestRangeMax':
            return /(\d+)$/.exec(obj.bestRange)[0].toString();
          default:
            return Reflect.get(obj, prop);
        }
      }
    }
  );

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
    this.http
      .get(environment.apiUrl + '/v1/cognitive-process-loa-info-by-subprocess-id/' + this.subprocessId)
      .toPromise()
      .catch(
        () => this.http
          .post(environment.apiUrl + '/v1/process-loa-info', this.actualLoaInfoRequest)
          .toPromise()
      )
      .then(
        (info: any) => {
          this.infoId = info.pkTbId;
          this.loaInfoRequest.possibility = info.possibility;
          this.loaInfoRequest.fkTbAcePhyLoa = info.fkTbAcePhyLoa;
          this.loaInfoRequest.fkTbAceCogLoa = info.fkTbAceCogLoa;
          this.loaInfoRequest.bestRangeMin = /^(\d+)/.exec(info.bestRange)[0];
          this.loaInfoRequest.bestRangeMax = /(\d+)$/.exec(info.bestRange)[0];
        }
      );
    const cognitiveLoaSubscription: Subscription = this.http
      .get(`${environment.apiUrl}/v1/cognitive-loa`)
      .subscribe(
        (cognitiveLoaArray: Array<any>) => {
          this.cognitiveLoaArray = cognitiveLoaArray;
          cognitiveLoaSubscription.unsubscribe();
        }
      );
    const physicalLoaSubscription: Subscription = this.http
      .get(`${environment.apiUrl}/v1/physical-loa`)
      .subscribe(
        (physicalLoaArray: Array<any>) => {
          this.physicalLoaArray = physicalLoaArray;
          physicalLoaSubscription.unsubscribe();
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

  opSuc: boolean;
  saveLoaInfo(min, max) {
    this.http
      .put(
        environment.apiUrl + '/v1/process-loa-info/' + this.infoId,
        this.actualLoaInfoRequest
      )
      .toPromise()
      .then(() => {
        this.opSuc = true;
      })
      .catch((err) => {
        this.opSuc = false;
        console.log(err);
      });
    }
  }

}
