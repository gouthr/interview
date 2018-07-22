package threads;

public class EvenOddTask implements Runnable {
	
	private boolean isOdd = true;
	private int max;
	private int start;
	
	public EvenOddTask(int max, int start) {
		this.max = max;
		this.start = start;
	}
	
	public void run() {
		int number = (start == 1) ? 1 : 2;
		while (number <= max) {
			if (number % 2 != 0) {
				oddNumber(number);
			} else {
				evenNumber(number);
			}
			number += 1;
		}
	}
	
	public synchronized void evenNumber(int number) {
		while(isOdd) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Even: " + number);
		isOdd = true;
		notify();
	}
	
	public synchronized void oddNumber(int number) {
		while(!isOdd) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Odd: " + number);
		isOdd = false;
		notify();
	}
	
	public static void main(String[]  args) {
		Thread t1 = new Thread(new EvenOddTask(10, 2));
		Thread t2 = new Thread(new EvenOddTask(10, 1));
		t1.start();
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