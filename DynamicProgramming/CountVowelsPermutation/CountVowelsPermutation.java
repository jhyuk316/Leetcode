package DynamicProgramming.CountVowelsPermutation;
// 1220. Count Vowels Permutation
// https://leetcode.com/problems/count-vowels-permutation/

import java.util.Arrays;


// O(N), space: O(1) long 12ms
class Solution {
    final int MOD = (int) 1E9 + 7;

    public int countVowelPermutation(int n) {
        long preA = 1;
        long preE = 1;
        long preI = 1;
        long preO = 1;
        long preU = 1;

        long A = 1;
        long E = 1;
        long I = 1;
        long O = 1;
        long U = 1;

        for (int i = 1; i < n; ++i) {
            preA = A;
            preE = E;
            preI = I;
            preO = O;
            preU = U;

            A = (preE + preI + preU) % MOD;
            E = (preA + preI) % MOD;
            I = (preE + preO) % MOD;
            O = preI;
            U = (preI + preO) % MOD;
        }

        return (int) ((A + E + I + O + U) % MOD);
    }
}


// O(N) long 44ms
class Solution2 {
    final int MOD = (int) 1E9 + 7;

    final int A = 0;
    final int E = 1;
    final int I = 2;
    final int O = 3;
    final int U = 4;

    public int countVowelPermutation(int n) {
        long[][] dp = new long[n + 1][5];

        for (int i = 0; i < 5; ++i) {
            dp[1][i] = 1;
        }

        for (int i = 2; i < n + 1; ++i) {
            dp[i][A] = (dp[i - 1][E] + dp[i - 1][I] + dp[i - 1][U]) % MOD;
            dp[i][E] = (dp[i - 1][A] + dp[i - 1][I]) % MOD;
            dp[i][I] = (dp[i - 1][E] + dp[i - 1][O]) % MOD;
            dp[i][O] = dp[i - 1][I];
            dp[i][U] = (dp[i - 1][I] + dp[i - 1][O]) % MOD;
        }

        return (int) (Arrays.stream(dp[n]).sum() % MOD);
    }
}


// O(N) int 39ms
class Solution1 {
    final int MOD = (int) 1E9 + 7;

    final int A = 0;
    final int E = 1;
    final int I = 2;
    final int O = 3;
    final int U = 4;

    public int countVowelPermutation(int n) {
        int[][] dp = new int[n + 1][5];

        for (int i = 0; i < 5; ++i) {
            dp[1][i] = 1;
        }

        for (int i = 2; i < n + 1; ++i) {
            dp[i][A] = ((dp[i - 1][E] + dp[i - 1][I]) % MOD + dp[i - 1][U]) % MOD;
            dp[i][E] = (dp[i - 1][A] + dp[i - 1][I]) % MOD;
            dp[i][I] = (dp[i - 1][E] + dp[i - 1][O]) % MOD;
            dp[i][O] = dp[i - 1][I];
            dp[i][U] = (dp[i - 1][I] + dp[i - 1][O]) % MOD;
        }

        return Arrays.stream(dp[n]).reduce(0, (i1, i2) -> (i1 + i2) % MOD);
    }
}


public class CountVowelsPermutation {
    public static void main(String[] args) {
        testSol(1, 5);
        testSol(2, 10);
        testSol(5, 68);
        testSol(144, 18208803);
    }

    static void testSol(int input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.countVowelPermutation(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
