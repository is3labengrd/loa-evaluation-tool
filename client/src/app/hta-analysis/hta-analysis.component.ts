import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hta-analysis',
  templateUrl: './hta-analysis.component.html',
  styleUrls: ['./hta-analysis.component.css']
})
export class HTAAnalysisComponent implements OnInit {

  criteriaMatrix = {
    "dimensionalStability": null
  };

  constructor() { }

  ngOnInit() {
  }

}
