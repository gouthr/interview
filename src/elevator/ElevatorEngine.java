package elevator;

public class ElevatorEngine extends Thread {
	
	boolean startFlag = false;
	ElevatorCabin cabin = new ElevatorCabin();
	
	public void startEngine() {
		startFlag = true;
		this.start();
	}
	
	public void stopEngine() {
		startFlag = false;
	}
	
	public void pressButton (int floor) {
		cabin.addFloor(floor);
	}
	
	public void run() {
		while (true) {
			if (!startFlag) {
				if (cabin.floorList.isEmpty()) {
					break;
				}
			}
			Integer nextLevel = cabin.gotoNext();
			System.out.println("Next floor: " + nextLevel);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
