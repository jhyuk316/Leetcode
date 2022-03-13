package DynamicProgramming.LargestDivisibleSubset;
// 368. Largest Divisible Subset
// https://leetcode.com/problems/largest-divisible-subset/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<>();
        List<Integer> resultTemp = new ArrayList<>();

        for (int num : nums) {
            result.add(num);
        }

        int i = 0;
        while (i < result.size()) {
            resultTemp.clear();
            for (int j = 0; j < i; ++j) {
                resultTemp.add(result.get(j));
            }
            for (int j = i; j < result.size(); ++j) {
                if (result.get(j) % result.get(i) == 0) {
                    resultTemp.add(result.get(j));
                }
            }
            result = List.copyOf(resultTemp);
            i++;
            System.out.println(result);
        }

        return result;
    }
}


class Solution1 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < nums.length; ++i) {
            results.add(new ArrayList<>());
        }

        int maxLength = 0;
        int maxPos = -1;

        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums.length; ++j) {
                if (nums[j] % nums[i] == 0) {
                    results.get(j).add(nums[i]);
                    if (maxLength < results.get(j).size()) {
                        maxLength = results.get(j).size();
                        maxPos = j;
                    }
                }
            }
            System.out.println(results);
        }

        return results.get(maxPos);
    }
}


public class LargestDivisibleSubset {
    public static void main(String[] args) {
        testSol(new int[] {1, 2, 3}, List.of(1, 2));
        testSol(new int[] {1, 2, 3, 4}, List.of(1, 2, 4));
        testSol(new int[] {2, 3, 4, 5, 6, 9, 27}, List.of(3, 9, 27));

    }

    static void testSol(int[] input, List<Integer> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<Integer> res = sol.largestDivisibleSubset(input);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
