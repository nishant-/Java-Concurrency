package threads.communication;

public class LongRunningAlgo {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            int i = Integer.MAX_VALUE;
            int diff = 0;
            for(int j = 0; j <= i; j++) {
                for(int k = j; k <= i; k++) {
                    if(Thread.currentThread().isInterrupted()) { //check for interrupts and take appropriate action
                        //in this case return
                        System.out.println(Thread.currentThread().getName() + " is interrupted and computation will end.");
                        return;
                    }
                    diff = diff - k - i;
                }
            }
            System.out.println(diff);
        });

        t.setName("LONG_COMPUTATION_THREAD");
        t.start();
        t.interrupt();

    }
}
