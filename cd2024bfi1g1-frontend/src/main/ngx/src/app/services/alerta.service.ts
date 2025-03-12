import { Injectable } from '@angular/core';
import { OntimizeService } from 'ontimize-web-ngx';
import { Title } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {

  alertsIds: any[] = [];
  constructor(
    private titleService: Title,
    private ontimizeService: OntimizeService,
    private oService: OntimizeService
  ) {

  }

  obtenerAlertas(): void {

    this.oService.configureService(this.oService.getDefaultServiceConfiguration("users"));
    this.oService.query({}, ["ROL_ID", "ROL_NAME"], "myRole").subscribe(ress => {
      console.log(ress)
      if (ress['data'][0]['ROL_NAME'] == "user") {

        this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration('alerts'));
        this.ontimizeService.query(undefined, ['ALT_ID', 'ALT_STATE'], 'alerts').subscribe({
          next: (value: any) => {
            let alertasPendientes = 0;
            this.alertsIds = value.data;
            this.alertsIds.forEach((alerta) => {

              if (alerta.ALT_STATE == false) {
                alertasPendientes++;
              }

              if (alertasPendientes > 0) {
                this.titleService.setTitle(`(${alertasPendientes}) ColdCare`);
              } else {
                this.titleService.setTitle(`ColdCare`);
              }

            });

          },
          error(err) {
            console.log(err)
          },
          complete() {

          }
        });

      }
    });

  }

}

