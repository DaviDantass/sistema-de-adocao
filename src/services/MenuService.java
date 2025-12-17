package services;

import enums.Sex;
import enums.Type;
import model.Pet;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuService {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void cadastrar() {
        String namePet = FormService.answerNameQuestion();
        Type typePet = FormService.answerTypePetQuestion();
        Sex sexPet = FormService.answerSexPetQuestion();
        String addressPet = FormService.answerAddressPetQuestion();
        Double agePet = FormService.answerAgePetQuestion();
        Double weightPet = FormService.answerWeightPetQuestion();
        String racePet = FormService.answerRacePetQuestion();

        Pet pet = new Pet(namePet, typePet, sexPet, addressPet, agePet, weightPet, racePet);
        try {
            PetService.save(pet);
            System.out.println("Pet cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar pet: " + e.getMessage());
        }
    }

    public static void listarTodos() {
        try {
            AtomicInteger count = new AtomicInteger(1);
            System.out.printf("%-10s %-22s %-15s %-15s %-15s%n", "Id", "Nome", "Sexo", "Idade", "Peso");
            PetService.findAll().forEach(p -> {
                System.out.printf("%-5d %-27s %-15s %-15s %-15s%n", 
                        count.getAndIncrement(),
                        p.getName(),
                        p.getSex().getSEX(),
                        (p.getAge() < 1 ? p.getAge() + " meses" : p.getAge().shortValue() + " anos"),
                        (p.getWeight() < 1 ? p.getWeight() + " kgs" : p.getWeight().shortValue() + " kg"));
            });
            System.out.println("Pressione Enter para continuar");
            SCANNER.nextLine();
        } catch (IOException e) {
            System.out.println("Erro ao listar pets: " + e.getMessage());
        }
    }

    public static void buscar() {
        try {
            System.out.println("Digite o ID do pet");
            Integer id = Integer.parseInt(SCANNER.nextLine());
            Pet pet = PetService.findById(id - 1);
            System.out.printf("%-22s %-10s %-8s %-10s %-7s%n", "Nome", "Sexo", "Idade", "Peso", "Raça");

            System.out.printf("%-22s %-10s %-8s %-8s %-5s%n",
                    pet.getName(),
                    pet.getSex().getSEX(),
                    (pet.getAge() < 1 ? pet.getAge() + " meses" : pet.getAge().shortValue() + " anos"),
                    (pet.getWeight() < 1 ? pet.getWeight() + " kgs" : pet.getWeight().shortValue() + " kg"),
                    pet.getBreed());

            System.out.println("Pressione Enter para continuar");
            SCANNER.nextLine();
        } catch (IOException e) {
            System.out.println("Erro ao buscar pet: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número.");
        }
    }

    public static void editar() {
        try {
            System.out.println("Digite o ID do pet");
            Integer id = Integer.parseInt(SCANNER.nextLine());
            Pet pet = PetService.findById(id - 1);
            System.out.printf("%-22s %-10s %-8s %-10s %-7s%n", "Nome", "Sexo", "Idade", "Peso", "Raça");

            System.out.printf("%-22s %-10s %-8s %-8s %-5s%n",
                    pet.getName(),
                    pet.getSex().getSEX(),
                    pet.getAge().shortValue() + " anos",
                    pet.getWeight().shortValue() + " kg",
                    pet.getBreed());

            System.out.println("\n=== Atualizando informações ===");
            String name = FormService.answerNameQuestion();
            String address = FormService.answerAddressPetQuestion();
            Double age = FormService.answerAgePetQuestion();
            Double weight = FormService.answerWeightPetQuestion();
            String race = FormService.answerRacePetQuestion();
            
            PetService.removeById(id - 1);
            Pet newPet = new Pet(name, pet.getType(), pet.getSex(), address, age, weight, race);
            PetService.save(newPet);
            
            System.out.println("Pet atualizado com sucesso!");
            System.out.println("Pressione Enter para continuar");
            SCANNER.nextLine();
        } catch (IOException e) {
            System.out.println("Erro ao editar pet: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número.");
        }
    }

    public static void remover() {
        try {
            System.out.println("Digite o ID do pet");
            int id = Integer.parseInt(SCANNER.nextLine());
            Pet pet = PetService.findById(id - 1);
            System.out.printf("%-22s %-10s %-8s %-10s %-7s%n", "Nome", "Sexo", "Idade", "Peso", "Raça");

            System.out.printf("%-22s %-10s %-8s %-8s %-5s%n",
                    pet.getName(),
                    pet.getSex().getSEX(),
                    pet.getAge().shortValue() + " anos",
                    pet.getWeight().shortValue() + " kg",
                    pet.getBreed());
            
            System.out.println("\nDeseja remover este pet? (S/N)");
            String confirma = SCANNER.nextLine();
            
            if (confirma.equalsIgnoreCase("s")) {
                PetService.removeById(id - 1);
                System.out.println("Pet removido com sucesso!");
            } else {
                System.out.println("Remoção cancelada.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao remover pet: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número.");
        }
    }

    public static void showMenu() {
        System.out.println("--- Seja Bem-vindo ao Sistema de Adoção de Pets ---");
        while (true) {
            System.out.println("\nEscolha uma das opções a seguir:");
            System.out.println("1 - Cadastrar um novo pet");
            System.out.println("2 - Listar todos os pets cadastrados");
            System.out.println("3 - Buscar um pet");
            System.out.println("4 - Editar um pet");
            System.out.println("5 - Deletar um pet cadastrado");
            System.out.println("6 - Sair");
            
            try {
                int option = Integer.parseInt(SCANNER.nextLine());
                
                switch (option) {
                    case 1 -> cadastrar();
                    case 2 -> listarTodos();
                    case 3 -> buscar();
                    case 4 -> editar();
                    case 5 -> remover();
                    case 6 -> {
                        System.out.println("Saindo... Até logo!");
                        return;
                    }
                    default -> System.out.println("Opção inválida! Escolha um número de 1 a 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }
}
