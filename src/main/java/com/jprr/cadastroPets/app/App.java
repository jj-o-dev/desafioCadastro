package main.java.com.jprr.cadastroPets.app;

import main.java.com.jprr.cadastroPets.model.entity.Pet;
import main.java.com.jprr.cadastroPets.repository.FileRepository;
import main.java.com.jprr.cadastroPets.service.PetService;

import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)){
            int opt = 0;
            FileRepository fr = new FileRepository();
            PetService ps = new PetService();
            List<Pet> petList = new ArrayList<>();

            do { //TODO: colocar todos os try-catch's dentro do loop
                System.out.println();
                fr.readFile(fr.MENU_PATH);
                System.out.print("Digite o número da operação desejada: ");
                try {
                    opt = 0;
                    opt = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Erro: digite apenas números válidos!");
                }

                scan.nextLine();
                switch (opt) {
                    case 1:
                        Pet newPet = ps.createPet(scan);
                        if (newPet != null) {
                            petList.add(newPet);
                            fr.createPetFile(petList.getLast());
                            System.out.println("Pet cadastrado com sucesso.");
                        } else {
                            System.out.println("Erro na criação, tente novamente.");
                        }

                        break;
                    case 2:
                        System.out.println();
                        fr.readFile(fr.SEARCH_PATH);
                        String criteria = scan.nextLine();
                        Set<String> searchResult = ps.searchPet(scan, criteria);

                        if (searchResult != null) {
                            if (!searchResult.isEmpty()) {
                                System.out.println("\nRegistro(s) encontrado(s): ");
                                searchResult.forEach(System.out::println);
                            } else {
                                System.out.println("\nNenhum pet encontrado com estas informações.");
                            }
                        }
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
        catch (IOException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }

    }
}
