package DynamicProgramming.CombinationSumIV;
// 377. Combination Sum IV
// https://leetcode.com/problems/combination-sum-iv/

import java.util.Arrays;


class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 0; i < target + 1; ++i) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}



public class CombinationSumIV {
    public static void main(String[] args) {
        testSol(new int[] {1, 2, 3}, 4, 7);
    }

    static void testSol(int[] input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.combinationSum4(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
