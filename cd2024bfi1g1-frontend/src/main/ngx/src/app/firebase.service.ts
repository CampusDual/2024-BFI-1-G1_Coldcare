import { Injectable } from '@angular/core';
import { getAuth, signInAnonymously } from 'firebase/auth';
import { getMessaging, getToken, onMessage } from 'firebase/messaging';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root',
})
export class FirebaseService {
  private messaging;

  constructor() {
    this.messaging = getMessaging();
  }

  //Le pasamos el resultado de getAuth y recibimos al usuario
  loguearse() {
    signInAnonymously(getAuth()).then((usuario) => {
      console.log(usuario);
    }).catch((error) => {
      console.error('Error al loguearse', error);
    });
  }

  //Hay que crear un VAPID KEY en firebase, vamos a configuracion del proyecto > Cloud Messaging > Certificados push web y creamos una clave
  async activarMensajes() {
    try {
      const registration = await navigator.serviceWorker.register('/assets/firebase-messaging-sw.js');
      const token = await getToken(this.messaging, {
        //Recogemos el VAPID desde environment
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

  activarNotificaciones(callback: (body: string) => void) {
    onMessage(this.messaging, (message) => {
      console.log('Tu mensaje:', message);

      // Extraer solo el body de la notificación
      const body = message.notification?.body || 'Sin cuerpo';

      // Llamar al callback solo con el body
      callback(body);
    });
  }


}
