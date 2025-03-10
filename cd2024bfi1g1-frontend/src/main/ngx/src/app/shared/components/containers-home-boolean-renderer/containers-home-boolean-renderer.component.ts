import { Component, ViewChild, TemplateRef } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-home-boolean-renderer',
  templateUrl: './containers-home-boolean-renderer.component.html',
  styleUrls: ['./containers-home-boolean-renderer.component.css']
})
export class ContainersHomeBooleanRendererComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

}
