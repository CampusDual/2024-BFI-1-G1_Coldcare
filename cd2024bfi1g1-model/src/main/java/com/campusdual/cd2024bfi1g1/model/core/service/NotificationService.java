package com.campusdual.cd2024bfi1g1.model.core.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public String sendNotification(String token, String title, String body) {
        try {
            // Construimos la notificación con el icono
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .setImage("https://cd2024bfi1g1-dev.dev.campusdual.com/app/assets/icons/ontimize64.png")// URL del icono
                    .build();

            // Construimos el mensaje
            Message message = Message.builder()
                    .setToken(token) // Token del dispositivo
                    .setNotification(notification)
                    .build();

            // Enviamos la notificación
            String response = FirebaseMessaging.getInstance().send(message);
            return "Notificación enviada con éxito: " + response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error enviando la notificación: " + e.getMessage();
        }
    }
}
