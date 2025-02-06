package com.mycompany.funeraria2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Metodos {
    public static final Scanner text = new Scanner(System.in);

    // ################################################################################################################################//
    // ################################################################################################################################//
    // Método estático para cadastrar um novo registro funerário no Firestore
    public static void cadastrar() {
        // Criação de um objeto Funeraria para armazenar os dados do falecido
        Funeraria fune = new Funeraria();

        // Exibe o nome da funerária no console
        System.out.println();
        System.out.println("============== FUNERARIA SANTA MARIA ============== ");

        // Solicita e armazena o nome completo
        System.out.print("Nome completo: ");
        String nomeCompleto = text.nextLine();
        fune.setNomeCompleto(nomeCompleto);

        // Solicita e armazena a data de nascimento
        System.out.print("Data de Nascimento: ");
        String dataNasci = text.nextLine();
        fune.setDataNasci(dataNasci);

        // Solicita e armazena a data de óbito
        System.out.print("Data de Óbito: ");
        String dataObito = text.nextLine();
        fune.setDataObito(dataObito);

        // Captura e valida o RG (deve ter entre 7 e 9 dígitos)
        String rg;
        do {
            System.out.print("RG: ");
            rg = text.nextLine();
            if (verificarTamanhoRG(rg)) {
                fune.setRg(rg);
            } else {
                System.out.println("Erro no salvamento: Esse RG deve possuir entre 7 e 9 dígitos.");
            }
        } while (!verificarTamanhoRG(rg));

        // Captura e valida o CPF (deve ter exatamente 11 dígitos)
        String cpf;
        do {
            System.out.print("CPF: ");
            cpf = text.nextLine();
            if (verificarTamanhoCPF(cpf)) {
                fune.setCpf(cpf);
            } else {
                System.out.println("Erro no salvamento: Esse CPF deve possuir exatamente 11 dígitos.");
            }
        } while (!verificarTamanhoCPF(cpf));

        System.out.println();

        // Lista para armazenar os serviços selecionados
        List<String> servicos = new ArrayList<>();
        String local = null; // Variável para armazenar o local do traslado, se necessário

        // Loop para seleção dos serviços funerários
        while (true) {
            System.out.println("============ Tipos de serviço ============ ");
            System.out.println("1. Cremação");
            System.out.println("2. Sepultamento");
            System.out.println("3. Velório");
            System.out.println("4. Traslado do corpo");
            System.out.println("5. Preparação do corpo");
            System.out.println("6. Finalizar seleção");
            System.out.print("Escolha uma das opções disponíveis: ");

            // Captura a opção do usuário
            int tipoServico = text.nextInt();
            text.nextLine(); // Consumir quebra de linha

            // Adiciona o serviço selecionado à lista
            switch (tipoServico) {
                case 1:
                    servicos.add("Cremação");
                    break;
                case 2:
                    servicos.add("Sepultamento");
                    break;
                case 3:
                    servicos.add("Velório");
                    break;
                case 4:
                    servicos.add("Traslado do corpo");
                    System.out.print("Local de destino: ");
                    local = text.nextLine(); // Captura o local para onde o corpo será levado
                    break;
                case 5:
                    servicos.add("Preparação do corpo");
                    break;
                case 6:
                    System.out.println();
                    System.out.println("=========================== ");
                    System.out.println("Seleção finalizada.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            // Se o usuário escolheu a opção 6, encerra o loop
            if (tipoServico == 6)
                break;
        }

        // Criação do mapa de dados para armazenamento no Firestore
        Map<String, Object> dados = new HashMap<>();
        dados.put("Nome completo", fune.getNomeCompleto());
        dados.put("Data de Nascimento", fune.getDataNasci());
        dados.put("Data de Óbito", fune.getDataObito());
        dados.put("Serviços prestados", servicos);
        dados.put("CPF", fune.getCpf());
        dados.put("RG", fune.getRg());

        // Se o traslado foi selecionado, adiciona o local ao mapa de dados
        if (local != null) {
            dados.put("Local para onde será levado o corpo", local);
        }

        // Define o ID do documento concatenando nome e RG
        String documentoId = nomeCompleto + rg;
        // Define o nome da coleção no Firestore
        String colecao = "Funeraria";

        // Chama o método para criar o documento no Firestore
        CrudFirestore.criarDocumento(colecao, documentoId, dados);
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método para atualizar os dados de um registro no Firestore
    public static void atualizar() {
        // Solicita ao usuário o nome completo e o RG para localizar o registro
        System.out.print("Digite o nome completo do falecido para atualizar: ");
        text.nextLine(); // Consumir a quebra de linha pendente
        String nomeCompleto = text.nextLine();

        System.out.print("Digite o RG: ");
        String rg = text.nextLine();

        // Concatena nome completo e RG para formar o ID do documento no Firestore
        String documentoId = nomeCompleto + rg;
        String colecao = "Funeraria";

        // Verifica se o documento existe antes de tentar atualizar
        if (!CrudFirestore.documentoExiste(colecao, documentoId)) {
            System.out.println("Registro não encontrado!");
            return;
        }

        // Lista para armazenar os serviços que serão atualizados
        List<String> servicos = new ArrayList<>();
        String local = null; // Variável para armazenar o local do traslado, se necessário

        // Loop para seleção dos novos serviços funerários
        while (true) {
            System.out.println("============ Tipos de serviço ============ ");
            System.out.println("1. Cremação");
            System.out.println("2. Sepultamento");
            System.out.println("3. Velório");
            System.out.println("4. Traslado do corpo");
            System.out.println("5. Preparação do corpo");
            System.out.println("6. Finalizar seleção");
            System.out.print("Escolha uma das opções disponíveis: ");

            // Captura a opção do usuário
            int tipoServico = text.nextInt();
            text.nextLine(); // Consumir a quebra de linha pendente

            // Adiciona o serviço selecionado à lista
            switch (tipoServico) {
                case 1:
                    servicos.add("Cremação");
                    break;
                case 2:
                    servicos.add("Sepultamento");
                    break;
                case 3:
                    servicos.add("Velório");
                    break;
                case 4:
                    servicos.add("Traslado do corpo");
                    System.out.print("Local para onde será levado o corpo: ");
                    local = text.nextLine(); // Captura o local do traslado
                    break;
                case 5:
                    servicos.add("Preparação do corpo");
                    break;
                case 6:
                    System.out.println("Seleção finalizada.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            // Se o usuário escolheu a opção 6, encerra o loop
            if (tipoServico == 6)
                break;
        }

        // Criação do mapa de dados com as informações atualizadas
        Map<String, Object> novosDados = new HashMap<>();
        novosDados.put("Serviços prestados", servicos);

        // Se o traslado foi selecionado, adiciona o local ao mapa de atualização
        if (local != null) {
            novosDados.put("Local para onde será levado o corpo", local);
        }

        // Chama o método para atualizar o documento no Firestore
        CrudFirestore.atualizarDocumento(colecao, documentoId, novosDados);

        // Confirmação da atualização para o usuário
        System.out.println("Dados atualizados com sucesso!");
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método responsável por buscar um registro no banco de dados baseado no nome
    // completo e RG
    public static void buscar() {
        // Solicita o nome completo do falecido
        System.out.print("Digite o nome completo do falecido: ");
        String nomeCompleto = text.nextLine(); // Armazena o nome completo digitado

        // Caso ocorra erro no input de dados, pode-se descomentar a linha abaixo para
        // limpar o buffer
        // text.nextLine();

        // Solicita o RG do falecido
        System.out.print("Digite o RG: ");
        String rg = text.nextLine(); // Armazena o RG digitado

        // Cria o identificador do documento combinando o nome completo e o RG
        String documentoId = nomeCompleto + "" + rg;

        // Define a coleção onde os dados estão armazenados
        String colecao = "Funeraria";

        // Realiza a busca no Firestore utilizando o identificador do documento e a
        // coleção
        Map<String, Object> dados = CrudFirestore.buscarDocumento(colecao, documentoId);

        // Verifica se o registro não foi encontrado
        if (dados == null) {
            System.out.println("Registro não encontrado!"); // Informa que o registro não foi encontrado
        } else {
            System.out.println(); // Imprime uma linha em branco
            System.out.println("=== Dados do falecido ==="); // Imprime um título para os dados encontrados

            // O método forEach percorre cada entrada (chave e valor) do Map imprimindo
            // cada chave e seu respectivo valor. Ou seja, 'chave' pode ser Nome Completo ou
            // Data de Nascimento, e 'valor' é tudo o que tem dentro dessa chave (nome,
            // idade, sexo, cpf, etc.)
            // O 'System.out.print()' é responsável por imprimir isso no console
            dados.forEach((chave, valor) -> System.out.println(chave + ": " + valor)); // Exibe as informações no
                                                                                       // console
        }
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método responsável por excluir um registro no banco de dados baseado no nome
    // completo e RG
    public static void excluir() {
        // Solicita o nome completo do falecido para exclusão
        System.out.print("Digite o nome completo do falecido para excluir: ");
        text.nextLine(); // Limpa o buffer de entrada
        String nomeCompleto = text.nextLine(); // Armazena o nome completo digitado

        // Solicita o RG do falecido para exclusão
        System.out.print("Digite o RG: ");
        String rg = text.nextLine(); // Armazena o RG digitado

        // Cria o identificador do documento combinando o nome completo e o RG
        String documentoId = nomeCompleto + rg;

        // Define a coleção onde os dados estão armazenados
        String colecao = "Funeraria";

        // Verifica se o documento existe na coleção, utilizando o método
        // 'documentoExiste'
        if (!CrudFirestore.documentoExiste(colecao, documentoId)) {
            System.out.println("Registro não encontrado!"); // Informa caso o registro não seja encontrado
            return; // Interrompe a execução do método se o registro não for encontrado
        }

        // Realiza a exclusão do documento no Firestore
        CrudFirestore.deletarDocumento(colecao, documentoId);

        // Informa que o registro foi excluído com sucesso
        System.out.println("Registro excluído com sucesso!");
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método para verificar o tamanho de um CPF
    public static boolean verificarTamanhoCPF(String cpf) {
        // Verifica se o CPF é nulo, retornando 'false' caso seja
        if (cpf == null) {
            return false; // CPF não pode ser nulo
        }

        // Remove possíveis caracteres como pontos (.) e traços (-) utilizando expressão
        // regular
        // O método replaceAll("\\D", "") substitui todos os caracteres que não são
        // dígitos por uma string vazia
        String cpfSemMascara = cpf.replaceAll("\\D", "");

        // Verifica se o CPF sem a máscara tem exatamente 11 caracteres
        return cpfSemMascara.length() == 11;
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método para verificar o tamanho de um RG
    public static boolean verificarTamanhoRG(String rg) {
        // Verifica se o RG é nulo, retornando 'false' caso seja
        if (rg == null) {
            return false; // RG não pode ser nulo
        }

        // Remove possíveis caracteres como pontos (.) e traços (-) utilizando expressão
        // regular
        // O método replaceAll("\\D", "") substitui todos os caracteres que não são
        // dígitos por uma string vazia
        String rgSemMascara = rg.replaceAll("\\D", "");

        // Verifica se o RG sem a máscara tem entre 7 e 9 dígitos
        // O RG pode ter no mínimo 7 dígitos e no máximo 9
        return rgSemMascara.length() >= 7 && rgSemMascara.length() <= 9;
    }

}
