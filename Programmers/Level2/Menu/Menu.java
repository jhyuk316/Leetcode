package Programmers.Level2.Menu;
// 메뉴 리뉴얼
// https://programmers.co.kr/learn/courses/30/lessons/72411

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    List<String> combi;

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        // order 정렬
        for (int i = 0; i < orders.length; ++i) {
            char[] tempSort = orders[i].toCharArray();
            Arrays.sort(tempSort);
            orders[i] = new String(tempSort);
        }

        for (int j = 0; j < course.length; ++j) {
            Map<String, Integer> countCombi = new HashMap<>();
            for (int i = 0; i < orders.length; ++i) {
                for (String combi : bestCourse(orders[i], course[j])) {
                    countCombi.put(combi, countCombi.getOrDefault(combi, 0) + 1);
                }
            }

            System.out.println(countCombi);

            int maxCount = 2;
            List<String> tempRes = new ArrayList<>();
            for (Map.Entry<String, Integer> combi : countCombi.entrySet()) {
                if (maxCount < combi.getValue()) {
                    maxCount = combi.getValue();
                    tempRes = new ArrayList<>();
                    tempRes.add(combi.getKey());
                } else if (maxCount == combi.getValue()) {
                    tempRes.add(combi.getKey());
                }
            }
            answer.addAll(tempRes);
        }

        answer.sort((o1, o2) -> o1.compareTo(o2));
        return answer.toArray(new String[answer.size()]);
    }

    private List<String> bestCourse(String order, int num) {
        combi = new ArrayList<>();
        _bestCourse(order, num, 0, "");
        return combi;
    }

    private void _bestCourse(String order, int num, int start, String res) {
        if (num <= 0) {
            combi.add(res);
            return;
        }

        for (int i = start; i < order.length(); ++i) {
            _bestCourse(order, num - 1, i + 1, res + order.charAt(i));
        }
    }
}


public class Menu {
    public static void main(String[] args) {
        testSol(new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[] {2, 3, 4},
                new String[] {"AC", "ACDE", "BCFG", "CDE"});
        testSol(new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[] {2, 3, 5},
                new String[] {"ACD", "AD", "ADE", "CD", "XYZ"});
        testSol(new String[] {"XYZ", "XWY", "WXA"}, new int[] {2, 3, 4}, new String[] {"WX", "XY"});

    }

    static void testSol(String[] input1, int[] input2, String[] output) {
        Solution sol = new Solution();
        // todo : solution match
        String[] res = sol.solution(input1, input2);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
