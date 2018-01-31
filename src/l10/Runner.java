package l10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	private void increment() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public void firstThread() throws InterruptedException {
		lock.lock();
		
		System.out.println("Waiting...");
		condition.await();
		System.out.println("Woken up...");
		
		try {
			increment();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	@SuppressWarnings("resource")
	public void secondThread() throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Press the return key.");
		new Scanner(System.in).nextLine();
		System.out.println("Got the return key.");
		
		condition.signal();
		
		try {
			increment();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void finished() {
		System.out.println("Count is : " + count);
	}

//	private LinkedList<Integer> list = new LinkedList<Integer>();
//	private final int LIMIT = 10;
//	private Object lock = new Object();

/*	public void produce() throws InterruptedException {

		int value = 0;
		while (true) {
			synchronized (lock) {
				// the condition that imposed wait should be checked again before wait is
				// released properly
				while (list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException {

		Random random = new Random();

		while (true) {
			synchronized (lock) {
				while (list.size() == 0) {
					lock.wait();
				}
				System.out.println("List size is : " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is : " + value);
				lock.notify();
			}

			Thread.sleep(random.nextInt(100));
		}
	}*/

}
