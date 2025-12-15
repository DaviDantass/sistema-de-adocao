import Model.Animal;
import service.RegisterPet;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        RegisterPet registerPet = new RegisterPet();

        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar pet");
            System.out.println("2 - Listar pets cadastrados");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                String input = sc.nextLine();

                if (input.isEmpty()) {
                    System.out.println("Entrada vazia. Digite um número.");
                    continue;
                }

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        Animal pet = registerPet.registerPet();
                        if (pet != null) {
                            System.out.println("Pet cadastrado: " + pet.getName());
                        }
                        break;

                    case 2:
                        List<Animal> pets = registerPet.getPets();
                        if (pets.isEmpty()) {
                            System.out.println("Nenhum pet cadastrado.");
                        } else {
                            System.out.println("\n=== LISTA DE PETS ===");
                            int i = 1;
                            for (Animal p : pets) {
                                System.out.println(i + " - " + p.getName() + " (" + p.getType() + ")");
                                i++;
                            }
                        }
                        break;

                    case 3:
                        running = false;
                        System.out.println("Saindo do programa...");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: digite apenas números.");
            }
        }

        sc.close();
    }
}
