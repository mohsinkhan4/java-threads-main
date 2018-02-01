/**
 * 
 */
package threads01.starting;

class _Runner implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello" + i);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class App extends Thread {

	public static void main(String[] args) {

		Thread t1 = new Thread(new _Runner() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("Hello" + i);

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		t1.start();
		
		
	}

}
