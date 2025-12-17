package repository;

import enums.Sex;
import enums.Type;
import model.Pet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PetRepository {
    private static Path createFile(Pet pet) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String petFileName = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss_")) + pet.getName().replace(" ", "") + ".txt";
        Path petPath = Paths.get("src", "db", petFileName.toUpperCase());
        if (!Files.exists(petPath)) {
            Files.createDirectories(petPath.getParent());
            return Files.createFile(petPath);
        }
        throw new IOException("Pet file already exists: " + petFileName);
    }

    public static void savePet(Pet pet) throws IOException {
        Path filePet = createFile(pet);
        try (BufferedWriter writer = Files.newBufferedWriter(filePet)) {
            writer.write("1 - " + pet.getName() + "\n");
            writer.write("2 - " + pet.getType().getTYPE() + "\n");
            writer.write("3 - " + pet.getSex().getSEX() + "\n");
            writer.write("4 - " + pet.getAddress() + "\n");
            writer.write("5 - " + pet.getAge() + "\n");
            writer.write("6 - " + pet.getWeight() + "\n");
            writer.write("7 - " + pet.getBreed() + "\n");
        }
    }

    private static List<Path> getAllPathsPet() {
        List<Path> petFiles = new ArrayList<>();
        Path databasePath = Paths.get("src", "db");
        try (DirectoryStream<Path> files = Files.newDirectoryStream(databasePath, "*.txt")) {
            files.forEach(petFiles::add);
        } catch (IOException e) {
            System.err.println("Error reading pet files: " + e.getMessage());
        }
        return petFiles;
    }

    public static void removePet(int index) throws IOException {
        Files.delete(getAllPathsPet().get(index));
    }

    public static Pet findById(int index) throws IOException {
        return findAll().get(index);
    }

    public static List<Pet> findAll() {
        List<Pet> listPets = new ArrayList<>();
        getAllPathsPet().forEach(p -> {
            try (Stream<String> path = Files.lines(p)) {
                List<String> list = path.map(s -> s.replaceAll("^\\d+\\s-\\s", ""))
                        .map(s -> s.replaceAll("[\\[\\]]", ""))
                        .toList();
                Pet pet = new Pet(list.getFirst(),
                        (list.get(1).equalsIgnoreCase("Cachorro") ? Type.DOG : Type.CAT),
                        (list.get(2).equalsIgnoreCase("Gato") ? Sex.MALE : Sex.FEMALE),
                        list.get(3), Double.parseDouble(list.get(4)), Double.parseDouble(list.get(5)), list.get(6));
                listPets.add(pet);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        return listPets;
    }

    public static List<Path> getAllPets() {
        return getAllPathsPet();
    }

}
