import { Component, Injector, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AppearanceService, OntimizeMatIconRegistry } from 'ontimize-web-ngx';
import { GeolocationService } from './services/geolocation.service';
import { AlertaService } from './services/alerta.service';
import { Title } from '@angular/platform-browser';
import { OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'o-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  altIds: number[] = [1, 2];

  constructor(private router: Router, protected appearanceService: AppearanceService, protected injector: Injector, private geolocationService: GeolocationService, private alertaService: AlertaService, private titleService: Title, private ontimizeService: OntimizeService, private cd: ChangeDetectorRef
  ) {
    this.ontimizeMatIconRegistry = this.injector.get(OntimizeMatIconRegistry);
    if (window['__ontimize'] !== undefined && window['__ontimize']['redirect'] !== undefined) {
      let redirectTo = window['__ontimize']['redirect'];
      window['__ontimize']['redirect'] = undefined;
      this.router.navigate([redirectTo]);
    }
  }

  ontimizeMatIconRegistry: OntimizeMatIconRegistry;


  ngOnInit() {
    if (this.ontimizeMatIconRegistry.addOntimizeSvgIcon) {
      this.ontimizeMatIconRegistry.addOntimizeSvgIcon('containerIcon', 'assets/icons/container.svg');
    }

    if (this.geolocationService.checkTrackingStatus()) {
      console.log('Tracking activo al recargar. Reiniciando...');
      const trpId = Number(localStorage.getItem('TRP_ID'));
      this.geolocationService.continueTracking(trpId);
    }
    this.obtenerAlertas();

    setInterval(() => {
      this.obtenerAlertas();
    }, 50000);
  }


  obtenerAlertas(): void {

    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration('alerts'));
    this.ontimizeService.query(undefined, ['ALT_ID', 'ALT_STATE'], 'alerts').subscribe({
      next(value) {

      },
      error(err) {
        console.log(err)
      },
      complete() {
        this.cd.detectChanges()
      }
    });

    console.log("valores : " + this.cd.checkNoChanges)
    console.log("valores : " + this.cd.detach)
    console.log("valores : " + this.cd.detectChanges)
    console.log("valores : " + this.cd.markForCheck)
    console.log("valores : " + this.cd.reattach)

    this.alertaService.obtenerAlertasPendientes(this.altIds).subscribe((alerts) => {

      console.log('Alertas pendienteseeeeeeee:', alerts);
      const alertasPendientes = alerts.filter(alerta => alerta.ESTADO === 'pendiente');

      this.altIds = alertasPendientes.map(alerta => alerta.ID);

      const cantidadAlertas = alertasPendientes.length;
      if (cantidadAlertas > 0) {
        this.titleService.setTitle(`Tienes ${cantidadAlertas} alertas pendientes`);
      } else {
        this.titleService.setTitle('No tienes alertas pendientes');
      }
      console.log('Alertas pendientes:', this.altIds);
    });
  }
}