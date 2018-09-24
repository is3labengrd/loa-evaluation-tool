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
    // this.BarChart = new Chart('barChart', {
    //   type: 'bar',
    //   data: {
    //   labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
    //   datasets: [{
    //       label: '# of Votes',
    //       data: [9, 7, 3, 5, 2, 10],
    //       backgroundColor: [
    //           'rgba(255, 99, 132, 0.2)',
    //           'rgba(54, 162, 235, 0.2)',
    //           'rgba(255, 206, 86, 0.2)',
    //           'rgba(75, 192, 192, 0.2)',
    //           'rgba(153, 102, 255, 0.2)',
    //           'rgba(255, 159, 64, 0.2)'
    //       ],
    //       borderColor: [
    //           'rgba(255,99,132,1)',
    //           'rgba(54, 162, 235, 1)',
    //           'rgba(255, 206, 86, 1)',
    //           'rgba(75, 192, 192, 1)',
    //           'rgba(153, 102, 255, 1)',
    //           'rgba(255, 159, 64, 1)'
    //       ],
    //       borderWidth: 1
    //   }]
    //   },
    //   options: {
    //   title:{
    //       text:"Bar Chart",
    //       display:true
    //   },
    //   scales: {
    //       yAxes: [{
    //           ticks: {
    //               beginAtZero:true
    //           }
    //       }]
    //   }
    //   }
    // });

    this.BablChart = new Chart('bablChart', {
      type: 'bubble',
      data: {
        datasets: [
          {
            label: 'mehurici',
            data: [
              {
                x: 3,
                y: 5,
                r: 10
              },
              {
                x: 6,
                y: 2,
                r: 10
              },
              {
                x: 4,
                y: 3,
                r: 10
              },
              {
                x: 7,
                y: 7,
                r: 10
              }
            ],
            backgroundColor: ['orange', 'red', 'green', 'blue'],
            borderColor: 'black',
            borderWidth: 1
          }
        ]
      },
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
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
          display: true,
          text: 'Moj chart',
          position: 'top'
        }
      }
    });

  }
}
