import { Component, OnInit } from '@angular/core';
import { Chart } from '../../../node_modules/chart.js/dist/Chart.min.js';

@Component({
  selector: 'app-as-is-loa',
  templateUrl: './as-is-loa.component.html',
  styleUrls: ['./as-is-loa.component.css']
})
export class AsIsLOAComponent implements OnInit {

  title = 'LoA Graph';

  BarChart = [];
  BablChart = [];

  ngOnInit() {
    this.BablChart = new Chart('bablChart', {
      type: 'bubble',
      data: {
        datasets: [
          {
            label: 'Checking, Final inspection',
            backgroundColor: 'orange',
            borderColor: 'black',
            borderWidth: 1,
            data: [
              {
                x: 1,
                y: 1,
                r: 10
              }
            ]
          },
          {
            label: 'Handling, Rear light adjustment',
            backgroundColor: 'red',
            borderColor: 'black',
            borderWidth: 1,
            data: [
              {
                x: 6,
                y: 2,
                r: 10
              }
            ]
          },
          {
            label: 'Handling, Rivet nut fastening',
            backgroundColor: 'blue',
            borderColor: 'black',
            borderWidth: 1,
            data: [
              {
                x: 2,
                y: 1,
                r: 10
              }
            ]
          }
        ]
      },
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
        title: {
          display: false,
          text: 'Moj chart',
          position: 'top'
        }
      }
    });

  }
}
