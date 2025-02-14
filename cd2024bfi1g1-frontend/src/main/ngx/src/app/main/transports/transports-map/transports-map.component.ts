import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { OMapComponent } from 'ontimize-web-ngx-map';
import * as L from 'leaflet';
import { OTableComponent } from 'ontimize-web-ngx';

@Component({
  selector: 'app-transports-map',
  templateUrl: './transports-map.component.html',
  styleUrls: ['./transports-map.component.css']
})
export class TransportsMapComponent implements AfterViewInit {

  @ViewChild('oMapRouting', { static: false }) oMap!: OMapComponent;

  dataArrayCoordinates: any = [];


  ngAfterViewInit() {
    this.cargarCoordenadas();
  }

  cargarCoordenadas() {
    try {
      // Cargar las coordenadas de la tabla


      

      // Obtener el mapa de Ontimize
      const map = this.oMap.getLMap();

      // Agregar marcadores al mapa
      this.dataArrayCoordinates.forEach(punto => {
        const marker = L.marker([punto["TC_LATITUDE"], punto["TC_LONGITUDE"]]);
        marker.addTo(map);
      });
      

    } catch (error) {
      console.error('Error al cargar coordenadas:', error);
    }
  }

  fillChart(ev: any) {

    const data = ev.map((row) => {
      return {
        ["TC_LATITUDE"]: row.TC_LATITUDE,
        ["TC_LONGITUDE"]: row.TC_LONGITUDE
      }
    });
    this.dataArrayCoordinates = data;
  }
}
