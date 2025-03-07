package com.campusdual.cd2024bfi1g1.api.core.service;

public interface INotificationService {
    String sendNotification(String token, String title, String body);
}
