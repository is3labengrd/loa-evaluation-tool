import { Component, OnInit } from "@angular/core";
import { CookieService } from "./../cookie.service";
import { environment } from "./../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Chart } from "../../../node_modules/chart.js/dist/Chart.min.js";

@Component({
  selector: "app-sub-scenarios-sorting",
  templateUrl: "./sub-scenarios-sorting.component.html",
  styleUrls: ["./sub-scenarios-sorting.component.css"]
})
export class SubScenariosSortingComponent implements OnInit {
  constructor(
        private http: HttpClient,
        private cookieService: CookieService
      ) {}
  cookie = this.cookieService.getCookie("selectedSubprocess");
  subProLevId: any = this.getFkAceSubProLevId(this.cookie);


  barChart: any;
  subScenario1: any;
  subScenario2: any;
  subScenario3: any;
  labelsXAxes: Array<any> = [];
  dataPhysical: Array<any> = [];
  dataCognitive: Array<any> = [];
  minimalTotalSatisfaction: number;
  minimalTotalSatisfactionID: number;


  ngOnInit() {
    var promise1 = this.minimalTotalSatisfactionGET();
    promise1.then((x) => {
        var promise2 = this.subScenarioGET();

         promise2.then((x) => {
          if(this.subScenario1!=null){
                    this.labelsXAxes.push("Scenario 1 ["+this.subScenario1.assemblyCostPerPiece+"€/piece]");
                    this.dataPhysical.push(this.subScenario1.resource.loaPhysical);
                    this.dataCognitive.push(this.subScenario1.resource.loaCognitive);
                }else{
                    this.labelsXAxes.push("Scenario 1");
                    this.dataPhysical.push(0);
                    this.dataCognitive.push(0);
                }

                if(this.subScenario2!=null){
                   this.labelsXAxes.push("Scenario 2 ["+this.subScenario2.assemblyCostPerPiece+"€/piece]");
                   this.dataPhysical.push(this.subScenario2.resource.loaPhysical);
                   this.dataCognitive.push(this.subScenario2.resource.loaCognitive);
                }else{
                   this.labelsXAxes.push("Scenario 2");
                   this.dataPhysical.push(0);
                   this.dataCognitive.push(0);
                }

                if(this.subScenario3!=null){
                 this.labelsXAxes.push("Scenario 3 ["+this.subScenario3.assemblyCostPerPiece+"€/piece]");
                 this.dataPhysical.push(this.subScenario3.resource.loaPhysical);
                 this.dataCognitive.push(this.subScenario3.resource.loaCognitive);
                }else{
                 this.labelsXAxes.push("Scenario 3");
                 this.dataPhysical.push(0);
                 this.dataCognitive.push(0);
               }

                 this.createBarChart();
             });
    });


  }

  createBarChart() {
    this.barChart = new Chart("barChart", {
      type: "bar",
      data: {
        labels: this.labelsXAxes, // responsible for how many bars are gonna show on the chart
        // create 12 datasets, since we have 12 items
        // data[0] = labels[0] (data for first bar - 'Standing costs') | data[1] = labels[1] (data for second bar - 'Running costs')
        // put 0, if there is no data for the particular bar
        datasets: [
          {
            label: "Minimal total satisfaction",
            data: [this.minimalTotalSatisfaction, this.minimalTotalSatisfaction, this.minimalTotalSatisfaction],
            backgroundColor: "#FF0000",
            type: "line",
            fill: false,
            borderColor: "red"
          },
          {
            label: "Physical",
            data: this.dataPhysical,
            backgroundColor: "#3366cc"
          },
          {
            label: "Cognitive",
            data: this.dataCognitive,
            backgroundColor: "#abbcff"
          }
        ]
      },
      options: {
        responsive: false,
        legend: {
          position: "right", // place legend on the right side of
          labels: {
            padding: 30
          }
        },
        scales: {
          xAxes: [
            {
              stacked: true, // this should be set to make the bars stacked
              barPercentage: 0.7
            }
          ],
          yAxes: [
            {
              stacked: true, // this also..
              scaleLabel: {
                display: true,
                labelString: "Satisfaction"
              },
              ticks: {
                beginAtZero: true,
                stepSize: 1,
                suggestedMax: 14
              }
            }
          ]
        }
      }
    });
  }

