import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StateHolderService {

  private subprocessCache:Array<any> = null;

  constructor() { }

  public storeData(data:Array<any>) {
    this.subprocessCache = data;
  }

  public read() {
    return this.subprocessCache;
  }

}
