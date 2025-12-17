package services;

import enums.Sex;
import enums.Type;
import utils.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FormService {
    final static Scanner SCANNER = new Scanner(System.in);

    private static List<String> readForm() {
        Path formPath = Paths.get("src\\forms\\formulario.txt");
        try (Stream<String> lines = Files.lines(formPath)) {
            return lines.filter(l -> !l.isBlank())
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Type answerTypePetQuestion(){
        try {
            System.out.println(readForm().get(1));
            String typePet = SCANNER.nextLine();

            if (!Validator.validateTypePet(typePet))
                throw new IllegalArgumentException("Digite um tipo válido: (Gato ou Cachorro)");

            return typePet.equalsIgnoreCase("Cachorro") ? Type.DOG : Type.CAT;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("========== Digite novamente ============");
            return answerTypePetQuestion();
        }
    }


    public static Sex answerSexPetQuestion() {
        try {
            System.out.println(readForm().get(2));
            String sexPet = SCANNER.nextLine();
            if (!Validator.validateSexPet(sexPet))
                throw new IllegalArgumentException("Digite um sexo válido: (Macho/Femea");
            return sexPet.equalsIgnoreCase("Macho") ? Sex.MALE : Sex.FEMALE;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerSexPetQuestion();
        }
    }

    public static String answerNameQuestion() {
        try {
            System.out.println(readForm().getFirst());
            String namePet = SCANNER.nextLine();
            if (namePet.isBlank()) {
                return "NÃO INFORMADO";
            }
            if (!Validator.validateNamePet(namePet))
                throw new IllegalArgumentException("Informe um sobrenome: Não permitido carateceres especiais");
            return namePet;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerNameQuestion();
        }
    }

    public static String answerAddressPetQuestion() {
        try {
            System.out.println(readForm().get(3));
            String addressPet = SCANNER.nextLine();
            if (addressPet.isBlank()) {
                return "NAO ENCONTRADO";
            }
            return addressPet;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerAddressPetQuestion();
        }
    }

    public static Double answerAgePetQuestion() {
        try {
            System.out.println(readForm().get(4));
            String agePet = SCANNER.nextLine();
            if (!Validator.validateAgePet(agePet)) {
                throw new IllegalArgumentException("Idade acima de 20 anos não permitida");
            }
            return Double.parseDouble(agePet.replace(",", "."));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerAgePetQuestion();
        }
    }

    public static Double answerWeightPetQuestion() {
        try {
            System.out.println(readForm().get(5));
            String weightPet = SCANNER.nextLine();
            if (!Validator.validateWeightPet(weightPet)) {
                throw new IllegalArgumentException("Peso abaixo de 0.5kg ou acima de 60kg não permitido");
            }
            return Double.parseDouble(weightPet.replace(",", "."));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerWeightPetQuestion();
        }
    }

    public static String answerRacePetQuestion() {
        try {
            System.out.println(readForm().get(6));
            String racePet = SCANNER.nextLine();
            if (racePet.isBlank()) {
                return "NÃO INFORMADO";
            }
            if (!Validator.validateRacePet(racePet)) {
                throw new IllegalArgumentException("Não permitido números caraceteres especiais");
            }
            return racePet;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerRacePetQuestion();
        }
    }


}
