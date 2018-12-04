import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-criteria-matrix',
  templateUrl: './criteria-matrix.component.html',
  styleUrls: ['./criteria-matrix.component.css']
})
export class CriteriaMatrixComponent implements OnInit {

  criteriaMatrixBody = {
    fkTbAceSubProLev: undefined,
    tcH: undefined,
    tcM: undefined,
    tcL: undefined,
    tcN: undefined,
    reN: undefined,
    reL: undefined,
    reM: undefined,
    reH: undefined,
    fcH: undefined,
    fcM: undefined,
    fcL: undefined,
    fcN: undefined,
    lvH: undefined,
    lvM: undefined,
    lvL: undefined,
    lvN: undefined,
    rdH: undefined,
    rdM: undefined,
    rdL: undefined,
    rdN: undefined,
    adL: undefined,
    adA: undefined,
    adS: undefined,
    adN: undefined,
    asL: undefined,
    asA: undefined,
    asS: undefined,
    asN: undefined,
    rmN: undefined,
    rmS: undefined,
    rmM: undefined,
    rmL: undefined,
    usN: undefined,
    usOc: undefined,
    usR: undefined,
    usOf: undefined
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
