package numbers;

import java.util.Arrays;

public class NumberImpl {

	public static void main(String[] args) {
		NumberImpl numImpl = new NumberImpl();
		// product array
		int arr[] = new int[] {10, 3, 5, 6, 2};
		int prod[] = numImpl.productArray(arr);
		System.out.println("Product array: ");
		for (int i=0; i<arr.length; i++) {
			System.out.print(prod[i] + " ");
		}
		System.out.println();
		
		// Sum of 2 nos. equal to key in less than O(n2)
		System.out.println("2 nos. in an array sum up to the given key: " + numImpl.sumTwoNumbers(arr, 9));
		System.out.println("2 nos. in an array sum up to the given key: " + numImpl.sumTwoNumbers(arr, 14));
	}

	/* 
	 * Given an array arr[] of n integers, construct a Product Array prod[] (of same size)
	 * such that prod[i] is equal to the product of all the elements of arr[] except arr[i].
	 * Solve it without division operator and in O(n
	 */
	public int[] productArray(int[] arr) {
		int len = arr.length;
		int[] left = new int[arr.length];
		int[] right = new int[arr.length];
		int[] prod = new int[arr.length];
		
		left[0] = 1;
		right[len-1] = 1;
		
		for (int i=1; i<len; i++) {
			left[i] = arr[i-1]*left[i-1];
		}
		
		for (int j=len-2; j>=0; j--) {
			right[j] = arr[j+1]*right[j+1];
		}
		
		for(int i=0; i<len; i++) {
			prod[i] = left[i]*right[i];
		}	
		return prod;
	}
	
	/*
	 * Check if 2 nos. in an array sum up to the given key in less than O(n2)
	 */
	public boolean sumTwoNumbers(int[] arr, int key) {
		int i = 0;
		int j = arr.length-1;
		Arrays.sort(arr);
		while(i<arr.length && j>=0 && i != j) {
			if ((arr[i] + arr[j]) == key) {
				return true;
			} else if ((arr[i] + arr[j]) > key) {
				j--;
			} else {
				i++;
			}
		}
		return false;
	}

}
