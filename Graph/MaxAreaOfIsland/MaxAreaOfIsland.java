package Graph.MaxAreaOfIsland;
// 695. Max Area of Island
// https://leetcode.com/problems/max-area-of-island/

// O(N*M) DFS
class Solution {
    int N;
    int M;
    int[][] direction;

    public int maxAreaOfIsland(int[][] grid) {
        M = grid.length;
        N = grid[0].length;
        direction = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int answer = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 1) {
                    answer = Math.max(answer, dfs(grid, i, j));
                }
            }
        }

        return answer;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= M || j < 0 || j >= N) {
            return 0;
        }

        if (grid[i][j] == 0) {
            return 0;
        }

        int result = 1;
        grid[i][j] = 0;
        for (int d = 0; d < 4; ++d) {
            int x = i + direction[d][0];
            int y = j + direction[d][1];
            result += dfs(grid, x, y);
        }

        return result;
    }
}


public class MaxAreaOfIsland {
    public static void main(String[] args) {
        testSol(new int[][] {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}},
                6);

    }

    static void testSol(int[][] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.maxAreaOfIsland(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
