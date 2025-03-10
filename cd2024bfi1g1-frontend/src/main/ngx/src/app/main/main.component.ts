import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';
import { FirebaseService } from '../firebase.service';
import { getMessaging, getToken, Messaging } from 'firebase/messaging';
import { FirebaseApp, initializeApp } from 'firebase/app';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  private app: FirebaseApp;
  private messaging: Messaging;

  constructor(private router: Router, private oService: OntimizeService, private firebaseService: FirebaseService) {
    this.app = initializeApp(environment.firebaseConfig);
    this.messaging = getMessaging(this.app);
  }

  ngOnInit() {
    const visited = localStorage.getItem('visited');

    this.oService.configureService(this.oService.getDefaultServiceConfiguration("users"));
    this.oService.query({}, ["ROL_ID", "ROL_NAME"], "myRole").subscribe(ress => {
      if (ress['data'][0]['ROL_NAME'] == "admin" && !visited) {
        localStorage.setItem('visited', 'true');
        this.router.navigate(['main', 'admin', 'devices-without-users'], {});
      } else if (ress['data'][0]['ROL_NAME'] == "transporter" && !visited) {
        localStorage.setItem('visited', 'true');
        this.router.navigate(['main', 'transporters'], {});
      }

      this.loguearse();
    });
  }

  loguearse() {
    this.firebaseService.loguearse();
    console.log("Logueado");
    this.requestPermission();
  }

  requestPermission = async (): Promise<void> => {
    try {
      const permission: NotificationPermission = await Notification.requestPermission();
      if (permission === "granted") {
        console.log("Permiso concedido");

        setTimeout(() => {
          this.firebaseService.activarMensajes();
        }, 100);


      } else {
        console.log("Permiso denegado");
      }
    } catch (error) {
      console.error("Error al solicitar permisos", error);
    }
  };


}
