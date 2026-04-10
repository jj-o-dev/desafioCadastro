package main.java.com.jprr.cadastroPets.app;

import main.java.com.jprr.cadastroPets.model.entity.Pet;
import main.java.com.jprr.cadastroPets.repository.FileRepository;
import main.java.com.jprr.cadastroPets.service.PetService;

import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opt = 0;
        FileRepository fr = new FileRepository();
        PetService ps = new PetService();
        List<Pet> petList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        String fileError = "Erro ao acessar arquivo";
        String badInputError = "Input inválido, digite apenas números inteiros";

        do {
            System.out.println();
            try {
                fr.readFile(fr.MENU_PATH);
                System.out.print("Digite o número da operação desejada: ");
                opt = 0;
                opt = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Erro: digite apenas números válidos!");
            }
            catch (IOException e) {
                System.out.println(fileError);
            }

            scan.nextLine();
            switch (opt) {
                case 1:
                    try {
                        Pet newPet = ps.createPet(scan);
                        if (newPet != null) {
                            petList.add(newPet);
                            fr.createPetFile(petList.getLast());
                            System.out.println("Pet cadastrado com sucesso.");
                        } else {
                            System.out.println("Erro na criação, tente novamente.");
                        }
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Erro: digite apenas números válidos!");
                    }
                    catch (IOException e) {
                        System.out.println(fileError);
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

                        try {
                            int index = scan.nextInt() - 1;
                            if (index >= resultList.size() || index < 0) {
                                System.out.println("Este índice não existe!");
                                continue;
                            }

                            fileString = resultList.get(index);
                        } catch (InputMismatchException e) {
                            System.out.println(badInputError);
                            continue;
                        }

                        System.out.println();
                        scan.nextLine();
                        try {
                            fr.readFile(fr.UPDATE_PATH);
                        } catch (IOException e) {
                            System.out.println(fileError);
                            continue;
                        }

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
                        try {
                            int index = scan.nextInt() - 1;
                            if (index >= resultList.size() || index < 0) {
                                System.out.println("Este índice não existe!");
                                continue;
                            }

                            scan.nextLine();
                            String fileToDelete = resultList.get(index);
                            ps.deletePet(scan, fileToDelete);
                        } catch (InputMismatchException e) {
                            System.out.println(badInputError);
                        }
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
                    try {
                        fr.readFile(fr.SEARCH_PATH);
                    } catch (IOException e) {
                        System.out.println(fileError);
                    }

                    String criteria = scan.nextLine();
                    resultList.clear();
                    Set<String> set = ps.searchPet(scan, criteria);
                    if (set != null) {
                        resultList.addAll(set);

                        if (!resultList.isEmpty()) {
                            System.out.println("\nCadastro(s) encontrado(s): ");
                            for (int i = 0; i < resultList.size(); i++) {
                                System.out.println((i + 1) + ". " + resultList.get(i));
                            }
                        } else {
                            System.out.println("\nNenhum cadastro encontrado com estas informações.");
                        }
                    }

                    break;
                case 6:
                    System.out.println("Finalizando programa...");
                    break;
                default:
                    System.out.println("\nNúmero de operação inválido!");
            }
        } while(opt != 6);
        scan.close();
    }
}
