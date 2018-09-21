import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-process-list',
  templateUrl: './process-list.component.html',
  styleUrls: ['./process-list.component.css']
})
export class ProcessListComponent implements OnInit {

  procesi = [
    { name: 'proces1',
      sublevels: '3',
      sub1: '-',
      sub2: '-',
      sub3: '-',
      actions: 'add',
      route: 'add-process'
    },
    { name: 'proces2',
      sublevels: '2',
      sub1: '-',
      sub2: '-',
      sub3: '-',
      actions: 'analysis',
      route: 'main-analysis'
    },
    { name: 'proces3',
      sublevels: '3',
      sub1: '-',
      sub2: '-',
      sub3: '-',
      actions: 'add',
      route: 'add-process'
    },
    { name: 'proces4',
      sublevels: '2',
      sub1: '-',
      sub2: '-',
      sub3: '-',
      actions: 'analysis',
      route: 'main-analysis'
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
