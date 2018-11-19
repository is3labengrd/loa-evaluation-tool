import { Component } from '@angular/core';
import { environment } from './../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'LoA-tool';
  baseImg = environment.production == true ?
    '/' + environment.hostUrl + '/assets/':
    '/assets/';


}
