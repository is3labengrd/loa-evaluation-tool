import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-hta-analysis',
  templateUrl: './hta-analysis.component.html',
  styleUrls: ['./hta-analysis.component.css']
})
export class HTAAnalysisComponent implements OnInit {

  rows = [
    {
      strong: 'Dimensional stabilty',
      criteria1: 'Stable',
      criteria2: 'Reduced stability',
      criteria3: 'Hardly stable',
      criteria4: 'Unstable'
    },
    {
      strong: 'Sensitivity',
      criteria1: 'Insesitive',
      criteria2: 'Handly sensitive',
      criteria3: 'Sensitive',
      criteria4: 'Very sensitive'
    },
    {
      strong: 'Grip',
      criteria1: 'External grip surfaces',
      criteria2: 'Internal grip surfaces',
      criteria3: 'Magnetic grip surfaces',
      criteria4: 'Fabric closure gripper'
    },
    {
      strong: 'No. of variants',
      criteria1: 'No futher variants',
      criteria2: 'One further variant',
      criteria3: 'Two further variants',
      criteria4: 'More than two variants'
    },
    {
      strong: 'Stable positions',
      criteria1: 'Up until four',
      criteria2: 'More than four',
      criteria3: 'Stable and unstable',
      criteria4: 'Only stable'
    },
    {
      strong: 'Symmetry',
      criteria1: 'Rotationally symmetrical',
      criteria2: 'Areal symmetry',
      criteria3: 'Markedly asymmetrical',
      criteria4: 'Seemingly symmetrical'
    },
    {
      strong: 'Hook up or adhesion',
      criteria1: 'None',
      criteria2: 'Sticking or jamming possible',
      criteria3: 'Component penetration possible',
      criteria4: 'Seemingly symmetry'
    },
    {
      strong: 'Faulty joining',
      criteria1: 'Never',
      criteria2: 'Occasionaly',
      criteria3: 'Rarely',
      criteria4: 'Often'
    },
    {
      strong: 'Accessibility',
      criteria1: 'Very good',
      criteria2: 'Good',
      criteria3: 'Satisfactory',
      criteria4: 'Sufficient'
    },
    {
      strong: 'Orienation',
      criteria1: 'No axis',
      criteria2: 'One axis',
      criteria3: 'Two axis',
      criteria4: 'Three axis'
    },
    {
      strong: 'Joining mov.',
      criteria1: 'Linear',
      criteria2: 'Rotation',
      criteria3: 'Linear-rotatory',
      criteria4: 'Path movement'
    },
    {
      strong: 'Joining force',
      criteria1: 'None',
      criteria2: 'Low',
      criteria3: 'Medium',
      criteria4: 'High'
    },
    {
      strong: 'Joining aid',
      criteria1: 'Joining and basic component',
      criteria2: 'Joining component',
      criteria3: 'Basic component',
      criteria4: 'None'
    },
    {
      strong: 'Number of basic components',
      criteria1: 'One basic component',
      criteria2: 'Two basic components',
      criteria3: 'Three basic components',
      criteria4: 'More than three basic components'
    },
    {
      strong: 'Joining security',
      criteria1: 'Secured in all directions',
      criteria2: 'Gravity and form fit',
      criteria3: 'Gravity and form fit',
      criteria4: 'Additional securing necessary'
    },
    {
      strong: 'Special operations',
      criteria1: 'None',
      criteria2: 'One',
      criteria3: 'Two',
      criteria4: 'More than two'
    }
  ];

  constructor(private http:HttpClient) { }

  ngOnInit() {
  }

}
