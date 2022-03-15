package Programmers.Level3.UndestroyedBuilding;
// 파괴되지 않은 건물
// https://programmers.co.kr/learn/courses/30/lessons/92344


import java.util.*;


class Solution {
    int N;
    int M;

    public int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;

        int[][] skillMap = new int[N][M];
        for (int[] s : skill) {
            skillCompress(skillMap, s);
        }
        printMap(skillMap);
        skillDecompress(skillMap);
        printMap(skillMap);
        effect(board, skillMap);

        return countBuilding(board);
    }

    private void printMap(int[][] map) {
        for (int[] m : map) {
            System.out.println(Arrays.toString(m));
        }
    }

    private void skillCompress(int[][] skillMap, int[] skill) {
        int x1 = skill[1];
        int y1 = skill[2];
        int x2 = skill[3] + 1;
        int y2 = skill[4] + 1;
        int degree = skill[0] == 1 ? -1 * skill[5] : skill[5];

        skillMap[x1][y1] += degree;
        if (x2 < N) {
            skillMap[x2][y1] += -1 * degree;
        }
        if (y2 < M) {
            skillMap[x1][y2] += -1 * degree;
        }
        if (x2 < N && y2 < M) {
            skillMap[x2][y2] += degree;
        }
    }

    private void skillDecompress(int[][] skillMap) {
        for (int i = 1; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                skillMap[i][j] += skillMap[i - 1][j];
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 1; j < M; ++j) {
                skillMap[i][j] += skillMap[i][j - 1];
            }
        }
    }

    private void effect(int[][] board, int[][] skillMap) {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                board[i][j] += skillMap[i][j];
            }
        }
    }

    private int countBuilding(int[][] board) {
        int count = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }
}


public class UndestroyedBuilding {
    public static void main(String[] args) {
        testSol(new int[][] {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
                new int[][] {{1, 0, 0, 3, 4, 4}}, 20);
        testSol(new int[][] {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
                new int[][] {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2},
                        {1, 0, 1, 3, 3, 1}},
                10);
        testSol(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                new int[][] {{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}}, 6);
        testSol(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, new int[][] {{1, 1, 1, 2, 2, 4},
                {1, 0, 0, 1, 1, 2}, {1, 0, 0, 1, 1, 2}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}},
                5);
    }

    private static void testSol(int[][] input1, int[][] input2, int out) {
        Solution sol = new Solution();
        int res = sol.solution(input1, input2);
        if (res == out) {
            System.out.println("O: " + res);
        } else {
            System.out.println("X: " + res + "\t expect:" + out);
        }
    }
}
