import { Component, ViewChild, OnDestroy, OnInit } from '@angular/core';
import { OButtonComponent, OTextInputComponent, OFormComponent, OTranslateService } from 'ontimize-web-ngx';
import { Subscription } from 'rxjs';
import { TRP_STATUS_END, TRP_STATUS_INIT } from 'src/app/shared/constants';

@Component({
  selector: 'app-transporters-details',
  templateUrl: './transporters-details.component.html',
  styleUrls: ['./transporters-details.component.css']
})
export class TransportersDetailsComponent implements OnInit, OnDestroy {

  @ViewChild('init', { static: false }) init!: OButtonComponent;
  @ViewChild('end', { static: false }) end!: OButtonComponent;
  @ViewChild('stateInput', { static: false }) stateInput!: OTextInputComponent;
  @ViewChild('form', { static: false }) form!: OFormComponent;

  private currentState: string = '';
  private languageChangeSubscription!: Subscription;

  constructor(private translateService: OTranslateService) {}

  ngOnInit() {
    this.languageChangeSubscription = this.translateService.onLanguageChanged.subscribe(() => {
      this.updateTranslatedState();
    });
  }

  setStatusInit() {
    this.currentState = TRP_STATUS_INIT;
    this.updateTransportState();
  }

  setStatusEnd() {
    this.currentState = TRP_STATUS_END;
    this.updateTransportState();
  }

  private updateTransportState() {
    if (this.form) {
      const translatedState = this.translateService.get(this.currentState);
      this.stateInput.setValue(translatedState);
      this.form.setFieldValue('TRP_STATE', translatedState);
    }
  }

  private updateTranslatedState() {
    if (this.currentState) {
      this.updateTransportState();
    }
  }

  ngOnDestroy() {
    if (this.languageChangeSubscription) {
      this.languageChangeSubscription.unsubscribe();
    }
  }

}
