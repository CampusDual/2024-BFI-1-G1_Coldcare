import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { OMapComponent } from 'ontimize-web-ngx-map';
import * as L from 'leaflet';
import 'leaflet-routing-machine';

@Component({
  selector: 'app-transports-map',
  templateUrl: './transports-map.component.html',
  styleUrls: ['./transports-map.component.css']
})
export class TransportsMapComponent implements AfterViewInit {

  @ViewChild('oMapRouting', { static: false }) oMap!: OMapComponent;
  
  dataArrayCoordinates: any[] = [];
  routingControl: L.Routing.Control | null = null; // Controlador de la ruta

  ngAfterViewInit() {
    if (this.oMap) {
      console.log('Referencia a oMap:', this.oMap);
      this.cargarCoordenadas();
    } else {
      console.error('oMap es undefined en ngAfterViewInit');
    }
  }

  cargarCoordenadas() {
    try {
      const map = this.oMap.getLMap();

      if (this.routingControl) {
        map.removeControl(this.routingControl);
      }

      const waypoints: L.Routing.Waypoint[] = [];
      this.dataArrayCoordinates.forEach(punto => {
        const latLng = L.latLng(punto["TC_LATITUDE"], punto["TC_LONGITUDE"]);
        waypoints.push(L.routing.waypoint(latLng));

        L.marker(latLng).addTo(map);
      });

      if (waypoints.length > 1) {
        this.routingControl = L.Routing.control({
          waypoints: waypoints,
          routeWhileDragging: true,
          createMarker: () => null, // Evitar marcadores extra
          lineOptions: { styles: [{ color: 'blue', weight: 4 }] },
          router: L.routing.osrmv1({ serviceUrl: 'https://router.project-osrm.org/route/v1' }), // Usa OSRM
        }).addTo(map);
        //Eliminar el panel de control
        if (this.routingControl) {
          const itineraryContainer = document.querySelector('.leaflet-routing-container');
          if (itineraryContainer) itineraryContainer.remove();
        }
      }

    } catch (error) {
      console.error('Error al cargar coordenadas:', error);
    }
  }

  fillChart(ev: any) {
    this.dataArrayCoordinates = ev.map(row => ({
      TC_LATITUDE: row.TC_LATITUDE,
      TC_LONGITUDE: row.TC_LONGITUDE
    }));

    this.cargarCoordenadas();
  }
}
