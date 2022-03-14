package CodeTEST.programers.RotateMatrixBorder;
// 행렬 테두리 회전하기
// https://programmers.co.kr/learn/courses/30/lessons/77485

import java.util.Arrays;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] mat = new int[rows][columns];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                mat[i][j] = i * columns + j + 1;
            }
        }

        for (int i = 0; i < rows; ++i) {
            // System.out.println(Arrays.toString(mat[i]));
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            answer[i] = rotate(mat, queries[i]);
        }

        for (int i = 0; i < rows; ++i) {
            // System.out.println(Arrays.toString(mat[i]));
        }

        return answer;
    }

    public int rotate(int[][] mat, int[] query) {
        int topRow = query[0] - 1;
        int botRow = query[2] - 1;
        int leftCol = query[1] - 1;
        int rightCol = query[3] - 1;

        int tempOrigin = mat[topRow][leftCol];
        int minVal = tempOrigin;
        for (int i = topRow; i < botRow; ++i) {
            minVal = Math.min(minVal, mat[i][leftCol]);
            mat[i][leftCol] = mat[i + 1][leftCol];
        }
        for (int i = leftCol; i < rightCol; ++i) {
            minVal = Math.min(minVal, mat[botRow][i]);
            mat[botRow][i] = mat[botRow][i + 1];
        }
        for (int i = botRow; i > topRow; --i) {
            minVal = Math.min(minVal, mat[i][rightCol]);
            mat[i][rightCol] = mat[i - 1][rightCol];
        }
        for (int i = rightCol; i > leftCol; --i) {
            minVal = Math.min(minVal, mat[topRow][i]);
            mat[topRow][i] = mat[topRow][i - 1];
        }
        mat[topRow][leftCol + 1] = tempOrigin;

        return minVal;
    }
}


public class RotateMatrixBorder {
    public static void main(String[] args) {
        testSol(3, 6, new int[][] {{2, 2, 3, 4}}, new int[] {8});
        testSol(6, 6, new int[][] {{2, 2, 5, 4}}, new int[] {8});
        testSol(6, 6, new int[][] {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}},
                new int[] {8, 10, 25});
        testSol(3, 3, new int[][] {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}},
                new int[] {1, 1, 5, 3});
        testSol(100, 97, new int[][] {{1, 1, 100, 97}}, new int[] {1});

    }

    static void testSol(int input1, int input2, int[][] input3, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.solution(input1, input2, input3);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }
}
