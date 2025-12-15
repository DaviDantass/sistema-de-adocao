package service;

import Model.Animal;
import Model.Cat;
import Model.Dog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterPet {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Animal> pets = new ArrayList<>();

    public Animal registerPet() {

        List<String> perguntas = FormReader.lerPerguntas();
        List<String> respostas = new ArrayList<>();

        System.out.println("\n=== CADASTRO DE PET ===");

        for (String pergunta : perguntas) {
            System.out.println(pergunta);
            System.out.print("> ");
            respostas.add(scanner.nextLine());
        }

        try {
            String name = respostas.get(0);
            String type = respostas.get(1);
            char sex = respostas.get(2).charAt(0);
            String address = respostas.get(3);
            int age = Integer.parseInt(respostas.get(4));
            double weight = Double.parseDouble(respostas.get(5));
            String race = respostas.get(6);

            Animal pet;

            if (type.equalsIgnoreCase("gato")) {
                pet = new Cat(name, type, race, sex, address, age, weight);
            } else if (type.equalsIgnoreCase("cachorro")) {
                pet = new Dog(name, type, race, sex, address, age, weight);
            } else {
                System.out.println("Tipo inválido. Pet não cadastrado.");
                return null;
            }

            pets.add(pet);

            System.out.println("Pet cadastrado com sucesso!");
            return pet;

        } catch (NumberFormatException e) {
            System.out.println("Erro: idade ou peso inválido.");
            return null;
        }
    }

    public List<Animal> getPets() {
        return pets;
    }
}
