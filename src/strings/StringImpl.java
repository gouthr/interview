package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

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
		
		// Edit distance between 2 strings
		System.out.println("Edit distance between 2 strings optimized: " + strImpl.editDistanceDP("sunday", "saturday"));

		// Longest substring of str (pwwkew)with no repeating characters
		System.out.println("Longest substring of str (pwwkew)with no repeating characters: " + strImpl.longestSubstringWithoutRepeatingChars("pwwkew"));
		
		// Reverse words in a string
		System.out.println("Reverse words in a string: " + strImpl.reverseWordsInString("the sky is     blue"));
		
		// Find index of substring in a given string
		System.out.println("Index of substr in a given str: " + strImpl.strStr("Goutham", "ham"));
		System.out.println("Index of substr in a given str: " + strImpl.strStr("aaaaa", "bba"));
		System.out.println("Index of substr in a given str: " + strImpl.strStr("hello", "ll"));
		
		// top k frequent words
		// ["i", "love", "leetcode", "i", "love", "coding"]
		String[] strInput = {"i", "love", "leetcode", "i", "love", "coding"};
		System.out.println("Top k(2) frequent words: " + strImpl.topKFrequentWords(strInput, 2));
		
		String[] strs = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
		List<List<String>> res2 = strImpl.groupShiftedStrings(Arrays.asList(strs));
		System.out.println("Grouping shifted strings together: " + res2);
		
		String encodeddUrl = strImpl.encodeUrl("www.facebook.com/user1/profilepage");
		System.out.println("Encode url - (www.facebook.com/user1/profilepage):" + encodeddUrl);
		System.out.println("Decode url:" + strImpl.decodeUrl(encodeddUrl));
		
		// Input: pattern = "abba", str = "dog cat cat dog"
		System.out.println("Word pattern match: " + strImpl.wordPattern("dog cat cat dog", "abba"));
		// Input: pattern = "abba", str = "dog dog dog dog"
		System.out.println("Word pattern match: " + strImpl.wordPattern("dog dog dog dog", "abba"));
		
		/**
		 * Example 1: Input: dict = ["cat", "bat", "rat"] sentence =
		 * "the cattle was rattled by the battery" Output:
		 * "the cat was rat by the bat"
		 */
		String[] dict1 = {"cat", "bat", "rat"};
		System.out.println("Replace words with roots: " + strImpl.replaceWords("the cattle was rattled by the battery", 
				Arrays.asList(dict1)));
		
		/**
		 * Input: s = "catsanddog" wordDict = ["cat", "cats", "and", "sand", "dog"]
		 */
		String[] wordDict = {"cat", "cats", "and", "sand", "dog"};
		System.out.println("Word break problem 2: " + strImpl.wordBreak2("catsanddog", Arrays.asList(wordDict)));
		
		System.out.println("String palindrome check: " + strImpl.isPalindrome("A man, a plan, a canal: Panama"));
		
		char[] chars = {'a','a','b','b','c','c','c'};
		System.out.println("String compression length " + strImpl.compress(chars));
		
		System.out.println("Longest substring with 2 unique characters: " + strImpl.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
		
		// backspace compare
		System.out.println("Backspace compare:");
		System.out.println(strImpl.backspaceCompare("ab#c", "ad#c"));
		System.out.println(strImpl.backspaceCompare("ab##", "c#d#"));
		System.out.println(strImpl.backspaceCompare("a##c", "#a#c"));
		System.out.println(strImpl.backspaceCompare("a#c", "b"));
		
		// Second most repeated character in a string
		String testStr = "aaabbb";
		System.out.println("Second most frequent occuring character in a string + (" + testStr + "): " + strImpl.secondMostRepeatedCharInString(testStr));
		
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

	private StringBuilder swap(StringBuilder str, int i, int j) {
		char tmp = str.charAt(i);
		str.setCharAt(i, str.charAt(j));
		str.setCharAt(j, tmp);
		return str;
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
	 * 
	 * Time complexity : O(n^n) in the worst case.
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
	 * Given a non-empty string s and a dictionary wordDict containing a list of
	 * non-empty words, add spaces in s to construct a sentence where each word
	 * is a valid dictionary word. Return all such possible sentences.
	 * 
	 * Note:
	 * 
	 * The same word in the dictionary may be reused multiple times in the
	 * segmentation. You may assume the dictionary does not contain duplicate
	 * words. Example 1:
	 * 
	 * Input: s = "catsanddog" wordDict = ["cat", "cats", "and", "sand", "dog"]
	 * Output: [ "cats and dog", "cat sand dog" ]
	 * 
	 * @param str
	 * @param dict
	 * @return
	 */
	public List<String> wordBreak2(String str, List<String> dict) {
		List<String> res = new ArrayList<>();
		if (str.length() == 0) {
			res.add("");
			return res;
		}
		
		for (int i=1; i<=str.length(); i++) {
			String prefix = str.substring(0, i);
			if (dict.contains(prefix)) {
				List<String> tmp = wordBreak2(str.substring(i), dict);
				for (String word : tmp) {
					res.add(prefix + (word.isEmpty()? "" : " ") + word);
				}
			}
		}
		return res;
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
	 * Edit distance bw 2 strings optimized - O(mn).
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public int editDistanceDP(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		int arr[][] = new int[m+1][n+1];
		
		for (int i=0; i<=m; i++) {
			for (int j=0; j<=n; j++) {
				if (i == 0) {
					arr[i][j] = j;
				} else if (j == 0) {
					arr[i][j] = j;
				} else if (str1.charAt(i-1) == str2.charAt(j-1)) {
					arr[i][j] = arr[i-1][j-1];
				} else {
					arr[i][j] = 1 + min(arr[i-1][j],
										arr[i][j-1],
										arr[i-1][j-1]);
				}
			}
		}		
		return arr[m][n];
	}
	
	/**
	 * Longest substring with no repeating characters.
	 * 
	 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
	 * answer must be a substring, "pwke" is a subsequence and not a substring.
	 * 
	 * @param str
	 * @return
	 */
	public String longestSubstringWithoutRepeatingChars(String str) {
		Map<Character, Integer> map = new HashMap<>();
		String res = "";
		int max = 0;
		int index = 0;
		for (int i=0; i<str.length(); i++) {
			if (map.containsKey(str.charAt(i))) {
				index = map.get(str.charAt(i)) + 1;
			} 
			map.put(str.charAt(i), i);
			if (max < i-index+1) {
				res = str.substring(index, i+1);
				max = i-index+1;
			}			
		}
		return res;
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
	
	/**
	 * Reverse words in a string. For example, Given s = "the sky is blue",
	 * return "blue is sky the".
	 * 
	 * @param str
	 */
	public String reverseWordsInString(String str) {
		StringBuilder sb = new StringBuilder(str);		
		sb.reverse();
		int start = 0;
		for (int i=0; i<sb.length(); i++) {
			char ch = sb.charAt(i);
			if (ch == ' ') {
				 int end = i;
				 String revStr = new StringBuilder(sb.substring(start, end)).reverse().toString();			 
				 sb.replace(start, end, revStr);
				 start = i;
			}
		}
		// Last word needs to be reversed
		 String revStr = new StringBuilder(sb.substring(start, sb.length())).reverse().toString();			 
		 sb.replace(start, sb.length(), revStr);
		
		return sb.toString();
	}
	
	/**
	 * Return index of matching substring in string. Else return -1.
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr(String haystack, String needle) {
		if (haystack == null || needle == null) {
			return -1;
		}

		int m = haystack.length();
		int n = needle.length();
		if (m==0 || n==0) {
			return -1;
		}
		
		for (int i=0; i<=m-n; i++) {
			int count = 0;
			for (int j=0; j<n; j++) {
				if (haystack.charAt(i+j) != needle.charAt(j)) {
					break;
				}
				count++;
				if (count==n) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Given a non-empty list of words, return the k most frequent elements.
	 * 
	 * Your answer should be sorted by frequency from highest to lowest. If two
	 * words have the same frequency, then the word with the lower alphabetical
	 * order comes first.
	 * 
	 * Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
	 * Output: ["i", "love"] Explanation: "i" and "love" are the two most
	 * frequent words. Note that "i" comes before "love" due to a lower
	 * alphabetical order.
	 * 
	 * @param words
	 * @param k
	 * @return
	 */
	public List<String> topKFrequentWords(String[] words, int k){
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0)+1);
		}
		
		List<String> candidates = new ArrayList<>(map.keySet());
		// Collections.sort(candidates, (w1, w2)->map.get(w1) != map.get(w2)? map.get(w2)-map.get(w1) : w1.compareTo(w2)); // Using lambda
		
		Collections.sort(candidates, new Comparator<String>() {
			@Override
			public int compare(String w1, String w2) {
				return map.get(w1) != map.get(w2)? map.get(w2)-map.get(w1) : w1.compareTo(w2);
			}
			
		});
		
		return candidates.subList(0, k);
	}
	
	/**
	 * Given a string, we can "shift" each of its letter to its successive
	 * letter, for example: "abc" -> "bcd". We can keep "shifting" which forms
	 * the sequence:
	 * 
	 * "abc" -> "bcd" -> ... -> "xyz" Given a list of strings which contains
	 * only lowercase alphabets, group all strings that belong to the same
	 * shifting sequence.
	 * 
	 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
	 * A solution is:
	 * 
	 * [ ["abc","bcd","xyz"], ["az","ba"], ["acef"], ["a","z"] ]
	 * 
	 * @param strs
	 * @return
	 */
	public List<List<String>> groupShiftedStrings(List<String> strs) {
		Map<String, List<String>> map = new HashMap<>();
		List<List<String>> res = new ArrayList<>();
		for (String str : strs) {
			int n = str.length();
			String key = "";
			for (int i=n-1; i>0; i--) {
				int diff = str.charAt(i) - str.charAt(i-1);
				if (diff < 0) {
					diff += 26;
				}
				key += diff + ":";
			}
			List<String> val = map.get(key) == null ? new ArrayList<String>() : map.get(key);
			val.add(str);
			map.put(key, val);
		}
		for (List<String> eachRes : map.values()) {
			res.add(eachRes);
		}
		return res;
	}

	/**
	 * TinyUrl encode and decode implementation.
	 */
	Map<String, String> map = new HashMap<>();
	String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Random rand = new Random();
	
	private String getRand() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<6; i++) {
			sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
		}
		return sb.toString();
	}
	
	public String encodeUrl(String longUrl) {
		String key = getRand();
		while (map.containsKey(key)) {
			key = getRand(); 
		}
		String value = "http://tinyurl.com/" + key;
		map.put(key, longUrl);
		return value;
	}
	
	public String decodeUrl(String tinyUrl) {
		return map.get(tinyUrl.replace("http://tinyurl.com/", ""));
	}
	
	/**
	 * Given a pattern and a string str, find if str follows the same pattern.

		Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
		
		Example 1:
		
		Input: pattern = "abba", str = "dog cat cat dog"
		Output: true
		Example 2:
		
		Input:pattern = "abba", str = "dog cat cat fish"
		Output: false
		Example 3:
		
		Input: pattern = "aaaa", str = "dog cat cat dog"
		Output: false
		Example 4:
		
		Input: pattern = "abba", str = "dog dog dog dog"
		Output: false
	 * @param str
	 * @param pattern
	 * @return
	 */
	public boolean wordPattern(String str, String pattern) {
		String[] arr = str.split(" ");
		int m = arr.length;
		int n = pattern.length();
		
		if (m != n) {
			return false;
		}
		Map<Character, String> map = new HashMap<>();
		for (int i=0; i<m; i++) {
			char ch = pattern.charAt(i);
			if (map.containsKey(ch)) {
				String val = map.get(ch);
				if (!val.equals(arr[i])) {
					return false;
				}
			} else {
				if (map.containsValue(arr[i])) {
					return false;
				}
				map.put(ch, arr[i]);
			}
		}
		return true;
	}
	
	/**
	 * In English, we have a concept called root, which can be followed by some
	 * other words to form another longer word - let's call this word successor.
	 * For example, the root an, followed by other, which can form another word
	 * another.
	 * 
	 * Now, given a dictionary consisting of many roots and a sentence. You need
	 * to replace all the successor in the sentence with the root forming it. If
	 * a successor has many roots can form it, replace it with the root with the
	 * shortest length.
	 * 
	 * You need to output the sentence after the replacement.
	 * 
	 * Example 1: Input: dict = ["cat", "bat", "rat"] sentence =
	 * "the cattle was rattled by the battery" 
	 * Output:
	 * "the cat was rat by the bat"
	 * 
	 * @param str
	 * @param dict
	 * @return
	 */
	public String replaceWords(String str, List<String> dict) {
		Set<String> dictionary = new HashSet<>();
		for (String tmp : dict) {
			dictionary.add(tmp);
		}
		String[] words = str.split(" ");
		StringBuilder sb = new StringBuilder();
		String substr = null;
		
		for(String word: words) {
			for (int i=1; i<=word.length(); i++) {
				substr = word.substring(0, i);
				if (dictionary.contains(substr)) {
					break;
				}
			}
			sb.append(" " + substr);
		}
		return sb.substring(1).toString();
	}
	
	/**
	 * Palindrome check, ignoring non-alphanumeric characters.
	 * 
	 * @param s
	 * @return
	 */
    public boolean isPalindrome(String s) {
        int n = s.length();
        if (n <= 1) {
            return true;
        }
        s = s.toLowerCase();
        
        int i = 0;
        int j = n-1;

        while(i<j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
            	i++;
            	continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(j))) {
            	j--;
            	continue;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;       
    }
    
	/**
	 * Given an array of characters, compress it in-place.
	 * 
	 * The length after compression must always be smaller than or equal to the
	 * original array.
	 * 
	 * Every element of the array should be a character (not int) of length 1.
	 * 
	 * After you are done modifying the input array in-place, return the new
	 * length of the array. Input:
	 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
	 * 
	 * Output: Return 4, and the first 4 characters of the input array should
	 * be: ["a","b","1","2"].
	 * 
	 * Explanation: Since the character "a" does not repeat, it is not
	 * compressed. "bbbbbbbbbbbb" is replaced by "b12". Notice each digit has
	 * it's own entry in the array.
	 */
    public int compress(char[] chars) {
        int indexAns = 0, index = 0;
        while(index < chars.length){
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar){
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if(count != 1)
                for(char c : Integer.toString(count).toCharArray()) 
                    chars[indexAns++] = c;
        }
        return indexAns;
    }
    
	/**
	 * You are given a license key represented as a string S which consists only
	 * alphanumeric character and dashes. The string is separated into N+1
	 * groups by N dashes.
	 * 
	 * Given a number K, we would want to reformat the strings such that each
	 * group contains exactly K characters, except for the first group which
	 * could be shorter than K, but still must contain at least one character.
	 * Furthermore, there must be a dash inserted between two groups and all
	 * lowercase letters should be converted to uppercase.
	 * 
	 * Given a non-empty string S and a number K, format the string according to
	 * the rules described above.
	 * 
	 * @param S
	 * @param K
	 * @return
	 */
    public String licenseKeyFormatting(String S, int K) {
    	S = S.replaceAll("-", "");
    	int n = S.length();
        if (n == 0) {
            return "";
        }
    	StringBuilder sb = new StringBuilder();
    	int cnt = 0;
    	for(int i=n-1; i>=0; i--) {
    		sb.append(S.charAt(i));
    		cnt++;
    		if (cnt == K) {
    			sb.append("-");
    			cnt = 0;
    		}
    	}
    	String res = sb.reverse().toString().toUpperCase();
    	if (res.charAt(0) == '-') {
    		return res.substring(1);
    	}
    	return res;
        
    }
    
	/**
	 * Given two strings A and B, find the minimum number of times A has to be
	 * repeated such that B is a substring of it. If no such solution, return
	 * -1.
	 * 
	 * For example, with A = "abcd" and B = "cdabcdab".
	 * 
	 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a
	 * substring of it; and B is not a substring of A repeated two times
	 * ("abcdabcd").
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder();
        sb.append(A);
        int i = 1;
        while (sb.indexOf(B) < 0) {
        	if (sb.length() - A.length() > B.length()) {
        		return -1;
        	}
            sb.append(A);
        	i++;
        }
        return i;
    }
    
    /**
     * Longest substring with 2 unique characters.
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        final HashMap<Character, Integer> map = new HashMap<>();
        final char[] chArr = s.toCharArray();
        int i = 0;
        int res = Integer.MIN_VALUE;
        for (int j=0; j<chArr.length; j++) {
            map.put(chArr[j], map.getOrDefault(chArr[j], 0) + 1);
            while (map.size() > 2) {
            	map.put(chArr[i], map.get(chArr[i])-1);
            	if (map.get(chArr[i]) == 0) {
            		map.remove(chArr[i]);
            	}
            	i++;
            }
            res = Math.max(res, j-i+1);
        }
        return res;
    }
    
    /**
     * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
		
		Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
		
		For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
		
		Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.
		
		If there isn't such day, output -1.
		
		Example 1:
		Input: 
		flowers: [1,3,2]
		k: 1
		Output: 2
		Explanation: In the second day, the first and the third flower have become blooming.
		Example 2:
		Input: 
		flowers: [1,2,3]
		k: 1
		Output: -1
     * @param flowers
     * @param k
     * @return
     */
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < days.length; i++) {
            days[flowers[i] - 1] = i + 1;
        }
        int left = 0;
        int right = k + 1;
        int res = Integer.MAX_VALUE;
        for (int i = 1; right < days.length; i++) {
            // current days[i] is valid, continue scanning
            if (days[i] > days[left] && days[i] > days[right]) {
                continue;
            }
           // reach boundary of sliding window, since previous number are all valid, record result  
            if (i == right) {
                res = Math.min(res, Math.max(days[left],days[right]));
            }
            // not valid, move the sliding window
            left = i;
            right = left + k + 1;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    /**
     * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

		Example 1:
		
		Input: S = "ab#c", T = "ad#c"
		Output: true
		Explanation: Both S and T become "ac".
		Example 2:
		
		Input: S = "ab##", T = "c#d#"
		Output: true
		Explanation: Both S and T become "".
		Example 3:
		
		Input: S = "a##c", T = "#a#c"
		Output: true
		Explanation: Both S and T become "c".
		Example 4:
		
		Input: S = "a#c", T = "b"
		Output: false
		Explanation: S becomes "c" while T becomes "b".
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
    	char[] arrS = S.toCharArray();
    	StringBuilder sbS = new StringBuilder();
    	int i=0;
    	while (i<arrS.length) {
    		if (sbS.length()==0 && arrS[i] == '#') {
    			i++;
    			continue;
    		} else if (sbS.length()>=1 && arrS[i] == '#') {
    			sbS.replace(sbS.length()-1, sbS.length(), "");
    		} else {
    			sbS.append(arrS[i]);
    		}
    		i++;
    	}
    	
    	char[] arrT = T.toCharArray();
    	StringBuilder sbT = new StringBuilder();
    	i=0;
    	while (i<arrT.length) {
    		if (sbT.length()==0 && arrT[i] == '#') {
    			i++;
    			continue;
    		} else if (sbT.length()>=1 && arrT[i] == '#') {
    			sbT.replace(sbT.length()-1, sbT.length(), "");
    		} else {
    			sbT.append(arrT[i]);
    		}
    		i++;
    	}
    	
    	if (sbS.toString().equals(sbT.toString())) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Most common word in a paragraph, not in banned list.
     * 
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || banned == null) {
            return null;
        }
        Map<String, Integer> map = new HashMap<>();
        List<String> bannedList = Arrays.asList(banned);
        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split("\\s+"); // replace non-word characters with space
        for (String word : words) {
        	if (!bannedList.contains(word)){
        		map.put(word, map.getOrDefault(word, 0)+1);
        	} else {
                map.put(word, -1);
            }
        }
        Arrays.sort(words, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return map.get(o2) - map.get(o1);
			}	
        });
        
        return words[0];
    }
    
    /**
     * Regex matching.
     *  Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

		'.' Matches any single character.
		'*' Matches zero or more of the preceding element.
		The matching should cover the entire input string (not partial).
		
		Note:
		
		s could be empty and contains only lowercase letters a-z.
		p could be empty and contains only lowercase letters a-z, and characters like . or *.
		Example 1:
		
		Input:
		s = "aa"
		p = "a"
		Output: false
		Explanation: "a" does not match the entire string "aa".
		Example 2:
		
		Input:
		s = "aa"
		p = "a*"
		Output: true
		Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
		Example 3:
		
		Input:
		s = "ab"
		p = ".*"
		Output: true
		Explanation: ".*" means "zero or more (*) of any character (.)".
		Example 4:
		
		Input:
		s = "aab"
		p = "c*a*b"
		Output: true
		Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
    	if (p.isEmpty()) {
    		return s.isEmpty();
    	}
    	
    	if (p.length() >=2 && p.charAt(1) == '*') {
            // x* matches empty string or at least one character: x* -> xx*
            // *s is to ensure s is non-empty
    		return (isMatch(s, p.substring(2)) || (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0)=='.') && isMatch(s.substring(1), p)));
    	} else {
    		return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0)=='.') && isMatch(s.substring(1), p.substring(1));
    	}
    }
    
    /**
     * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).

		Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.

		Example 1:

		Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
		Output: "eeebffff"
		Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
		"cd" starts at index 2 in S, so it's replaced by "ffff".
		Example 2:
		
		Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
		Output: "eeecd"
		Explanation: "ab" starts at index 0 in S, so it's replaced by "eee". 
		"ec" doesn't starts at index 2 in the original S, so we do nothing.

     * @param S
     * @param indexes
     * @param sources
     * @param targets
     * @return
     */
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
    	TreeMap<Integer, Integer> tm = new TreeMap<>();
    	for (int i=0; i<indexes.length; i++) {
    		tm.put(indexes[i], i);
    	}
    	
    	StringBuilder sb = new StringBuilder(S);
    	for (int key : tm.descendingKeySet()) {
    		int index = tm.get(key);
    		if (S.startsWith(sources[index], indexes[index])) {
    			sb.replace(indexes[index], indexes[index]+sources[index].length(), targets[index]);
    		}
    	}
    	return sb.toString();
    }
    
    /**
     * Second most frequent character in a string. If 2 characters have the same frequency, return the lower character in ascii value.
     * @param str
     * @return
     */
    public Character secondMostRepeatedCharInString(String str) {
    	if (str == null || str.length() == 0 || str.length() == 1) {
    		return null;
    	}
    	Map<Character, Integer> map = new HashMap<>();
    	for (int i=0; i<str.length(); i++) {
    		Character ch = str.charAt(i);
    		map.put(ch, map.getOrDefault(ch, 0)+1);
    	}
    	if (map.size() <=1) {
    		return null;
    	}
    	List<Character> candidates = new ArrayList<Character>(map.keySet());
    	Collections.sort(candidates, new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				return map.get(o1) != map.get(o2)? map.get(o2) - map.get(o1) : o1.compareTo(o2);
			}
    	});
    	if (map.get(candidates.get(0)) == map.get(candidates.get(candidates.size()-1))) {
    		return null;
    	}
    	
    	int resIndex = -1;
    	for (int i=0; i<candidates.size()-1; i++) {
    		if (map.get(candidates.get(i)) == map.get(candidates.get(i+1))) {
    			continue;
    		} else {
    			resIndex = i+1;
    			break;
    		}
    	}
		return candidates.get(resIndex);
    }
    
    /**
     * A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
		
		Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".
		
		We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.
		
		Example 1:
		Input: 
		["9001 discuss.leetcode.com"]
		Output: 
		["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
		Explanation: 
		We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
		
		Example 2:
		Input: 
		["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
		Output: 
		["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
		Explanation: 
		We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        if (cpdomains == null || cpdomains.length == 0) {
            return res;
        }
        
        Map<String, Integer> map = new HashMap<>();
        for (String cp : cpdomains) {
            String[] parts = cp.split(" ");
            String count = parts[0];
            String name = parts[1];
            map.put(name, map.getOrDefault(name, 0) + Integer.parseInt(count));
            for (int i=0; i<name.length(); i++) {
                if (name.charAt(i) == '.') {
                    map.put(name.substring(i+1), map.getOrDefault(name.substring(i+1), 0) + Integer.parseInt(count));
                }
            }
        }
        
        for (String key : map.keySet()) {
            res.add(new String(map.get(key) + " " + key));
        }
        
        return res;
    }
}