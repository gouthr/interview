package numbers;

import java.util.Stack;

public class MinStack {
	
	private Stack<Long> st;
	
	private long min;
	
	public MinStack() {
		st = new Stack<>();
		min = 0;
	}
	
	public void push(long data) {
		if (st.isEmpty()) {
			st.push(0L);
			min = data;
		} else {
			st.push(min-data);
			if (data < min) {
				min = data;
			}
		}
	}
	
	public long pop() throws Exception{
		if (st.isEmpty()) {
			throw new Exception("Stack is empty");
		}
		long res;
		long val = st.pop();
		if (val > 0) {
			res = min;
			min = min + val;
			return res;
		} else {
			return min-val;
		}
	}
	
	public long min() {
		return min;
	}
	
	// {5, 3, 7, -1, 9, 0}
	public static void main(String[] args) throws Exception {
		MinStack minSt = new MinStack();
		minSt.push(5);
		minSt.push(3);
		minSt.push(7);
		minSt.push(-1);
		minSt.push(9);
		minSt.push(0);
		
		System.out.println(minSt.pop());
		System.out.println(minSt.pop());
		System.out.println(minSt.pop());
		System.out.println(minSt.pop());
		System.out.println(minSt.pop());
		System.out.println(minSt.pop());
		System.out.println(minSt.pop());
	}

}
