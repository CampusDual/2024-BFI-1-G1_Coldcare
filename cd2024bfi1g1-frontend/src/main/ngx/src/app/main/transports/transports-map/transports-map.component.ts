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

  lineColor = {false:'blue', true:'red'};

  ngAfterViewInit() {
    if (this.oMap) {
      this.cargarCoordenadas();
    }
  }

  cargarCoordenadas() {
    try {
      const map = this.oMap.getLMap();
  
      for (let i = 0; i < this.dataArrayCoordinates.length - 1; i++) {
        const puntoActual = this.dataArrayCoordinates[i];
        const puntoSiguiente = this.dataArrayCoordinates[i + 1];
  
        const latLngsTramo: L.LatLng[] = [
          L.latLng(puntoActual["TC_LATITUDE"], puntoActual["TC_LONGITUDE"]),
          L.latLng(puntoSiguiente["TC_LATITUDE"], puntoSiguiente["TC_LONGITUDE"])
        ];
  
        const tieneAlerta = puntoActual["TC_HAS_ALERT"];
  
        const tramoPolyline = L.polyline(latLngsTramo, {
          color: this.lineColor[tieneAlerta],
          weight: 4,
          opacity: 0.7
        });
  
        tramoPolyline.addTo(map);
  
      }
  
    } catch (error) {
      console.error(error);
    }
  }
  
  fillChart(ev: any) {  
    this.dataArrayCoordinates = ev.map(row => ({
      TC_LATITUDE: row.TC_LATITUDE,
      TC_LONGITUDE: row.TC_LONGITUDE,
      TC_HAS_ALERT: row.TC_HAS_ALERT,
      TC_DATE: row.TC_DATE
    }));
  
    this.dataArrayCoordinates.sort((a, b) => a.TC_DATE - b.TC_DATE);
    
    this.cargarCoordenadas();
  }
  
  
}
