package Implement.StringToInteger;
// 8. String to Integer (atoi)
// https://leetcode.com/problems/string-to-integer-atoi/


class Solution {
    public int myAtoi(String s) {
        if (s.length() <= 0) {
            return 0;
        }

        // Trim
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        // sign
        int sign = 1;
        if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        // Digit
        double result = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            result *= 10;
            result += s.charAt(i) - '0';
            i++;
        }

        // Overflow
        result = sign * result;
        if (result <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (result >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int) result;
    }
}


public class StringToInteger {
    public static void main(String[] args) {
        testSol("42", 42);
        testSol("   -42", -42);
        testSol("4193 with words", 4193);
        testSol("00004193 with words", 4193);
        testSol("41dd93 with words", 41);
        testSol("--41dd93 with words", 0);
        testSol("words and 987", 0);
        testSol("21474836488words and 987", 2147483647);
        testSol("-21474836488words and 987", -2147483648);
        testSol("    ", 0);
        testSol("00000-42a1234", 0);
        testSol("9223372036854775808", 2147483647);
    }

    static void testSol(String input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.myAtoi(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
