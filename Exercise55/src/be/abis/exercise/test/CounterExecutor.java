package be.abis.exercise.test;
import be.abis.exercise.task.Counter;
import be.abis.exercise.task.SayHelloTask;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CounterExecutor {
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService es = Executors.newCachedThreadPool();
		//ExecutorService es = Executors.newFixedThreadPool(3);
		
		Counter c = new Counter();
		SayHelloTask sht = new SayHelloTask(c);
		Runnable rc = () -> c.count();
		
		long begin = System.currentTimeMillis();
		es.execute(rc);
		//es.awaitTermination(10, TimeUnit.SECONDS);
		es.execute(() -> System.out.println("doing something"));
		es.execute(rc);
		
		es.execute(() -> sht.sayHello());

		es.awaitTermination(10, TimeUnit.SECONDS);
	    es.shutdown();

		System.out.println("time="+ (System.currentTimeMillis() -begin) +" millis");
		System.out.println("finished");
	}

}
