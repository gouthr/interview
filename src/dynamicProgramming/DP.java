package dynamicProgramming;

public class DP {

	public static void main(String[] args) {
		final int n = 2;
		final int k = 10;
		System.out.println("Trials needed with " + n + " eggs and " + k + " floors:" + eggDrop(n, k));

	}
	
	/**
	 *  Find the min no. of trials needed to check when an egg breaks; given n eggs and k floors.
	 *  The below is a DP problem but solved using recursion. 
	 */
	public static int eggDrop(int n, int k) {
		if (k==1 || k==0) {
			return k;
		}	
		if (n==1) {
			return k;
		}
		int res = 0;
		int min = Integer.MAX_VALUE;
		for(int x=1; x<=k; x++) {
			res = Math.max(eggDrop(n-1, x-1), eggDrop(n, k-x));
			if (res < min) {
				min = 1+res;
			}
		}
		return min;
	}

}
