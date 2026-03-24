package main.java.com.jprr.cadastroPets.app;

import main.java.com.jprr.cadastroPets.utils.Menu;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            Menu.print("/home/jprr/Projects/desafioCadastro/src/main/java/com/jprr/cadastroPets/formulario.txt");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
