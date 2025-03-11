package com.campusdual.cd2024bfi1g1.model.core.service;

import com.campusdual.cd2024bfi1g1.api.core.service.INotificationService;
import com.campusdual.cd2024bfi1g1.model.core.dao.UserFirebaseTokenDao;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ontimize.jee.common.dto.EntityResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private UserFirebaseTokenService userFirebaseTokenService;

    public String sendNotification(String token, String title, String body) {
        try {
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();

            Message message = Message.builder()
                    .setToken(token)
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

    public void sendNotificationsToAllUsers(String title, String body) {
        try {
            EntityResult tokensResult = userFirebaseTokenService.userFirebaseTokenQuery(new HashMap<>(), List.of(UserFirebaseTokenDao.UFT_TOKEN));


            if (tokensResult.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
                List<Map<String, Object>> tokensList = (List<Map<String, Object>>) tokensResult.get(UserFirebaseTokenDao.UFT_TOKEN);

                for (Map<String, Object> tokenEntry : tokensList) {
                    String token = (String) tokenEntry.get(UserFirebaseTokenDao.UFT_TOKEN);
                    String response = sendNotification(token, title, body);
                    System.out.println(response);
                }
            } else {
                System.out.println("Error al obtener los tokens. Código de error: " + tokensResult.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar notificaciones: " + e.getMessage());
        }
    }

}
