package com.mycompany.funeraria2;

import java.util.Scanner;

public class Menu {

    public static void menuIncial() {
        Scanner text = new Scanner(System.in);
        int opc = 0;
        do {
            System.out.println("================ MENU PRINCIPAL ================");
            System.out.println("1. Cadastrar um falecido.");
            System.out.println("2. Atualizar dados.");
            System.out.println("3. Buscar dados.");
            System.out.println("4. Excluir dados.");
            System.out.println("5. Encerrar programa.");
            System.out.print("R: ");

            opc = text.nextInt();
            switch (opc) {
                case 1:
                    Metodos.cadastrar();
                    break;
                case 2:
                    Metodos.atualizar();
                    break;
                case 3:
                    Metodos.buscar();
                    break;
                case 4:
                    Metodos.excluir();
                    break;

                case 5:
                    System.out.println("Saindo......");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opc != 5);
    }
}
