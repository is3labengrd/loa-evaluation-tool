import { CookieService } from './../cookie.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { initializeMatrix, updateMatrix } from './matrix';

@Component({
  selector: 'app-hta-analysis',
  templateUrl: './hta-analysis.component.html',
  styleUrls: ['./hta-analysis.component.css']
})
export class HTAAnalysisComponent implements OnInit {

  constructor(
    private http:HttpClient,
    private route:ActivatedRoute,
    private cookieService:CookieService
  ) { }

  selectedSubprocess = this.cookieService
    .getCookie("selectedSubprocess");
  subprocessId = this.selectedSubprocess[
    "level" + this.selectedSubprocess.subLevels
  ].id;

  matrixRequest:any = {
    "fkTbAceSubProLev": this.subprocessId
  }

  matrix = initializeMatrix(this.matrixRequest);

  actualLoaInfoRequest = {
    "fkTbAcePhyLoa": "1",
    "fkTbAceCogLoa": "1",
    "possibility": false,
    "bestRange": "1-1",
    "fkTbAceSubProLev": this.subprocessId
  }

  loaInfoRequest:any = new Proxy(
    this.actualLoaInfoRequest,
    {
      set: function (obj, prop, value) {
        switch (prop) {
          case "bestRangeMin":
            obj.bestRange = obj.bestRange
              .replace(/^(\d+)/, value);
            return value;
          case "bestRangeMax":
            obj.bestRange = obj.bestRange
              .replace(/(\d+)$/, value);
            return value;
          case "bestRange":
            obj.bestRange = obj.bestRange;
            return value;
          default:
            return Reflect.set(obj, prop, value);
        }
      },
      get: function (obj, prop) {
        switch (prop) {
          case "bestRangeMin":
            return /^(\d+)/.exec(obj.bestRange)[0].toString();
          case "bestRangeMax":
            return /(\d+)$/.exec(obj.bestRange)[0].toString();
          default:
            return Reflect.get(obj, prop);
        }
      }
    }
  );

  matrixId;
  infoId;
  cognitiveLoaArray;
  physicalLoaArray;

  ngOnInit() {
    this.http
      .get(environment.apiUrl + "/v1/criteria-matrices-by-subprocess-id/" + this.subprocessId)
      .toPromise()
      .catch(
        () => this.http
          .post(environment.apiUrl + "/v1/criteria-matrices", this.matrixRequest)
          .toPromise()
      )
      .then(
        (matrix:any) => {
          this.matrixId = matrix.pkTbId;
          updateMatrix(this.matrix, matrix);
        }
      );
    this.http
      .get(environment.apiUrl + "/v1/process-loa-info-by-subprocess-id/" + this.subprocessId)
      .toPromise()
      .catch(
        () => this.http
          .post(environment.apiUrl + "/v1/process-loa-info", this.actualLoaInfoRequest)
          .toPromise()
      )
      .then(
        (info:any) => {
          this.infoId = info.pkTbId;
          this.loaInfoRequest.fkTbAcePhyLoa = info.fkTbAcePhyLoa;
          this.loaInfoRequest.fkTbAceCogLoa = info.fkTbAceCogLoa;
          this.loaInfoRequest.bestRangeMin = info.bestRange;
          this.loaInfoRequest.bestRangeMax = info.bestRange;
        }
      )

    var cognitiveLoaSubscription:Subscription = this.http
      .get(`${environment.apiUrl}/v1/cognitive-loa`)
      .subscribe(
        (cognitiveLoaArray:Array<any>) => {
          this.cognitiveLoaArray = cognitiveLoaArray;
          cognitiveLoaSubscription.unsubscribe();
        }
      );
    var physicalLoaSubscription:Subscription = this.http
      .get(`${environment.apiUrl}/v1/physical-loa`)
      .subscribe(
        (physicalLoaArray:Array<any>) => {
          this.physicalLoaArray = physicalLoaArray;
          physicalLoaSubscription.unsubscribe();
        }
      );
  }

  updateMatrix() {
    var subscription:Subscription = this.http
      .put(
        `${environment.apiUrl}/v1/criteria-matrices/${this.matrixId}`,
        this.matrixRequest
      )
      .subscribe(
        () => {
          subscription.unsubscribe();
        }
      );
  }

  saveLoaInfo() {
    this.http
      .put(
        environment.apiUrl + "/v1/process-loa-info/" + this.infoId,
        this.actualLoaInfoRequest
      )
      .toPromise()
  }

}
