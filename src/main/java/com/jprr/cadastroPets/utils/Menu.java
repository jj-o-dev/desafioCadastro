package main.java.com.jprr.cadastroPets.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class Menu {
    public static void print(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }
    }


}
