package be.abis.exercise.test;


import be.abis.exercise.task.Counter;
import be.abis.exercise.task.ReadFileTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class TestTasks {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService es = Executors.newCachedThreadPool();
		//ExecutorService es = Executors.newFixedThreadPool(3);
		
		Counter c = new Counter();
		Runnable rc = () -> c.count();
		
		long begin = System.currentTimeMillis();
		es.execute(rc);
		//es.awaitTermination(10, TimeUnit.SECONDS);
		es.execute(() -> System.out.println("doing something"));
		//es.execute(rc);
		
		es.execute(() -> System.out.println("and something else"));
		
		Future<Long> res = es.submit(()-> ReadFileTask.countLinesInFile("/temp/javacourses/coursesbig.csv"));
	    System.out.println("number of lines: " + res.get());
		//es.submit(()-> Files.lines(Paths.get("/temp/javacourses/coursesbig.csv")).parallel());
		es.submit(()-> {
			try {
				ReadFileTask.readFile("/temp/javacourses/coursesbig.csv");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		es.shutdown();
	    es.awaitTermination(20, TimeUnit.SECONDS);
		System.out.println("time="+ (System.currentTimeMillis() -begin) +" millis");
		
	}

}
