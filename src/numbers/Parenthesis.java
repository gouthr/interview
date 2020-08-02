package numbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Parenthesis {

	public static void main(String[] args) {
		Parenthesis p = new Parenthesis();
		
		// Balanced parenthesis check
		System.out.println("Balanced parenthesis check:");
		System.out.println("Braces are balanced: " + p.isParenthesisBalanced("{()()((())}[]"));
		
		// Print all possible correct combinations of parenthesis given a count
		int cnt = 3;
		System.out.println("Possible combinations of braces for cnt: " + cnt + "  is: " + p.generateParenthesis(cnt));
		
		// Print all valid parenthesis combinations by removing invalid parenthesis
		System.out.println("Valid parenthesis combinations: ");
		p.removeInvalidParenthesis("()())()");

	}
	
	public boolean isParenthesisBalanced(String paran) {
		if (paran == null) {
			return false;
		}
		Stack<Character> st = new Stack<Character>();
		int i = 0;
		while(i < paran.length()) {
			char ele = paran.charAt(i);
			if (ele == '{' || ele == '(' || ele == '[') {
				st.push(ele);
			} else {
				if (ele == '}' || ele == ')' || ele == ']') {
					if (!st.isEmpty() && isMatchingParan(ele, st.pop())) {
						i++;
						continue;
					} else {
						return false;
					}
				}
			}
			i++;
		}
		if (st.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Print all valid parenthesis combinations by removing invalid
	 * parenthesis.We need to remove minimum number of parentheses to make the
	 * input string valid. If more than one valid output are possible removing
	 * same number of parentheses then print all such output.
	 * 
	 * @param paren
	 */
	public void removeInvalidParenthesis(String paren) {
		Queue<String> q = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		boolean level = false;
		q.add(paren);
		visited.add(paren);
		
		while(!q.isEmpty()) {
			String tmp = q.remove();
			if (isParenthesisBalanced(tmp)) {
				System.out.println(tmp);
				// If answer is found, make level true
	            // so that valid string of only that level
	            // are processed.
				level = true;
			}
			if (level) {
				continue;
			}
			for (int i=0; i<tmp.length(); i++) {
				String newParen = tmp.substring(0,i) + tmp.substring(i+1);
				if (!visited.contains(newParen)) {
					q.add(newParen);
					visited.add(newParen);
				}
			}
		}
	}
	
	private boolean isMatchingParan(char ele, char pop) {
		if (ele == '}' && pop == '{') {
			return true;
		} else if (ele == ')' && pop == '(') {
			return true;
		} else if (ele == ']' && pop == '[') {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Given n pairs of parentheses, write a function to generate all
	 * combinations of well-formed parentheses.
	 * 
	 * For example, given n = 3, a solution set is:
	 * 
	 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
	 * 
	 * @param count
	 * @return
	 */
	public List<String> generateParenthesis(int count) {
		List<String> res = new ArrayList<>();
		generateParenthesisUtil("", 0, 0, count, res);
		return res;
	}
	
	private void generateParenthesisUtil(String str, int open, int close, int count, List<String> res) {
		if (close == count) {
			res.add(str);
			return;
		}
		if (open < count) {
			generateParenthesisUtil(str+"(", open+1, close, count, res);
		}
		if (open > close) {
			generateParenthesisUtil(str+")", open, close+1, count, res);
		}
	}
	
	/**
	 * Given a string s of '(' , ')' and lowercase English characters. 

		Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) 
		so that the resulting parentheses string is valid and return any valid string.
		
		Formally, a parentheses string is valid if and only if:
		
		It is the empty string, contains only lowercase characters, or
		It can be written as AB (A concatenated with B), where A and B are valid strings, or
		It can be written as (A), where A is a valid string.
	 * @param s
	 * @return
	 */
    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        boolean[] mark = new boolean[n];
        Stack<Integer> st = new Stack<>();
        
        int i = 0;
        while (i<n) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else if (s.charAt(i) == ')') {
                if (!st.isEmpty()) {
                    mark[st.pop()] = true;
                    mark[i] = true;
                }
            } else {
                mark[i] = true;
            }
            i++;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int j=0; j<n; j++) {
            if (mark[j]) {
                sb.append(s.charAt(j));
            }
        }
        return sb.toString();
    }
}
