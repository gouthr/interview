package numbers;

import java.util.Arrays;
import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer>{
	Integer next;
	Iterator<Integer> iter;
	
	public PeekingIterator(Iterator<Integer> iter) {
		this.iter = iter;
		if (iter.hasNext()) {
			next = iter.next();
		}
	}
	
	public Integer peek() {
		if (iter.hasNext()) {
			return next;
		}
		return null;
	}
	
	@Override
	public boolean hasNext() {
		if (next != null) {
			return true;
		}
		return false;
	}

	@Override
	public Integer next() {
		if (next != null) {
			Integer res = next;
			if (iter.hasNext()) {
				next = iter.next();
			} else {
				next = null;
			}
			return res;
		}
		return null;
	}
	
	public static void main(String args[]) {
		Integer[] arr = {1, 2, 3};
		Iterator<Integer> it = Arrays.asList(arr).iterator();
		PeekingIterator pit = new PeekingIterator(it);
		System.out.println(pit.next());
		System.out.println(pit.peek());
		System.out.println(pit.next());
		System.out.println(pit.next());
		System.out.println(pit.hasNext());
	}

}
