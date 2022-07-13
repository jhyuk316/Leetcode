package DynamicProgramming.JumpGameVI;
// 1696. Jump Game VI
// https://leetcode.com/problems/jump-game-vi/

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


// DP
class Solution {
    public int maxResult(int[] nums, int k) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];

        int maxPos = 0;
        int maxNum = nums[0];
        for (int i = 1; i < N; ++i) {
            // maxNum 만료
            if (maxPos == i - k - 1) {
                maxNum = Integer.MIN_VALUE;
                for (int j = i - k; j < i; ++j) {
                    if (dp[j] >= maxNum) {
                        maxPos = j;
                        maxNum = dp[j];
                    }
                }
            }

            dp[i] = maxNum + nums[i];

            // maxNum, maxPos 갱신
            if (dp[i] >= maxNum) {
                maxNum = dp[i];
                maxPos = i;
            }
        }

        System.out.println(Arrays.toString(dp));

        return dp[N - 1];
    }
}


//
class Solution1 {
    public int maxResult(int[] nums, int k) {
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        heap.add(dp[0]);

        for (int i = 1; i < N; ++i) {
            if (i - k - 1 >= 0) {
                heap.remove(dp[i - k - 1]);
            }
            dp[i] = heap.peek();
            dp[i] += nums[i];

            heap.add(dp[i]);
        }

        System.out.println(heap);
        System.out.println(Arrays.toString(dp));

        return dp[N - 1];
    }
}


public class JumpGameVI {

    public static void main(String[] args) {
        testSol(new int[] {1, -1, -2, 4, -7, 3}, 2, 7);
        testSol(new int[] {10, -5, -2, 4, 0, 3}, 3, 17);
        testSol(new int[] {1, -5, -20, 4, -1, 3, -6, -3}, 2, 0);

    }

    static void testSol(int[] input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.maxResult(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
