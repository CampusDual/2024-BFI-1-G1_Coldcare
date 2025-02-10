import { Component, ViewChild, NgZone } from '@angular/core';
import { OFormComponent, OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-lots-new',
  templateUrl: './lots-new.component.html',
  styleUrls: ['./lots-new.component.css']
})
export class LotsNewComponent {

  @ViewChild('lotsFormInsert', { static: false }) lotsFormInsert!: OFormComponent;

  constructor(private ontimizeService: OntimizeService, private ngZone: NgZone) {}

  onProductChange(event: any) {
    if (!event || !event.detail?.newValue) return;

    const selectedProduct = event.detail.newValue;

    // Ejecutar cambios fuera de la detección de cambios de Angular
    this.ngZone.run(() => {
      this.lotsFormInsert.setFieldValue('MIN_TEMP', selectedProduct.PRO_MIN_TEMP ?? '');
      this.lotsFormInsert.setFieldValue('MAX_TEMP', selectedProduct.PRO_MAX_TEMP ?? '');
    });
  }
}
