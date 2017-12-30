package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * When to use which one :
 * 
 * If a string is going to remain constant throughout the program, then use
 * String class object because a String object is immutable. If a string can
 * change (example: lots of logic and operations in the construction of the
 * string) and will only be accessed from a single thread, using a StringBuilder
 * is good enough. If a string can change, and will be accessed from multiple
 * threads, use a StringBuffer because StringBuffer is synchronous so you have
 * thread-safety.
 * 
 * Let me first highlight three different ways for similar purpose.
 * 
 * length -- arrays (int[], double[], String[]) -- to know the length of the
 * arrays
 * 
 * length() -- String related Object (String, StringBuilder, etc) -- to know the
 * length of the String
 * 
 * size() -- Collection Object (ArrayList, Set, etc) -- to know the size of the
 * Collection
 * 
 * Now forget about length() consider just length and size().
 * 
 * length is not a method, so it completely makes sense that it will not work on
 * objects. It only works on arrays. size() its name describes it better and as
 * it is a method, it will be used in the case of those objects who work with
 * collection (collection frameworks) as I said up there.
 * 
 * Now come to length(): String is not a primitive array (so we can't use
 * .length) and also not a Collection (so we cant use .size()) that's why we
 * also need a different one which is length() (keep the differences and serve
 * the purpose).
 * 
 * @author gouthr
 *
 */
public class StringImpl {

	public static void main(String[] args) throws Exception {
		StringImpl strImpl = new StringImpl();
		
		// Permutations of a given string
		String str = "ABC";
		StringBuilder sb = new StringBuilder(str);
		strImpl.permute(sb, 0, sb.length()-1);
		
		// length of the longest palindromic subsequence
		str = "geeks4geeks";
		System.out.println("Length of the longest palindromic subsequence of the string: " 
				+ str + " is:" + strImpl.lps(str, 0, str.length()-1));
		
		// Longest common substring of 2 given strings
		System.out.println("Longest common substring of 2 given strings ABCDGH and AERTCDOR is: "
				+ strImpl.lcs("ABCDOH", "AERTCDOR"));
		
		// Longest common subsequence of 2 given strings
		System.out.println("Longest common subsequence of 2 given strings AGGTAB and GXTXAYB is: "
				+ strImpl.lcsubsequence("AGGTAB", "GXTXAYB"));
		
		// Isomorphic string check
		System.out.println("Isomorphic string check for aab and xxy: " 
				+ strImpl.isomorphicStrings("aab", "xxy"));
		System.out.println("Isomorphic string check for aab and xzy: " 
				+ strImpl.isomorphicStrings("aab", "xzy"));
		
		// Min distance between 2 strings in a list of strings
		List<String> list = new ArrayList<String>();
		list.add("cat");
		list.add("rat");
		list.add("squirrel");;
		list.add("giraffe");
		list.add("dog");
		list.add("elephant");
		int res = strImpl.minDistanceBwWords(list, "dog", "cat");
		System.out.println("Min distance between 2 words: " + res);	
		
		// Generate palindromic substrings of a string
		System.out.println("Palindromic substrings of the given string:");	
		Set<String> palinSubStrings = strImpl.generatePalindromicSubstrings("abcbad");
		for (final String s : palinSubStrings) {
			System.out.print(s + " ");
		}
		System.out.println();
		
		// Convert string to int
		String n = "123";
		System.out.println("String to int of " + n + " :" + strImpl.convertStringToInt(n));
		
		// Print substrings of a given string
		strImpl.generateSubstrings("abc");
		
		// Check if 2 strings are anagrams
		System.out.println("Anagram check: " + strImpl.anagramCheck("abcde", "adecb"));
		System.out.println("Anagram check: " + strImpl.anagramCheck("abaaa", "aaaab"));
		System.out.println("Anagram check: " + strImpl.anagramCheck("abaaa", "aaabb"));
		
		// All possible combinations of binary expressions
		System.out.println("All possible combinations of binary expressions: ");
		String inputStr = "10?1?";
		strImpl.combinationsOfExpressions(inputStr.toCharArray(), 0);
		
	}
	
