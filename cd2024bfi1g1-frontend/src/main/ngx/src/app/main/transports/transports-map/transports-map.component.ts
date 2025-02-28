import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { OMapComponent } from 'ontimize-web-ngx-map';
import * as L from 'leaflet';

@Component({
  selector: 'app-transports-map',
  templateUrl: './transports-map.component.html',
  styleUrls: ['./transports-map.component.css']
})
export class TransportsMapComponent implements AfterViewInit {

  @ViewChild('oMapRouting', { static: false }) oMap!: OMapComponent;
  
  dataArrayCoordinates: any[] = [];
  polyline: L.Polyline | null = null;

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

      if (this.polyline) {
        map.removeLayer(this.polyline);
      }

      const latLngs: L.LatLng[] = [];

      this.dataArrayCoordinates.forEach(punto => {
        const latLng = L.latLng(punto["TC_LATITUDE"], punto["TC_LONGITUDE"]);
        latLngs.push(latLng);

      });

      if (latLngs.length > 1) {

        this.polyline = L.polyline(latLngs, {
          color: 'blue', 
          weight: 4,     
          opacity: 0.7  
        }).addTo(map);
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
