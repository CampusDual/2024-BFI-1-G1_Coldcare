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

  lineColor = { false: 'blue', true: 'red' };

  alertIcon = new L.Icon({
    iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]

  });

  ngAfterViewInit() {
    if (this.oMap) {
      this.cargarCoordenadas();
    }
  }

  cargarCoordenadas() {
    try {
      const map = this.oMap.getLMap();

      map.eachLayer(layer => {
        if (layer instanceof L.Polyline || layer instanceof L.Marker) {
          map.removeLayer(layer);
        }
      });

      for (let i = 0; i < this.dataArrayCoordinates.length - 1; i++) {
        const puntoActual = this.dataArrayCoordinates[i];
        const puntoSiguiente = this.dataArrayCoordinates[i + 1];

        const latLngsTramo: L.LatLng[] = [
          L.latLng(puntoActual["TC_LATITUDE"], puntoActual["TC_LONGITUDE"]),
          L.latLng(puntoSiguiente["TC_LATITUDE"], puntoSiguiente["TC_LONGITUDE"])
        ];

        const tieneAlertaActual = puntoActual["TC_HAS_ALERT"];
        const tieneAlertaAnterior = i > 0 ? this.dataArrayCoordinates[i - 1]["TC_HAS_ALERT"] : false;

        const tramoPolyline = L.polyline(latLngsTramo, {
          color: this.lineColor[tieneAlertaActual],
          weight: 4,
          opacity: 0.7
        });

        tramoPolyline.addTo(map);

        if (tieneAlertaActual && !tieneAlertaAnterior) {
          const marker = L.marker(
            [puntoActual["TC_LATITUDE"], puntoActual["TC_LONGITUDE"]],
            { icon: this.alertIcon }
          ).addTo(map);
        }
      }

      const ultimoIndex = this.dataArrayCoordinates.length - 1;
      const ultimoPunto = this.dataArrayCoordinates[ultimoIndex];
      const penultimoPunto = this.dataArrayCoordinates[ultimoIndex - 1];

      const tieneAlertaUltimo = ultimoPunto?.["TC_HAS_ALERT"];
      const tieneAlertaPenultimo = penultimoPunto?.["TC_HAS_ALERT"];

      if (tieneAlertaUltimo && !tieneAlertaPenultimo) {
        const marker = L.marker(
          [ultimoPunto["TC_LATITUDE"], ultimoPunto["TC_LONGITUDE"]],
          { icon: this.alertIcon }
        ).addTo(map);
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
