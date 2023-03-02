package be.abis.exercise.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFindFiles {

    public static void main(String[] args) {
        try {
            Files.walk(Paths.get("."))
                    .filter(path -> path.toString().toLowerCase().contains("course"))
                    .filter(path-> path.toString().endsWith(".java"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
