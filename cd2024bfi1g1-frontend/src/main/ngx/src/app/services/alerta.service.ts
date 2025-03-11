import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from 'ontimize-web-ngx';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {

  private apiUrl = 'http://localhost:8080/api/alertas/por-ids';  // URL de tu backend

  constructor(private http: HttpClient, private authService: AuthService) { }

  // Método para obtener alertas pendientes
  obtenerAlertasPendientes(altIds: number[]): Observable<any> {
    // Obtener el token desde el servicio de autenticación
    const token = this.authService.getSessionInfo(); // Asegúrate de tener un método en AuthService que te dé el token

    if (!token) {
      throw new Error('No se encontró el token de autenticación');
    }

    // Crear las cabeceras para la solicitud HTTP
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    // Llamar al backend y pasar los IDs para filtrar
    return this.http.get<any>(`${this.apiUrl}?altIds=${altIds.join(',')}`);
  }
}