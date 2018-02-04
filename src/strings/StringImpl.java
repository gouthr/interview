package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
		
		// String decodings
		System.out.println("String Decodings: ");
	    HashSet<String> result = new HashSet<String>();
	    strImpl.strDecodings("", "1123", result);
	    for (String s : result) {
	    	System.out.print(s + ", ");
	    }
	    System.out.println();
	    
	    // Word break problem
	    final List<String> dict = new ArrayList<String>();
	    dict.add("take");
	    dict.add("bat");
	    dict.add("bath");
	    dict.add("and");
	    dict.add("hand");
	    dict.add("come");
	    System.out.println("Word break problem soln: " + strImpl.wordBreak("takebathandcome", dict));
	    
	    System.out.println("Contiguous character occurence count: ");
	    strImpl.charContiguousOccurence("aaabbcbbaacz");
	    
	    System.out.println();
	    System.out.println("List of list of anagrams from the input list: ");
		List<String> input = Arrays.asList("cat", "dog", "dam", "mad", "donk", "key", "act", "god", "atc");
		System.out.println(strImpl.anagramList(input));
		
		// Edit distance between 2 strings
		System.out.println("Edit distance between 2 strings: " + strImpl.editDistance("sunday", "saturday"));

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

	/**
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
	
	/**
	 * If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that
	 * string can generate. Give a count as well as print the strings.
	 * 
	 * @param prefix
	 * @param code
	 * @param res
	 */
	public void strDecodings(String prefix, String code, HashSet<String> res) {
		int len = code.length();
		
		if (len == 0) {
			res.add(prefix);
			return;
		}
		
		if (code.charAt(0) == 0) {
			return;
		}
		
		strDecodings(prefix + (char)(code.charAt(0) - '1' + 'a'), code.substring(1), res);
		
		if (len >= 2) {
			int value = Integer.parseInt(code.substring(0, 2));
			if (value <= 26) {
				strDecodings(prefix + (char)(value  - 1 + 'a'), code.substring(2), res);
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
	
	/**
	 * Word Break problem - Given a string s and a dictionary, check whether the
	 * string can be broken down to valid words. Initially I was asked to return
	 * true or false based on whether string can be broken down or not. Later I
	 * was asked to modify my code to return the number of possible ways to
	 * break the string. Like for eg : "takebathandcome" can be broken down in 2
	 * ways
	 * 
	 * "take bath and come" "take bat hand come" So the modified function should
	 * return 2
	 */
	public int wordBreak(final String str, final List<String> dict) {
		if (str.length() == 0) {
			return 1;
		}
		int count = 0;
		for (int i=1; i<=str.length(); i++) {
			final String tmp = str.substring(0,i);
			if (dict.contains(tmp) && wordBreak(str.substring(i), dict) > 0) {
				count = count + wordBreak(str.substring(i), dict);
			}
		}
		
		return count;
	}
	
	/**
	 * Another one was say you had the following string: aaabbcbbaacc you have
	 * to print strings like that in the following format: a3b2c1b2a2c2
	 * 
	 * @param str
	 */
	public void charContiguousOccurence(final String str) {
		int count  = 1;
		int i;
		for (i=0; i<str.length()-1; i++) {
			if (str.charAt(i) == str.charAt(i+1)) {
				count++;
			} else {
				System.out.print(str.charAt(i));
				System.out.print(count);
				count = 1;
			}
		}
		System.out.print(str.charAt(i));
		System.out.print(count);
	}
	
	/**
	 * Return List<List<String>> which are anagrams of an List<String>
	 */
	public List<List<String>> anagramList(List<String> inputList) {
		Map<String, List<String>> map = new HashMap<>();
		for (String str : inputList) {
			char[] chArr = str.toCharArray();
			Arrays.sort(chArr);
			String key = String.valueOf(chArr);
			List<String> keyValue = map.get(key);
			if (keyValue == null) {
				List<String> value = new ArrayList<String>();
				value.add(str);		
				map.put(key, value);
			} else {
				keyValue.add(str);
				map.put(key, keyValue);
			}
		}
		List<List<String>> res = new ArrayList<List<String>>();
		for(List<String> tmp : map.values()) {
			res.add(tmp);
		}
		return res;
	}
	
	/**
	 * Given two strings str1 and str2 and below operations that can performed
	 * on str1. Find minimum number of edits (operations) required to convert
	 * ‘str1’ into ‘str2’.
	 * 
	 * Insert Remove Replace
	 * 
	 * @param str1
	 * @param str2
	 * @return int
	 * 
	 * 		The time complexity of above solution is exponential. In worst
	 *         case, we may end up doing O(3m) operations. The worst case
	 *         happens when none of characters of two strings match.
	 */
	public int editDistance(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		return editDistanceUtil(str1, str2, m, n);
	}
	
	private int editDistanceUtil(String str1, String str2, int m, int n) {
		if (m == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}
		
		if (str1.charAt(m-1) == str2.charAt(n-1)) {
			return editDistanceUtil(str1, str2, m-1, n-1);
		} else {
			return 1 + min(editDistanceUtil(str1, str2, m-1, n),
					editDistanceUtil(str1, str2, m, n-1),
					editDistanceUtil(str1, str2, m-1, n-1));
		}
	}
	
	private int min(int a, int b, int c) {
		if (a <= b && a <= c) {
			return a;
		} else if (b <= a && b <=c ) {
			return b;
		} else {
			return c;
		}
	}
	
	/**
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
