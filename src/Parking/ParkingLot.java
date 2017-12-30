package Parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	int capacity;
	
	List<ParkingSpace> occupiedList;
	
	List<ParkingSpace> vacantList;
	
	boolean isFull;
	
	boolean isEmpty;
	
	public ParkingLot(int capacity) {
		this.capacity = capacity;
		this.occupiedList = new ArrayList<ParkingSpace>();
		this.vacantList = new ArrayList<ParkingSpace>();		
		for (int i=0 ; i<capacity; i++) {
			ParkingSpace space = new ParkingSpace();
			space.isOccupied = false;
			space.type = ParkingType.REGULAR;
			space.vehicle = null;
			vacantList.add(space);
		}
	}
	
	public boolean isFull() {
		return isFull;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	private ParkingSpace getParkingSpace(Vehicle vehicle) {
		for(ParkingSpace space: vacantList) {
			if (space.type == vehicle.type) {
				return space;
			}
		}
		return null;
	}
	
	public boolean parkVehicle(Vehicle vehicle) {
		if (!isFull) {
			ParkingSpace space = getParkingSpace(vehicle);
			space.isOccupied = true;
			space.vehicle = vehicle;
			vacantList.remove(space);
			occupiedList.add(space);
			if (occupiedList.size() == capacity) {
				isFull = true;
			}
			isEmpty = false;
			return true;
		}
		return false;
	}
	
	public boolean releaseVehicle(Vehicle vehicle) {
		if (!isEmpty) {
			for (ParkingSpace space : occupiedList) {
				if (space.vehicle == vehicle) {
					space.isOccupied = false;
					space.vehicle = null;
					occupiedList.remove(space);
					vacantList.add(space);
					if (occupiedList.isEmpty()) {
						isEmpty = true;
					}
					isFull = false;
					return true;
				}
			}
			
		}
		return false;
	}
}
