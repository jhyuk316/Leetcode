package DynamicProgramming.JumpGameII;
// 45. Jump Game II
// https://leetcode.com/problems/jump-game-ii/

import java.util.Arrays;


// O(N) 최대 도달 가능 거리 갱신 할 때마다 카운트
class Solution {
    public int jump(int[] nums) {
        int maxPos = 0;
        int nextMaxPos = nums[0];
        int count = 0;

        int i = 0;
        while (maxPos < nums.length - 1) {
            maxPos = nextMaxPos;
            count++;
            while (i < nums.length && i <= maxPos) {
                nextMaxPos = Math.max(nextMaxPos, i + nums[i]);
                i++;
            }
        }

        return count;
    }
}


// O(N^2)
// dp[i] = min(dp[i+j]) if 1 < j <= nums[i]
// dp[i] = inf if j == 0
class Solution1 {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];

        for (int i = nums.length - 2; i >= 0; --i) {
            int minJump = 10001;
            for (int j = 1; j <= nums[i]; ++j) {
                if (i + j == nums.length) {
                    break;
                }
                minJump = Math.min(minJump, dp[i + j]);
            }
            dp[i] = minJump + 1;
        }

        System.out.println(Arrays.toString(dp));

        return dp[0];
    }
}


public class JumpGameII {
    public static void main(String[] args) {
        testSol(new int[] {2, 3, 1, 1, 4}, 2);
        testSol(new int[] {2, 3, 0, 1, 4}, 2);
        testSol(new int[] {2, 3, 0, 10, 4}, 2);
        testSol(new int[] {5, 9, 9, 9, 9, 0, 10, 4}, 2);
        testSol(new int[] {2, 3, 3, 0, 3, 0, 0, 4, 0, 5}, 4);
    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.jump(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
