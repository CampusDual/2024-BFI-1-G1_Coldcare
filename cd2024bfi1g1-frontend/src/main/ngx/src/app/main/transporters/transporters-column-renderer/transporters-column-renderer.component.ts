import { Component, Injector, Input, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-transporters-column-renderer',
  templateUrl: './transporters-column-renderer.component.html',
  styleUrls: ['./transporters-column-renderer.component.css']
})
export class TransportersColumnRendererComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;
  @Input() statusId!: number;

  constructor(protected injector: Injector) {
    super(injector);
  }
  
}
