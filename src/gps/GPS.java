package gps;

import java.util.Scanner;

public class GPS {
	/**
	 *  https://en.wikipedia.org/wiki/Earth_radius
	 */
    public static final double R = 6371; // In kilometers

	public static class Location {
		
		private double latitude;
		private double longitude;
		
		public Location(double lat, double lon) {
			this.latitude = lat;
			this.longitude = lon;
		}	
	}
    
	/**
	 * Haversine distance formula - https://en.wikipedia.org/wiki/Haversine_formula#The_haversine_formula
	 * 
	 * @param loc1 - location 1
	 * @param loc2 - location 2
	 * @return distance between the 2 locations
	 */
    public static double haversineDistance(Location loc1, Location loc2) {
        double lat1 = loc1.latitude;
        double lon1 = loc1.longitude;
        double lat2 = loc2.latitude;
        double lon2 = loc2.longitude;
        
    	double diffLat = Math.toRadians(lat2 - lat1);
        double diffLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
 
        double partEquation = Math.pow(Math.sin(diffLat / 2),2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(diffLon / 2),2);
        return 2 * R * Math.asin(Math.sqrt(partEquation));
    }
    
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	
    	Location[] arrLoc = new Location[4];
    	int i = 0;
    	while(i < 4) {
    		String location = in.nextLine();
    		String[] parts = location.split(",");
    		double lat = Double.parseDouble(parts[0]);
    		double lon = Double.parseDouble(parts[1]);
    		arrLoc[i] = new Location(lat, lon);
    		i++;
    	}
    	
    	in.close();
    	
    	Location locA = arrLoc[0];
    	Location locB = arrLoc[1];
    	Location locC = arrLoc[2];
    	Location locD = arrLoc[3];
    	
    	double distAB = haversineDistance(locA, locB);
    	double distCD = haversineDistance(locC, locD);
    	
    	/**
    	 * Two paths are possible: [A->C->D->B] or [C->A->B->D]
    	 * ACDB = AC + CD + DB; CABD = CA + AB + BD;
    	 * ACDB - CABD = CD - AB
    	 * if (CD > AB) , then CABD is shorter else ACDB is shorter
    	 */
    	if (distCD > distAB) {
    		System.out.println("Detour [C->A->B->D] is shorter.");
    	} else if (distCD == distAB) {
    		System.out.println("Both detours, [C->A->B->D] and [A->C->D->B] are the same.");
    	} else {
    		System.out.println("Detour [A->C->D->B] is shorter.");
    	}  	
    }
}
