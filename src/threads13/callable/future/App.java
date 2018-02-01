package threads13.callable.future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();

//		Future<Integer> future = executor.submit(new Callable<Integer>() {
		Future<?> future = executor.submit(new Callable<Void>() {
			@Override
//			public Integer call() throws IOException {
			public Void call() throws IOException {

				Random random = new Random();
				int duration = random.nextInt(4000);
				
				if(duration > 2000) {
					throw new IOException("Sleeping for too long.");
				}

				System.out.println("Starting ... ");
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Finished ... ");
//				return duration;
				return null;
			}
		});

		executor.shutdown();
		
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			IOException ioe = (IOException) e.getCause();
			System.out.println(ioe.getMessage());
		}


	}

}
