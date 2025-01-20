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
    this.oService.query({}, ["ROL_ID", "ROL_NAME"],"myRole").subscribe(ress => {console.log(ress)
      /*
      if(ress[0].get("ROL_NAME") == "admin"){
        console.log(ress[0].get("ROL_NAME"));
      }
        */
    })
    
  }

}
