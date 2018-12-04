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

  constructor() { }

  ngOnInit() {
  }

}
