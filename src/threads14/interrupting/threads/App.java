package threads14.interrupting.threads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Starting... ");

		/*Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Random random = new Random();
				for (int i = 0; i < 1E10; i++) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("1. interrupted");
						break;
					}
					Math.sin(random.nextDouble());
				}
			}
		});
		t.start();
		Thread.sleep(500);
		t.interrupt();
		t.join();*/


		ExecutorService executor = Executors.newCachedThreadPool();
		Future<?> future = executor.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				Random random = new Random();

				for (int i = 0; i < 1E10; i++) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("2. interrupted");
						break;
					}
					Math.sin(random.nextDouble());
				}
				return null;
			}

		});
		
		executor.shutdown();
		Thread.sleep(50);
//		future.cancel(true);
		executor.shutdownNow();
		executor.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("Finished... ");
	}

}
