package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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
		
		// Sq root of a number
		System.out.println();
		System.out.println("Square root of a number: " + numImpl.sqroot(81));
		
		// max sub array product
		int[] arr8 = {2, 3, -5, 1, 0, -17, 23, 3};
		System.out.println("Max sub-array product: " + numImpl.maxProductSubarray(arr8));
		
		// kth largest number in an array
		// {-17, -5, 0, 1, 2, 3, 3, 23}
		System.out.println("5th largest number in arr = " + numImpl.kLargestNum(arr8, 5));
		
		int[] arr9 = {5,  5, 10, 40, 50, 35};
		System.out.println("Max non-consecutive sum: " + numImpl.maxNonConsecutiveSum(arr9));
		
		int[] arr10 = {1,3,-1,-3,5,3,6,7};
		int[] swRes = numImpl.slidingWindowMax(arr10, 3);
		System.out.println("Sliding window array: ");
		for (int each : swRes) {
			System.out.print(each + " ");
		}
		System.out.println();
		
		System.out.println("k most frequent elements in O(n): ");
		int[] arr11 = {1,1,1,1,2,3,3,3,3,4,4,4,4,5,5,5};
		System.out.print(numImpl.kMostFrequent(arr11, 2));
		System.out.println();
		
		// Add 2 nos. without arithmetic operators
		System.out.println("Sum: " + numImpl.addNoArithmeticOperators(2, 3));
		
       // Let us search 3 in below array
       int arr12[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
       int key = 1;
       System.out.println("Index of the element is: "
                      + numImpl.rotatedBinarySearch(arr12, key));
       
       int arr13[] = {0,1,0,2,1,0,1,3,2,1,2,1};
       System.out.println("Trappung rain water. Max water trapped = " + numImpl.trapRainWater(arr13));
       
       int arr14[][] = {{1,2,3},{4,5,6},{7,8,9}};
       System.out.println("Original array: ");
       for (int i=0; i<arr14.length; i++) {
    	   for (int j=0; j<arr14[0].length; j++) {
    		   System.out.print(arr14[i][j] + " ");
    	   }
    	   System.out.println();
       }
       numImpl.rotateMatrix90(arr14);
       System.out.println("Rotated array:");
       for (int i=0; i<arr14.length; i++) {
    	   for (int j=0; j<arr14[0].length; j++) {
    		   System.out.print(arr14[i][j] + " ");
    	   }
    	   System.out.println();
       }
       
       // max points on a straight line given a 2d points array
       int[][] arr15 = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
       System.out.println("Max points on a staright line: " + numImpl.maxPointsOnLine(arr15));
       
       // N queens problem
       numImpl.placeNQueens(4);
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
	
	public double sqroot(int num) {
		double f1 = num/2;
		double f2 = (f1 + num/f1)/2;
		
		while(Math.abs(f1-f2) > 0.01) {
			f1 = f2;
			f2 = (f1 + num/f1)/2;		
		}
		
		return f1;
	}
	
	/**
	 * Given an array of int, find a contiguous sub-array whose product is maximum.
	 * 
	 */
	public int maxProductSubarray(final int[] arr) {
		int n = arr.length;
		int max_ending_here = 1;
		int min_ending_here = 1;
		int max_so_far = Integer.MIN_VALUE;
		
		for (int i=0; i<n; i++) {
			if (arr[i] > 0) {
				max_ending_here *= arr[i];
				min_ending_here = Math.min(min_ending_here*arr[i], 1);
			}
			
			if (arr[i] == 0) {
				max_ending_here = 1;
				min_ending_here = 1;
			}
			
			if (arr[i] < 0) {
				int tmp = min_ending_here;
				min_ending_here = max_ending_here * arr[i];
				max_ending_here = Math.max(tmp*arr[i], 1);
			}
			
			if (max_so_far < max_ending_here) {
				max_so_far = max_ending_here;
			}
		}
		return max_so_far;		
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
	
	// kth largest number in an array
	// Efficiency: O(nlogk) - nlogk to heapify k elemnets n times
	public int kLargestNum(int[] arr, int k) {
		Queue<Integer> q = new PriorityQueue<Integer>(k);
		
		for (int num : arr) {
			q.add(num);
			if (q.size() > k) {
				q.remove();
			}
		}
		return q.peek();
	}
	
	/**
	 * Given an array nums, there is a sliding window of size k which is moving
	 * from the very left of the array to the very right. You can only see the k
	 * numbers in the window. Each time the sliding window moves right by one
	 * position. For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
	 * Therefore, return the max sliding window as [3,3,5,5,6,7].
	 */
	public int[] slidingWindowMax(int[] arr, int k) {
		int[] res = new int[arr.length - k +1];
		int count = 0;
		Queue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		for (int i=0; i<arr.length-k+1; i++) {
			for (int j=i; j<i+k; j++) {
				pq.add(arr[j]);
			}
			res[count++] = pq.poll();
			pq.clear();
		}
		return res;
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
	}
	
	/**
	 * The API: int read4(char *buf) reads 4 characters at a time from a file.
	 * The return value is the actual number of characters read. For example, it
	 * returns 3 if there is only 3 characters left in the file. By using the
	 * read4 API, implement the function int read(char *buf, int n) that reads n
	 * characters from the file.
	 */
	public int read(char[] buf, int n) {
		boolean eof = false;
		int count = 0;
		int total = 0;
		char[] tmp = new char[4];	
		
		while(!eof && total < n) {
			// count = read4(tmp); // Commenting since it cannot be compiled. This line exists in actual code.
			eof = count < 4;
			
			count = Math.min(count, n-total);
			
			for(int i=0; i<count; i++) {
				buf[total++] = tmp[i];
			}
		}
		return total;
	}
	
	/**
	 * Given an array of positive numbers, find the maximum sum of a subsequence
	 * with the constraint that no 2 numbers in the sequence should be adjacent
	 * in the array. So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10
	 * 7 should return 15 (sum of 3, 5 and 7).Answer the question in most
	 * efficient way.
	 * 
	 * @param arr
	 * @return
	 */
	// {5,  5, 10, 40, 50, 35}
	public int maxNonConsecutiveSum(int[] arr) {
		
		if (arr.length < 1) {
			return 0;
		}
		
		int previncl = arr[0]; //max sum including the previous element
		int prevexcl = 0; // max sum excluding the previous element
		
		int excl = 0;
		int incl = 0;
		
		for (int i=1; i<arr.length; i++) {
			excl = Math.max(previncl, prevexcl); // max sum excluding the current element
			incl = prevexcl + arr[i]; // max sum including the current element
			previncl = incl;
			prevexcl = excl;
		}
		
		return Math.max(incl, excl);
	}
	
	/**
	 * Find k most frequent occurring elements in the array.
	 * 
	 */
	public List<Integer> kMostFrequent(int[] arr,  int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int n = arr.length;
		List<Integer>[] freqMap = new List[n+1];
		
		for(int i=0; i<n; i++) {
			Integer val = map.get(arr[i]);
			if (val == null) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], val+1);
			}
		}
		
		for(Integer key : map.keySet()) {
			int freq = map.get(key);
			if (freqMap[freq] == null) {
				freqMap[freq] = new ArrayList();
			}
			freqMap[freq].add(key);
		}
		
		List<Integer> res = new ArrayList();
		for(int i=freqMap.length-1; i>=0; i--) {
			if (freqMap[i] != null) {
				res.addAll(freqMap[i]);
			}
		}
		
		return res.subList(0, k);
	}
	
	/**
	 * Add 2 numbers without using arithmetic operators.
	 *
	 */
	public int addNoArithmeticOperators(int x, int y) {
		int carry = 0;
		while(y != 0) {
            // carry now contains common
            // set bits of x and y
			carry = x & y;
            // Sum of bits of x and 
            // y where at least one 
            // of the bits is not set
			x = x ^ y;
            // Carry is shifted by 
            // one so that adding it 
            // to x gives the required sum
			y = carry << 1;
		}		
		return x;
	}
	
	/**
	 * Binary sum of 2 binary numbers provided as string input.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public String binarySum(String a, String b) {
		int i = a.length() - 1;
		int j = b.length() - 1;
		int sum = 0;
		int carry = 0;
		
		StringBuilder sb = new StringBuilder();
		
		while(i>=0 || j>=0) {
			sum = carry;
			if (i>=0) {
				sum += a.charAt(i) - '0';
				i--;
			}
			if (j>=0) {
				sum += b.charAt(j) - '0';
				j--;
			}
			sb.append(sum%2);
			carry = sum/2;
		}
		if (carry != 0) {
			sb.append(carry);
		}
		return sb.reverse().toString();
	}
	
	/**
	 * Find 2 nos. whose sum is closest to 0.
	 * 
	 */
	public NumPair twoNumbersSumToZero(int[] arr) {
		int i = 0;
		int j = arr.length - 1;
		int minSum = Integer.MAX_VALUE;
		int sum = 0;
		NumPair pair = new NumPair();
		Arrays.sort(arr);
		
		while(i<j) {
			sum = arr[i] + arr[j];
			if (Math.abs(sum) < Math.abs(minSum)) {
				minSum = sum;
				pair.low = arr[i];
				pair.high = arr[j];
			}
			if (sum < 0) {
				i++;
			} else {
				j--;
			}
		}		
		return pair;	
	}
	
	private class NumPair {
		int low;
		int high;
	}

	/**
	 * Given a set, we need to find maximum and minimum possible product among all subsets of the set.
	 * 
	 * @param arr
	 * @return
	 */
	public Product maxMinProd(int[] arr) {
		int prevMaxProd = arr[0];
		int prevMinProd = arr[0];
		int curMaxProd = arr[0];
		int curMinProd = arr[0];
		int maxProd = arr[0];
		int minProd = arr[0];
		
		Product prod = new Product();
		
		for (int i=1; i<arr.length; i++) {
			/*
			 * Current maximum product is maximum of following 1) prevMax *
			 * curelement (when curelement is +ve) 2) prevMin * curelement (when
			 * curelement is -ve) 3) Element itself 4) Previous max product
			 */
			curMaxProd = Math.max(prevMaxProd*arr[i], Math.max(prevMinProd*arr[i], arr[i]));
			curMaxProd = Math.max(curMaxProd, prevMaxProd);
			
			curMinProd = Math.min(prevMinProd*arr[i], Math.min(prevMaxProd*arr[i], arr[i]));
			curMinProd = Math.min(curMinProd, prevMinProd);
			
			maxProd = Math.max(maxProd, curMaxProd);
			minProd = Math.max(minProd, curMinProd);
			
			prevMaxProd = curMaxProd;
			prevMinProd = curMinProd;
		}
		prod.maxProd = maxProd;
		prod.minProd = minProd;
		
		return prod;
	}
	
	private class Product {
		int maxProd;
		int minProd;
	}

	/**
	 * Given a set of integers, the task is to divide it into two sets S1 and S2
	 * such that the absolute difference between their sums is minimum.
	 * 
	 * If there is a set S with n elements, then if we assume Subset1 has m
	 * elements, Subset2 must have n-m elements and the value of
	 * abs(sum(Subset1) – sum(Subset2)) should be minimum.
	 * 
	 * @param arr
	 * @return
	 */
	public int partitionSubsets(int[] arr) {
		int sumTotal = 0;
		int n = arr.length;
		for (int i=0; i<n; i++) {
			sumTotal += arr[i];
		}
		return partitionUtil(arr, n, 0, sumTotal);
	}
	
	private int partitionUtil(int[] arr, int n, int curTotal, int sumTotal) {
		if (n == 0) {
			return Math.abs((sumTotal - curTotal) - curTotal);
		}
		
        // For every item arr[i], we have two choices
        // (1) We do not include it first set
        // (2) We include it in first set
        // We return minimum of two choices
		return Math.min(partitionUtil(arr, n-1, curTotal + arr[n-1], sumTotal), partitionUtil(arr, n-1, curTotal, sumTotal));
	}
	
	public int rotatedBinarySearch(int[] arr, int key) {
		if (arr == null || arr.length == 0) {
			System.out.println("Invalid input.");
			return -1;
		}
		
		int n = arr.length;
		int pivot = findPivot(arr, 0, n-1);
		if (pivot == -1) { // Array is not rotated
			return binarySearch(arr, 0, n-1, key);
		}
		if (arr[pivot] == key) {
			return pivot;
		} else if (key < arr[0]) {
			return binarySearch(arr, pivot+1, n-1, key);
		} else {
			return binarySearch(arr, 0, pivot-1, key);
		}
	}
	
	private int findPivot(int[] arr, int l, int h) {
		if (h < l) {
			return -1;
		}
		
		int mid = (l + h)/2;
		if (mid < h && arr[mid] > arr[mid+1]) {
			return mid;
		}
		if (mid > l && arr[mid] < arr[mid-1]) {
			return mid-1;
		}
		if (arr[mid] <= arr[l]) {
			return findPivot(arr, l, mid-1);
		} else {
			return findPivot(arr, mid+1, h);
		}
	}
	
	private int binarySearch(int[] arr, int l, int h, int key) {
		if (h < l) {
			return -1;
		}
		
		int mid = (l + h)/2;
		if (arr[mid] == key) {
			return mid;
		} else if (key < arr[mid]) {
			return binarySearch(arr, l, mid-1, key);
		} else {
			return binarySearch(arr, mid+1, h, key);
		}
	}
	
	/**
	 * Given n non-negative integers representing an elevation map where the
	 * width of each bar is 1, compute how much water it is able to trap after
	 * raining.
	 * 
	 * Reference - https://leetcode.com/problems/trapping-rain-water/description/
	 * 
	 * @param arr
	 * @return
	 */
	public int trapRainWater(int[] arr) {
		int n = arr.length;
		int i = 0;
		int j = n-1;
		int maxleft = 0; 
		int maxright = 0;
		int res = 0;
		
		while(i<j) {
			if (arr[i] <= arr[j]) {
				if (arr[i] >= maxleft) {
					maxleft = arr[i];
				} else {
					res+=maxleft-arr[i];
				}
				i++;
			} else {
				if(arr[j] >= maxright) {
					maxright = arr[j];
				} else {
					res+=maxright-arr[j];
				}
				j--;
			}
		}
		return res;
	}
	
	/**
	 * You are given an n x n 2D matrix representing an image.
	 * 
	 * Rotate the image by 90 degrees (clockwise).
	 * 
	 * Given input matrix = [ [1,2,3], 
	 * 						  [4,5,6], 
	 * 					      [7,8,9] ],
	 * 
	 * rotate the input matrix in-place such that it becomes: [ [7,4,1],
	 * 															[8,5,2],
	 * 														    [9,6,3] ]
	 * 
	 * Reference - https://leetcode.com/problems/rotate-image/discuss/18872/A-common-method-to-rotate-the-image
	 * 
	 * 
	 * clockwise rotate
	 * first reverse up to down, then swap the symmetry 
	 * 1 2 3     7 8 9     7 4 1
	 * 4 5 6  => 4 5 6  => 8 5 2
	 * 7 8 9     1 2 3     9 6 3
	 * 
	 * 
	 * anticlockwise rotate
	 * first reverse left to right, then swap the symmetry
	 * 1 2 3     3 2 1     3 6 9
	 * 4 5 6  => 6 5 4  => 2 5 8
	 * 7 8 9     9 8 7     1 4 7

	 * 
	 * @param arr
	 */
	public void rotateMatrix90(int[][] arr) {
		int r = arr.length;
		
		int firstRow = 0;
		int lastRow = r-1;
		
		while(firstRow < lastRow) {
			int[] tmp = arr[firstRow];
			arr[firstRow] = arr[lastRow];
			arr[lastRow] = tmp;
			firstRow++;
			lastRow--;
		}
		
		for (int i=0; i<r; i++) {
			for (int j=0; j<i; j++) {
				int tmp = arr[i][j];
				arr[i][j] = arr[j][i];
				arr[j][i] = tmp;
			}
		}
	}
	
	/**
	 * Given n points on a 2D plane, find the maximum number of points that lie
	 * on the same straight line.
	 * 
	 * 
	 * A line is determined by two factors,say y=ax+b
	 * 
	 * If two points(x1,y1) (x2,y2) are on the same line(Of course).
	 * 
	 * Consider the gap between two points.
	 * 
	 * We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled
	 * since b is a constant
	 * 
	 * If a third point (x3,y3) are on the same line. So we must have y3=ax3+b
	 * 
	 * Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a
	 * 
	 * Since a is a rational, there exists y0 and x0,
	 * y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a
	 * 
	 * So we can use y0&x0 to track a line;
	 * 
	 * @param arr
	 * @return
	 */
	public int maxPointsOnLine(int[][] arr) {
		int n = arr.length;
		Map<String, Integer> map = new HashMap<>();
		int res = 0;
			
		for(int i=0; i<n; i++) {
			int maxRes = 0;
			int overlap = 0;
			map.clear();
			for (int j=i+1; j<n; j++) {
				int x1 = arr[i][0];
				int x2 = arr[j][0];
				int y1 = arr[i][1];
				int y2 = arr[j][1];
				
				int x = x1-x2;
				int y = y1-y2;
				
				if (x==0 && y==0) {
					overlap++;
					continue;
				}
				
				int gcd = gcd(x, y); // gcd will never be zero
				
				x = x/gcd;
				y = y/gcd;
				
				String key = x+":"+y;
				Integer val = map.get(key);
				if (val == null) {
					map.put(key, 1);
				} else {
					map.put(key, val+1);
				}
				maxRes = Math.max(maxRes, map.get(key));
			}
			res = Math.max(maxRes + overlap + 1, res);
		}
		return res;
	}
	
	private int gcd(int a, int b) {
		if (b==0) {
			return a;
		} else {
			return gcd(b, a%b);
		}
	}
	
	/**
	 * Place n queens on a n*n chess board such that they do not collide with each other.
	 * 
	 * @param n
	 */
	public void placeNQueens(int n) {
		List<Integer[]> res = new ArrayList<>();
		Integer[] columns = new Integer[n];
		placeNQueensUtil(n, 0, columns, res);
		System.out.println("Queen positions: ");
		for (Integer[] each : res) {
			for (int pos : each) {
				System.out.print(pos + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void placeNQueensUtil(int n, int row, Integer[] columns, List<Integer[]> res) {
		if (row == n) {
			res.add(columns.clone());
			return;
		}
		for (int col=0; col<n; col++) {
			if (checkValidPos(columns, row, col)) {
				columns[row] = col;
				placeNQueensUtil(n, row+1, columns, res);
			}
		}
	}
	
	private boolean checkValidPos(Integer[] columns, int row1, int col1) {
		for (int row2 = 0; row2 < row1; row2++) {
			int col2 = columns[row2];
			// check previous row's queen position
			if (col2 == col1) {
				return false;
			}
			// check diagonal - if distance bw rows == dist bw columns, then
			// they are on the same diagonal
			int col = Math.abs(col1 - col2);
			int row = row1 - row2;

			if (row - col == 0) {
				return false;
			}
		}
		return true;
	}
	
}
