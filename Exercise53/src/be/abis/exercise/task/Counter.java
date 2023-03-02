package be.abis.exercise.task;

public class Counter {

	public synchronized void count() {
		for (int count=1; count<=1000;count++){
			System.out.println(Thread.currentThread().getName() + ":" +count);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Thread.yield();
		}
	}
	
	
	
	
}
