package Graph.NumberOfEnclaves;
// 1020. Number of Enclaves
// https://leetcode.com/problems/number-of-enclaves/

import java.util.Arrays;


// O(M*N) DFS 색칠
class Solution {
    int M;
    int N;

    public int numEnclaves(int[][] grid) {
        M = grid.length;
        N = grid[0].length;

        // set zero
        for (int i = 0; i < M; ++i) {
            dfsZero(grid, i, 0);
            dfsZero(grid, i, N - 1);
        }
        for (int j = 0; j < N; ++j) {
            dfsZero(grid, 0, j);
            dfsZero(grid, M - 1, j);
        }

        // for (int[] row : grid) {
        // System.out.println(Arrays.toString(row));
        // }

        int count = 0;
        for (int i = 1; i < M - 1; ++i) {
            for (int j = 1; j < N - 1; ++j) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private void dfsZero(int[][] grid, int i, int j) {
        if (i < 0 || i >= M || j < 0 || j >= N) {
            return;
        }

        if (grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;
        dfsZero(grid, i + 1, j);
        dfsZero(grid, i - 1, j);
        dfsZero(grid, i, j + 1);
        dfsZero(grid, i, j - 1);
    }
}


// O(M*N) DFS 색칠, DFS로 카운트
class Solution1 {
    boolean[][] visited;

    int M;
    int N;

    public int numEnclaves(int[][] grid) {
        M = grid.length;
        N = grid[0].length;

        visited = new boolean[M][N];

        // set zero
        for (int i = 0; i < M; ++i) {
            dfsZero(grid, i, 0);
            dfsZero(grid, i, N - 1);
        }
        for (int j = 0; j < N; ++j) {
            dfsZero(grid, 0, j);
            dfsZero(grid, M - 1, j);
        }

        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }

        int count = 0;
        for (int i = 1; i < M - 1; ++i) {
            for (int j = 1; j < N - 1; ++j) {
                if (grid[i][j] == 1 && visited[i][j] == false) {
                    count += dfs(grid, i, j, 0);
                }
            }
        }

        return count;
    }

    private void dfsZero(int[][] grid, int i, int j) {
        if (i < 0 || i >= M || j < 0 || j >= N) {
            return;
        }

        if (grid[i][j] == 0) {
            return;
        }

        visited[i][j] = true;
        grid[i][j] = 0;
        dfsZero(grid, i + 1, j);
        dfsZero(grid, i - 1, j);
        dfsZero(grid, i, j + 1);
        dfsZero(grid, i, j - 1);
    }

    private int dfs(int[][] grid, int i, int j, int depth) {
        if (i < 0 || i >= M || j < 0 || j >= N) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }

        if (visited[i][j] == true) {
            return 0;
        }
        visited[i][j] = true;

        int count = 0;
        count += dfs(grid, i + 1, j, depth);
        count += dfs(grid, i - 1, j, depth);
        count += dfs(grid, i, j + 1, depth);
        count += dfs(grid, i, j - 1, depth);

        return count + 1;
    }
}


public class NumberOfEnclaves {
    public static void main(String[] args) {
        testSol(new int[][] {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}}, 3);
        testSol(new int[][] {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}}, 0);
    }

    static void testSol(int[][] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.numEnclaves(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
