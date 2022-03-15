package Programmers.Level2.bigInteger;
// 가장 큰 수
// https://programmers.co.kr/learn/courses/30/lessons/42746

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        ArrayList<String> numberList = new ArrayList<>();
        for (Integer number : numbers) {
            numberList.add(number.toString());
        }

        numberList.sort((o1, o2) -> {
            return Integer.parseInt(o2 + o1) - Integer.parseInt(o1 + o2);
        });

        for (String number : numberList) {
            answer += number;
        }

        return answer.charAt(0) == '0' ? "0" : answer;
    }
}


public class bigInteger {

    public static void main(String[] args) {
        testSol(new int[] {6, 10, 2}, "6210");
        testSol(new int[] {3, 30, 34, 5, 9}, "9534330");
        testSol(new int[] {0, 0, 0, 0}, "0");

    }

    static void testSol(int[] input, String output) {
        Solution sol = new Solution();
        // todo : solution match
        String res = sol.solution(input);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }


}

