package main.java.com.jprr.cadastroPets.app;

import main.java.com.jprr.cadastroPets.model.entity.Pet;
import main.java.com.jprr.cadastroPets.repository.FileRepository;
import main.java.com.jprr.cadastroPets.service.PetService;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)){
            int opt = 0;
            FileRepository fr = new FileRepository();
            PetService ps = new PetService();

            do { //TODO: colocar todos os try-catch's dentro do loop
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
        catch (IOException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }

    }
}
