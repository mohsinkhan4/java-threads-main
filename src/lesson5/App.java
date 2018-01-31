package lesson5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

	private int id;

	public Processor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Starting: " + id);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Completing: " + id);
	}

}

public class App {

	public static void main(String[] args) {

		// create pool
		ExecutorService executor = Executors.newFixedThreadPool(2);
		// submit tasks (runnable)
		for (int i = 0; i < 5; i++) {
			executor.submit(new Processor(i));
		}
		// stop executor service from submitting new tasks and then terminate the
		// service. Else java.util.concurrent.RejectedExecutionException
		executor.shutdown();
		System.out.println("All tasks submitted.");

		// don't wait forever for all tasks to complete.
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All tasks completed.");
	}

}
