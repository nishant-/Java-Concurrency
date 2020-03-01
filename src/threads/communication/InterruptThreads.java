package threads.communication;

public class InterruptThreads {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000000); //simulate blocking
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " is interrupted, and it will be terminated.");
            }
        });

        t.setName("BIG_BLOCKING_THREAD");
        t.start();
        t.interrupt(); //this sends and interrupt signal to thread t, the sleep method can be interrupted

    }

}
