import { Component, TemplateRef, ViewChild } from '@angular/core';
import { OBaseTableCellRenderer } from 'ontimize-web-ngx';
import { TRP_WAIT_USER } from 'src/app/shared/constants';
import { OTranslateModule } from 'ontimize-web-ngx';


@Component({
  selector: 'app-transport-column-renderer',
  templateUrl: './transport-column-renderer.component.html',
  styleUrls: ['./transport-column-renderer.component.css']
})
export class TransportColumnRendererComponent extends OBaseTableCellRenderer {
  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

  trpWaitUser: string = TRP_WAIT_USER;
}