package Programmers.Level3.BadUser;
// 불량 사용자
// https://programmers.co.kr/learn/courses/30/lessons/64064

import java.util.*;
import java.util.regex.Pattern;

class Solution {
    boolean[] visited;
    Set<Set<String>> allAnswer = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        Arrays.sort(banned_id);

        allAnswer = new HashSet<>();
        visited = new boolean[user_id.length];
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
            if (!visited[i] && Pattern.matches(pattern, user_id[i])
                    && !answerSet.contains(user_id[i])) {
                // System.out.println(user_id[i] + " " + pattern);
                visited[i] = true;
                answerSet.add(user_id[i]);
                dfs(user_id, banned_id, j + 1, answerSet);
                answerSet.remove(user_id[i]);
                visited[i] = false;
            }
        }
        return;
    }
}


class Solution1 {
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        System.out.println(Arrays.toString(banned_id));

        Set<String> res = new HashSet<>();
        for (int i = 0; i < banned_id.length; ++i) {
            Set<String> temp = match(user_id, banned_id[i]);
            System.out.println(banned_id[i] + " " + temp);
            answer += count(res, temp);
            res.addAll(temp);
            System.out.println(answer);
        }

        return answer;
    }

    private Set<String> match(String[] user_id, String banned_id) {
        String pattern = banned_id.replace("*", ".");
        Set<String> set = new HashSet<>();
        for (int i = 0; i < user_id.length; ++i) {

            if (Pattern.matches(pattern, user_id[i])) {
                set.add(user_id[i]);
            }
        }
        return set;
    }

    private int count(Set<String> s1, Set<String> s2) {
        Set<String> retainSet = new HashSet<>(s1);
        retainSet.retainAll(s2);
        System.out.println("retainSet: " + retainSet.size());

        int combi = retainSet.size() * (retainSet.size() - 1) / 2;

        return s1.size() * s2.size() - (retainSet.size() * retainSet.size()) + combi;
    }

    // }private int count(Set<String> s1, Set<String> s2) {
    // Set<String> sumSet = new HashSet<>(s1);
    // sumSet.addAll(s2);
    //
    // Set<String> retainSet = new HashSet<>(s1);
    // retainSet.removeAll(s2);
    //
    // Set<String> pureS1 = new HashSet<>(s1);
    // pureS1.removeAll(retainSet);
    //
    // Set<String> pureS2 = new HashSet<>(s2);
    // pureS2.removeAll(retainSet);
    //
    // Set<String> complement = new HashSet<>();
    // complement.addAll(pureS1);
    // complement.addAll(pureS2);
    //
    // int combi = retainSet.size() * (retainSet.size() - 1) / 2;
    //
    // return pureS1.size() * pureS2.size() + retainSet.size() * complement.size() + combi;
    // }

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
