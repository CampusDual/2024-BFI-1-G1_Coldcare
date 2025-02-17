import { Component, TemplateRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-bills-month-renderer',
  templateUrl: './bills-month-renderer.component.html',
  styleUrls: ['./bills-month-renderer.component.css']
})
export class BillsMonthRendererComponent {
  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;

  getCellData(value: any) {
    switch (value) {
      case 1:
        return "Enero";
      case 2:
        return "Febrero";
      case 3:
        return "Marzo";
      case 4:
        return "Abril";
      case 5:
        return "Mayo";
      case 6:
        return "Junio";
      case 7:
        return "Julio";
      case 8:
        return "Agosto";
      case 9:
        return "Septiembre";
      case 10:
        return "Octubre";
      case 11:
        return "Noviembre";
      case 12:
        return "Diciembre";
      default:
        return "Valor inválido";
    }
  }

}
