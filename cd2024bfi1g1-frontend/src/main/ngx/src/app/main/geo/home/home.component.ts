import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  ubicacion: string = 'Esperando ubicación...';  // Variable para mostrar la ubicación
  private ubicacionInterval: any;  // Variable para almacenar el intervalo

  constructor() { }

  ngOnInit(): void {
    this.obtenerUbicacion();  // Obtener la ubicación inicial al cargar el componente

    // Actualizar la ubicación cada 5 segundos
    this.ubicacionInterval = setInterval(() => {
      this.obtenerUbicacion();
    }, 5000);  // 5000 ms = 5 segundos
  }

  ngOnDestroy(): void {
    // Limpiar el intervalo cuando el componente se destruya
    if (this.ubicacionInterval) {
      clearInterval(this.ubicacionInterval);
    }
  }

  obtenerUbicacion(): void {
    // Verificar si la geolocalización está disponible en el navegador
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          // Obtener latitud y longitud
          const lat = position.coords.latitude;
          const lon = position.coords.longitude;

          // Actualizar el valor de 'ubicacion' con las coordenadas
          this.ubicacion = `Latitud: ${lat} <br> Longitud: ${lon}`;
          console.log(this.ubicacion);
        },
        (error) => {
          // En caso de error, mostrar el mensaje de error
          this.ubicacion = `Error al obtener la ubicación: ${error.message}`;
        }
      );
    } else {
      // Si la geolocalización no está disponible
      this.ubicacion = "La geolocalización no está soportada en este navegador.";
    }
  }
}
