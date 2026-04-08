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
            List<String> resultList = new ArrayList<>();

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
                        String fileString;
                        resultList.clear();
                        resultList.addAll(fr.printAllFiles());

                        if (!resultList.isEmpty()) {
                            System.out.println("\nTodos os cadastros: ");
                            for (int i = 0; i < resultList.size(); i++) {
                                System.out.println((i + 1) + ". " + resultList.get(i));
                            }
                            System.out.print("\nInsira o índice (número da linha) do arquivo que deseja modificar: ");
                            int index = scan.nextInt() - 1;

                            if (index >= resultList.size() || index < 0) {
                                System.out.println("Este índice não existe!");
                                continue;
                            }

                            fileString = resultList.get(index);

                            System.out.println();
                            scan.nextLine();
                            fr.readFile(fr.UPDATE_PATH);
                            String update = scan.nextLine();

                            ps.updatePet(scan, update, fileString);
                        } else {
                            System.out.println("\nNenhum cadastro feito ainda.");
                        }

                        break;
                    case 3:
                        resultList.clear();
                        resultList.addAll(fr.printAllFiles());

                        if (!resultList.isEmpty()) {
                            System.out.println("\nTodos os cadastros: ");
                            for (int i = 0; i < resultList.size(); i++) {
                                System.out.println((i + 1) + ". " + resultList.get(i));
                            }

                            System.out.print("\nInsira o índice (número da linha) do arquivo que deseja deletar: ");
                            int index = scan.nextInt() - 1;

                            if (index >= resultList.size() || index < 0) {
                                System.out.println("Este índice não existe!");
                                continue;
                            }

                            scan.nextLine();
                            String fileToDelete = resultList.get(index);
                            ps.deletePet(scan, fileToDelete);

                        } else {
                            System.out.println("\nNenhum cadastro feito ainda.");
                        }

                        break;
                    case 4:
                        resultList.clear();
                        resultList.addAll(fr.printAllFiles());

                        if (!resultList.isEmpty()) {
                            System.out.println("\nTodos os cadastros: ");
                            for (int i = 0; i < resultList.size(); i++) {
                                System.out.println((i + 1) + ". " + resultList.get(i));
                            }
                        } else {
                            System.out.println("\nNenhum cadastro feito ainda.");
                        }

                        break;
                    case 5:
                        System.out.println();
                        fr.readFile(fr.SEARCH_PATH);
                        String criteria = scan.nextLine();
                        resultList.clear();
                        resultList.addAll(ps.searchPet(scan, criteria));

                        if (!resultList.isEmpty()) {
                            System.out.println("\nCadastro(s) encontrado(s): ");
                            for (int i = 0; i < resultList.size(); i++) {
                                System.out.println((i + 1) + ". " + resultList.get(i));
                            }
                        } else {
                            System.out.println("\nNenhum cadastro encontrado com estas informações.");
                        }

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
            System.out.println("Falha ao acessar arquivos");
        }
        catch (InputMismatchException e) {
            System.out.println("Input inválido, digite apenas valores válidos");
        }

    }
}
