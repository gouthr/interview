package interval;

public class Interval implements Comparable <Interval>{
	int start;
	int end;
	
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Interval interval) {
		return this.start - interval.start;
	}
}
