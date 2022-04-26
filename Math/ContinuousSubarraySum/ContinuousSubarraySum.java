package Math.ContinuousSubarraySum;
// 523. Continuous Subarray Sum
// https://leetcode.com/problems/continuous-subarray-sum/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// O(n?) preSum의 나머지 값이 같은 index를 찾기.
// A % k - B % k = (A - B + k) % k
// A % k - B % k = 0
// A % k = B % k
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        int temp = 0;
        for (int i = 0; i < nums.length; ++i) {
            temp += nums[i];
            preSum[i + 1] = temp;
        }

        // System.out.println(Arrays.toString(preSum));

        Map<Integer, List<Integer>> preSumMod = new HashMap<>();
        for (int i = 0; i < preSum.length; ++i) {
            int key = preSum[i] % k;
            if (!preSumMod.containsKey(key)) {
                preSumMod.put(key, new ArrayList<>());
            }
            preSumMod.get(key).add(i);
        }

        // System.out.println(preSumMod);

        for (int num : preSumMod.keySet()) {
            if (preSumMod.get(num).size() >= 2) {
                // 1 차이가 아닌 index 조합이 있는지 판단.
                List<Integer> index = preSumMod.get(num);
                for (int i = 0; i < index.size() - 1; ++i) {
                    for (int j = i + 1; j < index.size(); ++j) {
                        if (Math.abs(index.get(i) - index.get(j)) != 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}


// brute-force
class Solution1 {
    public boolean checkSubarraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; ++i) {
            int temp = nums[i];
            for (int j = i + 1; j < nums.length; ++j) {
                temp += nums[j];
                if (temp % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}


public class ContinuousSubarraySum {
    public static void main(String[] args) {
        testSol(new int[] {1, 3}, 2, true);
        testSol(new int[] {0, 0}, 1, true);
        testSol(new int[] {23, 2, 4, 6, 7}, 6, true);
        testSol(new int[] {23, 2, 6, 4, 7}, 6, true);
        testSol(new int[] {23, 2, 6, 4, 7}, 13, false);
    }

    static void testSol(int[] input1, int input2, Boolean output) {
        Solution sol = new Solution();
        // todo : solution match
        Boolean res = sol.checkSubarraySum(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
