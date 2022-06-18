package DynamicProgramming.UniquePaths;
// 62. Unique Paths
// https://leetcode.com/problems/unique-paths/

import java.util.Arrays;
import java.util.List;


// O(n) tabulation dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; ++i) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}


// O(n) combination
class Solution3 {
    public int uniquePaths(int m, int n) {
        int num = m + n - 1;
        int combi = Math.min(m, n);

        double result = 1;
        for (int i = 1; i < combi; ++i) {
            result *= num - i;
            result /= i;
        }

        return (int) result;
    }
}


// DFS memorization
class Solution2 {
    int[][] memo;

    public int uniquePaths(int m, int n) {
        memo = new int[m][n];
        return dfs(m - 1, n - 1);
    }

    private int dfs(int i, int j) {
        if (i <= 0 || j <= 0) {
            return 1;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        memo[i][j] = dfs(i - 1, j) + dfs(i, j - 1);
        return memo[i][j];
    }
}


// DFS
class Solution1 {
    int count = 1;
    int N;
    int M;

    public int uniquePaths(int m, int n) {
        M = m;
        N = n;
        dfs(0, 0);

        return count;

    }

    private void dfs(int i, int j) {
        if (i >= M - 1 || j >= N - 1) {
            return;
        }

        dfs(i + 1, j);
        dfs(i, j + 1);
        count += 1;
    }
}


public class UniquePaths {
    public static void main(String[] args) {
        testSol(List.of(3, 7), 28);
        testSol(List.of(3, 2), 3);
        testSol(List.of(8, 20), 657800);
        testSol(List.of(15, 19), 471435600);
        // testSol(List.of(100, 100), 22750883079422934966181954039568885395604168260154104734000);
    }

    static void testSol(List input, Object output) {
        // todo : input, output match
        int arg1 = (int) input.get(0);
        int arg2 = (int) input.get(1);
        int out = (int) output;
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.uniquePaths(arg1, arg2);
        if (res == out) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + out);
        }
    }


}
