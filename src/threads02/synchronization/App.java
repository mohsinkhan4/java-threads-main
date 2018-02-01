package threads02.synchronization;

import java.util.Scanner;

class Processor extends Thread {

	private volatile boolean running = true;

	public void run() {
		while (running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void shutdown() {
		running = false;
	}
	
	public void restart() {
		running = true;
	}
}

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Processor proc1 = new Processor();
		proc1.start();

		System.out.println("return to stop..");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

		proc1.shutdown();
	}

}
