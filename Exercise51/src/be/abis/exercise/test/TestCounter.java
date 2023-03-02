package be.abis.exercise.test;

import be.abis.exercise.task.Counter;

public class TestCounter {
	
	public static void main(String[] args) throws InterruptedException  {
		Counter c = new Counter();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(c);
		//System.out.println(t3.getPriority());
		//t3.setPriority(8);
		long begin = System.currentTimeMillis();
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		System.out.println("time="+ (System.currentTimeMillis() -begin) +" millis");
		
	}

}
