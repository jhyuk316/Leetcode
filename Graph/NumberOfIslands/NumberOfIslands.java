package Graph.NumberOfIslands;
// 200. Number of Islands
// https://leetcode.com/problems/number-of-islands/

import java.util.Arrays;

// O(m*n) DFS
class Solution {
    int N;
    int M;

    public int numIslands(char[][] grid) {
        N = grid.length;
        M = grid[0].length;
        char num = 2;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, (char) (num + '0'));
                    num++;
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            System.out.println(Arrays.toString(grid[i]));
        }

        return num - 2;
    }

    private void dfs(char[][] grid, int x, int y, char num) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return;
        }
        if (grid[x][y] != '1') {
            return;
        }

        grid[x][y] = num;

        dfs(grid, x + 1, y, num);
        dfs(grid, x - 1, y, num);
        dfs(grid, x, y + 1, num);
        dfs(grid, x, y - 1, num);
    }
}


public class NumberOfIslands {
    public static void main(String[] args) {
        testSol(new char[][] {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}}, 1);
        testSol(new char[][] {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}, 3);

    }

    static void testSol(char[][] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.numIslands(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
