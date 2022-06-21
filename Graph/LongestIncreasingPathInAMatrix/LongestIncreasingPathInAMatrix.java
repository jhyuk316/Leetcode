package Graph.LongestIncreasingPathInAMatrix;
// 329. Longest Increasing Path in a Matrix
// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

import java.util.Arrays;


// O(M*N) DFS, Memoization
class Solution {
    int M;
    int N;
    int[][] memo; // visited. 역주행이 없으므로 doing 필요없음.
    final int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        M = matrix.length;
        N = matrix[0].length;

        memo = new int[M][N];

        int maxLength = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (memo[i][j] == 0) {
                    maxLength = Math.max(maxLength, dfs(matrix, i, j));
                }
            }
        }

        return maxLength;
    }

    private int dfs(int[][] matrix, int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int result = 0;
        for (int k = 0; k < dir.length; ++k) {
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            if (x < 0 || x >= M || y < 0 || y >= N) {
                continue;
            }
            if (matrix[i][j] > matrix[x][y]) {
                result = Math.max(result, dfs(matrix, x, y));
            }
        }

        memo[i][j] = result + 1;
        return result + 1;
    }
}


public class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        testSol(new int[][] {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}, 4);
        testSol(new int[][] {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}, 4);
        testSol(new int[][] {{1}}, 1);

    }

    static void testSol(int[][] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.longestIncreasingPath(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
