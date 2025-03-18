package com.campusdual.cd2024bfi1g1.model.core;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
@Configuration
public class FirebaseConfig {
    @Value("${firebase.serviceAccountKey}")
    private String FIREBASE_SERVICE_ACCOUNT_KEY;

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        ByteArrayInputStream serviceAccount = new ByteArrayInputStream(FIREBASE_SERVICE_ACCOUNT_KEY.getBytes());

        if (serviceAccount == null) {
            throw new IOException("No se encontró el archivo firebase-credentials.json en resources/");
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        return FirebaseApp.initializeApp(options);

    }
}