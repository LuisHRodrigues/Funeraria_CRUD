package com.mycompany.funeraria2;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import java.io.FileInputStream;
import java.io.IOException;

public class Conexao {
    public static Firestore bd;

    public static void ConectarFirebase() {
        try {
            // Acesse o arquivo de credenciais (verifique o caminho)
            FileInputStream serviceAccount = new FileInputStream(
                    "Funeraria2\\fune.json"); // Caminho do JSON

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // Inicializa o Firebase App
            if (FirebaseApp.getApps().isEmpty()) { // Verifica se o Firebase já foi inicializado
                FirebaseApp.initializeApp(options);
            }

            // Inicializa a conexão com o Firestore
            bd = FirestoreClient.getFirestore();

            if (bd != null) {
                System.out.println("Conexão com o Firestore estabelecida com sucesso!");
            } else {
                System.err.println("Falha ao conectar com o Firestore.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao conectar ao Firebase: " + e.getMessage());
        }
    }
}
