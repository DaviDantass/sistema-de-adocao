package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormReader {

    public static List<String> lerPerguntas() {

        List<String> perguntas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/forms/form.txt"))) {

            String linha;
            while ((linha = reader.readLine()) != null) {
                perguntas.add(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return perguntas;
    }
}
