import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {

  private apiUrl = 'http://localhost:8080/api/alertas/por-ids';  // URL de tu backend

  constructor(private http: HttpClient) { }



  obtenerAlertasPendientes(altIds: number[]): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?altIds=${altIds.join(',')}`);
  }
} 