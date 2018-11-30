import { Component, OnInit } from '@angular/core';
import { CookieService } from './../cookie.service';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Chart } from '../../../node_modules/chart.js/dist/Chart.min.js';

@Component({
  selector: 'app-sub-scenarios-sorting',
  templateUrl: './sub-scenarios-sorting.component.html',
  styleUrls: ['./sub-scenarios-sorting.component.css']
})
export class SubScenariosSortingComponent implements OnInit {

  constructor(
      private http: HttpClient,
      private cookieService: CookieService
    ) {}

  cookie = this.cookieService.getCookie('selectedSubprocess');
  minimalTotalSatisfaction: number;

  barChart: any;

  ngOnInit() {

  this.barChart = new Chart('barChart', {
                      type: 'bar',
                       data: {
                            labels: ['Scenario 1 [...€/piece]', 'Scenario 2 [...€/piece]', 'Scenario 3 [...€/piece]'], // responsible for how many bars are gonna show on the chart
                            // create 12 datasets, since we have 12 items
                            // data[0] = labels[0] (data for first bar - 'Standing costs') | data[1] = labels[1] (data for second bar - 'Running costs')
                            // put 0, if there is no data for the particular bar
                            datasets: [{
                              label: 'LoA value',
                              data: [6, 0, 0],
                              backgroundColor: '#3366cc'
                            }, {
                              label: 'LoA value',
                              data: [4, 0, 0],
                              backgroundColor: '#abbcff'
                            }, {
                               label: 'LoA value',
                               data: [0, 4, 0],
                               backgroundColor: '#3366cc'
                            }, {
                               label: 'LoA value',
                               data: [0, 2, 0],
                               backgroundColor: '#abbcff'
                            }, {
                               label: 'LoA value',
                               data: [0, 0, 7],
                               backgroundColor: '#3366cc'
                             },{
                               label: 'LoA value',
                               data: [0, 0, 2],
                               backgroundColor: '#abbcff'
                             }]
                         },
                        options: {
                                     responsive: false,
                                     legend: {
                                        position: 'right' // place legend on the right side of chart
                                      },
                                     scales: {
                                        xAxes: [{
                                           stacked: true, // this should be set to make the bars stacked
                                           barPercentage: 0.7,
                                         }],
                                        yAxes: [{
                                           stacked: true, // this also..
                                           scaleLabel: {
                                                display: true,
                                                labelString: 'Satisfaction'
                                           },
                                           ticks: {
                                                beginAtZero: true,
                                                stepSize: 1,
                                                suggestedMax: 14
                                           }
                                         }]
                                     }
                                  }
                  });
  }

}
