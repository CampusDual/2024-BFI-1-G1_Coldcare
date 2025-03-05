package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.INotificationService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    public String sendNotification(String token, String title, String body) {
        try {
            // Construimos el mensaje
            Message message = Message.builder()
                    .setToken(token) // Token del dispositivo
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
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
