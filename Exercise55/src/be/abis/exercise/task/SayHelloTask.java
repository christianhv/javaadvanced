package be.abis.exercise.task;

public class SayHelloTask {
    Counter c;

    public SayHelloTask(Counter c){
        this.c=c;

    }
    public void sayHello(){
        synchronized(c) {
            try {
                c.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hello everybody");
        }
    }
}
