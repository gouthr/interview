package strings;

public class StringImpl {

	public static void main(String[] args) {
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
		
	}
	
	/*
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
}
