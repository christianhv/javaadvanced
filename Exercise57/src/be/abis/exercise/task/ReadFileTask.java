package be.abis.exercise.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFileTask {
	
	public static long countLinesInFile(String fileName) throws IOException{
		Path p = Paths.get(fileName);
		return Files.lines(p)
				.parallel()
				.count();
	}

}
