import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(private router: Router, private oService: OntimizeService) { }

  ngOnInit() {

    const visited = localStorage.getItem('visited');

    this.oService.configureService(this.oService.getDefaultServiceConfiguration("users"));
    this.oService.query({}, ["ROL_ID", "ROL_NAME"],"myRole").subscribe(ress => {
      
      if(ress['data'][0]['ROL_NAME'] == "admin" && !visited){
        localStorage.setItem('visited', 'true');
        this.router.navigate(['main','admin','devices-without-users'],{});
      } else if (ress['data'][0]['ROL_NAME'] == "transporter" && !visited){
        localStorage.setItem('visited', 'true');
        this.router.navigate(['main','transporters'],{});
      }else if (ress['data'][0]['ROL_NAME'] == "usermicros" && !visited){
        localStorage.setItem('visited', 'true');
        this.router.navigate(['main','usermicros'],{});
      }
    });
  }
}