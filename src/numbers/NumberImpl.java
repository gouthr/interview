package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		
		// Union and intersection of 2 sorted arrays
		int[] arr1 = {1, 3, 4, 5, 7};
		int[] arr2 = {2, 3, 5, 6};
		int[] res = numImpl.unionSortedArrays(arr1, arr2);
		System.out.println("Union of 2 sorted arrays:");
		for(int i=0; i<res.length; i++) {
			System.out.print(res[i] + " ");
		}
		System.out.println();
		List<Integer> resList = numImpl.intersectionSortedArrays(arr1, arr2);
		System.out.println("Intersection of 2 sorted arrays:");
		for(int resEle : resList) {
			System.out.print(resEle + " ");
		}
		System.out.println();
		
		// Max contiguous sum
		int[] arr3 = {3, 5, -9, 2, 4, -1, 7 };
		System.out.println("Max contiguous sum of the array: " + numImpl.maxContiguousSum(arr3));
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
	
	public int[] unionSortedArrays(int[] arr1, int[] arr2) {
		int[] res = new int[arr1.length + arr2.length];
		int i = 0, j = 0, k = 0;
		while(i<arr1.length && j<arr2.length) {
			if (arr1[i] < arr2[j]) {
				res[k] = arr1[i];
				k++;
				i++;
			} else if (arr1[i] > arr2[j]) {
				res[k] = arr2[j];
				k++;
				j++;
			} else {
				res[k] = arr1[i];
				i++;
				j++;
				k++;
			}
		}
		while(i<arr1.length) {
			res[k++] = arr1[i++];
		}
		while(j<arr2.length) {
			res[k++] = arr2[j++];
		}
		return res;
	}
	
	public List<Integer> intersectionSortedArrays(int[] arr1, int[] arr2) {
		List<Integer> res = new ArrayList<Integer>();
		int i=0, j=0;
		while(i<arr1.length && j<arr2.length) {
			if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr1[i] > arr2[j]) {
				j++;
			} else {
				res.add(arr1[i]);
				i++;
				j++;
			}
		}
		return res;
	}
	
	public int maxContiguousSum(int[] arr) {
		int sum = 0;
		int maxsum = 0;
		for(int i=0; i<arr.length; i++) {
			sum += arr[i];
			if (sum < 0) {
				sum = 0;
			}
			maxsum = Math.max(maxsum, sum);
		}
		return maxsum;
	}

}
