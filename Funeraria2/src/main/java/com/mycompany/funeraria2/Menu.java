package com.mycompany.funeraria2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static final Scanner text = new Scanner(System.in);

    public static void menuIncial() {
        System.out.println("================ MENU PRINCIPAL ================");
        System.out.println("1. Cadastrar um falecido.");
        System.out.println("2. Atualizar dados.");
        System.out.println("3. Buscar dados.");
        System.out.println("4. Excluir dados.");
        System.out.print("R: ");

        int opc = text.nextInt();
        switch (opc) {
            case 1:
                cadastrar();
                break;
            case 2:
                // atualizar();
                break;
            case 3:
                // buscar();
                break;
            case 4:
                // excluir();
                break;
        }

    }

    public static void cadastrar() {
        Funeraria fune = new Funeraria();
        System.out.println();
        System.out.println("============== FUNERARIA SANTA MARIA ============== ");

        System.out.print("Nome completo: ");
        text.nextLine();
        String nomeCompleto = text.nextLine();
        fune.setNomeCompleto(nomeCompleto);

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

        System.out.print("Data de Nascimento: ");

        String dataNasci = text.nextLine();
        fune.setDataNasci(dataNasci);

        System.out.print("Data de Obito: ");
        text.nextLine();
        String dataObito = text.nextLine();
        fune.setDataObito(dataObito);

        System.out.println();

        System.out.println("============ Tipos de serviço ============ ");
        System.out.println("1. Cremação");
        System.out.println("2. Sepultamento");
        System.out.println("3. Velorio");
        System.out.println("4. Traslado do corpo");
        System.out.println("5. Preparação do corpo");
        System.out.print("Escolha uma das opções disponiveis: ");

        int tipoServico = text.nextInt();
        switch (tipoServico) {
            case 1:
                fune.setTipoServico("Cremação");
                break;
            case 2:
                fune.setTipoServico("Sepultamento");
                break;
            case 3:
                fune.setTipoServico("Velorio");
                break;
            case 4:
                fune.setTipoServico("Traslado do corpo");
                break;
            case 5:
                fune.setTipoServico("Preparação do corpo");
                break;

        }
        if (tipoServico == 4) {
            System.out.println("Local para onde será levado o corpo: ");
            String local = text.nextLine();
            fune.setLocal(local);
        }

        Map<String, Object> dados = new HashMap<>();
        dados.put("Nome completo", fune.getNomeCompleto());
        dados.put("Data de Nascimento", fune.getDataNasci());
        dados.put("Data de obito", fune.getDataObito());
        dados.put("RG", fune.getRg());
        dados.put("CPF", fune.getCpf());
        dados.put("Tipo de serviço que será prestado", fune.getTipoServico());
        dados.put("Local para onde será levado o corpo", fune.getLocal());

        String documentoId = nomeCompleto;
        String colecao = "Funeraria";

        CrudFirestore.criarDocumento(colecao, documentoId, dados);

    }

    // Método para verificar o tamanho de um CPF
    public static boolean verificarTamanhoCPF(String cpf) {
        if (cpf == null) {
            return false; // CPF não pode ser nulo
        }
        // Remove possíveis caracteres como pontos e traços
        String cpfSemMascara = cpf.replaceAll("\\D", "");
        return cpfSemMascara.length() == 11;
    }

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
