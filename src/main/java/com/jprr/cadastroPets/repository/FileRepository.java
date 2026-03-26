package main.java.com.jprr.cadastroPets.repository;

import main.java.com.jprr.cadastroPets.model.entity.Pet;
import main.java.com.jprr.cadastroPets.model.enums.PetSex;
import main.java.com.jprr.cadastroPets.model.enums.PetType;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FileRepository {

    public final Path FORM_PATH = Path.of("src", "main", "java", "com", "jprr",
            "cadastroPets", "docs", "formulario.txt");
    public final Path MENU_PATH = Path.of("src", "main", "java", "com", "jprr",
            "cadastroPets", "docs", "mainMenu.txt");
    public final Path PET_DIR = Path.of("src", "petsCadastrados");

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
        BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path.toAbsolutePath())));
        String line = br.readLine();

        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
    }

    public void fetchLine(Path path, int lineNum) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path.toAbsolutePath())));
        String line;
        int count = 1;

        while ((line = br.readLine()) != null) {
            if (count == lineNum) {
                System.out.println(line);
            }
            count++;
        }
    }

    public void createPetFile(Pet pet) throws IOException {
        final String NO_INFO = "NAO INFORMADO";
        //TODO: criar método para gerar nome do arquivo
        BufferedWriter bw = new BufferedWriter(new FileWriter(
                PET_DIR.toAbsolutePath() + "/" + generateFileName(pet)));

        //linha nome
        if (pet.getName().isBlank()) {
            bw.write("1 - " + NO_INFO);
        } else {
            bw.write("1 - " + pet.getName());
        }
        bw.newLine();

        //linha tipo
        if (pet.getType().equals(PetType.DOG)) {
            bw.write("2 - " + "Cachorro");
        } else {
            bw.write("2 - " + "Gato");
        }
        bw.newLine();

        //linha sexo
        if (pet.getPetSex().equals(PetSex.FEMALE)) {
            bw.write("3 - " + "Femea");
        } else {
            bw.write("3 - " + "Macho");
        }
        bw.newLine();

        //linha endereço
        if (pet.getPetAddress().getNumber() == 0) {
            bw.write("4 - " + pet.getPetAddress().getStreet()+", "+NO_INFO+", "+pet.getPetAddress().getCity());
        } else {
            bw.write("4 - " + pet.getPetAddress().getStreet()+", "+
                    pet.getPetAddress().getNumber() +", "+pet.getPetAddress().getCity());
        }
        bw.newLine();

        //linha idade
        if (pet.getAge() == 0) {
            bw.write(NO_INFO);
        } else if (pet.getAge() < 1) {
            String months = String.valueOf(pet.getAge()).split("\\.")[1];
            bw.write("5 - " + months + " mes(es)");
        } else {
            String years = String.valueOf(pet.getAge()).split("\\.")[0];
            bw.write("5 - " + years + " ano(s)");
        }
        bw.newLine();

        //linha peso
        if (pet.getWeight() == 0) {
            bw.write("6 - " + NO_INFO);

        }
        bw.write("6 - " + pet.getWeight());
        bw.newLine();

        //linha raça
        if (pet.getBreed().isBlank()) {
            bw.write("7 - " + NO_INFO);
        } else {
            bw.write("7 - " + pet.getBreed());
        }

        bw.flush();
    }

    public String generateFileName(Pet pet) {
        LocalDateTime moment = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String name = Arrays.stream(pet.getName().split(" "))
                .reduce("", (a, b) -> a + b);

        return (moment.format(format) + "-" + name + ".txt").toUpperCase();
    }
}
