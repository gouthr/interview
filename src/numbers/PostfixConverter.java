package numbers;

import java.util.Stack;

public class PostfixConverter {

	public static void main(String[] args) throws Exception {
		postfixConverter("(4+2)*3");
		System.out.println();
		System.out.println("Postfix exp value: " + evaluatePostfixExpression("42+3*"));
	}
	
	/* Given a infix expression, convert it into a postfix expression */
	public static void postfixConverter(String exp) {
		int len = exp.length();
		int i = 0;
		Stack<Character> st = new Stack<Character>();
		while(i<len) {
			char ch = exp.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				System.out.print(ch);
			} else {
				if (ch == '(') {
					st.push(ch);
				} else {
					if (ch == ')') {
						char pop_ele;
						while((pop_ele = st.pop()) != '(' && !st.isEmpty()) {
							System.out.print(pop_ele);
						}
					} else {
						while(!st.isEmpty() && checkPriority(ch) <= checkPriority(st.peek())) {
							System.out.print(st.pop());
						}
						st.push(ch);
					}
				}
			}
			i++;
		}
		while(!st.isEmpty()) {
			System.out.print(st.pop());
		}
	}
	
	private static int checkPriority(char ch) {
		if (ch == '(') {
			return 0;
		}
		if (ch == '+' || ch == '-') {
			return 1;
		}
		if (ch == '*' || ch == '/' || ch == '%') {
			return 2;
		}
		return 3;
	}
	
	public static int evaluatePostfixExpression(String exp) throws Exception {
		int len = exp.length();
		int i = 0;
		Stack<Integer> st = new Stack<Integer>();
		while(i<len) {
			char ch = exp.charAt(i);
			if (Character.isDigit(ch)) {
				st.push(Character.getNumericValue(ch));
			} else {
				int pop2 = st.pop();
				int pop1 = st.pop();
				int res = evaluate(pop1, pop2, ch);
				st.push(res);
			}
			i++;
		}
		return st.pop();
	}

	private static int evaluate(int arg1, int arg2, char op) throws Exception {
		if (op == '+') {
			return arg1 + arg2;
		} else if (op == '-') {
			return arg1 - arg2;
		} else if (op == '*') {
			return arg1*arg2;
		} else if (op == '/') {
			return arg1/arg2;
		} else if (op == '%') {
			return arg1/arg2;
		} else {
			throw new Exception("Invalid operator.");
		}
	}
}
