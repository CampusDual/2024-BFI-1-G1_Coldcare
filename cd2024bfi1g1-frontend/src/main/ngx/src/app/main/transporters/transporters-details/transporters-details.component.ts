import { Component, Injector, ViewChild, AfterViewInit } from '@angular/core';
import { OFormComponent, OntimizeService, OButtonComponent, OComboComponent } from 'ontimize-web-ngx';
import { TRP_STATUS_END, TRP_STATUS_INIT, TRP_STATUS_PENDING } from 'src/app/shared/constants';

@Component({
  selector: 'app-transporters-details',
  templateUrl: './transporters-details.component.html'
})
export class TransportersDetailsComponent implements AfterViewInit {

  ubicacion: string = 'Esperando ubicación...';
  private ubicacionInterval: any;

  @ViewChild('form', { static: true }) form!: OFormComponent;
  @ViewChild('combo', { static: false }) combo!: OComboComponent;
  @ViewChild('init', { static: false }) initButton!: OButtonComponent;
  @ViewChild('end', { static: false }) endButton!: OButtonComponent;

  protected service: OntimizeService;

  constructor(protected injector: Injector) {
    this.service = this.injector.get(OntimizeService);
  }

  ngAfterViewInit() {
    this.updateButtonState();
    this.form.onDataLoaded.subscribe(() => this.updateButtonState());
  }

  updateButtonState() {
    const status = this.form.getFieldValue('TST_ID');

    if (status === TRP_STATUS_PENDING) {
      this.initButton.enabled = true;
      this.endButton.enabled = false;
    } else if (status === TRP_STATUS_INIT) {
      this.initButton.enabled = false;
      this.endButton.enabled = true;
    } else if (status === TRP_STATUS_END) {
      this.initButton.enabled = false;
      this.endButton.enabled = false;
    }
  }

  setStatusInit() {
    this.updateStatus(TRP_STATUS_INIT);
    this.insertGeolocation()

    this.ubicacionInterval = setInterval(() => {
      this.insertGeolocation()
    }, 5000);
  }

  setStatusEnd() {
    this.updateStatus(TRP_STATUS_END);
    console.log("Hola");

    if (this.ubicacionInterval) {
      console.log("adios");

      clearInterval(this.ubicacionInterval);
    }
  }

  private updateStatus(status: number) {
    const trpId = this.form.getUrlParam('TRP_ID');
    const data = { TST_ID: status };
    const filter = { TRP_ID: Number(trpId) };

    const conf = this.service.getDefaultServiceConfiguration('transports');
    this.service.configureService(conf);

    this.service.update(filter, data, 'transports').subscribe(response => {
      if (response.code === 0) {
        this.form.setFieldValue('TST_ID', status);
        this.combo.refresh();
        this.updateButtonState();
      } else {
        alert('Impossible to update data!');
      }
    });
  }

  private insertGeolocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const lat = position.coords.latitude;
          const lon = position.coords.longitude;

          const trpId = this.form.getUrlParam('TRP_ID');
          const data = { TRP_ID: Number(trpId), TC_LATITUDE: lat, TC_LONGITUDE: lon };

          const conf = this.service.getDefaultServiceConfiguration('transportsCoordinates');
          this.service.configureService(conf);

          this.service.insert(data, 'transportsCoordinates').subscribe(response => {
            if (response.code !== 0) {
              console.error('Error inserting geolocation:', response);
            }
          });
        },
        (error) => {
          console.error('Error getting location:', error.message);
        }
      );
    } else {
      console.error('Geolocation is not supported by this browser.');
    }
  }
}
