package be.abis.exercise.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;

public class TestFileAttributes {

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));

        try {
            Path p = Paths.get("/temp/javacourses/courses.csv");
            System.out.println("regular file? " + Files.isRegularFile(p, new LinkOption[0]));
            System.out.println("directory? " + Files.isDirectory(p, new LinkOption[0]));
            System.out.println("symbolic link? " + Files.isSymbolicLink(p));
            System.out.println("hidden? " + Files.isHidden(p));
            System.out.println("readable? " + Files.isReadable(p));
            System.out.println("executable? " + Files.isExecutable(p));
            System.out.println("size: " + Files.size(p) + " bytes");
            System.out.println("last modified: " + Files.getLastModifiedTime(p));
            System.out.println("owner: " + Files.getOwner(p));
            System.out.println("-----------------------------------------");
            DosFileAttributes data = (DosFileAttributes)Files.readAttributes(p, DosFileAttributes.class);
            System.out.println("Is path a directory? " + data.isDirectory());
            System.out.println("Is path a regular file? " + data.isRegularFile());
            System.out.println("Is path a symbolic link? " + data.isSymbolicLink());
            System.out.println("Path not a file, directory, nor symbolic link? " + data.isOther());
            System.out.println("Size (in bytes): " + data.size());
            System.out.println("Creation date/time: " + data.creationTime());
            System.out.println("Last modified date/time: " + data.lastModifiedTime());
            System.out.println("Last accessed date/time: " + data.lastAccessTime());
            System.out.println("Unique file identifier (if available): " + data.fileKey());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
