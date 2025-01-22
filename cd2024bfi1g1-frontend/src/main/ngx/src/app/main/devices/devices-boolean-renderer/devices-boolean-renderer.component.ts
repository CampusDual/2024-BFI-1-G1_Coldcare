import { Component, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-devices-boolean-renderer',
  templateUrl: './devices-boolean-renderer.component.html',
  styleUrls: ['./devices-boolean-renderer.component.css']
})
export class DevicesBooleanRendererComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

}
