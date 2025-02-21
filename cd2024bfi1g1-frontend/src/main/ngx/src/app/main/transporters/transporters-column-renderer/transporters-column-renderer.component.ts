import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { TRP_STATUS_END, TRP_STATUS_INIT, TRP_STATUS_PENDING } from 'src/app/shared/constants';

@Component({
  selector: 'app-transporters-column-renderer',
  templateUrl: './transporters-column-renderer.component.html',
  styleUrls: ['./transporters-column-renderer.component.css']
})
export class TransportersColumnRendererComponent implements OnInit {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;
  @Input() statusId!: number;

  ngOnInit() {
    console.log('statusId recibido:', this.statusId);
  }

  getStatusIcon(): string {
    console.log('statusId en getStatusIcon:', this.statusId);
    switch (this.statusId) {
      case TRP_STATUS_INIT:
        return 'play_circle';
      case TRP_STATUS_END:
        return 'check_circle';
      case TRP_STATUS_PENDING:
        return 'hourglass_empty';
      default:
        return 'help_outline';
    }
  }
}
