import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OntimizeService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(private router: Router, private oService: OntimizeService) {}

  ngOnInit() {
    
    this.oService.configureService(this.oService.getDefaultServiceConfiguration("users"));
    this.oService.query({}, ["ROL_ID", "ROL_NAME"],"myRole").subscribe(ress => {
      
      if(ress['data'][0]['ROL_NAME'] == "admin"){
        this.router.navigate(['main','admin','devices-without-users'],{});
      } else if (ress['data'][0]['ROL_NAME'] == "transporters"){
        this.router.navigate(['main','transporters'],{});
      }
    }) 
  }
}
