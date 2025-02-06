package com.mycompany.funeraria2;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CrudFirestore {

    // Método estático para criar um documento em uma coleção do Firestore
    public static void criarDocumento(String colecao, String documentoId, Map<String, Object> dados) {
        // Obtém a instância do Firestore a partir da classe Conexao
        Firestore db = Conexao.bd;

        // Referência ao documento dentro da coleção especificada
        DocumentReference docRef = db.collection(colecao).document(documentoId);

        // Envia os dados para o Firestore e obtém um futuro com o resultado da operação
        ApiFuture<WriteResult> resultado = docRef.set(dados);

        try {
            // Aguarda a operação ser concluída e exibe o horário da atualização/criação do
            // documento
            System.out.println("Documento criado em: " + resultado.get().getUpdateTime());
        } catch (Exception e) {
            // Captura e exibe erros caso a criação do documento falhe
            System.err.println("Erro ao criar documento: " + e.getMessage());
        }
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método estático para atualizar um documento existente no Firestore
    public static void atualizarDocumento(String colecao, String documentoId, Map<String, Object> novosDados) {
        // Obtém a instância do Firestore a partir da classe Conexao
        Firestore db = Conexao.bd;

        // Referência ao documento dentro da coleção especificada
        DocumentReference docRef = db.collection(colecao).document(documentoId);

        // Envia os novos dados para atualização do documento e obtém um futuro com o
        // resultado da operação
        ApiFuture<WriteResult> resultado = docRef.update(novosDados);

        try {
            // Aguarda a conclusão da operação e exibe o horário da atualização do documento
            System.out.println("Documento atualizado em: " + resultado.get().getUpdateTime());
        } catch (Exception e) {
            // Captura e exibe erros caso a atualização do documento falhe
            System.err.println("Erro ao atualizar documento: " + e.getMessage());
        }
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método estático para deletar um documento do Firestore
    public static void deletarDocumento(String colecao, String documentoId) {
        // Obtém a instância do Firestore a partir da classe Conexao
        Firestore db = Conexao.bd;

        // Referência ao documento dentro da coleção especificada
        DocumentReference docRef = db.collection(colecao).document(documentoId);

        // Solicita a exclusão do documento e obtém um futuro com o resultado da
        // operação
        ApiFuture<WriteResult> resultado = docRef.delete();

        try {
            // Aguarda a conclusão da operação e exibe o horário da deleção do documento
            System.out.println("Documento deletado em: " + resultado.get().getUpdateTime());
        } catch (Exception e) {
            // Captura e exibe erros caso a exclusão do documento falhe
            System.err.println("Erro ao deletar documento: " + e.getMessage());
        }
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método estático para verificar se um documento existe no Firestore
    public static boolean documentoExiste(String colecao, String documentoId) {
        // Obtém a instância do Firestore
        Firestore db = FirestoreClient.getFirestore();

        try {
            // Obtém o snapshot do documento especificado dentro da coleção
            DocumentSnapshot document = db.collection(colecao).document(documentoId).get().get();

            // Retorna verdadeiro se o documento existir, falso caso contrário
            return document.exists();
        } catch (InterruptedException | ExecutionException e) {
            // Em caso de erro, imprime o stack trace e retorna falso
            e.printStackTrace();
            return false;
        }
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método estático para buscar um documento no Firestore e retornar seus dados
    public static Map<String, Object> buscarDocumento(String colecao, String documentoId) {
        // Obtém a instância do Firestore
        Firestore db = FirestoreClient.getFirestore();

        try {
            // Obtém o snapshot do documento especificado dentro da coleção
            DocumentSnapshot document = db.collection(colecao).document(documentoId).get().get();

            // Verifica se o documento existe
            if (document.exists()) {
                return document.getData(); // Retorna os dados do documento como um Map
            } else {
                return null; // Retorna null caso o documento não seja encontrado
            }
        } catch (InterruptedException | ExecutionException e) {
            // Em caso de erro, imprime o stack trace e retorna null
            e.printStackTrace();
            return null;
        }
    }

}
