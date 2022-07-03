package DynamicProgramming.WiggleSubsequence;
// 376. Wiggle Subsequence
// https://leetcode.com/problems/wiggle-subsequence/

import java.util.Arrays;

// O(N) dp
// + : dp[0][i] = Math.max(dp[0][i], dp[1][i - 1] + 1); if nums[i] > nums[i - 1]
// - : dp[1][i] = Math.max(dp[1][i], dp[0][i - 1] + 1); if nums[i] < nums[i - 1]
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int N = nums.length;
        int[][] dp = new int[2][N];

        for (int i = 1; i < N; ++i) {
            dp[0][i] = dp[0][i - 1];
            dp[1][i] = dp[1][i - 1];

            if (nums[i] > nums[i - 1]) {
                dp[0][i] = Math.max(dp[0][i], dp[1][i - 1] + 1);
            }
            if (nums[i] < nums[i - 1]) {
                dp[1][i] = Math.max(dp[1][i], dp[0][i - 1] + 1);
            }
        }

        System.out.println(Arrays.toString(dp[0]));
        System.out.println(Arrays.toString(dp[1]));

        return Math.max(dp[0][N - 1], dp[1][N - 1]) + 1;
    }
}


// dfs, bruteforce
class Solution1 {
    int maxLength = 0;
    int N;

    public int wiggleMaxLength(int[] nums) {
        N = nums.length;

        dfs(nums, 0, 0, 1);
        return maxLength;
    }

    private void dfs(int[] nums, int index, int sign, int depth) {
        maxLength = Math.max(maxLength, depth);
        for (int i = index + 1; i < N; ++i) {
            if (sign >= 0 && nums[index] > nums[i]) {
                dfs(nums, i, -1, depth + 1);
            }
            if (sign <= 0 && nums[index] < nums[i]) {
                dfs(nums, i, 1, depth + 1);
            }
        }
        return;
    }
}


public class WiggleSubsequence {
    public static void main(String[] args) {
        testSol(new int[] {1}, 1);
        testSol(new int[] {1, 1, 1}, 1);
        testSol(new int[] {1, 2, 1, 2, 2, 2}, 4);
        testSol(new int[] {1, 7, 4, 9, 2, 5}, 6);
        testSol(new int[] {1, 17, 5, 10, 13, 15, 10, 5, 16, 8}, 7);
        testSol(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 2);
    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.wiggleMaxLength(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
