package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
		
		// Separate even odd numbers in an array
		numImpl.separateEvenOdd(arr3);
		System.out.println("Separation of even odd nos in an array:");
		for(int resEle : arr3) {
			System.out.print(resEle + " ");
		}
		System.out.println();
		
		// Remove duplicates in an array without using a map
		int[] arr4 = {1, 3, 5, 9, 7, 8, 3, 2, 1, 3, 13, 3};
		int arrLen = numImpl.removeDuplicates(arr4);
		System.out.println("Remove suplicates in an array:");
		for(int i=0; i<arrLen; i++) {
			System.out.print(arr4[i] + " ");
		}
		System.out.println();
		
		// Find indices start and end for a particular value in a sorted integer array with duplicates
		int[] arr7 = {1, 3, 5, 9, 7, 8, 3, 2, 1, 3, 13, 3};
		Arrays.sort(arr7);
		for(int i=0; i<arr7.length; i++) {
			System.out.print(arr7[i] + " ");
		}
		System.out.println();
		numImpl.findIndicesOfDuplicates(arr7, 3);
		
		// Randomize an array of nos.
		System.out.println("Randomize: ");
		numImpl.randomize(arr3);
		for(int i=0; i<arr3.length; i++) {
			System.out.print(arr3[i] + " ");
		}
		System.out.println();
		
		// Intersection of 2 unsorted arrays
		int[] arr5 = {1, 3, 4, 5, 7, 3};
		int[] arr6 = {2, 3, 5, 6, 3, 3};
		resList = numImpl.intersection(arr5, arr6);
		System.out.println("Intersection of 2 UN-sorted arrays:");
		for(int resEle : resList) {
			System.out.print(resEle + " ");
		}
		System.out.println();
		
		// Stock buy sell profit - 1 time
		int[] stock = {1, 2, 6, 80, 100};
		numImpl.stockBuySell1Time(stock);
		
		// Stock buy sell profit - 1 time optimized
		int[] stock2 = {100, 80, 6, 2, 1};
		numImpl.stockBuySell1TimeOptimized(stock2);
		
		// Stock buy sell profit - k times
		int[] stock1 = {100, 180, 260, 310, 40, 535, 695};
		numImpl.stockBuySellKTimes(stock1);
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
	
	public void separateEvenOdd(int[] arr) {
		int n = arr.length;
		int i = 0;
		int j = n-1;
		while(i<j) {
			while(i<n && arr[i]%2 != 0) {
				i++;
			}
			while(j>=0 && arr[j]%2 == 0) {
				j--;
			}
			if (i<j) {
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
	}
	
	// {1, 3, 5, 9, 7, 8, 3, 2, 1};
	// {1, 1, 2, 3, 3, 5, 7, 8, 9}
	// {1, 2, 3, 5, 7, 8, 9}
	public int removeDuplicates(int[] arr) {
		Arrays.sort(arr);
		int n = arr.length;
		int[] tmp = arr;
		int i = 0;
		int j = 0;
		while(i<n-1) {
			if (tmp[i] == tmp[i+1]) {
				i++;
			} else {
				arr[j++] = tmp[i++];
			}
		}
		if (tmp[n-2] != tmp[n-1]) {
			arr[j++] = tmp[n-1];
		}
		return j;
	}
	
	// Find indices start and end for a particular value in a sorted integer array with duplicates
	// 1 1 2 3 3 3 3 5 7 8 9 13 
	public void findIndicesOfDuplicates(int[] arr, int key) {
		int n = arr.length;
		int[] tmp = arr;
		int i = 0;
		int st = -1;
		while(i<n) {
			if (tmp[i] == key) {
				if (st==-1) {
					st = i;
				}
			}else if (st != -1) {
				break;
			}
			i++;
		}
		
		System.out.println("Indices of start and end of duplicate sorted array: Start= " + st + "; end=" + (i-1));
	}
	
	public void randomize(int[] arr) {
		int n = arr.length -1;
		for(int i=n-1; i>0; i--) {
			int random = (int)Math.round(Math.random()*10) % i;
			int tmp = arr[i];
			arr[i] = arr[random];
			arr[random] = tmp;
		}
	}

	public List<Integer> intersection(int arr1[], int arr2[]) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<arr1.length; i++) {
			Integer cnt = map.get(arr1[i]) ;
			if (cnt == null) {
				map.put(arr1[i], 1);
			} else {
				map.put(arr1[i], cnt+1);
			}
		}		
		List<Integer> resList = new ArrayList<Integer>();
		for(int i=0; i<arr2.length; i++) {
			Integer cnt = map.get(arr2[i]) ;
			if (cnt != null && cnt != 0) {
				resList.add(arr2[i]);
				map.put(arr2[i], cnt-1);
			}
		}
		return resList;
	}
	
	public void stockBuySell1Time(int[] arr) {
		int max = 0;
		int n = arr.length;
		int buy = 0, sell = 0;
		for(int i=n-1; i>0; i--) {
			for (int j=i-1; j>=0; j--) {
				if (arr[i] - arr[j] > max) {
					max = arr[i] - arr[j];
					buy = j;
					sell = i;
				}
			}
		}
		System.out.println("Max profit is by buying on day " + (buy+1) + " and selling on day " + (sell+1) + " ; Profit: " + max);
	}
	
	// {1, 2, 6, 80, 100}
	// {100, 80, 6, 2, 1}
	public void stockBuySell1TimeOptimized(int[] arr) {
		int min_val = arr[0];
		int max_profit = arr[1] - arr[0];
		int n = arr.length;
		
		for(int i=1; i<n; i++) {
			int diff = arr[i] - min_val;
			if (diff > max_profit) {
				max_profit = diff;
			}
			if (arr[i] < min_val) {
				min_val = arr[i];
			}
		}
		System.out.println("Max profit (optimized): " + max_profit);
	}
	
	// {100, 180, 260, 310, 40, 535, 695}
	public void stockBuySellKTimes(int[] arr) {
		int n = arr.length;
		int i = 0;
		Interval[] inter = new Interval[n/2 + 1];
		int cnt = 0;
		while(i<n-1) {
			// Find local minima
			while(i<n-1 && arr[i+1]<=arr[i]) {
				i++;
			}
			
			if (i==n-1) {
				break;
			}
			
			inter[cnt] = new Interval();
			inter[cnt].buy = i++;
			
			//Find local maxima
			while(i<n && arr[i]>arr[i-1]) {
				i++;
			}
			inter[cnt].sell = i-1;
			cnt++;
		}
		if (cnt == 0) {
			System.out.println("No profit.");
		} else {
			for (int k=0; k<cnt; k++) {
				System.out.print("{" + inter[k].buy + ", " + inter[k].sell + "}, ");
			}
		}
	}
	
	private class Interval {
		int buy;
		int sell;
	};
}
