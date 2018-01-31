package l12;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	Account acc1 = new Account();
	Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquireLock(Lock lock1, Lock lock2) throws InterruptedException {
		while(true) {
			boolean acquiredLock1 = false;
			boolean acquiredLock2 = false;
			
			try {
				acquiredLock1 = lock1.tryLock();
				acquiredLock2 = lock2.tryLock();
			} finally {
				if(acquiredLock1 && acquiredLock2) {
					return;
				}
				if(acquiredLock1) {
					lock1.unlock();
				}
				if(acquiredLock2) {
					lock2.unlock();
				}
			}
			Thread.sleep(1);
		}
	}

	public void firstThread() throws InterruptedException {
		Random random = new Random();
		
		for (int i = 0; i < 10000; i++) {

			acquireLock(lock1, lock2);

			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread() throws InterruptedException {
		Random random = new Random();

		for (int i = 0; i < 10000; i++) {

			acquireLock(lock1, lock2);
			
			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void finished() {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}

}
