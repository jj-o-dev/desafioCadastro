package main.java.com.jprr.cadastroPets.service;

import main.java.com.jprr.cadastroPets.model.entity.Pet;
import main.java.com.jprr.cadastroPets.model.entity.PetAddress;
import main.java.com.jprr.cadastroPets.model.enums.PetType;
import main.java.com.jprr.cadastroPets.model.enums.PetSex;
import main.java.com.jprr.cadastroPets.model.exceptions.PetInfoException;
import main.java.com.jprr.cadastroPets.repository.FileRepository;

import java.io.IOException;
import java.util.Scanner;

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
}
