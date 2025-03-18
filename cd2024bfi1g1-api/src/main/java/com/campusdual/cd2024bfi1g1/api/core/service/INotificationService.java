package com.campusdual.cd2024bfi1g1.api.core.service;

import java.util.Map;

public interface INotificationService {
    String sendNotification(String token, String title, String body);
}
