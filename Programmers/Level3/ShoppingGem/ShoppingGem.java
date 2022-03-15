package Programmers.Level3.ShoppingGem;
// 보석 쇼핑
// https://programmers.co.kr/learn/courses/30/lessons/67258

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] gems) {
        int left = 0;
        int right = 0;

        Map<String, Integer> gemMap = new HashMap<>();
        for (String gem : gems) {
            gemMap.put(gem, 0);
        }
        // System.out.println(gemMap);

        int maxLength = 100001;
        int start = 0;
        int end = 0;
        while (right < gems.length) {
            gemMap.put(gems[right], gemMap.get(gems[right]) + 1);
            // System.out.println(gemMap);

            // check
            if (gemMap.get(gems[right]) == 1 && isAllBuy(gemMap)) {
                while (left <= right) {
                    gemMap.put(gems[left], gemMap.get(gems[left]) - 1);
                    if (gemMap.get(gems[left]) <= 0) {
                        left++;
                        break;
                    }
                    left++;
                }

                int length = right - left;
                if (maxLength > length) {
                    maxLength = length;
                    start = left;
                    end = right + 1;
                }
            }
            right++;
        }

        return new int[] {start, end};
    }

    private boolean isAllBuy(Map<String, Integer> gemMap) {
        boolean isAllBuy = true;
        for (int gemCount : gemMap.values()) {
            if (gemCount <= 0) {
                isAllBuy = false;
                break;
            }
        }
        return isAllBuy;
    }
}


public class ShoppingGem {
    public static void main(String[] args) {
        testSol(new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"},
                new int[] {3, 7});
        testSol(new String[] {"AA", "AB", "AC", "AA", "AC"}, new int[] {1, 3});
        testSol(new String[] {"XYZ", "XYZ", "XYZ"}, new int[] {1, 1});
        testSol(new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"}, new int[] {1, 5});
    }

    static void testSol(String[] input, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.solution(input);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }
}
