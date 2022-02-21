package DynamicProgramming.UncrossedLines;
// 1035. Uncrossed Lines
// https://leetcode.com/problems/uncrossed-lines/

import java.util.Arrays;
import java.util.List;


// O(M*N)
// dp[i][j] = dp[i-1][j-1]+1 if num1[i] == nums2[j]
// dp[i][j] = max(dp[i-1][j], dp[i][j-1]) if num1[i] != nums2[j]
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i < nums1.length + 1; ++i) {
            for (int j = 1; j < nums2.length + 1; ++j) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        for (int[] dpRow : dp) {
            System.out.println(Arrays.toString(dpRow));
        }

        return dp[nums1.length][nums2.length];
    }
}


public class UncrossedLines {
    public static void main(String[] args) {
        testSol(List.of(new int[] {1, 4, 2}, new int[] {1, 2, 4}), 2);
        testSol(List.of(new int[] {2, 5, 1, 2, 5}, new int[] {10, 5, 2, 1, 5, 2}), 3);
        testSol(List.of(new int[] {1, 3, 7, 1, 7, 5}, new int[] {1, 9, 2, 5, 1}), 2);
    }

    static void testSol(List input, Object output) {
        // todo : input, output match
        int[] arg1 = (int[]) input.get(0);
        int[] arg2 = (int[]) input.get(1);
        int out = (int) output;
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.maxUncrossedLines(arg1, arg2);
        if (res == out) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + out);
        }
    }

}
