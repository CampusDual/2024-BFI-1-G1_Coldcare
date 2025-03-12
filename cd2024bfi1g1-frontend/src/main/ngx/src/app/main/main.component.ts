import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';
import { AlertaService } from '../services/alerta.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit, OnDestroy {

  constructor(private router: Router, private oService: OntimizeService, private alertaService: AlertaService, private titleService: Title) { }
  alertsTimer;
  ngOnInit() {

    const visited = localStorage.getItem('visited');


    this.alertsTimer = setInterval(() => {
      this.alertaService.obtenerAlertas();
    }, 1000);

    this.oService.configureService(this.oService.getDefaultServiceConfiguration("users"));
    this.oService.query({}, ["ROL_ID", "ROL_NAME"], "myRole").subscribe(ress => {

      if (ress['data'][0]['ROL_NAME'] == "admin" && !visited) {
        localStorage.setItem('visited', 'true');
        this.router.navigate(['main', 'admin', 'devices-without-users'], {});
      } else if (ress['data'][0]['ROL_NAME'] == "transporter" && !visited) {
        localStorage.setItem('visited', 'true');
        this.router.navigate(['main', 'transporters'], {});
      } else if (ress['data'][0]['ROL_NAME'] == "usermicros" && !visited) {
        localStorage.setItem('visited', 'true');
        this.router.navigate(['main', 'usermicros'], {});
      } else if (ress['data'][0]['ROL_NAME'] == "user" && !visited) {
        localStorage.setItem('visited', 'true');
        this.router.navigate(['main', 'lots'], {});
      }
    });
  }

  ngOnDestroy(): void {
    clearInterval(this.alertsTimer)
    this.titleService.setTitle(`ColdCare`)
  }
}