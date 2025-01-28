import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { OMapComponent } from 'ontimize-web-ngx-map';

@Component({
  selector: 'app-geo-home',
  templateUrl: './geo-home.component.html',
  styleUrls: ['./geo-home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  ubicacion: string = 'Esperando ubicación...';
  private ubicacionInterval: any;

  @ViewChild('oMap') oMap: OMapComponent;

  constructor() { }

  ngOnInit(): void {
    this.obtenerUbicacion();

    this.ubicacionInterval = setInterval(() => {
      this.obtenerUbicacion();
    }, 5000);  // 5 segundos
  }

  ngOnDestroy(): void {
    // Limpiar el intervalo cuando el componente se destruya
    if (this.ubicacionInterval) {
      clearInterval(this.ubicacionInterval);
    }
  }

  obtenerUbicacion(): void {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const lat = position.coords.latitude;
          const lon = position.coords.longitude;

          this.ubicacion = `Latitud: ${lat} <br> Longitud: ${lon}`;
          console.log(this.ubicacion);
        },
        (error) => {
          this.ubicacion = `Error al obtener la ubicación: ${error.message}`;
        }
      );
    } else {
      this.ubicacion = "La geolocalización no está soportada en este navegador.";
    }
  }
}
