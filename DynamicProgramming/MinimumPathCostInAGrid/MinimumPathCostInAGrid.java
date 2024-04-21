package DynamicProgramming.MinimumPathCostInAGrid;

import java.util.Arrays;

// 2304. Minimum Path Cost in a Grid
// https://leetcode.com/problems/minimum-path-cost-in-a-grid/

class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int[][] dp = new int[grid.length][grid[0].length];

        for (int i = 1; i < grid.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < grid[0].length; i++) {
            dp[0][i] = grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int k = 0; k < grid[0].length; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
        }

        return Arrays.stream(dp[dp.length - 1]).min().orElse(Integer.MAX_VALUE);
    }
}


public class MinimumPathCostInAGrid {

    public static void main(String[] args) {
        Solution sol = new Solution();

        int sol1 = sol.minPathCost(new int[][] {{5, 3}, {4, 0}, {2, 1}},
                new int[][] {{9, 8}, {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}});
        System.out.println("17 = " + sol1);

        int sol2 = sol.minPathCost(new int[][] {{5, 1, 2}, {4, 0, 3}}, new int[][] {{12, 10, 15},
                {20, 23, 8}, {21, 7, 1}, {8, 1, 13}, {9, 10, 25}, {5, 3, 2}});
        System.out.println("6 = " + sol2);

        int sol3 = sol.minPathCost(
                new int[][] {{28, 35, 29, 5, 13, 17, 18, 23, 14},
                        {30, 15, 12, 27, 2, 26, 25, 19, 7}, {1, 16, 34, 21, 9, 3, 20, 24, 8},
                        {4, 33, 22, 11, 31, 0, 6, 10, 32}},
                new int[][] {{87, 46, 11, 33, 55, 26, 26, 56, 23},
                        {77, 56, 72, 49, 35, 18, 37, 66, 37}, {54, 40, 62, 1, 64, 49, 95, 81, 77},
                        {80, 7, 76, 71, 91, 67, 75, 84, 52}, {65, 11, 13, 15, 9, 34, 10, 98, 20},
                        {1, 95, 100, 61, 33, 47, 28, 100, 44}, {39, 56, 94, 7, 64, 91, 66, 34, 70},
                        {37, 99, 62, 7, 23, 78, 74, 89, 97}, {84, 41, 63, 42, 84, 15, 46, 31, 11},
                        {60, 36, 27, 25, 37, 18, 4, 90, 43}, {35, 83, 90, 37, 91, 27, 61, 99, 53},
                        {85, 2, 98, 99, 67, 70, 38, 91, 68}, {66, 46, 7, 99, 26, 81, 95, 51, 51},
                        {41, 96, 66, 84, 61, 73, 78, 28, 63}, {38, 34, 49, 55, 35, 29, 93, 5, 28},
                        {3, 30, 80, 20, 23, 10, 93, 40, 33}, {8, 86, 47, 15, 45, 84, 47, 19, 58},
                        {72, 5, 76, 82, 60, 50, 13, 74, 38}, {4, 8, 25, 38, 29, 4, 60, 81, 21},
                        {65, 50, 74, 32, 9, 47, 71, 55, 14}, {90, 30, 92, 51, 45, 51, 4, 85, 22},
                        {30, 56, 1, 45, 63, 72, 91, 73, 60}, {51, 61, 53, 49, 44, 99, 11, 5, 3},
                        {24, 54, 91, 11, 5, 30, 50, 89, 44}, {87, 97, 46, 92, 93, 41, 64, 73, 15},
                        {94, 76, 90, 80, 30, 9, 88, 8, 33}, {50, 34, 4, 63, 49, 90, 46, 55, 16},
                        {10, 46, 80, 21, 97, 69, 70, 85, 31}, {10, 66, 74, 43, 65, 45, 85, 34, 91},
                        {82, 26, 77, 10, 2, 5, 89, 39, 4}, {80, 70, 89, 73, 54, 61, 100, 89, 23},
                        {30, 66, 80, 51, 3, 34, 92, 100, 63}, {74, 15, 4, 33, 37, 3, 87, 76, 92},
                        {44, 43, 77, 99, 27, 1, 23, 10, 8}, {8, 74, 17, 35, 31, 84, 97, 98, 34},
                        {99, 9, 28, 43, 55, 39, 93, 64, 93}});
        System.out.println("59 = " + sol3);

    }


}
