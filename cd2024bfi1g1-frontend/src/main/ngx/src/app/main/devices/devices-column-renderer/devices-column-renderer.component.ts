import { Component, OnInit } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-devices-column-renderer',
  templateUrl: './devices-column-renderer.component.html',
  styleUrls: ['./devices-column-renderer.component.css']
})
export class DevicesColumnRendererComponent extends OBaseTableCellRenderer implements OnInit {
  
  obj = {
    "background-color": "green"
  }
  isTimeElapsedGreaterThan15Min(cellVal):boolean{
    const time: number = 15*60*1000;
    const currentDate:  number = new Date ().getTime();
    const dif: number = cellVal - currentDate;
    return dif>time;
  }
  getCellData(value: any) {
    console.log("llamada a getcellData")
    if(this.isTimeElapsedGreaterThan15Min(value)){
      this.obj["background-color"] = "red";
    }else{
      this.obj["background-color"] = "green";
    }

    return value;
  }
}
