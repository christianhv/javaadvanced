package be.abis.exercise.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFileTask {
	
	public static long countLinesInFile(String fileName) throws IOException{
		Path p = Paths.get(fileName);
		return Files.lines(p)
				.parallel()
				.count();
	}

	public static void readFile(String fileName) throws IOException{
		Path p = Paths.get(fileName);
		 Files.lines(p)
				.parallel().forEach(System.out::println);
	}


}
