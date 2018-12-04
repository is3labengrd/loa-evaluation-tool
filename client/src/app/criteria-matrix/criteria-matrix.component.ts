import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-criteria-matrix',
  templateUrl: './criteria-matrix.component.html',
  styleUrls: ['./criteria-matrix.component.css']
})
export class CriteriaMatrixComponent implements OnInit {

  criteriaMatrixBody = {
    fkTbAceSubProLev: undefined,
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
      'complexity1': 'none',
      'complexity2': 'low',
      'complexity3': 'medium',
      'complexity4': 'high',
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
      'complexity1': 'large',
      'complexity2': 'average',
      'complexity3': 'small',
      'complexity4': 'none',
    },
    {
      'criteria': 'Amount of input sources',
      'complexity1': 'large',
      'complexity2': 'average',
      'complexity3': 'small',
      'complexity4': 'none',
    },
    {
      'criteria': 'Reaction/Decision making time',
      'complexity1': 'none',
      'complexity2': 'short',
      'complexity3': 'medium',
      'complexity4': 'long',
    },
    {
      'criteria': 'Unforessn situations',
      'complexity1': 'never',
      'complexity2': 'occasionally',
      'complexity3': 'rarley',
      'complexity4': 'often',
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
