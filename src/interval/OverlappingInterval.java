package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 
 * Merge overlapping intervals.
 * 
 * @author gouthr
 * 
 *         To sort a collection of any object, one of the following must be
 *         true:
 * 
 *         The object must implement Comparable. In this case, implement
 *         Comparable<Interval>. Then Collections.sort will be able to sort using
 *         the compareTo method. 
 *         
 *         Create your own class that implements
 *         Comparator, specifically Comparator<Interval>, and pass an instance of
 *         that class as the second parameter to Collections.sort. Then the
 *         sorting algorithm will use the Comparator to sort the collection.
 *
 */

public class OverlappingInterval {
	private Stack<Interval> st = new Stack<Interval>();
	
	public void mergeOverlappingIntervals(List<Interval> intervalList) {		
		// Collections.sort(intervalList); // Using Interval implemented Comparable
		
		Collections.sort(intervalList, new IntervalComparator()); // Using IntervalComparator
		
		Interval[] arr = (Interval[]) intervalList.toArray();
		
		st.push(arr[0]);
		
		for (int i=1; i<arr.length; i++) {
			if (arr[i].start > st.peek().end) {
				st.push(arr[i]);
			} else {
				if (arr[i].end > st.peek().end) {
					Interval top = st.pop();
					top.end = arr[i].end;
					st.push(top);
				}
			}
		}		
	}
	
	public void printOverlappingIntervals() {
		while(!st.isEmpty()) {
			Interval node = st.pop();
			System.out.println("Interval start: " + node.start + " , interval end: " + node.end);
		}
	}
	
	/**
	 * Given an array of meeting time intervals consisting of start and end
	 * times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of
	 * conference rooms required.
	 * 
	 * Example 1:
	 * 
	 * Input: [[0, 30],[5, 10],[15, 20]] Output: 2
	 * 
	 * Just want to share another idea that uses min heap, average time
	 * complexity is O(nlogn).
	 * 
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms(Interval[] intervals) {
	    if (intervals == null || intervals.length == 0)
	        return 0;
	        
	    // Sort the intervals by start time
	    Arrays.sort(intervals, new Comparator<Interval>() {
	        public int compare(Interval a, Interval b) { return a.start - b.start; }
	    });
	    
	    // Use a min heap to track the minimum end time of merged intervals
	    PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
	        public int compare(Interval a, Interval b) { return a.end - b.end; }
	    });
	    
	    // start with the first meeting, put it to a meeting room
	    heap.offer(intervals[0]);
	    
	    for (int i = 1; i < intervals.length; i++) {
	        // get the meeting room that finishes earliest
	        Interval interval = heap.poll();
	        
	        if (intervals[i].start >= interval.end) {
	            // if the current meeting starts right after 
	            // there's no need for a new room, merge the interval
	            interval.end = intervals[i].end;
	        } else {
	            // otherwise, this meeting needs a new room
	            heap.offer(intervals[i]);
	        }
	        
	        // don't forget to put the meeting room back
	        heap.offer(interval);
	    }
	    
	    return heap.size();
	}
	
	/**
	 * Given a list of intervals which are occupied and a start and end time, find the free time available b/w start
	 *  and end times.
	 * 
	 */
	public List<Interval> freeMeetingRoomTime(List<Interval> intervals, int start, int end) {
		if (intervals == null || intervals.size() == 0) {
			return Collections.emptyList();
		}

		// Sort based on start time
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		
		List<Interval> res = new ArrayList<>();
		Interval[] arr = (Interval[]) intervals.toArray();
		
		int begin = start;
		for(int i=0; i<arr.length; i++) {
			if (begin >= end) {
				break;
			}
			if (arr[i].start > begin) {
				res.add(new Interval(begin, Math.min(end, arr[i].start)));
			}
			begin = Math.max(arr[i].end, begin);
		}
		return res;
	}
	
	public static void main(String[] args) {
		Interval node1 = new Interval(1, 5);
		Interval node2 = new Interval(1, 2);
		Interval node3 = new Interval(7, 9);
		Interval node4 = new Interval(2, 7);
		Interval node5 = new Interval(2, 5);
		Interval node6 = new Interval(5, 6);
		Interval node7 = new Interval(11, 15);
		
		List<Interval> list = Arrays.asList(node1, node2, node3, node4, node5, node6, node7);
		
		OverlappingInterval overlapInterval = new OverlappingInterval();
		
		overlapInterval.mergeOverlappingIntervals(list);
		overlapInterval.printOverlappingIntervals();
	}
	
	private class IntervalComparator implements Comparator<Interval> {

		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.start - o2.start;
		}
		
	}
}