package Bank;

public class Customer {
	int amount = 10000;
	
	synchronized public void withdraw(int amount) {
		if (amount > this.amount) {
			System.out.println("Not enough balance in the account.");
			try {
				while(amount > this.amount) {
					wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.amount -= amount;
		System.out.println("Amount withdraw completed: " + amount);
	}
	
	synchronized public void deposit(int amount) {
		this.amount += amount;
		System.out.println("Deposit completed: " + amount);
		notify();
	}
	
	public static void main(String[] args) {
		Customer c = new Customer();
		Thread t1 = new Thread() {
			public void run() {
				c.withdraw(15000);
			}
		};
		t1.start();
		
		Thread t2 = new Thread() {
			public void run() {
				c.deposit(2000);
			}
		};
		t2.start();
		
		Thread t3 = new Thread() {
			public void run() {
				c.deposit(2000);
			}
		};
		t3.start();
		
		Thread t4 = new Thread() {
			public void run() {
				c.deposit(2000);
			}
		};
		t4.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Amount in account: " + c.amount);
		
	}
}