	public void permute(StringBuilder str, int start, int end) {
		if (start == end) {
			System.out.println(str);
		} else {
			for (int i=start; i<=end; i++) {
				str = swap(str, start, i);
				permute(str, start+1, end);
				str = swap(str, start, i); //backtrack
			}
		}
	}
	
	/**
	 * Given an expression, find all possible combinations of expressions where
	 * '?' can be replaced by either 0 or 1. The expression is a binary
	 * expression. Eg: For the expression "10?1", the output would be "1001" and "1011".
	 * 
	 * @param res
	 * @param str
	 * @param index
	 */
	public void combinationsOfExpressions(char[] str, int index) {
		if (index == str.length) {
			System.out.println(str);
			return;
		}
		
		char ch = str[index];
		if (ch == '0' || ch == '1') {
			combinationsOfExpressions(str,index+1);
		} else if (ch == '?') {
			str[index] = '0';
			combinationsOfExpressions(str, index+1);
			str[index] = '1';
			combinationsOfExpressions(str, index+1);
			str[index] = '?'; // We can get back to this branch because of an earlier '?'
		}

	}
	
	/**
	 * Length of the longest palindromic subsequence in a given string.
	 * 
	 * Given a sequence, find the length of the longest palindromic subsequence in it. 
	 * For example, if the given sequence is “BBABCBCAB”, 
	 * then the output should be 7 as “BABCBAB” is the longest palindromic subseuqnce in it.
	 * “BBBBB” and “BBCBB” are also palindromic subsequences of the given sequence, but not the longest ones.
	 */
	public int lps(String str, int st, int end) {
		if (str.charAt(st) == str.charAt(end) && st == end) {
			return 1;
		} else if (str.charAt(st) == str.charAt(end) && st+1 == end) {
			return 2;
		} else if (str.charAt(st) == str.charAt(end)) {
			return 2 + lps(str, st+1, end-1);
		} else {
			return Math.max(lps(str, st+1, end), lps(str, st, end-1));
		}
	}
	
	/* 
	 * Length of longest common SUBSTRING of 2 given strings.
	 */
	public int lcs(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		int[][] arr = new int[m+1][n+1];
		int maxLen = 0;
		
		for(int i=0; i<=m; i++) {
			for (int j=0; j<=n; j++) {
				if (i==0 || j==0) {
					arr[i][j] = 0;
				} else if (str1.charAt(i-1) == str2.charAt(j-1)) {
						arr[i][j] = 1 + arr[i-1][j-1];
						maxLen = Math.max(maxLen, arr[i][j]);
				} else {
					arr[i][j] = 0;
				}
			}
		}
		return maxLen;		
	}
	
	/* 
	 * Length of longest common SUBSEQUENCE of 2 given strings.
	 */
	public int lcsubsequence(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		int[][] arr = new int[m+1][n+1];
		
		for(int i=0; i<=m; i++) {
			for (int j=0; j<=n; j++) {
				if (i==0 || j==0) {
					arr[i][j] = 0;
				} else if (str1.charAt(i-1) == str2.charAt(j-1)) {
					arr[i][j] = 1 + arr[i-1][j-1];
				} else {
					arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
				}
			}
		}
		return arr[m][n];		
	}
	
	/*
	 * Two strings str1 and str2 are called isomorphic if there is a one to one mapping possible 
	 * for every character of str1 to every character of str2. And all occurrences of every character
	 * in ‘str1′ map to same character in ‘str2′
	 */
	/*	Input:  str1 = "aab", str2 = "xxy"
			Output: True
			'a' is mapped to 'x' and 'b' is mapped to 'y'.

			Input:  str1 = "aab", str2 = "xyz"
			Output: False
			One occurrence of 'a' in str1 has 'x' in str2 and 
			other occurrence of 'a' has 'y'.*/
	public boolean isomorphicStrings(String str1, String str2) {
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		int m = str1.length();
		int n = str2.length();
		
		if (m != n) {
			return false;
		}
		
		if (m == n) {
			for (int i=0; i<m; i++) {
				if (map.get(str1.charAt(i)) == null) {
					map.put(str1.charAt(i), str2.charAt(i));
				} else {
					char val = (char)map.get(str1.charAt(i)) ;
					if (val != str2.charAt(i)) {
						return false;
					}
				}
			}
		}		
		return true;	
	}
	
