package Programmers.Level3.BadUser;
// 불량 사용자
// https://programmers.co.kr/learn/courses/30/lessons/64064

import java.util.*;

class Solution {
    Set<Set<String>> allAnswer = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        allAnswer = new HashSet<>();
        dfs(user_id, banned_id, 0, new HashSet<>());

        for (Set<String> answer : allAnswer) {
            System.out.println(answer);
        }

        return allAnswer.size();
    }

    private void dfs(String[] user_id, String[] banned_id, int j, Set<String> answerSet) {
        if (j == banned_id.length) {
            allAnswer.add(new HashSet<>(answerSet));
            return;
        }

        String pattern = banned_id[j].replace("*", ".");
        for (int i = 0; i < user_id.length; ++i) {
            if (!answerSet.contains(user_id[i]) && user_id[i].matches(pattern)) {
                // System.out.println(user_id[i] + " " + pattern);
                answerSet.add(user_id[i]);
                dfs(user_id, banned_id, j + 1, answerSet);
                answerSet.remove(user_id[i]);
            }
        }
        return;
    }
}


public class BadUser {
    public static void main(String[] args) {
        testSol(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[] {"fr*d*", "abc1**"}, 2);
        testSol(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[] {"*rodo", "*rodo", "******"}, 2);
        testSol(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[] {"*****"}, 3);
        testSol(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[] {"*rodo", "*rodo", "*****"}, 1);
        testSol(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[] {"*rodo", "*rodo", "******", "******"}, 1);
        testSol(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[] {"fr*d*", "*rodo", "******", "******"}, 3);
    }

    private static void testSol(String[] input1, String[] input2, int output) {
        Solution sol = new Solution();
        int res = sol.solution(input1, input2);
        if (res == output) {
            System.out.println("O: " + res);
        } else {
            System.out.println("X: " + res + "\t expect: " + output);
        }
    }
}
