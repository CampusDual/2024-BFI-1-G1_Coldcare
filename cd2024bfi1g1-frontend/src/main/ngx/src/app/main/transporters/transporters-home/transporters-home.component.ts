import { Component } from '@angular/core';

@Component({
  selector: 'app-transporters-home',
  templateUrl: './transporters-home.component.html',
  styleUrls: ['./transporters-home.component.css']
})
export class TransportersHomeComponent {

  public listStyle = {
    display: 'block',
    height: (innerHeight - 112) + 'px'
  };
}
  