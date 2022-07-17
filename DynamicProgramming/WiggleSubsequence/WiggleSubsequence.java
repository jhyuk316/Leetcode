package DynamicProgramming.WiggleSubsequence;
// 376. Wiggle Subsequence
// https://leetcode.com/problems/wiggle-subsequence/

import java.util.Arrays;

// O(N) greedy
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return 1;
        }

        int count = 1;
        int prev = 0;
        for (int i = 1; i < nums.length; ++i) {
            int cur = nums[i] - nums[i - 1];
            if (prev >= 0 && cur < 0 || prev <= 0 && cur > 0) {
                prev = cur;
                count++;
            }
        }
        return count;
    }
}


// O(N) dp
class Solution3 {
    public int wiggleMaxLength(int[] nums) {
        int up = 0;
        int down = 0;

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            }
            if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down) + 1;
    }
}


// O(N) dp
// + : up[i] = down[i - 1] + 1 if nums[i] > nums[i - 1]
// - : down[i] = up[i - 1] + 1 if nums[i] < nums[i - 1]
class Solution2 {
    public int wiggleMaxLength(int[] nums) {
        int N = nums.length;
        int[] up = new int[N];
        int[] down = new int[N];

        for (int i = 1; i < N; ++i) {
            up[i] = up[i - 1];
            down[i] = down[i - 1];

            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
            }
            if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
            }
        }

        System.out.println(Arrays.toString(up));
        System.out.println(Arrays.toString(down));

        return Math.max(up[N - 1], down[N - 1]) + 1;
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
