package Array.SubarraySumEqualsK;
// 560. Subarray Sum Equals K
// https://leetcode.com/problems/subarray-sum-equals-k/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


// O(N) prefixSum + hashMap
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);

        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (preSumMap.containsKey(sum - k)) {
                count += preSumMap.get(sum - k);
            }
            preSumMap.put(sum, preSumMap.getOrDefault(sum, 0) + 1);
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(preSumMap);

        return count;
    }
}


// O(N^2) prefixSum
class Solution1 {
    public int subarraySum(int[] nums, int k) {
        int N = nums.length;
        int[] dp = new int[N + 1];
        for (int i = 1; i < N + 1; ++i) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }

        System.out.println(Arrays.toString(dp));

        int count = 0;
        for (int i = 1; i < N + 1; ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[i] - dp[j] == k) {
                    count++;
                }
            }
        }

        return count;
    }
}


public class SubarraySumEqualsK {
    public static void main(String[] args) {
        testSol(new int[] {1, -1, 0}, 0, 3);
        testSol(new int[] {1}, 0, 0);
        testSol(new int[] {1}, 1, 1);
        testSol(new int[] {1, 1, 1}, 2, 2);
        testSol(new int[] {1, 2, 3}, 3, 2);
    }

    static void testSol(int[] input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.subarraySum(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
