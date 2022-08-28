package Array.SortTheMatrixDiagonally;
// 1329. Sort the Matrix Diagonally
// https://leetcode.com/problems/sort-the-matrix-diagonally/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


// O(M*N*logD) Solution. heap sort
// D: Max(M, N)
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int M = mat.length;
        int N = mat[0].length;
        Map<Integer, PriorityQueue<Integer>> d = new HashMap<>();

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                d.putIfAbsent(i - j, new PriorityQueue<>());
                d.get(i - j).add(mat[i][j]);
            }
        }

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                mat[i][j] = d.get(i - j).poll();
            }
        }
        return mat;
    }
}


// O(M*N*D) bubble sort
class Solution1 {
    int M;
    int N;

    public int[][] diagonalSort(int[][] mat) {
        M = mat.length;
        N = mat[0].length;

        sort(mat, 0, 0);
        for (int i = 1; i < M - 1; ++i) {
            sort(mat, i, 0);
        }

        for (int j = 1; j < N - 1; ++j) {
            sort(mat, 0, j);
        }

        return mat;
    }

    private void sort(int[][] mat, int r, int c) {
        int minLength = Math.min(M - r, N - c);
        int endI = r + minLength - 1;
        int endJ = c + minLength - 1;

        while (r < M - 1 && c < N - 1) {
            int i = endI;
            int j = endJ;
            while (r < i && c < j) {
                if (mat[i][j] < mat[i - 1][j - 1]) {
                    int temp = mat[i][j];
                    mat[i][j] = mat[i - 1][j - 1];
                    mat[i - 1][j - 1] = temp;
                }
                i--;
                j--;
            }
            r++;
            c++;
        }
    }
}


public class SortTheMatrixDiagonally {
    public static void main(String[] args) {
        testSol(new int[][] {{3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}},
                new int[][] {{1, 1, 1, 1}, {1, 2, 2, 2}, {1, 2, 3, 3}});
        testSol(new int[][] {{11, 25, 66, 1, 69, 7},
                {23, 55, 17, 45, 15, 52},
                {75, 31, 36, 44, 58, 8},
                {22, 27, 33, 25, 68, 4},
                {84, 28, 14, 11, 5, 50}},
                new int[][] {{5, 17, 4, 1, 52, 7},
                        {11, 11, 25, 45, 8, 69},
                        {14, 23, 25, 44, 58, 15},
                        {22, 27, 31, 36, 50, 66},
                        {84, 28, 75, 33, 55, 68}});
    }

    static void testSol(int[][] input, int[][] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[][] res = sol.diagonalSort(input);
        if (Arrays.deepEquals(res, output)) {
            System.out.println("O : " + matToString(res));
        } else {
            System.out.println("x : " + matToString(res) + "	expect : " + matToString(output));
        }
    }

    public static String matToString(int[][] mat) {
        String res = "\n";
        for (int[] row : mat) {
            res += Arrays.toString(row);
            res += "\n";
        }
        return res;
    }
}
