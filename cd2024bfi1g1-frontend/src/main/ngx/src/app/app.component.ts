import { Component, Injector, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { AppearanceService, OntimizeMatIconRegistry, OntimizeService } from 'ontimize-web-ngx';
import { GeolocationService } from './services/geolocation.service';
import { AlertaService } from './services/alerta.service';


@Component({
  selector: 'o-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(
    private router: Router,
    protected appearanceService: AppearanceService,
    protected injector: Injector,
    private geolocationService: GeolocationService,
    private alertaService: AlertaService,
    private oService: OntimizeService
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

    this.oService.configureService(this.oService.getDefaultServiceConfiguration("users"));
    this.oService.query({}, ["ROL_ID", "ROL_NAME"], "myRole").subscribe(ress => {
      if (ress['data'][0]['ROL_NAME'] == "user") {
        setInterval(() => {
          this.alertaService.obtenerAlertas();
        }, 1000);
      }
    });
  }
}
