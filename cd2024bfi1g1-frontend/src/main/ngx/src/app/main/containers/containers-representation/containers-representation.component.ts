import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { OMapComponent } from 'ontimize-web-ngx-map';

@Component({
  selector: 'app-containers-representation',
  templateUrl: './containers-representation.component.html',
  styleUrls: ['./containers-representation.component.css']
})
export class ContainersRepresentationComponent{
  _eventsArray: Array<any> = [];

  @ViewChild('oMap')
  protected oMap: OMapComponent;

  constructor() { }


  ngAfterViewInit() {
    this.getDrawLayer();
  }

  getDrawLayer() {
    console.log(this.oMap.getMapService().getDrawLayer());
  }

  addDrawEvent(arg) {
    this._eventsArray.push(arg);
  }

  get eventsArray(): Array<any> {
    return this._eventsArray;
  }

  set eventsArray(arg: Array<any>) {
    this._eventsArray = arg;
  }
}

  
