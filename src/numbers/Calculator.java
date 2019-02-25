package numbers;

import java.util.Stack;

public class Calculator {
    /**
     * Implement a basic calculator to evaluate a simple expression string.

		The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
		
		Example 1:
		
		Input: "1 + 1"
		Output: 2
		Example 2:
		
		Input: " 2-1 + 2 "
		Output: 3
		Example 3:
		
		Input: "(1+(4+5+2)-3)+(6+8)"
		Output: 23
     * @param s
     * @return
     */
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = 0;
        int number = 0;
        int res = 0;
        int sign = 1;
        Stack<Integer> st = new Stack<Integer>();
        while (i<s.length()) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                number = number*10 + (ch-'0');
            } else if (ch == '+') {
                res += sign*number;
                sign = 1;
                number = 0;
            } else if (ch == '-') {
                res += sign*number;
                sign = -1;
                number = 0;
            } else if (ch == '(') {
                st.push(res);
                st.push(sign);
                res = 0;
                sign = 1;
            } else if (ch == ')') {
                res += sign*number;
                number = 0;
                res *= st.pop();
                res += st.pop();
            }
            i++;
        }
        if (number != 0 ) {
            res += sign*number;
        }
        return res;
    }
    
	/**
	 * Implement a basic calculator to evaluate a simple expression string.
	 * 
	 * The expression string may contain open ( and closing parentheses ), the
	 * plus + or minus sign -, non-negative integers and empty spaces .
	 * 
	 * The expression string contains only non-negative integers, +, -, *, /
	 * operators , open ( and closing parentheses ) and empty spaces . The
	 * integer division should truncate toward zero.
	 * 
	 * @param s
	 * @return
	 */
	public int calculate2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int l1 = 0;
		int o1 = 1;
		int l2 = 1;
		int o2 = 1;

		int i = 0;
		int n = s.length();
		while (i < n) {
			if (Character.isDigit(s.charAt(i))) {
				int number = s.charAt(i) - '0';
				while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
					number = number * 10 + s.charAt(++i) - '0';
				}
				l2 = (o2 == 1 ? l2 * number : l2 / number);
			} else if (s.charAt(i) == '(') {
				int j = i;
				int cnt = 0;
				for (; i < n; i++) {
					if (s.charAt(i) == '(')
						cnt++;
					if (s.charAt(i) == ')')
						cnt--;
					if (cnt == 0)
						break;
				}
				int num = calculate(s.substring(j + 1, i));
				l2 = (o2 == 1 ? l2 * num : l2 / num);
			} else if (s.charAt(i) == '*' || s.charAt(i) == '/') {
				o2 = (s.charAt(i) == '*' ? 1 : -1);
			} else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
				l1 = l1 + o1 * l2;
				o1 = (s.charAt(i) == '+' ? 1 : -1);
				l2 = 1;
				o2 = 1;
			}
			i++;
		}
		return l1 + o1 * l2;
	}

}
