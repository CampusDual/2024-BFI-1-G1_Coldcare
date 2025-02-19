import { Component, Injector, ViewChild } from '@angular/core';
import { OFormComponent, OntimizeService } from 'ontimize-web-ngx';
import { TRP_STATUS_END, TRP_STATUS_INIT } from 'src/app/shared/constants';
import { TransportService } from 'src/app/shared/services/transport.service';

@Component({
  selector: 'app-transporters-details',
  templateUrl: './transporters-details.component.html'
})
export class TransportersDetailsComponent {

  @ViewChild('form', { static: true }) form!: OFormComponent;

  constructor(private transportService: TransportService, protected injector: Injector) {
    this.service = this.injector.get(OntimizeService);
  }
  protected service: OntimizeService;


  ngOnInit() {
    this.configureService();
  }

  protected configureService() {
    // Configure the service using the configuration defined in the `app.services.config.ts` file
    const conf = this.service.getDefaultServiceConfiguration('transports');
    this.service.configureService(conf);
  }


  setStatusInit() {
    const trpId = this.form.getUrlParam('TRP_ID');
    const data = {
      TRP_STATE_ID: TRP_STATUS_INIT
    };
    const filter = {
      TRP_ID: Number(trpId)
    };

    this.configureService();
    this.service.update(filter, data, 'transports').subscribe(response => {
      if (response.code === 0) {



      } else {
        alert('Impossible to query data!');
      }
    });

  }

  setStatusEnd() {
    const trpId = this.form.getUrlParam('TRP_ID');
    const data = {
      TRP_STATE_ID: TRP_STATUS_END
    };
    const filter = {
      TRP_ID: Number(trpId)
    };
    this.service.update(filter, data, 'transports').subscribe(response => {
      if (response.code === 0) {



      } else {
        alert('Impossible to query data!');
      }
    });

  }
}
