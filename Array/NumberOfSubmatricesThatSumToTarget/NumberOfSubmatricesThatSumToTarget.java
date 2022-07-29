package Array.NumberOfSubmatricesThatSumToTarget;
// 1074. Number of Submatrices That Sum to Target
// https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
// 난이도 강화 문제 - https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/#문제-6-파괴되지-않은-건물

import java.util.Arrays;

class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] preSum = new int[N + 1][M + 1];

        // 가로 sum
        for (int i = 1; i < N + 1; ++i) {
            for (int j = 1; j < M + 1; ++j) {
                preSum[i][j] += preSum[i][j - 1] + matrix[i - 1][j - 1];
            }
        }
        // 세로 sum
        for (int j = 1; j < M + 1; ++j) {
            for (int i = 2; i < N + 1; ++i) {
                preSum[i][j] += preSum[i - 1][j];
            }
        }

        for (int[] mat : preSum) {
            System.out.println(Arrays.toString(mat));
        }

        int count = 0;
        for (int x = 1; x < N + 1; ++x) {
            for (int y = 1; y < M + 1; ++y) {
                for (int i = 0; i < x; ++i) {
                    for (int j = 0; j < y; ++j) {
                        int submatrices = preSum[x][y] - preSum[i][y] - preSum[x][j] + preSum[i][j];
                        if (submatrices == target) {
                            System.out.println(x + " " + y + " " + i + " " + j);
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}


class Solution1 {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] preSum = new int[N + 1][M + 1];

        // 가로 sum
        for (int i = 1; i < N + 1; ++i) {
            for (int j = 1; j < M + 1; ++j) {
                preSum[i][j] += preSum[i][j - 1] + matrix[i - 1][j - 1];
            }
        }
        // 세로 sum
        for (int j = 1; j < M + 1; ++j) {
            for (int i = 2; i < N + 1; ++i) {
                preSum[i][j] += preSum[i - 1][j];
            }
        }

        for (int[] mat : preSum) {
            System.out.println(Arrays.toString(mat));
        }

        int count = 0;
        for (int x = 1; x < N + 1; ++x) {
            for (int y = 1; y < M + 1; ++y) {
                for (int i = 0; i < x; ++i) {
                    for (int j = 0; j < y; ++j) {
                        int submatrices = preSum[x][y] - preSum[i][y] - preSum[x][j] + preSum[i][j];
                        if (submatrices == target) {
                            System.out.println(x + " " + y + " " + i + " " + j);
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}


public class NumberOfSubmatricesThatSumToTarget {
    public static void main(String[] args) {
        testSol(new int[][] {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0, 4);
        testSol(new int[][] {{1, -1}, {-1, 1}}, 0, 5);
        testSol(new int[][] {{904}}, 0, 0);
    }

    static void testSol(int[][] input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.numSubmatrixSumTarget(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
