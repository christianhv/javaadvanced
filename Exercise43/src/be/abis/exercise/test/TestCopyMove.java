package be.abis.exercise.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestCopyMove {

    public static void main(String[] args) {
        Path p1 = Paths.get("/temp/javacourses/courses.csv");
        Path p2 = Paths.get("/temp/javacourses/courses2.csv");
        Path p3 = Paths.get("/temp/javacourses/inputfiles/courses2.csv");
        Path p4 = Paths.get("/temp/javacourses/inputfiles");
        try {
            Files.copy(p1,p2, StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES);
            if(!Files.exists(p4))Files.createDirectory(p4);
            Files.move(p2,p3, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
