package CodeTEST.programers.WordChange;
// 단어 변환
// https://programmers.co.kr/learn/courses/30/lessons/43163

import java.util.Arrays;

class Solution {
    int MAX = 100;

    public int solution(String begin, String target, String[] words) {
        int answer = MAX;
        for (int i = 0; i < words.length; ++i) {
            if (diffCount(begin, words[i]) == 1) {
                int temp = dfs(words[i], target, words, new boolean[words.length]);
                if (temp != 0) {
                    answer = Math.min(answer, temp);
                }
            }
        }

        return answer != MAX ? answer : 0;
    }

    private int dfs(String begin, String target, String[] words, boolean[] visited) {
        System.out.println(begin + " " + Arrays.toString(visited));
        if (begin.equals(target)) {
            return 1;
        }

        if (Arrays.asList(words).indexOf(begin) != -1
                && visited[Arrays.asList(words).indexOf(begin)]) {
            return 0;
        }

        visited[Arrays.asList(words).indexOf(begin)] = true;

        int answer = MAX;
        for (int i = 0; i < words.length; ++i) {

            if (!visited[Arrays.asList(words).indexOf(words[i])]
                    && diffCount(begin, words[i]) == 1) {
                int temp = dfs(words[i], target, words, visited);
                if (temp != 0) {
                    answer = Math.min(answer, temp);
                }
            }
        }

        return answer != MAX ? answer + 1 : 0;
    }


    public int diffCount(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}


public class WordChange {
    public static void main(String[] args) {
        testSol("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}, 4);
        testSol("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}, 0);
    }

    static void testSol(String input1, String input2, String[] input3, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.solution(input1, input2, input3);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
