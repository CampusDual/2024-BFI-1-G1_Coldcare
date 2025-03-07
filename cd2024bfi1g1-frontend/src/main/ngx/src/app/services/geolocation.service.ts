import { Injectable } from '@angular/core';
import { OntimizeService } from 'ontimize-web-ngx';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeolocationService {
  private ubicacionInterval: any;

  constructor(private service: OntimizeService) { }

  startTracking(trpId: number): void {
    localStorage.setItem('trackingActive', 'true');
    localStorage.setItem('TRP_ID', trpId.toString());

    if (!this.ubicacionInterval) {
      this.ubicacionInterval = setInterval(() => {
        this.insertGeolocation(trpId);
      }, 5000);
    }
  }

  continueTracking(trpId: number): void {

    if (!this.ubicacionInterval) {
      this.ubicacionInterval = setInterval(() => {
        this.insertGeolocation(trpId);
      }, 5000);
    }
  }

  stopTracking(): void {
    localStorage.removeItem('trackingActive');
    localStorage.removeItem('TRP_ID');

    if (this.ubicacionInterval) {
      clearInterval(this.ubicacionInterval);
      this.ubicacionInterval = null;
    }
  }

  checkTrackingStatus(): boolean {
    return localStorage.getItem('trackingActive') === 'true';
  }

  getTrpId(): number | null {
    const trpId = localStorage.getItem('TRP_ID');
    return trpId ? Number(trpId) : null;
  }

  private insertGeolocation(trpId: number): void {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const lat = position.coords.latitude;
          const lon = position.coords.longitude;
          const data = { TRP_ID: trpId, TC_LATITUDE: lat, TC_LONGITUDE: lon };

          const conf = this.service.getDefaultServiceConfiguration('transportsCoordinates');
          this.service.configureService(conf);

          this.service.insert(data, 'transportsCoordinates').subscribe(response => {
            if (response.code !== 0) {
              console.error('Error inserting geolocation:', response);
            }
          });
        },
        (error) => {
          console.error('Error getting location:', error.message);
        }
      );
    } else {
      console.error('Geolocation is not supported by this browser.');
    }
  }
}
