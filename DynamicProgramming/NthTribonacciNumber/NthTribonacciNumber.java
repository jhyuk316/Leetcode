package DynamicProgramming.NthTribonacciNumber;

// 1137. N-th Tribonacci Number
// https://leetcode.com/problems/n-th-tribonacci-number/description/?envType=daily-question&envId=2024-04-23


import java.util.Arrays;

class Solution {
    int[] dp = new int[38];

    public int tribonacci(int n) {
        Arrays.fill(dp, -1);

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;

        return _tribonacci(n);
    }

    private int _tribonacci(int n) {
        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] = _tribonacci(n - 3) + _tribonacci(n - 2) + _tribonacci(n - 1);
        return dp[n];
    }
}

public class NthTribonacciNumber {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int tribonacci = solution.tribonacci(37);
        System.out.println("tribonacci = " + tribonacci);
    }
}