	public int minDistanceBwWords(List<String> list, String str1, String str2) {
		int prev = -1;
		int minDist = Integer.MAX_VALUE;
		for(int i=0; i<list.size(); i++) {
			if (!list.get(i).equals(str1) && !list.get(i).equals(str2)) {
				continue;
			}
			if (prev == -1) {
				prev = i;
			} else {
				if (list.get(prev) != list.get(i) && minDist > (i - prev)) {
					minDist = i - prev;
				}
				prev = i;
			}
		}		
		return minDist;
	}

	/*
	 * The idea is to simply pick every character and move both the sides till
	 * the characters are same on the end. If they are not, then you break of
	 * the loop because if the substring is not a palindrome than the bigger
	 * string will not be a plaindrome too. TimeComplexity: O(n^2). Brute force
	 * is O(n^3). Generation of all substring of a string is itself O(n^2),
	 * hence this is an optimal solution.
	 */
	public Set<String> generatePalindromicSubstrings(String str) {
		Set<String> res = new LinkedHashSet<String>();
		for(int i=0; i<str.length(); i++) {
			StringBuilder sb = new StringBuilder();
			int j = i;
			int k = i+1;
			res.add(str.charAt(i)+"");
			for (int it=0; it<2; it++) {
				if (it == 1) {
					sb.setLength(0);
					sb.append(str.charAt(i));
					j = i-1;
					k = i+1;
				}
				while (j >=0 && k<str.length() && str.charAt(j) == str.charAt(k)) {
					sb.insert(0, str.charAt(j));
					res.add(sb.append(str.charAt(k)).toString());
					j--;
					k++;
				}
			}
		}
		return res;
	}
	
	/**
	 * Generate substrings of a given string.
	 * 
	 * @param str input string
	 */
	public void generateSubstrings(final String str) {
		int len = str.length();
		
		for (int i=0; i<len; i++) {
			for(int j=i+1; j<=len; j++) {
				String res = str.substring(i, j);
				System.out.println(res);
			}
		}
	}
	
	public boolean anagramCheck(final String str1, final String str2) {
		int m = str1.length();
		int n  = str2.length();
		
		if (m != n) {
			return false;
		}
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		Iterator<Integer> it = str1.chars().iterator();
		while(it.hasNext()) {
			Integer key = it.next();
			Integer val = map.get(key);
			if (val == null) {
				map.put(key, 1);
			} else {
				map.put(key, val+1);
			}
		}
		
		Iterator<Integer> it2 = str2.chars().iterator();
		while(it2.hasNext()) {
			Integer key = it2.next();
			Integer val = map.get(key);
			if (val == null || val == 0) {
				return false;
			} else if (val == 1) {
				map.remove(key);
			} else {
				map.put(key, val-1);
			}
		}
		
		if (!map.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	/*
	 * Convert String to integer
	 */
	public int convertStringToInt(String number) throws Exception {
		int res = 0;
		for(int i=0; i<number.length(); i++) {
			char c = number.charAt(i);
			if (c >= '0' && c <= '9') {
				res = (res*10) + (c - '0');
			} else {
				throw new Exception("Invalid input " + number);
			}
		}
		return res;
	}
	
	private StringBuilder swap(StringBuilder str, int i, int j) {
		char tmp = str.charAt(i);
		str.setCharAt(i, str.charAt(j));
		str.setCharAt(j, tmp);
		return str;
	}
}
