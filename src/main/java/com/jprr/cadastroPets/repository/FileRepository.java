package main.java.com.jprr.cadastroPets.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class FileRepository {

    public final Path FORM_PATH = Path.of("src", "main", "java", "com", "jprr",
            "cadastroPets", "docs", "formulario.txt");
    public final Path MENU_PATH = Path.of("src", "main", "java", "com", "jprr",
            "cadastroPets", "docs", "mainMenu.txt");

    public void checkFile(Path path) throws IOException {
        File file = new File(String.valueOf(path.toAbsolutePath()));
        if (!file.exists()) {
            //TODO: terminar depois
            createFile(String.valueOf(path.toAbsolutePath()));
        }
    }

    public void createFile(String path) {
        //TODO: terminar depois
    }

    public void readFile(Path path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path.toAbsolutePath())))) {
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }
    }

    public void fetchLine(Path path, int lineNum) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path.toAbsolutePath())))) {
            String line;
            int count = 1;

            while ((line = br.readLine()) != null) {
                if (count == lineNum) {
                    System.out.println(line);
                }
                count++;
            }
        }
    }
}
