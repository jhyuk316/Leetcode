package Programmers.Level4.Camping;
// 캠핑
// https://programmers.co.kr/learn/courses/30/lessons/1833

import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;

        Arrays.sort(data, (o1, o2) -> o1[0] - o2[0]);

        for (int[] d : data) {
            System.out.print(Arrays.toString(d) + " ");
        }

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (isCorrect(n, data, i, data[i], data[j])) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private boolean isCorrect(int n, int[][] data, int start, int[] t1, int[] t2) {
        if (t1[0] == t2[0] || t1[1] == t2[1]) {
            return false;
        }

        int x1 = Math.min(t1[0], t2[0]);
        int x2 = Math.max(t1[0], t2[0]);
        int y1 = Math.min(t1[1], t2[1]);
        int y2 = Math.max(t1[1], t2[1]);

        for (int i = start; i < n; ++i) {
            if (x1 < data[i][0] && data[i][0] < x2 && y1 < data[i][1] && data[i][1] < y2) {
                return false;
            }
        }
        return true;
    }
}


public class Camping {
    public static void main(String[] args) {
        testSol(4, new int[][] {{0, 0}, {1, 1}, {0, 2}, {2, 0}}, 3);
        testSol(5, new int[][] {{0, 0}, {1, 1}, {0, 2}, {2, 0}, {2, 1}}, 5);
        testSol(4, new int[][] {{0, 0}, {2, 2}, {0, 2}, {2, 0}}, 2);
        testSol(4, new int[][] {{0, 0}, {2, 1}, {0, 2}, {2, 0}}, 3);
        testSol(4, new int[][] {{0, 0}, {3, 1}, {3, 0}, {1, 1}}, 3);
        testSol(3, new int[][] {{0, 0}, {0, 1}, {0, 2}}, 0);
        testSol(3, new int[][] {{0, 0}, {1, 1}, {2, 2}}, 2);
        testSol(7, new int[][] {{0, 0}, {3, 0}, {0, 1}, {2, 1}, {4, 1}, {1, 2}, {3, 2}}, 12);
    }

    static void testSol(int input1, int[][] input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.solution(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
