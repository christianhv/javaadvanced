package be.abis.exercise.task;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

	public synchronized void count() {
		int randomInt = ThreadLocalRandom.current().nextInt(0,1000);
		for (AtomicInteger count = new AtomicInteger(1); count.get()<=randomInt; count.incrementAndGet()){
			System.out.println(Thread.currentThread().getName() + ":" +count);

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	
	
}
