import { CookieService } from './../cookie.service';
import { DataSet } from './data-set';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Chart } from '../../../node_modules/chart.js/dist/Chart.min.js';
import { SubprocessSequence } from './subprocess-sequence';
import { LoaInfo } from './loa-info';


@Component({
  selector: 'app-as-is-loa',
  templateUrl: './as-is-loa.component.html',
  styleUrls: ['./as-is-loa.component.css']
})
export class AsIsLOAComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private cookieService: CookieService
  ) {}

  cookie = this.cookieService
    .getCookie('selectedSubprocess');

  title = 'LoA Graph';

  bubbleChart: any;

  analysisData: any;

  data = { datasets: [] };


  getAnalysisData() {
    const mainProcessId = this.cookie.mainProcessId;
    this.analysisData = this.http
      .get(`${environment.apiUrl}/v1/process-segments/${mainProcessId}`)
      .toPromise()
      .then(
        function createSubprocessArraySequences(process: any) {
          const subprocessSequences = [];
          let singleSequenceReference = [];
          let lastLevel = 0;
          for (const subprocess of process.subprocessLevels) {
            if (subprocess.proLevel > lastLevel) {
              lastLevel = subprocess.proLevel;
              singleSequenceReference.push(subprocess);
            } else {
              lastLevel = subprocess.proLevel;
              subprocessSequences.push(new SubprocessSequence(singleSequenceReference));
              singleSequenceReference = [subprocess];
            }
          }
          subprocessSequences.push(new SubprocessSequence(singleSequenceReference));
          return subprocessSequences;
        }
      )
      .then(
        function generateLoaToLabelMap(sequences) {
          const loaInfoPrimitiveToLabel = new Map();
          for (const sequence of sequences) {
            if (!sequence.loaInfo) { continue; }
            // tslint:disable-next-line:no-shadowed-variable
            const label = ((sequence) => {
              // tslint:disable-next-line:no-shadowed-variable
              let label = '';
              sequence.forEach(element => {
                label += element.name + ' - ';
              });
              return label.slice(0, label.length - 3);
            })(sequence.subprocessArray);
            if (loaInfoPrimitiveToLabel.has(sequence.loaInfo.valueOf())) {
              let currentValue = loaInfoPrimitiveToLabel.get(sequence.loaInfo.valueOf());
              currentValue += ` | ${label}`;
              loaInfoPrimitiveToLabel.set(sequence.loaInfo.valueOf(), currentValue);
            } else {
              loaInfoPrimitiveToLabel.set(sequence.loaInfo.valueOf(), label);
            }
          }
          return loaInfoPrimitiveToLabel;
        }
      )
      .then(
        /* UpdateBubbleChart */(map: Map<any, any>) => {
          map.forEach(
            (value, key) => {
              this.data.datasets.push(
                new DataSet(
                  LoaInfo.valueOfInverse(key),
                  value
                )
              );
            }
          );
          this.bubbleChart.update();
        }
      );
  }

  ngOnInit() {
    const data = this.data;
    this.bubbleChart = new Chart('bubbleChart', {
      type: 'bubble',
      data,
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
                stepSize: 0.5,
                suggestedMax: 7
              },
              scaleLabel: {
                display: true,
                labelString: 'Cognitive LoA'
              }
            }
          ],
          xAxes: [
            {
              ticks: {
                beginAtZero: true,
                stepSize: 0.5,
                suggestedMax: 7
              },
              scaleLabel: {
                display: true,
                labelString: 'Physical LoA'
              }
            }
          ]
        },
        tooltips: {
          callbacks: {
            label: function (tooltipItem) {
              return `Physical Loa: ${tooltipItem.xLabel}, Cognitive Loa: ${tooltipItem.yLabel}`
            }
          }
        }
      }
    });

    const bubbleChart = this.bubbleChart;

    this.getAnalysisData();

  }
}
