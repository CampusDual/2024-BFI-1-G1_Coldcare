import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-devices-column-renderer',
  templateUrl: './devices-column-renderer.component.html',
  styleUrls: ['./devices-column-renderer.component.css']
})
export class DevicesColumnRendererComponent extends OBaseTableCellRenderer implements OnInit {
  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;
  obj = {
    "color": "black"
  }
  isTimeElapsedGreaterThan15Min(cellVal):boolean{
    const time: number = 15*60*1000;
    const currentDate:  number = new Date ().getTime();
    const dif: number = currentDate - cellVal;
    return dif>time;
  }
  getCellData(value: any) {
    console.log("llamada a getcellData")
    if(this.isTimeElapsedGreaterThan15Min(value)){
      this.obj["color"] = "red";
    }else{
      this.obj["color"] = "black";
    }

    return value;
  }
}
