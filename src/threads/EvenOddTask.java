package threads;

public class EvenOddTask {
	
	private int numsInSeq;
	private int count;
	
	public EvenOddTask(int numsInSeq) {
		this.numsInSeq = numsInSeq;
		this.count = 1;
	}
	
	public synchronized void printer(int num) {
		while (count < numsInSeq) {
			while (count % 2 != num) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + " : " + count);
			count++;
			notifyAll();
		}
	}
	
	public static void main(String[] args) {
		EvenOddTask task = new EvenOddTask(10);
		Thread t1 = new Thread("EvenThread") {
			public void run() {
				task.printer(0);
			}
		};
		t1.start();
		
		Thread t2 = new Thread("OddThread") {
			public void run() {
				task.printer(1);
			}
		};
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}