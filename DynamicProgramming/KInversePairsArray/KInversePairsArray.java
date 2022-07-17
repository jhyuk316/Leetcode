package DynamicProgramming.KInversePairsArray;
// 629. K Inverse Pairs Array
// https://leetcode.com/problems/k-inverse-pairs-array/

import java.util.Arrays;

// O(N*K) DP
// dp[i][j] = sum(dp[i - 1][j - k]), 0 < k < i
class Solution {
    int MOD = (int) 1e9 + 7;

    public int kInversePairs(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        dp[1][0] = 1;

        int limit = 0;
        int sum = 0;
        for (int i = 2; i <= n; ++i) {
            limit += i - 1;
            sum = 0;
            for (int j = 0; j <= Math.min(k, limit); ++j) {
                if (j >= i) {
                    sum -= dp[i - 1][j - i];
                    sum = sum < 0 ? sum + MOD : sum;
                }
                sum = (sum + dp[i - 1][j]) % MOD;
                dp[i][j] = sum;
            }
        }

        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }

        return dp[n][k];
    }
}


public class KInversePairsArray {
    public static void main(String[] args) {
        testSol(3, 0, 1);
        testSol(3, 1, 2);
        testSol(3, 2, 2);
        testSol(3, 3, 1);
        testSol(4, 6, 1);
        testSol(6, 15, 1);

    }

    static void testSol(int input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.kInversePairs(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
