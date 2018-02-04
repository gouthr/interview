package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Arrays.sort() vs Collections.sort() Arrays.sort works for arrays which can be
 * of primitive data type also. Collections.sort() works for objects - Collections
 * like ArrayList, LinkedList, etc.
 *
 */
public class BackTracking {
	
	public static void main(String[] args) {
		BackTracking bt = new BackTracking();
		
		// Return all permutations of the given array (array does not have duplicates)
		int[] arr = {1, 2, 3};
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		bt.permutation(arr, new ArrayList<Integer>(), res);
		System.out.println("Permutations of the given array: ");
		System.out.println(res);
		
		System.out.println("Combinations of k of the given array: ");
		List<List<Integer>> res1 = new ArrayList<List<Integer>>();
		bt.combinations(arr, new ArrayList<Integer>(), 0, 2, res1);
		System.out.println(res1);
		
		System.out.println("Combinations of numbers that add upto the given target: ");
		int[] arr4 = {1, 3, 2};
		List<List<Integer>> res4 = new ArrayList<List<Integer>>();
		bt.combinationSum(arr4, 5, new ArrayList<Integer>(), res4, 0);
		System.out.println(res4);
		
		System.out.println("Powerset of the given int array: ");
		List<List<Integer>> res2 = new ArrayList<List<Integer>>();
		bt.powerset(arr, new ArrayList<Integer>(), 0, res2);
		System.out.println(res2);
		
		System.out.println("All possible strings from a phone number pad digits combination: ");
		List<String> res3 = new ArrayList<>();
		bt.phoneNumberPadMapper("23", "", res3, 0);
		System.out.println(res3);
	}
	
	/**
	 * Generate all permutations of an array of nums (array does not have
	 * duplicates). 
	 * 
	 * Time Complexity: O(n*n!) Note that there are n!
	 * permutations and it requires O(n) time to print a permutation.
	 * 
	 * @param arr
	 * @param tmp
	 * @param n
	 * @param res
	 */
	public void permutation(int[] arr, List<Integer> tmp, List<List<Integer>> res) {
		int n = arr.length;
		if (tmp.size() == n) {
			res.add(new ArrayList<Integer>(tmp));
			return;
		}
		for (int i=0; i<n; i++) {
			if (tmp.contains(arr[i])) {
				continue; // element already exists, skip
			}
			tmp.add(arr[i]);
			permutation(arr, tmp, res);
			tmp.remove(tmp.size()-1);
		}
	}
	
	/**
	 * Given an integer array, generate all possible combinations of numbers
	 * that add up to the target value. Same number can be used multiple times.
	 * Same as the coins problem, given a set of coins, how many ways can the
	 * target amount be paid.
	 * 
	 * @param arr
	 * @param target
	 * @param tmp
	 * @param res
	 * @param st
	 */
	public void combinationSum(int[] arr, int target, List<Integer> tmp, List<List<Integer>> res, int st) {
		if (target < 0) {
			return;
		} else if (target == 0) {
			res.add(new ArrayList<Integer>(tmp));
		} else {
			for (int i=st; i<arr.length; i++) {
				tmp.add(arr[i]);
				combinationSum(arr, target - arr[i], tmp, res, i);
				tmp.remove(tmp.size()-1);
			}
		}
	}
	
	
	/**
	 * Given an int array, find all combinations of size k. Assume unique ints.
	 */
	public void combinations(int[] arr, List<Integer> tmp, int st, int k, List<List<Integer>> res) {
		if (tmp.size() == k) {
			res.add(new ArrayList<>(tmp));
			return;
		}
		if (st < arr.length) {
			tmp.add(arr[st]);
			combinations(arr, tmp, st+1, k, res);
			tmp.remove(tmp.size()-1);
			combinations(arr, tmp, st+1, k, res);
		}
	}
	
	/**
	 * A power set/ subsets contains all those subsets generated from a given set. The
	 * size of such a power set is 2^N.
	 */
	public void powerset(int[] arr, List<Integer> tmp, int st, List<List<Integer>> res) {
		res.add(new ArrayList<>(tmp));
		for (int i=st; i<arr.length; i++) {
			if(i > st && arr[i] == arr[i-1]) continue; // skip duplicates - for this arr needs to be sorted
			tmp.add(arr[i]);
			powerset(arr, tmp, i+1, res);
			tmp.remove(tmp.size()-1);
		}
	}
	
	/**
	 * Map mobile number pad keyboard to all possible strings that can be formed
	 * from the given digits.
	 */
	public void phoneNumberPadMapper(String digits, String tmp, List<String> res, int offset) {
		final String[] mapper = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		
		if (offset == digits.length()) {
			res.add(tmp);
			return;
		}
		
		for (char ch : mapper[digits.charAt(offset)-'0'].toCharArray()) {			
			phoneNumberPadMapper(digits, tmp+ch, res, offset+1);
		}
	}
	
	

}
