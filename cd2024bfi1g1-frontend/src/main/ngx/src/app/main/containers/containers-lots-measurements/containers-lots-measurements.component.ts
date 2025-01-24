import { Component} from '@angular/core';

@Component({
  selector: 'app-containers-lots-measurements',
  templateUrl: './containers-lots-measurements.component.html',
  styleUrls: ['./containers-lots-measurements.component.css']
})
export class ContainersLotsMeasurementsComponent{

  public rowClass=(rowData: any, rowIndex: number) : string | string[] =>{
    console.log({rowData});
    return "error-row";
    
  }

}
