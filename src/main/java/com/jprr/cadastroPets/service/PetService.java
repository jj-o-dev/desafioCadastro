package main.java.com.jprr.cadastroPets.service;

import main.java.com.jprr.cadastroPets.model.entity.Pet;
import main.java.com.jprr.cadastroPets.model.entity.PetAddress;
import main.java.com.jprr.cadastroPets.model.enums.PetType;
import main.java.com.jprr.cadastroPets.model.enums.PetSex;
import main.java.com.jprr.cadastroPets.model.exceptions.PetInfoException;
import main.java.com.jprr.cadastroPets.repository.FileRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PetService {
    public Pet createPet(Scanner scan) throws IOException {
        FileRepository fr = new FileRepository();

        String name;
        PetType type;
        PetSex sex;
        String addressNum;
        String city;
        String street;
        PetAddress address;
        double age;
        String timeUnit;
        double weight;
        String breed;

        try {
            System.out.println();
            // pergunta 1
            fr.fetchLine(fr.FORM_PATH ,1);
            name = scan.nextLine();
            Validate.name(name);

            // pergunta 2
            fr.fetchLine(fr.FORM_PATH, 2);
            type = Validate.type(scan.nextLine());

            // pergunta 3
            fr.fetchLine(fr.FORM_PATH, 3);
            sex = Validate.sex(scan.nextLine());

            // pergunta 4
            fr.fetchLine(fr.FORM_PATH, 4);
            System.out.print("Número: ");
            addressNum = scan.nextLine();
            System.out.print("Cidade: ");
            city = scan.nextLine();
            System.out.print("Rua: ");
            street = scan.nextLine();
            address = Validate.address(addressNum, city, street);

            // pergunta 5
            fr.fetchLine(fr.FORM_PATH, 5);
            System.out.print("Em anos ou meses? (ano/mes):");
            timeUnit = scan.nextLine();
            System.out.print("Digite o tempo: ");
            age = Validate.age(scan.nextLine(), timeUnit);

            // pergunta 6
            fr.fetchLine(fr.FORM_PATH, 6);
            weight = Validate.weight(scan.nextLine());

            // pergunta 7
            fr.fetchLine(fr.FORM_PATH, 7);
            breed = scan.nextLine();
            Validate.breed(breed);
        }
        catch(PetInfoException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return new Pet(name, type, sex, address, age, weight, breed);
    }

    public Set<String> searchPet(Scanner scan, String criteria) {
        //TODO: acrescentar as regras opcionais, depois de já ter terminado
        FileRepository fr = new FileRepository();
        List<Integer> options = new ArrayList<>();
        List<String> infos = new ArrayList<>();

        try {
            List<String> crit = List.of(Validate.criteria(criteria).split(""));

            if (crit.size() > 2) {
                throw new PetInfoException("Escolha até no máximo 2 critérios");
            }

            crit.forEach(s -> options.add(Integer.parseInt(s)));

            for (Integer i: options) {
                switch (i) {
                    case 1:
                        System.out.print("Insira o nome ou sobrenome: ");
                        break;
                    case 2:
                        System.out.print("Insira o sexo: ");
                        break;
                    case 3:
                        System.out.print("Insira a idade: ");
                        break;
                    case 4:
                        System.out.print("Insira o peso: ");
                        break;
                    case 5:
                        System.out.print("Insira a raça: ");
                        break;
                    case 6:
                        System.out.print("Insira o endereço [rua, número, cidade]: ");
                }
                String info = scan.nextLine();
                infos.add(info);
            }
        }
        catch (PetInfoException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return fr.searchPetFile(infos);
    }

    public void updatePet(Scanner scan, String modify, String fileString) {
        FileRepository fr = new FileRepository();
        List<Integer> options = new ArrayList<>();
        Map<Integer, String> info = new LinkedHashMap<>();

        try {
            List<String> input = List.of(Validate.modify(modify).split(""));
            input.forEach(s -> options.add(Integer.parseInt(s)));

            for (Integer i: options) {
                switch (i) {
                    case 1:
                        System.out.print("Insira o novo nome: ");
                        String newName = scan.nextLine();
                        Validate.name(newName);
                        if (newName.isBlank()) {
                            info.put(1, "1 - NAO INFORMADO");
                        } else {
                            info.put(1, "1 - " + newName);
                        }

                        break;
                    case 2:
                        System.out.print("Insira o novo endereço ");
                        System.out.print("Número: ");
                        String newNum = scan.nextLine();
                        System.out.print("Cidade: ");
                        String newCity = scan.nextLine();
                        System.out.print("Rua: ");
                        String newStreet = scan.nextLine();
                        PetAddress newAddress = Validate.address(newNum, newCity, newStreet);

                        if (newAddress.getNumber() == 0) {
                            info.put(4, "4 - " + newAddress.getStreet() + ", NAO INFORMADO, " + newAddress.getCity());
                        } else {
                            info.put(4, "4 - " + newAddress.getStreet() + ", "+ newNum + ", " + newAddress.getCity());
                        }

                        break;
                    case 3:
                        System.out.print("Em anos ou meses? (ano/mes):");
                        String timeUnit = scan.nextLine();
                        System.out.print("Insira a nova idade: ");
                        double newAge = Validate.age(scan.nextLine(), timeUnit);
                        if (newAge == 0) {
                            info.put(5, "5 - NAO INFORMADO");
                        } else if (newAge < 1) {
                            String months = String.valueOf(newAge).split("\\.")[1];
                            info.put(5, "5 - " + months + " mes(es)");
                        } else {
                            String years = String.valueOf(newAge).split("\\.")[0];
                            info.put(5, "5 - " + years + " ano(s)");
                        }

                        break;
                    case 4:
                        System.out.print("Insira o novo peso: ");
                        double newWeight = Validate.weight(scan.nextLine());
                        if (newWeight == 0) {
                            info.put(6, "6 - NAO INFORMADO");
                        } else {
                            info.put(6,"6 - " + newWeight + "kg");
                        }

                        break;
                    case 5:
                        System.out.print("Insira a nova raça: ");
                        String newBreed = scan.nextLine();
                        Validate.breed(newBreed);
                        if (newBreed.isBlank()) {
                            info.put(7, "7 - NAO INFORMADO");
                        } else {
                            info.put(7, "7 - " + newBreed);
                        }

                        break;
                }

            }

            fr.updatePetFile(fileString, info);
        }
        catch(PetInfoException e) {
            System.out.println(e.getMessage());
        }
    }
}
