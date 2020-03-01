package threads.communication;

public class DaemonThread {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            for(;;){
                //infinite loop
            }
        });

        t.setDaemon(true); //does not prevent the jvm from exiting once all user threads have finished,
        //if thread t is not set as daemon then it will block and jvm will not exit.
        t.start();
    }
}
