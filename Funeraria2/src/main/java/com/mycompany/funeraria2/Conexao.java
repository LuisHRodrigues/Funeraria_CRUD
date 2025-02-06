package com.mycompany.funeraria2;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import java.io.FileInputStream;
import java.io.IOException;

// Classe responsável por estabelecer a conexão com o Firestore
public class Conexao {
    // Variável estática que armazenará a instância do Firestore
    public static Firestore bd;

    // Método para conectar ao Firebase e inicializar o Firestore
    public static void ConectarFirebase() {
        try {
            // Carrega o arquivo de credenciais JSON do Firebase
            FileInputStream serviceAccount = new FileInputStream(
                    "Funeraria2\\fune.json"); // Caminho do JSON com as credenciais

            // Configura as opções do Firebase, utilizando as credenciais carregadas
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // Verifica se o Firebase já foi inicializado para evitar inicializações
            // duplicadas
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options); // Inicializa o Firebase
            }

            // Inicializa a conexão com o Firestore
            bd = FirestoreClient.getFirestore();

            // Verifica se a conexão foi bem-sucedida e exibe uma mensagem correspondente
            if (bd != null) {
                System.out.println("Conexão com o Firestore estabelecida com sucesso!");
            } else {
                System.err.println("Falha ao conectar com o Firestore.");
            }
        } catch (IOException e) {
            // Captura e exibe erros caso ocorra um problema ao carregar as credenciais ou
            // conectar ao Firebase
            e.printStackTrace();
            System.err.println("Erro ao conectar ao Firebase: " + e.getMessage());
        }
    }
}
