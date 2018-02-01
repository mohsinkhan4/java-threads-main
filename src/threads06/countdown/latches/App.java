package threads06.countdown.latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {

	private CountDownLatch latch;

	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Started.");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		latch.countDown();
		
		System.out.println("Run Completed.");

	}

}

public class App {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(3);

		// create pool
		ExecutorService executor = Executors.newFixedThreadPool(3);
		// submit tasks (runnable)
		for (int i = 0; i < 3; i++) {
			executor.submit(new Processor(latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Await Completed.");
		
	}

}
