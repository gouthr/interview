package threads;

public class SequencePrinter {
	private int numsInSeq;
	private int numThreads;
	private int number;
	private int count;
	
	public SequencePrinter(int numsInSeq, int numThreads) {
		this.numsInSeq = numsInSeq;
		this.numThreads = numThreads;
		this.number = 1;
		this.count = 1;
	}
	
	public synchronized void printer(int res) {
		while(count < numsInSeq-1) {
			while (this.number % numThreads != res) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.number = res + 1;
			System.out.println(Thread.currentThread().getName() + ": " + count);
			count++;
			notifyAll();
		}
	}

	public static void main(String[] args) {
		SequencePrinter seq = new SequencePrinter(20, 3);
		Thread t1 = new Thread("Thread1") {
			public void run() {
				seq.printer(1);
			}
		};
		t1.start();
		
		Thread t2 = new Thread("Thread2") {
			public void run() {
				seq.printer(2);
			}
		};
		t2.start();
		
		Thread t3 = new Thread("Thread3") {
			public void run() {
				seq.printer(0);
			}
		};
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
