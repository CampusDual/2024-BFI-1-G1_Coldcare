import { Component, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { AppearanceService, OntimizeMatIconRegistry } from 'ontimize-web-ngx';
import { GeolocationService } from './services/geolocation.service';
import { FirebaseService } from './firebase.service';
import { DialogService } from 'ontimize-web-ngx';

@Component({
  selector: 'o-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
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

    // Activar notificaciones y mostrar en un Snackbar
    this.firebaseService.activarNotificaciones((body: string) => {
      this.showAlertDialog(body);
    });

  }


  constructor(
    private router: Router,
    protected appearanceService: AppearanceService,
    protected injector: Injector,
    private geolocationService: GeolocationService,
    private firebaseService: FirebaseService,
    private dialogService: DialogService // Inyectamos DialogService
  ) {
    this.ontimizeMatIconRegistry = this.injector.get(OntimizeMatIconRegistry);
    if (window['__ontimize'] !== undefined && window['__ontimize']['redirect'] !== undefined) {
      let redirectTo = window['__ontimize']['redirect'];
      window['__ontimize']['redirect'] = undefined;
      this.router.navigate([redirectTo]);
    }
  }


  // Método para mostrar el snackBar
  showAlertDialog(message: string) {
    this.dialogService.alert('ALERT_TITLE', 'ALERT_GENERATED');
  }

}
