package numbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NestedInteger {
	Integer data;
	List<NestedInteger> list;
	
	public NestedInteger(int data) {
		this.data = data;
		this.list = new ArrayList<NestedInteger>();
	}
	
	public void addInteger(NestedInteger ni) {
		list.add(ni);
	}
	
	public List<NestedInteger> getList() {
		return list;
	}
	
	public boolean isInteger() {
		if (data != null) {
			return true;
		}
		return false;
	}
	
	public Integer getInteger() {
		return data;
	}
	
	
	public static void main(String args[]) {
		/**
		 * Example input = [1, [2, 3, [4, 5]], 6]
		 */
		List<NestedInteger> input = new ArrayList<NestedInteger>();

		NestedInteger ni6 = new NestedInteger(6);
		NestedInteger ni4 = new NestedInteger(4);
		NestedInteger ni5 = new NestedInteger(5);
		NestedInteger ni2 = new NestedInteger(2);
		
		NestedInteger ni3 = new NestedInteger(3);
		ni3.addInteger(ni4);
		ni3.addInteger(ni5);
		
		NestedInteger ni1 = new NestedInteger(1);
		ni1.addInteger(ni2);
		ni1.addInteger(ni3);
		
		input.add(ni1);
		input.add(ni6);
		
		System.out.println("Weighted sum (recursive solution): " + weightedSum(input, 1));
		System.out.println("Weighted sum (non-recursive solution): " + weightedSumNonRecursive(input));
		System.out.println("Inverse weighted sum (non-recursive solution): " + inverseWeightedSum(input));
		
		System.out.println("Flattened list: ");
		NestedIntegerIterator nestedInterator = new NestedIntegerIterator(input);
		while(nestedInterator.hasNext()) {
			System.out.print(nestedInterator.next() + " ");
		}
		System.out.println();
	}
	
	public static int weightedSum(List<NestedInteger> list, int level) {
		if (list == null || list.isEmpty()) {
			return 0;
		}
		
		int sum = 0;
		for(NestedInteger ni : list) {
			if (ni.isInteger()) {
				sum += ni.getInteger() * level;
			}
			sum += weightedSum(ni.getList(), level+1);

		}
		return sum;		
	}
	
	public static int weightedSumNonRecursive(List<NestedInteger> list) {
		if (list == null || list.isEmpty()) {
			return 0;
		}
		
		LinkedList<NestedInteger> queue = new LinkedList<NestedInteger>();
		LinkedList<Integer> depth = new LinkedList<Integer>();
		for (NestedInteger ni : list) {
			queue.add(ni);
			depth.add(1);
		}
		
		int sum = 0;
		while(!queue.isEmpty()) {
			NestedInteger top = queue.remove();
			Integer level = depth.remove();
			if (top.isInteger()) {
				sum += top.getInteger() * level;
			} 
			for (NestedInteger ni : top.getList()) {
				queue.add(ni);
				depth.add(level+1);
			}
		}
		return sum;		
	}

	public static int inverseWeightedSum(List<NestedInteger> list) {
		if (list == null || list.isEmpty()) {
			return 0;
		}
		
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		
		LinkedList<NestedInteger> q1 = new LinkedList<NestedInteger>();
		LinkedList<Integer> q2 = new LinkedList<Integer>();
		int maxLevel = Integer.MIN_VALUE;
		int sum = 0;
		
		for (NestedInteger ni : list) {
			q1.add(ni);
			q2.add(1);
		}
		
		while(!q1.isEmpty()) {
			NestedInteger top = q1.remove();
			Integer level = q2.remove();
			
			maxLevel = Math.max(level, maxLevel);
			
			if (top.isInteger()) {
				if (map.containsKey(level)) {
					map.get(level).add(top.getInteger());
				} else {
					ArrayList<Integer> vals = new ArrayList<Integer>();
					vals.add(top.getInteger());
					map.put(level, vals);
				}
			}
			for (NestedInteger ni : top.getList()) {
				q1.add(ni);
				q2.add(level+1);
			}			
		}
		
		for (int i=maxLevel; i>0; i--) {
			if (map.get(i) != null) {
				for (Integer val : map.get(i)) {
					sum += val * (maxLevel-i + 1);
				}
			}
		}		
		return sum;
	}
	
	private static class NestedIntegerIterator implements Iterator<Integer> {
		private Stack<NestedInteger> st;
		
		public NestedIntegerIterator(List<NestedInteger> list) {
			st = new Stack<NestedInteger>();
			for (int i=list.size()-1; i>=0; i--) {
				st.push(list.get(i));
			}
		}
		
		// Example input = [1, [2, 3, [4, 5]], 6]
		@Override
		public boolean hasNext() {
			while (!st.isEmpty()) {
				NestedInteger cur = st.peek();
				if (cur.isInteger()) {
					return true;
				} 
				st.pop();
				for (int i=cur.getList().size()-1; i>=0; i--) {
					st.push(cur.getList().get(i));
				}
			}
			return false;
		}
		
		@Override
		public Integer next() {
			NestedInteger front = st.pop();
			return front.getInteger();
		}
	}

}
