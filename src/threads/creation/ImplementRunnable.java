package threads.creation;

public class ImplementRunnable {

    public static void main(String[] args) {

        int numberOfTasks = 10;
        for(int i = 1; i <= 10; i++) {
            RunnableTask task = new RunnableTask();
            Thread t = new Thread(task);
            t.setName("Task - " + i);
            t.start();
        }

    }

}


class RunnableTask implements Runnable {


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}