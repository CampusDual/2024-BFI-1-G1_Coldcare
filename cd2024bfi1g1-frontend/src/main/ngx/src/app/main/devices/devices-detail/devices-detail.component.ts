import { Component, ViewChild, OnInit } from '@angular/core';

@Component({
  selector: 'app-devices-detail',
  templateUrl: './devices-detail.component.html',
  styleUrls: ['./devices-detail.component.css']
})
export class DevicesDetailComponent {

  constructor() {

  }

//   ngOnInit(): void {
//     // Verificar si DEV_NAME está vacío al inicio
//     if (!this.DEV_NAME && this.DEV_MAC) {
//       // Si DEV_NAME no está definido, asignar el valor de DEV_MAC
//       this.DEV_NAME = this.DEV_MAC;
//     }
//   }

//   // Método que se puede llamar cuando el valor de DEV_MAC cambie (si es necesario)
//   onDevMacChange(newMac: string): void {
//     if (!this.DEV_NAME) {
//       this.DEV_NAME = newMac;
//     }
//     this.DEV_MAC = newMac;
//   }
}