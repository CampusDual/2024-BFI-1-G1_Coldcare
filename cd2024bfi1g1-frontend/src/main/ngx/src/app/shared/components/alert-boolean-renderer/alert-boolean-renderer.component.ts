import { Component, TemplateRef, ViewChild } from '@angular/core';

@Component({
  selector: 'alert-boolean-renderer',
  templateUrl: './alert-boolean-renderer.component.html',
  styleUrls: ['./alert-boolean-renderer.component.css']
})
export class AlertBooleanRendererComponent {

  @ViewChild('templateref', { read: TemplateRef }) public templateref: TemplateRef<any>;

  isDateInRange(): boolean {
    return (true);
  }

}
