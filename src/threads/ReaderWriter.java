package threads;

import java.util.concurrent.Semaphore;

/**
 * Reader writer problem where readers have priority.
 *
 */
public class ReaderWriter {
	
	private Semaphore wsem;
	private Semaphore x;
	private int readers;
	private int iterations;
	
	public ReaderWriter(int iterations) {
		this.iterations = iterations;
		wsem = new Semaphore(1);
		x = new Semaphore(1);
		readers = 0;
	}
	
	public class Reader extends Thread {
		public void run() {
			try {
				for (int i=0; i<iterations; i++) {
					x.acquire();
					readers++;
					if (readers == 1) {
						wsem.acquire();
					}
					x.release();
					System.out.println("Performing read.");
					x.acquire();
					readers--;
					if (readers == 0) {
						wsem.release();
					}
					x.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public class Writer extends Thread {
		public void run() {
			try {
				for (int i=0; i<iterations; i++) {
					wsem.acquire();
					System.out.println("Performing write.");
					wsem.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ReaderWriter rw = new ReaderWriter(20);
		
		Reader r = rw.new Reader();
		r.start();
		
		Writer w = rw.new Writer();
		w.start();
		
		try {
			r.join();
			w.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
