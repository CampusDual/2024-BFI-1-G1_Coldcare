import { Component, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { AppearanceService, OntimizeMatIconRegistry } from 'ontimize-web-ngx';
import { GeolocationService } from './services/geolocation.service';
import { AlertaService } from './services/alerta.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'o-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  altIds: number[] = [1, 2];

  constructor(private router: Router, protected appearanceService: AppearanceService, protected injector: Injector, private geolocationService: GeolocationService, private alertaService: AlertaService, private titleService: Title
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
    }, 5000);
  }


  obtenerAlertas(): void {

    this.alertaService.obtenerAlertasPendientes([]).subscribe((alerts) => {
      // Filtrar las alertas pendientes y extraer los IDs
      console.log('Alertas pendienteseeeeeeee:', alerts);
      const alertasPendientes = alerts.filter(alerta => alerta.ESTADO === 'pendiente');

      // Actualizar altIds con los IDs de las alertas pendientes
      this.altIds = alertasPendientes.map(alerta => alerta.ID); // Suponiendo que cada alerta tiene un campo ID

      // Cambiar el título según la cantidad de alertas pendientes
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


