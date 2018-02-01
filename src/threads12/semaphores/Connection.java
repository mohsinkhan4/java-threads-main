package threads12.semaphores;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();
	private int connections = 0;

	// true means whichever block calls acquire on semaphore will be the first to
	// get a permit when a permit becomes available.
	Semaphore sem = new Semaphore(10, true);

	private Connection() {

	}

	public static Connection getInstance() {
		return instance;
	}

	public void connect() {
		try {
			sem.acquire(); // semaphore acquire
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			doConnect();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			sem.release(); // semaphore release
		}
	}

	public void doConnect() throws InterruptedException {

		synchronized (this) {
			connections++;
			System.out.println("Current connections: " + connections);
		}

		Thread.sleep(2000);

		synchronized (this) {
			connections--;
			System.out.println("Current connections: " + connections);
		}

	}

}
