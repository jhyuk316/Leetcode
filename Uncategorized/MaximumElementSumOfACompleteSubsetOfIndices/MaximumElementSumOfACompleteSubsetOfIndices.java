package Uncategorized.MaximumElementSumOfACompleteSubsetOfIndices;
// 2862. Maximum Element-Sum of a Complete Subset of Indices
// https://leetcode.com/problems/maximum-element-sum-of-a-complete-subset-of-indices/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public long maximumSum(List<Integer> nums) {
        List<Integer> preSum = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < nums.size(); ++i) {
            sum += nums.get(i);
            preSum.add(sum);
        }

        System.out.println(preSum);

        return 0;
    }
}


public class MaximumElementSumOfACompleteSubsetOfIndices {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumSum(List.of(8, 7, 3, 5, 7, 2, 4, 9)));
    }
}
