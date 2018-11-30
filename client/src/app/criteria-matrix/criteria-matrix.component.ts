import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-criteria-matrix',
  templateUrl: './criteria-matrix.component.html',
  styleUrls: ['./criteria-matrix.component.css']
})
export class CriteriaMatrixComponent implements OnInit {

  podaci = [
    {
      'criteria': 'Task complexity',
      'complexity1': 'high',
      'complexity2': 'medium',
      'complexity3': 'low',
      'complexity4': 'none',
    },
    {
      'criteria': 'Regularity of exercise',
      'complexity1': 'high',
      'complexity2': 'medium',
      'complexity3': 'low',
      'complexity4': 'none',
    },
    {
      'criteria': 'Frequency of task change',
      'complexity1': 'high',
      'complexity2': 'medium',
      'complexity3': 'low',
      'complexity4': 'none',
    },
    {
      'criteria': 'Level of product variants',
      'complexity1': 'high',
      'complexity2': 'medium',
      'complexity3': 'low',
      'complexity4': 'none',
    },
    {
      'criteria': 'Re-utilization of data',
      'complexity1': 'high',
      'complexity2': 'medium',
      'complexity3': 'low',
      'complexity4': 'none',
    },
    {
      'criteria': 'Amount of process data',
      'complexity1': 'high',
      'complexity2': 'medium',
      'complexity3': 'low',
      'complexity4': 'none',
    },
    {
      'criteria': 'Amount of input sources',
      'complexity1': 'high',
      'complexity2': 'medium',
      'complexity3': 'low',
      'complexity4': 'none',
    },
    {
      'criteria': 'Reaction/Decision making time',
      'complexity1': 'high',
      'complexity2': 'medium',
      'complexity3': 'low',
      'complexity4': 'none',
    },
    {
      'criteria': 'Unforessn situations',
      'complexity1': 'high',
      'complexity2': 'medium',
      'complexity3': 'low',
      'complexity4': 'none',
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
