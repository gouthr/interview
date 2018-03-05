package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class DP {

	public static void main(String[] args) {
		final int n = 2;
		final int k = 10;
		System.out.println("Trials needed with " + n + " eggs and " + k + " floors:" + eggDrop(n, k));
		
		// Longest increasing subsequence count in an int arr
		int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
		System.out.println("LIS max count: " + lis(arr));
		
		// Max sum increasing subsequence in an int arr
		int[] arr1 = {1, 101, 2, 3, 100, 4, 5};
		System.out.println("Max increasing subsequence sum: " + msis(arr1));
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

	/**
	 * Longest increasing subsequence.
	 * 
	 * The Longest Increasing Subsequence (LIS) problem is to find the length of
	 * the longest subsequence of a given sequence such that all elements of the
	 * subsequence are sorted in increasing order. For example, the length of
	 * LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33,
	 * 50, 60, 80}.
	 * 
	 * @param arr
	 * @return
	 */
	public static List<Integer> lis(int[] arr) {
		int n = arr.length;
		int[] lis = new int[n];
		
		for(int i=0; i<n; i++) {
			lis[i] = 1;
		}
	
		List<Integer>[] list = new ArrayList[n];
		
		for(int i=1; i<n; i++) {
			list[i] = new ArrayList<>();
			for (int j=0; j<n; j++) {
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
					list[i].add(arr[j]);
				}
			}
			list[i].add(arr[i]);
		}
		
		int max = Integer.MIN_VALUE;
		int resIndex = 0;
		for (int i=0; i<n; i++) {
			if (max < lis[i]) {
				max = lis[i];
				resIndex = i;
			}
		}
		
		return list[resIndex];
	}
	
	/**
	 * Max sum increasing subsequence of an int arr.
	 * 
	 * Given an array of n positive integers. Write a program to find the sum of
	 * maximum sum subsequence of the given array such that the intgers in the
	 * subsequence are sorted in increasing order. For example, if input is {1,
	 * 101, 2, 3, 100, 4, 5}, then output should be 106 (1 + 2 + 3 + 100), if
	 * the input array is {3, 4, 5, 10}, then output should be 22 (3 + 4 + 5 +
	 * 10) and if the input array is {10, 5, 4, 3}, then output should be 10.
	 */
	
	public static List<Integer> msis(int[] arr) {
		int n  = arr.length;
		int[] msis = new int[n];
		
		for(int i=0; i<n; i++) {
			msis[i] = arr[i];
		}
		
		List<Integer>[] list = new ArrayList[n];
		for(int i=1; i<n; i++) {
			list[i] = new ArrayList<>();
			for(int j=0; j<i; j++) {
				if (arr[i] > arr[j] && msis[i] < msis[j] + arr[i]) {
					msis[i] = msis[j] + arr[i];
					list[i].add(arr[j]);
				}
			}
			list[i].add(arr[i]);
		}
		
		int max = Integer.MIN_VALUE;
		int resIndex = 0;
		for (int i=0; i<n; i++) {
			if (max < msis[i]) {
				max = msis[i];
				resIndex = i;
			}
		}
		return list[resIndex];
	}
}
