package numbers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median of a stream of numbers. Online algorithm. Algorithm
 * 
 * Two priority queues:
 * 
 * A max-heap lo to store the smaller half of the numbers. A min-heap hi to
 * store the larger half of the numbers. The max-heap lo is allowed to store, at
 * worst, one more element more than the min-heap hi. Hence if we have processed
 * k elements:
 * 
 * If k = 2*n + 1, then lo is allowed to hold n+1 elements, while hi can hold n
 * elements. If k = 2*n , then both heaps are balanced and hold n elements each.
 * This gives us the nice property that when the heaps are perfectly balanced,
 * the median can be derived from the tops of both heaps. Otherwise, the top of
 * the max-heap lo holds the legitimate median.
 * 
 * Adding a number num:
 * 
 * Add num to max-heap lo. Since lo received a new element, we must do a
 * balancing step for hi. So remove the largest element from lo and offer it to
 * hi. The min-heap hi might end holding more elements than the max-heap lo,
 * after the previous operation. We fix that by removing the smallest element
 * from hi and offering it to lo. The above step ensures that we do not disturb
 * the nice little size property we just mentioned.
 * 
 * Referred from - https://leetcode.com/problems/find-median-from-data-stream/solution/
 * 
 *
 */
public class Median {
	private Queue<Integer> lo = new PriorityQueue<>(1, new Descending());
	
	private Queue<Integer> hi = new PriorityQueue<>();
	
	public void addNum(int num) {
		lo.add(num);
		if (lo.size() > hi.size()) {
			hi.add(lo.poll());
		}
		if (hi.size() > lo.size()) {
			lo.add(hi.poll());
		}
	}
	
	public double getMedian() {
		return lo.size() > hi.size()? lo.peek() : (lo.peek() + hi.peek())/2.0;
	}
	
	public class Descending implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2-o1;
		}	
	}
	
	public static void main(String[] args) {
		Median med = new Median();
		// [41, 35, 62, 5, 97, 108]
		// [5, 35, 41, 62, 97, 108]
		med.addNum(41);
		System.out.println(med.getMedian());
		med.addNum(35);
		System.out.println(med.getMedian());
		med.addNum(62);
		System.out.println(med.getMedian());
		med.addNum(5);
		System.out.println(med.getMedian());
		med.addNum(97);
		System.out.println(med.getMedian());
		med.addNum(108);
		System.out.println(med.getMedian());
	}
}
