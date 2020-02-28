package threads.communication;

public class DeadLock {

	public void aMethod() {
		synchronized (String.class) {
			System.out.println("Acquired Lock on String class");
			synchronized (Integer.class) {
				System.out.println("Acquired Lock on Integer class");
			}
		}

	}

	public void bMethod() {
		synchronized (Integer.class) {
			System.out.println("Acquired Lock on Integer class");
			synchronized (String.class) {
				System.out.println("Acquired Lock on String class");
			}
		}
	}

	//Run a few times and deadlock might occur
	public static void main(String[] args) {

		final DeadLock d = new DeadLock();

		new Thread(() -> {
			 {
				d.aMethod();
				d.bMethod();
			}
		}).start();
		new Thread(() -> {
			 {
				d.aMethod();
				d.bMethod();
			}
		}).start();

	}
}
