package threads.communication;

public class WaitNotifyExample {


    public static void main(String[] args) {

        final Object obj = new Object();
        new Thread(() -> {
            synchronized (obj) {
                try {
                    System.out.println("Going to wait");
                    obj.wait();//thread on obj is put to wait and lock is released
                    System.out.println("Wait over");
                } catch (InterruptedException e) {
                    System.out.println("Exception caught in " + Thread.currentThread().getName());
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Going to notify the waiting thread....");
                    obj.notify();//thread waiting on "obj" is notified
                    Thread.sleep(500);//remaining task needs to be finished before control can go back to the waiting thread
                    System.out.println("Sleep over");

                } catch (InterruptedException e) {
                    System.out.println("Exception caught in " + Thread.currentThread().getName());
                }
            }
        }).start();
    }
}
