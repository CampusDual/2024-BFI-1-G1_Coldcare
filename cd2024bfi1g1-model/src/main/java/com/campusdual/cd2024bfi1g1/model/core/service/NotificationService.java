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

    @Autowired
    private UserAndRoleService userAndRoleService; // Se inyecta en lugar de instanciar manualmente

    /**
     * Envía una notificación a un dispositivo usando el token.
     * Los parámetros "title" y "body" se tratan como claves de traducción,
     * y se envía un parámetro "language" (en este ejemplo hard-coded a "es") para que el service worker traduzca.
     */
    @Override
    public String sendNotification(String token, String title, String body) {
        try {
            // Hard-codeamos el idioma (puedes modificarlo o extraerlo según la preferencia del usuario)
            String language = "es";
            Message message = Message.builder()
                    .setToken(token)
                    .putData("language", language) // Se envía el idioma en el payload data
                    .setNotification(Notification.builder()
                            .setTitle(title) // Se envía la clave de traducción para el título
                            .setBody(body)   // Se envía la clave de traducción para el cuerpo
                            .build())
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            return "Notificación enviada con éxito: " + response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error enviando la notificación: " + e.getMessage();
        }
    }

    /**
     * Recorre todos los tokens registrados y envía una notificación a los que pertenecen a usuarios con rol "user".
     * Se envía la notificación usando claves de traducción, por ejemplo, "Coldcare" para el título y "ALERT_GENERATED" para el cuerpo.
     */
    public void sendNotificationsToAllUsers(String language) {
        try {
            EntityResult tokensResult = userFirebaseTokenService.userFirebaseTokenQuery(
                    new HashMap<>(),
                    List.of(UserFirebaseTokenDao.UFT_TOKEN, UserFirebaseTokenDao.USR_ID)
            );

            if (tokensResult.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
                int numRecords = tokensResult.calculateRecordNumber();

                for (int i = 0; i < numRecords; i++) {
                    String token = (String) tokensResult.getRecordValues(i).get(UserFirebaseTokenDao.UFT_TOKEN);
                    Integer userId = (Integer) tokensResult.getRecordValues(i).get(UserFirebaseTokenDao.USR_ID);

                    if (userId != null && isUserRole(userId)) {
                        // Aquí se llaman las claves de traducción que el service worker usará para traducir el mensaje.
                        // Por ejemplo, en este caso se envían "Coldcare" y "ALERT_GENERATED".
                        String response = sendNotification(token, "Coldcare", "ALERT_GENERATED");
                        System.out.println(response);
                    }
                }
            } else {
                System.out.println("Error al obtener los tokens. Código de error: " + tokensResult.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al enviar notificaciones: " + e.getMessage());
        }
    }

    /**
     * Comprueba si el usuario tiene el rol "user".
     */
    private boolean isUserRole(Integer userId) {
        Map<String, Object> filter = new HashMap<>();
        filter.put("USR_ID", userId);

        EntityResult roleResult = userAndRoleService.roleQuery(filter, List.of("ROL_NAME"));

        if (roleResult.getCode() == EntityResult.OPERATION_SUCCESSFUL) {
            int numRecords = roleResult.calculateRecordNumber();
            for (int i = 0; i < numRecords; i++) {
                String currentRole = (String) roleResult.getRecordValues(i).get("ROL_NAME");
                if ("user".equalsIgnoreCase(currentRole)) {
                    return true;
                }
            }
        }
        return false;
    }
}
