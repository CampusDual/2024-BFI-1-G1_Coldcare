import { Component, Injector, Input, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-transports-column-renderer',
  templateUrl: './transports-column-renderer.component.html',
  styleUrls: ['./transports-column-renderer.component.css']
})
export class TransportsColumnRendererComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;
  @Input() statusId!: number;

  constructor(protected injector: Injector) {
    super(injector);
  }

}
