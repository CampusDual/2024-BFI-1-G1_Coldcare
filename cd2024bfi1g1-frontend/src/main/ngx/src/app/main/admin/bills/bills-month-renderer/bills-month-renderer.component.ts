import { Component, Injector, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer, OTranslateService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-bills-month-renderer',
  templateUrl: './bills-month-renderer.component.html',
  styleUrls: ['./bills-month-renderer.component.css']
})
export class BillsMonthRendererComponent extends OBaseTableCellRenderer {
  @ViewChild('templateref', { read: TemplateRef, static: false }) public templateref: TemplateRef<any>;

  translator: OTranslateService;

  constructor(protected injector: Injector) {
    super(injector); // Pasamos el injector a la clase padre
    this.translator = this.injector.get(OTranslateService); // Obtenemos el servicio de traducción
  }

  getCellData(value: any): string {
    if (!this.translator) {
      return 'ERROR';
    }

    switch (value) {
      case 1:
        return this.translator.get('JANUARY');
      case 2:
        return this.translator.get('FEBRUARY');
      case 3:
        return this.translator.get('MARCH');
      case 4:
        return this.translator.get('APRIL');
      case 5:
        return this.translator.get('MAY');
      case 6:
        return this.translator.get('JUNE');
      case 7:
        return this.translator.get('JULY');
      case 8:
        return this.translator.get('AUGUST');
      case 9:
        return this.translator.get('SEPTEMBER');
      case 10:
        return this.translator.get('OCTOBER');
      case 11:
        return this.translator.get('NOVEMBER');
      case 12:
        return this.translator.get('DECEMBER');
      default:
        return 'ERROR';
    }
  }
}