  subScenarioGET() {
    let fkTbAceResID;
    let fkTbAceSubProLevID;
    let subscenarios: Array<any>;

    var postObj = {
      subprocessLevel: {
        pkTbId: {}
      }
    };

    postObj.subprocessLevel.pkTbId = this.getFkAceSubProLevId(this.cookie);
    return this.http
      .post(environment.apiUrl + "/v1/subscenarios/search", postObj)
      .toPromise()
      .then(
        (result: any) => {
          subscenarios = result;
          subscenarios.forEach((element: any) => {
            switch (element.scenarioNumber) {
              case 1:
                this.subScenario1 = element;
                break;
              case 2:
                this.subScenario2 = element;
                break;
              case 3:
                this.subScenario3 = element;
                break;
            }
          });
        },
        err => {}
      );
  }

  getFkAceSubProLevId(cookie): number {
       if(!("undefined" === typeof(cookie['level3']))){
              return cookie.level3.id;
          }else{
           if(!("undefined" === typeof(cookie['level2']))){
           return cookie.level2.id;
           }else{
            return cookie.level1.id;
            }
          }

      }
  updateSubScenarios(){
      if(this.subScenario1!=null && this.minimalTotalSatisfaction!=null) {
          if((this.subScenario1.resource.loaPhysical+ this.subScenario1.resource.loaCognitive)>=this.minimalTotalSatisfaction){
          this.subScenario1.resSorting=true;
          }else{
          this.subScenario1.resSorting=false;
          }
          this.http.put(environment.apiUrl + '/v1/subscenarios/'+this.subScenario1.pkTbId, this.subScenario1)
              .toPromise().then((res:any) => {});
      }

      if(this.subScenario2!=null && this.minimalTotalSatisfaction!=null){
          if((this.subScenario2.resource.loaPhysical+ this.subScenario2.resource.loaCognitive)>=this.minimalTotalSatisfaction){
          this.subScenario2.resSorting=true;
          }else{
          this.subScenario2.resSorting=false;
          }
          this.http.put(environment.apiUrl + '/v1/subscenarios/'+this.subScenario2.pkTbId, this.subScenario2)
              .toPromise().then((res:any) => {});
      }

      if(this.subScenario3!=null && this.minimalTotalSatisfaction!=null){
          if((this.subScenario3.resource.loaPhysical+ this.subScenario3.resource.loaCognitive)>=this.minimalTotalSatisfaction){
          this.subScenario3.resSorting=true;
          }else{
          this.subScenario3.resSorting=false;
          }
          this.http.put(environment.apiUrl + '/v1/subscenarios/'+this.subScenario3.pkTbId, this.subScenario3)
              .toPromise().then((res:any) => {});
      }
  }

   enableSaveButton(): boolean {
        if(this.minimalTotalSatisfaction==null){
            return false;
        }else{
          if(this.subScenario1==null && this.subScenario2==null && this.subScenario3==null){
            return false;
         }
         return true;
        }
    }
    minimalTotalSatisfactionGET() {
              return this.http
                .get(environment.apiUrl + '/v1/minimal-satisfactions-by-subprocess-level-id/'+this.subProLevId)
                .toPromise()
                .then(
                (result:any) => {
                     this.minimalTotalSatisfaction=result.minTotalSat;
                     this.minimalTotalSatisfactionID=result.pkTbId;
                 },
                 err => {
                    this.minimalTotalSatisfactionID=null;
                 })
    }


    minimalTotalSatisfactionUpdate(): void {
        var minimalTotalSatisfactionObj = {
                                  minTotalSat: {},
                                  fkTbAceSubProLev: {},
                                  fkTbAceProSeq: {}
                            };
        minimalTotalSatisfactionObj.minTotalSat = this.minimalTotalSatisfaction;
        minimalTotalSatisfactionObj.fkTbAceProSeq = this.cookie.mainProcessId;
        minimalTotalSatisfactionObj.fkTbAceSubProLev = this.subProLevId;

        if (this.minimalTotalSatisfactionID == null) {
         this.http.post(environment.apiUrl + '/v1/minimal-satisfactions', minimalTotalSatisfactionObj)
                            .toPromise().then((res:any) => {
                              this.minimalTotalSatisfactionID=res.pkTbId;
                            });
          this.createBarChart();
         }else{
           this.http.put(environment.apiUrl + '/v1/minimal-satisfactions/'+this.minimalTotalSatisfactionID, minimalTotalSatisfactionObj)
                    .subscribe(res => console.log('Done put'));
          this.createBarChart();
        }

     }

}
