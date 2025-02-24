import { Component, ViewChild, TemplateRef } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-transfers-boolean-render',
  templateUrl: './containers-transfers-boolean-render.component.html',
  styleUrls: ['./containers-transfers-boolean-render.component.css']
})
export class ContainersTransfersBooleanRenderComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

}
