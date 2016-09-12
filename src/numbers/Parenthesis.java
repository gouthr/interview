package numbers;

import java.util.Stack;

public class Parenthesis {

	public static void main(String[] args) {
		Parenthesis p = new Parenthesis();
		
		// Balanced parenthesis check
		System.out.println("Balanced parenthesis check:");
		System.out.println("Braces are balanced: " + p.isParenthesisBalanced("{()()((())}[]"));
		
		// Print all possible correct combinations of parenthesis given a count
		int cnt = 3;
		System.out.println("Possible combinations of braces for cnt: " + cnt + "  is :");
		p.printParenthesis(cnt);
		

	}
	
	public boolean isParenthesisBalanced(String paran) {
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
	
	public void printParenthesis(int cnt) {
		if (cnt > 0) {
			char[] str = new char[50];
			_printParenthesis(str, 0, cnt, 0, 0);
		}
	}
	
	private void _printParenthesis(char[] paran, int index, int cnt, int open, int close) {
		if (close == cnt) {
			System.out.println(paran);
			return;
		}
		if (open > close) {
			paran[index] = '}';
			_printParenthesis(paran, index+1, cnt, open, close+1);
		} 
		if (open < cnt) {
			paran[index] = '{';
			_printParenthesis(paran, index+1, cnt, open+1, close);
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
	
	private String parenthesis;
}
