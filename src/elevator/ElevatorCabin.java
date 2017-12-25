package elevator;

import java.util.TreeSet;

public class ElevatorCabin {
	
	TreeSet<Integer> floorList = new TreeSet<Integer>();
	Integer currentFloor = 0;
	Direction direction = Direction.UP;
	
	public int gotoNext() {
		int nextFloor = currentFloor;
		switch(direction) {
		case UP:
			for (Integer floor : floorList) {
				if (floor > nextFloor) {
					nextFloor = floor;
					direction = Direction.UP;
					break;
				}
			}
			
			if (nextFloor == currentFloor) {
				for (Integer floor : floorList.descendingSet()) {
					if (floor < nextFloor) {
						nextFloor = floor;
						direction = Direction.DOWN;
						break;
					}
				}
			}
			break;
		case DOWN:
			for (Integer floor : floorList.descendingSet()) {
				if (floor < nextFloor) {
					nextFloor = floor;
					direction = Direction.DOWN;
					break;
				}
			}
			
			if (nextFloor == currentFloor) {
				for (Integer floor : floorList) {
					if (floor > nextFloor) {
						nextFloor = floor;
						direction = Direction.UP;
						break;
					}
				}
			}
			break;
		}
		floorList.remove(nextFloor);
		currentFloor = nextFloor;
		return nextFloor;
	}
	
	public void addFloor(int floor) {
		floorList.add(floor);
	}
	
	public enum Direction {
		UP,
		DOWN
	}
}
