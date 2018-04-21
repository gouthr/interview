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
		
		System.out.println("Permutations of a string: ");
		bt.strPermutations("ABC");
		
		System.out.println("Add operators to evaluate an expression to a target value:");
		bt.insertOpToEvaluateToTarget("105", 5);
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
		for(int i=st; i<arr.length; i++) {
			tmp.add(arr[i]);
			combinations(arr, tmp, i+1, k, res);
			tmp.remove(tmp.size()-1);
			// combinations(arr, tmp, st+1, k, res);
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
	
	/** 
	 * Generate all permutations of a string.
	 * 
	 * @param str
	 */
	public void strPermutations(String str) {
		List<Character> tmp = new ArrayList<>();
		List<List<Character>> res = new ArrayList<>();
		strPermutationsUtil(str.toCharArray(), tmp, res);
		System.out.println(res);
	}

	private void strPermutationsUtil(char[] str, List<Character> tmp, List<List<Character>> res) {
		if (str.length == tmp.size()) {
			res.add(new ArrayList<>(tmp));
			return;
		}
		for (int i=0; i<str.length; i++) {
			if (tmp.contains(str[i])) {
				continue;
			}
			tmp.add(str[i]);
			strPermutationsUtil(str, tmp, res);
			tmp.remove(tmp.size()-1);
		}
	}
	
	/**
	 * Given a string that contains only digits 0-9 and a target value, return
	 * all possibilities to add binary operators (not unary) +, -, or * between
	 * the digits so they evaluate to the target value.
	 * 
	 * This problem has a lot of edge cases to be considered:
	 * overflow: we use a long type once it is larger than Integer.MAX_VALUE or
	 * minimum, we get over it. 
	 * 0 sequence: because we can’t have numbers with multiple digits started with zero, we have to deal with it too.
	 * a little trick is that we should save the value that is to be multiplied in the next recursion.
	 */
	public void insertOpToEvaluateToTarget(String exp, int target) {
		String resExp = "";
		List<String> resList = new ArrayList<>();
		insertOpToEvaluateToTargetUtil(exp, resExp, resList, target, 0, 0, 0);
		System.out.println(resList);
	}
	
	/**
	 * if(i != pos && num.charAt(pos) == ‘0’) break;
	 * 
	 * I think this condition it to exclude numbers with leading zeros
	 * 
	 * Valid answers are:
	 * 
	 * "105", 5 -> ["1*0+5","10-5"] "00", 0 -> ["0+0", "0-0", "0*0"] but with
	 * leading zeros we will have
	 * 
	 * "105", 5 -> ["1*0+5","10-5", "1*05"] "00", 0 -> ["0+0", "0-0", "0*0",
	 * "00"]
	 */
	private void insertOpToEvaluateToTargetUtil(String exp, String resExp, List<String> resList, int target, int pos,
			int eval, int lastOperand) {
		if (pos == exp.length() && eval == target) {
			resList.add(resExp);
			return;
		}

		for (int i = pos; i < exp.length(); i++) {
			if (i != pos && exp.charAt(pos) == '0') {
				break;
			}
			int val = Integer.parseInt(exp.substring(pos, i + 1));
			if (pos == 0) {
				insertOpToEvaluateToTargetUtil(exp, resExp + val, resList, target, i + 1, val, val);
			} else {
				insertOpToEvaluateToTargetUtil(exp, resExp + "+" + val, resList, target, i + 1, eval + val, val);
				insertOpToEvaluateToTargetUtil(exp, resExp + "-" + val, resList, target, i + 1, eval - val, -val);
				insertOpToEvaluateToTargetUtil(exp, resExp + "*" + val, resList, target, i + 1,
						eval - lastOperand + (lastOperand * val), lastOperand * val);
			}
		}
	}
	
    // Returns count of possible paths to reach 
    // cell at row number m and column number n 
    // from the topmost leftmost cell (cell at 1, 1)
    static int  numberOfPaths(int m, int n)
    {
        // If either given row number is first or 
        // given column number is first
        if (m == 1 || n == 1)
            return 1;
  
        // If diagonal movements are allowed then 
        // the last addition is required.
        return  numberOfPaths(m-1, n) + numberOfPaths(m, n-1);
                // + numberOfPaths(m-1,n-1);
    }
	
	/**
	 * Returns count of possible paths to reach cell at row number m and column
	 * number n from the topmost leftmost cell (cell at 1, 1)
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int countPaths(int m, int n) {
		int[][] arr = new int[m][n];
		
        // Count of paths to reach any cell in 
        // first column is 1
		for (int i=0; i<m; i++) {
			arr[i][0] = 1;
		}
		
        // Count of paths to reach any cell in
        // first row is 1
		for (int i=0; i<n; i++) {
			arr[0][i] = 1;
		}
		
		for(int i=1; i<m; i++) {
			for (int j=1; j<n; j++) {
		        // By uncommenting the last part the 
	            // code calculates the total possible paths 
	            // if the diagonal Movements are allowed
				arr[i][j] = arr[i-1][j] + arr[i][j-1]; // + arr[i-1][j-1];
			}
		}	
		return arr[m-1][n-1];
	}
}
