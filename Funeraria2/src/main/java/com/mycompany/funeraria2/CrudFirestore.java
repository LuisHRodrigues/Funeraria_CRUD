package com.mycompany.funeraria2;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CrudFirestore {

    // CREATE - Adicionar um documento ao Firebase
    public static void criarDocumento(String colecao, String documentoId, Map<String, Object> dados) {
        Firestore db = Conexao.bd;

        DocumentReference docRef = db.collection(colecao).document(documentoId);

        ApiFuture<WriteResult> resultado = docRef.set(dados);
        try {
            System.out.println("Documento criado em: " + resultado.get().getUpdateTime());
        } catch (Exception e) {
            System.err.println("Erro ao criar documento: " + e.getMessage());
        }
    }

    // UPDATE - Atualizar um documento do Firebase
    public static void atualizarDocumento(String colecao, String documentoId, Map<String, Object> novosDados) {
        Firestore db = Conexao.bd;

        DocumentReference docRef = db.collection(colecao).document(documentoId);
        ApiFuture<WriteResult> resultado = docRef.update(novosDados);
        try {
            System.out.println("Documento atualizado em: " + resultado.get().getUpdateTime());
        } catch (Exception e) {
            System.err.println("Erro ao atualizar documento: " + e.getMessage());
        }
    }

    // DELETE - Remover um documento do Firebase
    public static void deletarDocumento(String colecao, String documentoId) {
        Firestore db = Conexao.bd;

        DocumentReference docRef = db.collection(colecao).document(documentoId);
        ApiFuture<WriteResult> resultado = docRef.delete();
        try {
            System.out.println("Documento deletado em: " + resultado.get().getUpdateTime());
        } catch (Exception e) {
            System.err.println("Erro ao deletar documento: " + e.getMessage());
        }
    }

    public static boolean documentoExiste(String colecao, String documentoId) {
        Firestore db = FirestoreClient.getFirestore();
        try {
            DocumentSnapshot document = db.collection(colecao).document(documentoId).get().get();
            return document.exists();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Object> buscarDocumento(String colecao, String documentoId) {
        Firestore db = FirestoreClient.getFirestore();
        try {
            DocumentSnapshot document = db.collection(colecao).document(documentoId).get().get();
            if (document.exists()) {
                return document.getData(); // Retorna os dados do documento
            } else {
                return null; // Documento não encontrado
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
