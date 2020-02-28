package threads.communication;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

	List<Integer> aList = new ArrayList<Integer>(10);//shared object
	int size = 0;

	public void put(int i) {
		synchronized (aList) { //lock on shared data, own monitor for aList
			while (size == 10) {
				try {
					/*
					  Causes the current thread to wait until another thread invokes the notify()
					  method or the notifyAll() method for this object(aList)
					  Waiting only releases the lock for the object you call wait() on
					*/
					aList.wait();
				} catch (InterruptedException e) {
				}
			}
			
			aList.add(i); //add data to shared object
			size++; //increment buffer size
			System.out.println("Produced " + i);
			//System.out.println("Size "+Thread.currentThread().getName()+" "+size);
			aList.notifyAll();//notify waiting threads that data has been added to buffer
		}
	}

	public void get() {
		synchronized (aList) {
			while (size == 0) {
				try {
					aList.wait();
				} catch (InterruptedException e) {

				}
			}
			int x = aList.remove(size - 1);
			size--;
			System.out.println("Consumed " + x);
			//System.out.println("Size "+Thread.currentThread().getName()+" "+size);
			aList.notifyAll();
		}
	}

	public static void main(String[] args) {

		final ProducerConsumer producerConsumer = new ProducerConsumer();
		new Thread("Producer") {
			public void run() {
				for (int i = 0; i < 10; i++) {
					producerConsumer.put(i);
					try{Thread.sleep(1);}catch (InterruptedException e) {} //sleep for a while to see correct implementation, not necessary if output doesn't need to be printed to console
				}
			}
		}.start();
		new Thread("Consumer") {
			public void run() {
				for (int i = 0; i < 10; i++) {
					producerConsumer.get();
					try{Thread.sleep(1);}catch (InterruptedException e) {}
				}
			}
		}.start();
	}
}
