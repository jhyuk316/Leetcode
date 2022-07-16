package DynamicProgramming.OutOfBoundaryPaths;
// 576. Out of Boundary Paths
// https://leetcode.com/problems/out-of-boundary-paths/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


// O(M*N*K), DP, k = maxMove
class Solution {
    int MOD = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] direction = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] dp = new int[m][n];

        if (maxMove == 0) {
            return 0;
        }

        for (int i = 0; i < m; ++i) {
            dp[i][0]++;
            dp[i][n - 1]++;
        }
        for (int j = 0; j < n; ++j) {
            dp[0][j]++;
            dp[m - 1][j]++;
        }

        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }

        long res = dp[startRow][startColumn];

        for (int k = 0; k < maxMove - 1; ++k) {
            int[][] tempDp = new int[m][n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    for (int d = 0; d < 4; ++d) {
                        int x = i + direction[d][0];
                        int y = j + direction[d][1];
                        if (x < 0 || x >= m || y < 0 || y >= n) {
                            continue;
                        }
                        tempDp[i][j] = (tempDp[i][j] + dp[x][y]) % MOD;
                    }
                }
            }

            dp = tempDp;
            res = (res + dp[startRow][startColumn]) % MOD;
            for (int[] d : tempDp) {
                System.out.println(Arrays.toString(d));
            }
        }

        return (int) res;
    }
}


// BFS, Time-Out
class Solution1 {
    int MOD = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] direction = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] dp = new int[m][n];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startRow, startColumn});

        int result = 0;
        for (int i = 0; i < maxMove; ++i) {
            int size = queue.size();

            for (int j = 0; j < size; ++j) {
                int count = 0;
                int[] pos = queue.poll();
                int x = pos[0];
                int y = pos[1];

                if (x == 0) {
                    count++;
                }
                if (y == 0) {
                    count++;
                }
                if (x == m - 1) {
                    count++;
                }
                if (y == n - 1) {
                    count++;
                }

                for (int d = 0; d < 4; ++d) {
                    int xx = x + direction[d][0];
                    int yy = y + direction[d][1];
                    if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                        continue;
                    }
                    // count += dp[xx][yy];
                    queue.add(new int[] {xx, yy});
                }

                dp[x][y] = count;
                result = (result + count) % MOD;
            }

            System.out.println("dp");
            for (int[] d : dp) {
                System.out.println(Arrays.toString(d));
            }
        }

        return result;
    }
}


public class OutOfBoundaryPaths {

    public static void main(String[] args) {
        testSol(2, 2, 2, 0, 0, 6);
        testSol(1, 3, 3, 0, 1, 12);

    }

    static void testSol(int input1, int input2, int input3, int input4, int input5, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.findPaths(input1, input2, input3, input4, input5);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
