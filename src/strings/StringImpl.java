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
		System.out.println("Length of the longest palindromic subsequence of the string: " + str + " is:" + strImpl.lps(str, 0, str.length()-1));		
		
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
