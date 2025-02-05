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
    public static void cadastrar() {
        Funeraria fune = new Funeraria();
        System.out.println();
        System.out.println("============== FUNERARIA SANTA MARIA ============== ");

        System.out.print("Nome completo: ");
        text.nextLine();
        String nomeCompleto = text.nextLine();
        fune.setNomeCompleto(nomeCompleto);

        System.out.print("Data de Nascimento: ");
        String dataNasci = text.nextLine();
        fune.setDataNasci(dataNasci);

        System.out.print("Data de Obito: ");
        String dataObito = text.nextLine();
        fune.setDataObito(dataObito);

        String rg;
        do {
            System.out.print("RG: ");
            rg = text.nextLine();
            if (verificarTamanhoRG(rg) == true) {
                fune.setRg(rg);
            } else {
                System.out.println("Erro no salvamento: Esse RG deve possuir entre 7 e 9 digitos.");
            }
        } while (!verificarTamanhoRG(rg));

        String cpf;
        do {
            System.out.print("CPF: ");
            cpf = text.nextLine();
            if (verificarTamanhoCPF(cpf) == true) {
                fune.setCpf(cpf);
            } else {
                System.out.println("Erro no salvamento: Esse CPF deve possuir exatamente 11 dígitos.");
            }
        } while (!verificarTamanhoCPF(cpf));

        System.out.println();

        List<String> servicos = new ArrayList<>();
        String local = null;

        while (true) {
            System.out.println("============ Tipos de serviço ============ ");
            System.out.println("1. Cremação");
            System.out.println("2. Sepultamento");
            System.out.println("3. Velorio");
            System.out.println("4. Traslado do corpo");
            System.out.println("5. Preparação do corpo");
            System.out.println("6. Finalizar seleção");
            System.out.print("Escolha uma das opções disponíveis: ");

            int tipoServico = text.nextInt();
            text.nextLine(); // Consumir quebra de linha

            switch (tipoServico) {
                case 1:
                    servicos.add("Cremação");
                    break;
                case 2:
                    servicos.add("Sepultamento");
                    break;
                case 3:
                    servicos.add("Velorio");
                    break;
                case 4:
                    servicos.add("Traslado do corpo");
                    System.out.print("Local para onde será levado o corpo: ");
                    local = text.nextLine();
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
            if (tipoServico == 6)
                break;
        }

        Map<String, Object> dados = new HashMap<>();
        dados.put("Nome completo", fune.getNomeCompleto());
        dados.put("Data de Nascimento", fune.getDataNasci());
        dados.put("Data de obito", fune.getDataObito());
        dados.put("Serviços prestados", servicos);
        dados.put("CPF", fune.getCpf());
        dados.put("RG", fune.getRg());

        if (local != null) {
            dados.put("Local para onde será levado o corpo", local);
        }

        String documentoId = nomeCompleto + rg;
        String colecao = "Funeraria";

        CrudFirestore.criarDocumento(colecao, documentoId, dados);
    }

    // ################################################################################################################################//
    // ################################################################################################################################//
    // Metodo para atualizar dados
    public static void atualizar() {
        System.out.print("Digite o nome completo do falecido para atualizar: ");
        text.nextLine();
        String nomeCompleto = text.nextLine();
        System.out.print("Digite o RG: ");
        String rg = text.nextLine();

        String documentoId = nomeCompleto + rg;
        String colecao = "Funeraria";

        if (!CrudFirestore.documentoExiste(colecao, documentoId)) {
            System.out.println("Registro não encontrado!");
            return;
        }

        List<String> servicos = new ArrayList<>();
        String local = null;

        while (true) {
            System.out.println("============ Tipos de serviço ============ ");
            System.out.println("1. Cremação");
            System.out.println("2. Sepultamento");
            System.out.println("3. Velorio");
            System.out.println("4. Traslado do corpo");
            System.out.println("5. Preparação do corpo");
            System.out.println("6. Finalizar seleção");
            System.out.print("Escolha uma das opções disponíveis: ");

            int tipoServico = text.nextInt();
            text.nextLine();

            switch (tipoServico) {
                case 1:
                    servicos.add("Cremação");
                    break;
                case 2:
                    servicos.add("Sepultamento");
                    break;
                case 3:
                    servicos.add("Velorio");
                    break;
                case 4:
                    servicos.add("Traslado do corpo");
                    System.out.print("Local para onde será levado o corpo: ");
                    local = text.nextLine();
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
            if (tipoServico == 6)
                break;
        }

        Map<String, Object> novosDados = new HashMap<>();
        novosDados.put("Serviços prestados", servicos);
        if (local != null) {
            novosDados.put("Local para onde será levado o corpo", local);
        }

        CrudFirestore.atualizarDocumento(colecao, documentoId, novosDados);
        System.out.println("Dados atualizados com sucesso!");
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    public static void buscar() {
        System.out.print("Digite o nome completo do falecido: ");
        text.nextLine();
        String nomeCompleto = text.nextLine();
        System.out.print("Digite o RG: ");
        String rg = text.nextLine();

        String documentoId = nomeCompleto + "" + rg;
        String colecao = "Funeraria";

        Map<String, Object> dados = CrudFirestore.buscarDocumento(colecao, documentoId);

        if (dados == null) {
            System.out.println("Registro não encontrado!");
        } else {
            System.out.println();
            System.out.println("=== Dados do falecido ===");

            // O método forEach percorre cada entrada (chave e valor) do Map imprimindo
            // cada chave e seu respectivo valor. Ou seja 'chave' pode ser Nome Completo ou
            // Data de Nascimento
            // e 'valor' é tudo o que tem dentro dessa chave, seja nome, idade, sexo, cpf,
            // etc
            // sendo o 'System.out.print()' responsavel por imprimir isso no console
            dados.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
        }
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    public static void excluir() {
        System.out.print("Digite o nome completo do falecido para excluir: ");
        text.nextLine();
        String nomeCompleto = text.nextLine();
        System.out.print("Digite o RG: ");
        String rg = text.nextLine();

        String documentoId = nomeCompleto + rg;
        String colecao = "Funeraria";

        if (!CrudFirestore.documentoExiste(colecao, documentoId)) {
            System.out.println("Registro não encontrado!");
            return;
        }

        CrudFirestore.deletarDocumento(colecao, documentoId);
        System.out.println("Registro excluído com sucesso!");
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método para verificar o tamanho de um CPF
    public static boolean verificarTamanhoCPF(String cpf) {
        if (cpf == null) {
            return false; // CPF não pode ser nulo
        }
        // Remove possíveis caracteres como pontos e traços
        String cpfSemMascara = cpf.replaceAll("\\D", "");
        return cpfSemMascara.length() == 11;
    }

    // ################################################################################################################################//
    // ################################################################################################################################//

    // Método para verificar o tamanho de um RG
    public static boolean verificarTamanhoRG(String rg) {
        if (rg == null) {
            return false; // RG não pode ser nulo
        }
        // Remove possíveis caracteres como pontos e traços
        String rgSemMascara = rg.replaceAll("\\D", "");
        return rgSemMascara.length() >= 7 && rgSemMascara.length() <= 9; // RG pode ter entre 7 e 9 dígitos

    }

}
