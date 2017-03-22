package strings;

import java.util.HashSet;

public class Subsequence {
     
    // set to store all the subsequences
    static HashSet<String> st = new HashSet<>();
    static HashSet<String> st1 = new HashSet<>();
 
    // It computes all the subsequence of an string
    static void subsequence(String str)
    {
        // iterate over the entire string
        for (int i = 0; i < str.length(); i++) {
             
            // iterate from the end of the string
            // to generate substrings
            for (int j = str.length(); j > i; j--) {
                String sub_str = str.substring(i, j);
             
                if (!st.contains(sub_str))
                    st.add(sub_str);
 
                // drop kth character in the substring
                // and if its not in the set then recur
                for (int k = 1; k < sub_str.length() - 1; k++) {
                    StringBuffer sb = new StringBuffer(sub_str);
 
                    // drop character from the string
                    sb.deleteCharAt(k);
                    if (!st.contains(sb))
                        ;
                    subsequence(sb.toString());
                }
            }
        }
    }
    
    static void subStrings(final String str) {
    	int len = str.length();
    	for(int c=0; c<len; c++) {
    		for (int i=len; i>c; i--) {
    			String subStr = str.substring(c, i);
    			st1.add(subStr);
    		}
    	}
    }
 
    // Driver code
    public static void main(String[] args)
    {
        String s = "aabc";
        subsequence(s);
        System.out.println(st);
        subStrings("fun");
        System.out.println(st1);
    }
}
