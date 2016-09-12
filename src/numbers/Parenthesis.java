package numbers;

import java.util.Stack;

public class Parenthesis {

	public static void main(String[] args) {
		Parenthesis p = new Parenthesis();
		// Balanced parenthesis check
		System.out.println("Balanced parenthesis check:");
		System.out.println("Braces are balanced: " + p.isParenthesisBalanced("{()()((())}[]"));

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
}
