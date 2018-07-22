package threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Bounded buffer producer consumer problem.
 *
 */
public class BoundedBuffer {
	
	private Lock lock;	
	private Condition condBufferSize;
	private Condition condEmpty;
	private int bufferSize;
	private int count;
	private int[] arr;
	private int putPtr;
	private int takePtr;
	
	public BoundedBuffer(int bufferSize) {
		lock = new ReentrantLock();	
		condBufferSize = lock.newCondition();
		condEmpty = lock.newCondition();
		this.bufferSize = bufferSize;
		count  = 0;
		arr = new int[bufferSize];	
		putPtr = 0;
		takePtr = 0;
	}
	
	public void producer(int number) {
		try {
			lock.lock();
			while (count == bufferSize) {
				try {
					condBufferSize.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			arr[putPtr++] = number;
			count++;
			if (putPtr == bufferSize) {
				putPtr = 0;
			}
			condEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public int consumer() {
		try {
			lock.lock();
			while (count == 0) {
				try {
					condEmpty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int res = arr[takePtr++];
			--count;
			if (takePtr == bufferSize) {
				takePtr = 0;
			}
			condBufferSize.signal();
			return res;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		int bufSize = 5;
		BoundedBuffer buf = new BoundedBuffer(bufSize);
		
		Thread t1 = new Thread() {
			public void run() {
				for (int i = 1; i <= 10; i++) {
					buf.producer(i);
					System.out.println("Produced number: " + i);
				}
			}
		};
		t1.start();
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		Thread t2 = new Thread() {
			public void run() {
				for (int i = 1; i <= 10; i++) {
					int res = buf.consumer();
					System.out.println("Consumed number: " + res);
				}
			}
		};
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
