import { Component, ViewChild, TemplateRef } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';

@Component({
  selector: 'app-lots-boolean-render',
  templateUrl: './lots-boolean-render.component.html',
  styleUrls: ['./lots-boolean-render.component.css']
})
export class LotsBooleanRenderComponent extends OBaseTableCellRenderer {
  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;
}


