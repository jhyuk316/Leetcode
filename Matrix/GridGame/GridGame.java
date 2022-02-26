package Matrix.GridGame;
// 2017. Grid Game
// https://leetcode.com/problems/grid-game/

import java.util.Arrays;

// O(N) 해답
class Solution1 {
    public long gridGame(int[][] g) {
        long top = Arrays.stream(g[0]).asLongStream().sum();
        long bottom = 0;
        long res = Long.MAX_VALUE;
        for (int i = 0; i < g[0].length; ++i) {
            top -= g[0][i];
            res = Math.min(res, Math.max(top, bottom));
            bottom += g[1][i];
        }
        return res;
    }
}


// O(N)
class Solution {
    public long gridGame(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        long[][] grid2 = new long[M][N];

        // 윗 줄은 왼쪽으로 더해감, 아랫 줄은 오른쪽으로 더해감.
        grid2[0][N - 1] = grid[0][N - 1];
        for (int j = N - 2; j >= 0; --j) {
            grid2[0][j] = grid[0][j] + grid2[0][j + 1];
        }
        grid2[1][0] = grid[1][0];
        for (int j = 1; j < N; ++j) {
            grid2[1][j] = grid2[1][j - 1] + grid[1][j];
        }

        System.out.println(Arrays.toString(grid2[0]));
        System.out.println(Arrays.toString(grid2[1]));

        // i 오른쪽 위의 값, 왼쪽 아래의 값의 최대값이 로봇2의 최대값.
        // 이 값의 최솟값을 찾으면 됨.
        long minValue = (long) Math.pow(2, 64) - 1;
        for (int j = 0; j < N; ++j) {
            long temp1 = j < N - 1 ? grid2[0][j + 1] : 0;
            long temp2 = j > 0 ? grid2[1][j - 1] : 0;
            minValue = Math.min(minValue, Math.max(temp1, temp2));
        }

        return minValue;
    }
}


public class GridGame {
    public static void main(String[] args) {
        testSol(new int[][] {{2, 5, 4}, {1, 5, 1}}, 4);
        testSol(new int[][] {{3, 3, 1}, {8, 5, 2}}, 4);
        testSol(new int[][] {{1, 3, 1, 15}, {1, 3, 3, 1}}, 7);
        testSol(new int[][] {{20, 3, 20, 17, 2, 12, 15, 17, 4, 15},
                {20, 10, 13, 14, 15, 5, 2, 3, 14, 3}}, 63);
    }

    static void testSol(int[][] input, long output) {
        Solution sol = new Solution();
        // todo : solution match
        long res = sol.gridGame(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
