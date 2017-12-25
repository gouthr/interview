package elevator;

public class ElevatorEnginerDriver {
	
	public static void main(String args[]) {
		ElevatorEngine engine  = new ElevatorEngine();
		engine.startEngine();
		engine.pressButton(1);
		engine.pressButton(5);
		engine.pressButton(6);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		engine.pressButton(1);
		engine.pressButton(4);
		try {
			Thread.sleep(1500);
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		engine.pressButton(2);
		engine.stopEngine();
		try {
			engine.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
