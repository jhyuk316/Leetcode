package Programmers.Level2.ArcheryCompetition;
// 양궁대회
// https://programmers.co.kr/learn/courses/30/lessons/92342


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    List<int[]> answerList;
    int maxScoreDiff;

    public int[] solution(int n, int[] info) {
        answerList = new ArrayList<>();
        maxScoreDiff = 0;

        calculate(n, info, 0, new int[11]);
        // System.out.println(maxScoreDiff);

        answerList.sort((o1, o2) -> {
            int k = 10;
            while (o1[k] == o2[k]) {
                k--;
            }
            return o2[k] - o1[k];
        });

        return maxScoreDiff == 0 ? new int[] {-1} : answerList.get(0);
    }

    private void calculate(int n, int[] info, int k, int[] answer) {
        if (n == 0 || k >= 10) {
            if (n != 0 || k >= 10) {
                answer[10] = n;
            }
            int diff = diffScore(info, answer);
            // System.out.println(diff + " " + Arrays.toString(answer));
            if (diff > maxScoreDiff) {
                maxScoreDiff = diff;
                this.answerList.clear();
                this.answerList.add(answer);
            } else if (diff >= maxScoreDiff) {
                this.answerList.add(answer);
            }
            return;
        }

        calculate(n, info, k + 1, Arrays.copyOf(answer, answer.length));
        if (n > info[k]) {
            answer[k] = info[k] + 1;
            calculate(n - info[k] - 1, info, k + 1, Arrays.copyOf(answer, answer.length));
        }
        return;
    }

    private int diffScore(int[] info, int[] answer) {
        int apeach = 0;
        int lion = 0;
        for (int i = 0; i < 10; ++i) {
            if (info[i] < answer[i]) {
                lion += 10 - i;
            } else if (info[i] > 0) {
                apeach += 10 - i;
            }
        }
        return lion - apeach;
    }
}


public class ArcheryCompetition {
    public static void main(String[] args) {
        testSol(5, new int[] {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                new int[] {0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0});
        testSol(1, new int[] {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[] {-1});
        testSol(9, new int[] {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1},
                new int[] {1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0});
        testSol(10, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3},
                new int[] {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2});
    }

    private static void testSol(int input1, int[] input2, int[] output) {
        Solution sol = new Solution();
        int[] res = sol.solution(input1, input2);
        if (Arrays.equals(res, output)) {
            System.out.println("O: " + Arrays.toString(res));
        } else {
            System.out.println(
                    "X: " + Arrays.toString(res) + "\t expect: " + Arrays.toString(output));
        }
    }
}
