package main.java.com.jprr.cadastroPets.app;

import main.java.com.jprr.cadastroPets.utils.Menu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)){
            int opt = 0;
            String filesDir = "/home/jprr/Projects/desafioCadastro/src/main/java/com/jprr/cadastroPets/";

            do {
                Menu.print( filesDir + "mainMenu.txt");
                System.out.print("Digite o número da operação desejada: ");
                try {
                    opt = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Erro: digite apenas números válidos!");
                    scan.nextLine();
                }

                switch (opt) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        System.out.println("Finalizando programa...");
                        break;
                    default:
                        System.out.println("\nNúmero inválido!");
                }

            } while(opt != 6);

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
