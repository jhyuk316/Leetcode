package Uncategorized.MinimumRemoveToMakeValidParentheses;
// 1249. Minimum Remove to Make Valid Parentheses
// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

class Solution {
    public String minRemoveToMakeValid(String s) {

        Deque<Character> stack = new LinkedList<>();

        StringBuilder sb = new StringBuilder();

        for (Character c : s.toCharArray()) {
            switch (c) {
                case '(' -> {
                    stack.addLast(c);
                    sb.append(c);
                }
                case ')' -> {
                    if (Character.valueOf('(').equals(stack.peekLast())) {
                        stack.pollLast();
                        sb.append(c);
                    }
                }
                default -> {
                    sb.append(c);
                }

            }
        }

        int count = stack.size();
        for (int j = sb.length() - 1; j >= 0; --j) {
            if (count == 0) {
                break;
            }

            if (sb.charAt(j) == '(') {
                sb.deleteCharAt(j);
                count--;
            }
        }

        return sb.toString();
    }
}



public class MinimumRemoveToMakeValidParentheses {

    public static void main(String[] args) {

        Solution sol = new Solution();
        System.out.println(sol.minRemoveToMakeValid("))(("));
        System.out.println(sol.minRemoveToMakeValid("lee(t(c)o)de)").equals("lee(t(c)o)de"));
        System.out.println(sol.minRemoveToMakeValid("a)b(c)d").equals("ab(c)d"));
        System.out.println(sol.minRemoveToMakeValid("))((").equals(""));
    }
}
