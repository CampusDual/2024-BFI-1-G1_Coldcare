import { Injectable } from '@angular/core';
import { getAuth, signInAnonymously } from 'firebase/auth';
import { getMessaging, getToken, onMessage } from 'firebase/messaging';
import { environment } from 'src/environments/environment'; // Ajusta la ruta según tu entorno

@Injectable({
  providedIn: 'root',
})
export class FirebaseService {
  private messaging;

  constructor() {
    this.messaging = getMessaging();
  }

  loguearse() {
    signInAnonymously(getAuth()).then((usuario) => {
      console.log(usuario);
    }).catch((error) => {
      console.error('Error al loguearse', error);
    });
  }

  async activarMensajes() {
    try {
      const registration = await navigator.serviceWorker.register('/assets/firebase-messaging-sw.js');
      const token = await getToken(this.messaging, {
        vapidKey: environment.firebaseVapidKey,
        serviceWorkerRegistration: registration,
      });

      if (token) {
        console.log('tu token:', token);
      } else {
        console.log('no tienes token');
      }
    } catch (error) {
      console.log('Tuviste un error al generar el token', error);
    }
  }

  activarNotificaciones(callback: (message: any) => void) {
    onMessage(this.messaging, (message) => {
      console.log('tu mensaje:', message);
      callback(message.notification.title);
    });
  }
}
