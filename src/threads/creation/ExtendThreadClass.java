package threads.creation;

public class ExtendThreadClass {


    public static void main(String[] args) {

        int numberOfTasks = 10;
        for(int i = 1; i <= numberOfTasks; i++) {
            Thread t = new Task();
            t.setName("Task - " + i);
            t.start();
        }
    }
}

class Task extends Thread {

    @Override
    public void  run() {
        System.out.println("I am " + Thread.currentThread().getName());
    }
}