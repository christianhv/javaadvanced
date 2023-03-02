package be.abis.exercise.test;

import be.abis.exercise.task.Counter;
import be.abis.exercise.task.SayHelloTask;

public class TestTasks {
	
	public static void main(String[] args) throws InterruptedException  {
		
		Runnable c =  () -> new Counter().count();
		SayHelloTask sht = new SayHelloTask();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(() -> System.out.println("doing something"));
		Thread t4 = new Thread(() -> sht.sayHello());
		//System.out.println(t3.getPriority());
		//t3.setPriority(8);
		long begin = System.nanoTime();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		System.out.println("time="+ (System.nanoTime() -begin) +" nanos");
		
	}

}
