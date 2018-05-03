package numbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Design a data structure that supports all following operations in average
 * O(1) time.
 * 
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present. getRandom: Returns
 * a random element from current set of elements. Each element must have the
 * same probability of being returned. Example:
 * 
 * // Init an empty set. RandomizedSet randomSet = new RandomizedSet();
 * 
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * 
 * // Returns false as 2 does not exist in the set. randomSet.remove(2);
 * 
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * 
 * // getRandom should return either 1 or 2 randomly. randomSet.getRandom();
 * 
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * 
 * // 2 was already in the set, so return false. randomSet.insert(2);
 * 
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 *
 */
public class RandomizedSet {
	
	Map<Integer, Integer> map;
	List<Integer> list;
	Random rand;
	
	public RandomizedSet() {
		map = new HashMap<>();
		list = new ArrayList<>();
		rand = new Random();
	}
	
	public boolean insert(int data) {
		if (!map.containsKey(data)) {
			int size = list.size();
			map.put(data, size);
			list.add(data);
			return true;
		}
		return false;
	}
	
	public boolean remove(int data) {
		if (!map.containsKey(data)) {
			return false;
		}
		int pos = map.get(data);
		int val = list.get(list.size() - 1);
		list.set(pos, val);
		map.put(val, pos);
		list.remove(list.size()-1);
		map.remove(data);
		return true;
	}
	
	public int getRandom() {
		return list.get(rand.nextInt(list.size()));
	}
	
	public static void main(String[] args) {
		RandomizedSet rs = new RandomizedSet();
		
		System.out.println(rs.insert(1));
		System.out.println(rs.remove(2));
		System.out.println(rs.insert(2));
		System.out.println(rs.getRandom());
		System.out.println(rs.getRandom());
		System.out.println(rs.getRandom());
		System.out.println(rs.getRandom());
		System.out.println(rs.getRandom());
		System.out.println(rs.remove(1));
		System.out.println(rs.insert(2));
		System.out.println(rs.getRandom());
		
	}

}
