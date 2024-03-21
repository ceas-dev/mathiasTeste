package com.easyapp.adm.mathias.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Bean
    FirebaseApp firebaseApp() throws IOException {
        // Lê as variáveis de ambiente
        String credentialsEnv = System.getenv("FIREBASE_CREDENTIALS");
        String databaseUrlEnv = System.getenv("FIREBASE_DATABASE_URL");

        // Configura as opções do Firebase
        FirebaseOptions.Builder optionsBuilder = FirebaseOptions.builder();
        optionsBuilder.setDatabaseUrl(databaseUrlEnv);

        // Carrega as credenciais a partir de uma string em JSON
        GoogleCredentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(credentialsEnv.getBytes()));
        optionsBuilder.setCredentials(credentials);

        // Inicializa o Firebase App
        return FirebaseApp.initializeApp(optionsBuilder.build());
    }
}
