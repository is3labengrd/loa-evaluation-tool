import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-process-list',
  templateUrl: './process-list.component.html',
  styleUrls: ['./process-list.component.css']
})
export class ProcessListComponent implements OnInit {

  procesi = [
    { name: 'Rear Light Assembly',
      sublevels: '2',
      sub1: '-',
      sub2: '-',
      sub3: '-',
      actions: 'Add',
      route: 'add-process'
    },
    { name: 'Rear Light Assembly',
      sublevels: '2',
      sub1: 'Checking',
      sub2: 'Final inspection',
      sub3: '-',
      actions: 'Analysis',
      route: 'main-analysis'
    },
    { name: 'Rear Light Assembly',
      sublevels: '2',
      sub1: 'Handling',
      sub2: 'Rear light adjustment',
      sub3: '-',
      actions: 'Analysis',
      route: 'main-analysis'
    },
    { name: 'Rear Light Assembly',
      sublevels: '2',
      sub1: 'Handling',
      sub2: 'Rivet nut fastening',
      sub3: '-',
      actions: 'Analysis',
      route: 'main-analysis'
    },
    { name: 'Rear Light Assembly',
       sublevels: '2',
       sub1: 'Joining',
       sub2: 'Add cover plates',
       sub3: '-',
       actions: 'Analysis',
       route: 'main-analysis'
      },
      { name: 'Rear Light Assembly',
       sublevels: '2',
       sub1: 'Joining',
       sub2: 'Install brake light module',
       sub3: '-',
       actions: 'Analysis',
       route: 'main-analysis'
      },
     { name: 'Rear Light Assembly',
       sublevels: '2',
       sub1: 'Joining',
       sub2: 'Mount screw',
       sub3: '-',
       actions: 'Analysis',
       route: 'main-analysis'
      },
      { name: 'Rear Light Assembly',
       sublevels: '2',
       sub1: 'Joining',
       sub2: 'Screw positioning',
       sub3: '-',
       actions: 'Analysis',
       route: 'main-analysis'
      },
      { name: 'Rear Light Assembly',
       sublevels: '2',
       sub1: 'Joining',
       sub2: 'Screw rear light',
       sub3: '-',
       actions: 'Analysis',
       route: 'main-analysis'
      },
      { name: 'Rear Light Assembly',
        sublevels: '2',
        sub1: 'Picking',
        sub2: 'Parts grasping',
        sub3: '-',
        actions: 'Analysis',
        route: 'main-analysis'
       },
       { name: 'Rear Light Assembly',
         sublevels: '2',
         sub1: 'Preparation',
         sub2: 'Initial trolley movement',
         sub3: '-',
         actions: 'Analysis',
         route: 'main-analysis'
        },
       { name: 'Rear Light Assembly',
         sublevels: '2',
         sub1: 'Preparation',
         sub2: 'Operation Start',
         sub3: '-',
         actions: 'Analysis',
         route: 'main-analysis'
       }
       ];

  constructor() { }

  ngOnInit() {
  }

}
