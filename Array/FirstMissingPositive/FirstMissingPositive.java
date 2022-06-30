package Array.FirstMissingPositive;
// 41. First Missing Positive
// https://leetcode.com/problems/first-missing-positive/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Solution
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // trim
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        System.out.println(Arrays.toString(nums));

        // hashing
        for (int i = 0; i < n; ++i) {
            int pos = Math.abs(nums[i]);
            if (pos > n) {
                continue;
            }
            nums[pos - 1] = -1 * Math.abs(nums[pos - 1]);
        }
        System.out.println(Arrays.toString(nums));

        // find
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}


// O(N) 공간 N
class Solution2 {
    public int firstMissingPositive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] <= 0) {
                continue;
            }
            numSet.add(nums[i]);
        }

        int i = 1;
        while (numSet.contains(i)) {
            i++;
        }

        return i;
    }
}


// brute-force
class Solution1 {
    public int firstMissingPositive(int[] nums) {
        for (int i = 1; i < 2147483647; ++i) {
            boolean isFind = false;
            for (int j = 0; j < nums.length; ++j) {
                if (nums[j] == i) {
                    isFind = true;
                    break;
                }
            }
            if (!isFind) {
                return i;
            }
        }
        return -1;
    }
}


public class FirstMissingPositive {
    public static void main(String[] args) {
        testSol(new int[] {1, 2, 0}, 3);
        testSol(new int[] {3, 4, -1, 1}, 2);
        testSol(new int[] {7, 8, 9, 11, 12}, 1);
        testSol(new int[] {1}, 2);
        testSol(new int[] {1, 1}, 2);
        testSol(new int[] {3, 4, -1, 1}, 2);
    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.firstMissingPositive(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
