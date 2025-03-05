package com.campusdual.cd2024bfi1g1;

import com.campusdual.cd2024bfi1g1.model.core.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ontimize.jee.server.security.encrypt.IPasswordEncryptHelper;
import com.ontimize.jee.server.security.encrypt.PasswordBCryptHelper;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner { // Implementamos CommandLineRunner

	@Autowired
	private NotificationService notificationService; // Inyectamos el servicio de notificaciones

	public static void main(final String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public IPasswordEncryptHelper passwordEncryptHelper() {
		return new PasswordBCryptHelper();
	}

	@Override
	public void run(String... args) throws Exception {
		String token = "f1l5v3Bi9BBmMuPk3X0E7E:APA91bFzwshG3-ej6Ji8qMA1V-lnMNhHsN14NNAJ6hw3xgJTVYyWoKo3tghNaMratT29HVOiRhnjinlltEryhf6CNORpe1CzGWnurSQfQw6gYjpB5J8sq9w";

		// Enviar una notificación push al arrancar la aplicación
		String response = notificationService.sendNotification(token, "Notificación automática", "Nueva alerta generada desde el backend");

	}
}
