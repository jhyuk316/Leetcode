package Math.BasicCalculator;
// 224. Basic Calculator
// https://leetcode.com/problems/basic-calculator/

/**
 * 구현이 참 더럽다.
 */

import java.util.Stack;


// O(n) 해답 : 부호만 스택에 저장 바로 바로 계산???
class Solution {
    public int calculate(String s) {
        int result = 0;
        int sign = 1;
        int num = 0;

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(sign);

        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                result += sign * num;
                sign = stack.peek() * (c == '+' ? 1 : -1);
                num = 0;
            } else if (c == '(') {
                stack.push(sign);
            } else if (c == ')') {
                stack.pop();
            }
        }

        result += sign * num;
        return result;
    }
}


// O(n) 스택에 넣으면서 계산.
class Solution1 {
    Stack<Character> opStack = new Stack<>();
    Stack<Integer> numStack = new Stack<>();

    public int calculate(String s) {
        s = s.replace(" ", "");

        this.opStack = new Stack<>();
        this.numStack = new Stack<>();

        int tempNum = 0;
        boolean start = true;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            char op = ' ';
            if (start && c == '-') {
                numStack.push(0);
            }
            start = false;

            if (Character.isDigit(c)) {
                tempNum *= 10;
                tempNum += (c - '0');
                if (i + 1 == s.length() || !Character.isDigit(s.charAt(i + 1))) {
                    tempNum = _calculate(tempNum);
                    numStack.push(tempNum);
                    tempNum = 0;
                }
            } else if (c == '+' || c == '-') {
                opStack.push(c);
            } else if (c == '(') {
                opStack.push(c);
                start = true;
                continue;
            } else if (c == ')') {
                op = opStack.empty() ? ' ' : opStack.peek();
                while (op != '(') {
                    numStack.push(_calculate(numStack.pop()));
                    op = opStack.empty() ? ' ' : opStack.peek();
                    // System.out.println("op: " + this.opStack);
                    // System.out.println("num: " + this.numStack);
                }
                opStack.pop();
                while (!opStack.empty() && opStack.peek() != '(') {
                    numStack.push(_calculate(numStack.pop()));
                }
            }

            // System.out.println("op: " + this.opStack);
            // System.out.println("num: " + this.numStack);
        }

        return numStack.pop();
    }

    private int _calculate(int operand2) {
        if (this.opStack.empty() || this.numStack.empty() || this.opStack.peek() == '(') {
            return operand2;
        }
        int operand1 = this.numStack.pop();
        char op = this.opStack.pop();

        if (op == '+') {
            operand2 = operand1 + operand2;
        } else if (op == '-') {
            operand2 = operand1 - operand2;
        }
        return operand2;
    }
}


public class BasicCalculator {
    public static void main(String[] args) {
        testSol("", 0);
        testSol("1 + 1", 2);
        testSol("(0)", 0);
        testSol("(-1+3)", 2);
        testSol(" 2-1 + 2 ", 3);
        testSol("-1 + 1", 0);
        testSol("101 + 22", 123);
        testSol("(1+(4+5+2)-3)+(6+8)", 23);
        testSol(" -( -2+3)", -1);
        testSol("(7)-(0)+(4)", 11);
    }

    static void testSol(String input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.calculate(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
