package threads;

import java.util.concurrent.Semaphore;

/** 
 * Bounded buffer producer consumer problem using semaphores.
 *
 */
public class BoundedBuffer1 {
	
	private Semaphore e;
	private Semaphore n;
	private Semaphore x;
	int size;
	int[] arr;
	int putPtr;
	int takePtr;
	int count;
	int iterations;
	
	public BoundedBuffer1(int size, int iterations) {
		this.size = size;
		arr = new int[size];
		this.iterations = iterations;
		e = new Semaphore(size);
		n = new Semaphore(0);
		x = new Semaphore(1);
		putPtr = 0;
		takePtr = 0;
		count = 0;
	}
	
	class Producer extends Thread {
		public void run() {
			try {
				for (int i=0; i<iterations; i++) {
					e.acquire();
					x.acquire();
					arr[putPtr++] = count;
					if (putPtr == size) {
						putPtr = 0;
					}
					System.out.println("Produced " + count);
					count++;
					x.release();
					n.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	
	class Consumer extends Thread {
		public void run() {
			try {
				for (int i=0; i<iterations; i++) {
					n.acquire();
					x.acquire();
					int res = arr[takePtr++];
					if (takePtr == size) {
						takePtr = 0;
					}
					System.out.println("Consumed " + res);
					x.release();
					e.release();
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		BoundedBuffer1 buf = new BoundedBuffer1(5, 25);
		
		
		Producer prod = buf.new Producer();
		prod.start();
		
		Consumer cons = buf.new Consumer();
		cons.start();
		
		try {
			prod.join();
			cons.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

}
