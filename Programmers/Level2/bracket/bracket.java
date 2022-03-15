package Programmers.Level2.bracket;
// 괄호 회전하기
// https://programmers.co.kr/learn/courses/30/lessons/76502

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); ++i) {
            if (isCorrect(s, i)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isCorrect(String s, int start) {
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put('(', ')');
        bracketMap.put('{', '}');
        bracketMap.put('[', ']');

        Stack<Character> stack = new Stack<>();
        int N = s.length();

        for (int i = 0; i < N; ++i) {
            char c = s.charAt((i + start) % N);
            if (bracketMap.containsKey(c)) {
                stack.add(bracketMap.get(c));
            } else {
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }

        }

        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}


public class bracket {
    public static void main(String[] args) {
        testSol("[](){}", 3);
        testSol("}]()[{", 2);

    }

    static void testSol(String input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.solution(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
