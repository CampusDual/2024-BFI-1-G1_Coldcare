import { Component, ViewChild, TemplateRef } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-containers-home-boolean-render',
  templateUrl: './containers-home-boolean-render.component.html',
  styleUrls: ['./containers-home-boolean-render.component.css']
})
export class ContainersHomeBooleanRenderComponent extends OBaseTableCellRenderer {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;


}
