package com.mycompany.funeraria2;

import java.util.HashMap;
import java.util.Map;

public class Funeraria2Main {
    public static void main(String[] args) {
        // Conecta ao Firebase
        Conexao.ConectarFirebase();

        // Verifique se a conexão foi bem-sucedida
        if (Conexao.bd != null) {
            System.out.println("Conexão bem-sucedida com o Firestore!");

            // Variaveis para adiconar os dados
            String nomeCompleto = "Joaozin da pastelaria";
            String tipoDeServico = "Cremação";
            String dataDeNascimento = "20/02/1994";
            String dataDeObito = "05/09/2024";
            String localVelorio = "Avenida Engenheiro Atílio Correia Lima, 1161 - qd-122 lt-10A, Cidade Jardim, Goiânia, GO";

            // CREATE
            Map<String, Object> dados = new HashMap<>();
            dados.put("Nome completo", nomeCompleto);
            dados.put("Tipo de serviço funerário solicitado", tipoDeServico);
            dados.put("Data de nascimento", dataDeNascimento);
            dados.put("Data de obito", dataDeObito);
            dados.put("Local do velorio", localVelorio);

            // Colocando o mesmo nome do falecido na identificação do documento
            String documentoId = nomeCompleto;

            // Testar CRUD
            String colecao = "Funeraia";
            CrudFirestore.criarDocumento(colecao, documentoId, dados);

            // Leitura do documento e imprimindo no console
            CrudFirestore.lerDocumento(colecao, documentoId);

            // UPDATE

            Map<String, Object> novosDados = new HashMap<>();
            novosDados.put("idade", 329);
            CrudFirestore.atualizarDocumento(colecao, documentoId, novosDados);

            // DELETE
            CrudFirestore.deletarDocumento(colecao, documentoId);

        } else {
            System.out.println("Erro: Firestore não está conectado.");
        }
    }
}
