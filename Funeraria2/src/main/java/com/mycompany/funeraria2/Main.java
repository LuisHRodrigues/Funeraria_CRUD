package com.mycompany.funeraria2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final Scanner text = new Scanner(System.in);

    public static void main(String[] args) {

        Conexao.ConectarFirebase();
        Menu.menuIncial();
    }
}
