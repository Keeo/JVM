package fit.cvut.run.test;

import java.util.Stack;

public class Sat {
	final static int AND = 1;
	final static int OR = 2;
	final static int NOT = 3;

	/**
	 * Input is in postfix notation with a-z variables without gaps.
	 * @param args
	 * @throws Exception
	 */
	public static String main() throws Exception {
		String sat = "abc!d&|&";
		return findSolution(sat);
	}
	
	public static String findSolution(String sat){  	
		Expression e = buildTree(sat);
		String result = "";
		boolean[] solution = solve(e, variableCount(sat));
		if (solution != null) {
			for (int i = 0; i < solution.length; i++) {
				result += (char) (97 + i);
			}
			
			result +="\n";
			for (int i = 0; i < solution.length; i++) {
				result += (solution[i] ? '1' : '0');
			}
		}
		return result;
	}

	public static boolean[] solve(Expression e, int variableCount) {
		for (int instance = 0; instance < variableCount * variableCount; instance++) {
			boolean[] bits = new boolean[variableCount];
			for (int j = variableCount - 1; j >= 0; j--) {
				bits[j] = (instance & (1 << j)) != 0;
			}
			if (e.evaluate(bits)) {
				return bits;
			}
		}
		return null;
	}

	public static int variableCount(String sat) {
		boolean[] a = new boolean[25];
		for (int i = 0; i < sat.length(); i++) {
			char letter = sat.charAt(i);
			if (Character.isLetter(letter)) {
				a[letter - 97] = true;
			}
		}
		int counter = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i]) counter++;
		}
		return counter;
	}

	public static Expression buildTree(String sat) {
		Stack<Expression> stack = new Stack<Expression>();

		for (int i = 0; i < sat.length(); i++) {
			char c = sat.charAt(i);
			if (c == ' ') {
				continue;
			}

			if (Character.isLetter(c)) {
				Expression e = new Expression();
				e.name = c;
				stack.push(e);
			} else {
				if (c == '&' || c == '|'){
			
					Expression e = new Expression();
					e.operator = c == '&' ? AND : OR;
					e.right = stack.pop();
					e.left = stack.pop();
					stack.push(e);
				}else if (c == '!'){
					Expression e = new Expression();
					e.operator = NOT;
					e.left = stack.pop();
					stack.push(e);
				}
			}
		}

		return stack.pop();
	}
}





