package threads;

import java.util.concurrent.Semaphore;

/**
 * Reader writer problem where writers have priority.
 *
 */
public class ReaderWriter1 {

	private Semaphore y;
	private Semaphore x;
	private Semaphore wsem;
	private Semaphore rsem;
	private Semaphore z;
	private int writers;
	private int readers;
	private int iterations;

	public ReaderWriter1(int iterations) {
		this.iterations = iterations;
		y = new Semaphore(1);
		x = new Semaphore(1);
		wsem = new Semaphore(1);
		rsem = new Semaphore(1);
		z = new Semaphore(1);
	}

	public class Reader extends Thread {
		public void run() {
			try {
				for (int i = 0; i < iterations; i++) {
					z.acquire(); // For readers, one additional semaphore is
									// needed. A long queue must not be allowed
									// to build up on rsem; otherwise writers
									// will not be able to jump the queue
					rsem.acquire(); // Semaphore rsem that inhibits all readers
									// while there is at least one writer
									// desiring access to data area
					x.acquire();
					readers++;
					if (readers == 1) {
						wsem.acquire();
					}
					x.release();
					rsem.release();
					z.release();

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
				for (int i = 0; i < iterations; i++) {
					y.acquire();
					writers++;
					if (writers == 1) {
						rsem.acquire();
					}
					y.release();
					wsem.acquire();
					System.out.println("Performing write.");
					wsem.release();
					y.acquire();
					writers--;
					if (writers == 0) {
						rsem.release();
					}
					y.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ReaderWriter1 rw = new ReaderWriter1(20);

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
