import { Component, OnInit } from '@angular/core';
import { environment } from './../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  ngOnInit(): void {
    var win:any = window;
    win.handleFloatWithMinimum = function(floatHolder, minimum) {
      let x:string = floatHolder.value.replace(
        /(\d+)\.(\d+)/,
        function limitFractionalPartTo5(match, integerPart, fracionalPart) {
          return [integerPart,fracionalPart.substring(0,5)].join('.')
        }
      );
      floatHolder.value < minimum? floatHolder.value=minimum : floatHolder.value=x;
    }
  }
  title = 'LoA-tool';
  baseImg = environment.production == true ?
    '/' + environment.hostUrl + '/assets/':
    '/assets/';

}