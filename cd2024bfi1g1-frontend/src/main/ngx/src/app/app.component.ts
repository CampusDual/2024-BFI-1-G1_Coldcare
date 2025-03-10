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

  constructor(private router: Router, protected appearanceService: AppearanceService, protected injector: Injector, private geolocationService: GeolocationService,
  ) {
    this.ontimizeMatIconRegistry = this.injector.get(OntimizeMatIconRegistry);
    if (window['__ontimize'] !== undefined && window['__ontimize']['redirect'] !== undefined) {
      let redirectTo = window['__ontimize']['redirect'];
      window['__ontimize']['redirect'] = undefined;
      this.router.navigate([redirectTo]);
    }
  }

  ontimizeMatIconRegistry: OntimizeMatIconRegistry;
  alertaService: AlertaService
  titleService: Title

  ngOnInit() {
    if (this.ontimizeMatIconRegistry.addOntimizeSvgIcon) {
      this.ontimizeMatIconRegistry.addOntimizeSvgIcon('containerIcon', 'assets/icons/container.svg');
    }

    if (this.geolocationService.checkTrackingStatus()) {
      console.log('Tracking activo al recargar. Reiniciando...');
      const trpId = Number(localStorage.getItem('TRP_ID'));
      this.geolocationService.continueTracking(trpId);
    }



    setInterval(() => {
      this.obtenerAlertas();
    }, 5000);
  }

  altIds = [1, 2, 3];
  obtenerAlertas(): void {
    this.alertaService.obtenerAlertasPendientes().subscribe((alertas) => {
      const cantidadAlertas = alertas.filter(alerta => alerta.ESTADO === 'pendiente').length;

      // Cambia el título de la pestaña para reflejar el número de alertas
      if (cantidadAlertas > 0) {
        this.titleService.setTitle(`Tienes ${cantidadAlertas} alertas pendientes`);
      } else {
        this.titleService.setTitle('No tienes alertas pendientes');
      }
    });
  }
}


