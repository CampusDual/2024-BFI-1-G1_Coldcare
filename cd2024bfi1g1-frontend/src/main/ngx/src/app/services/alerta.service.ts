import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from 'ontimize-web-ngx';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {

  private apiUrl = 'http://localhost:8080/alerts';

  constructor(private http: HttpClient, private authService: AuthService) { }


  obtenerAlertasPendientes(altIds: number[]): Observable<any> {

    const token = this.authService.getSessionInfo();

    if (!token) {
      throw new Error('No se encontró el token de autenticación');
    }


    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(`${this.apiUrl}?altIds=${altIds.join(',')}`, { headers });
  }
}