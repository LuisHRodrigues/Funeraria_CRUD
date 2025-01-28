package com.mycompany.funeraria2;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import java.util.HashMap;
import java.util.Map;

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

    // READ - Ler um documento do Firebase
    public static void lerDocumento(String colecao, String documentoId) {
        Firestore db = Conexao.bd;

        DocumentReference docRef = db.collection(colecao).document(documentoId);
        ApiFuture<DocumentSnapshot> futuro = docRef.get();

        try {
            DocumentSnapshot documento = futuro.get();
            if (documento.exists()) {
                System.out.println("Dados do documento: " + documento.getData());
            } else {
                System.out.println("Documento não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler documento: " + e.getMessage());
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
}
