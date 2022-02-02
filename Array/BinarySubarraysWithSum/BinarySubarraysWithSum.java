package Array.BinarySubarraysWithSum;
// 930. Binary Subarrays With Sum
// https://leetcode.com/problems/binary-subarrays-with-sum/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// O(n) Prefix Sums Solution
class Solution4 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int result = 0;
        int n = nums.length;
        int sum = 0;

        int[] count = new int[n + 1];

        count[0] = 1;
        for (int j = 0; j < n; j++) {
            if (nums[j] == 1)
                sum++;
            if (sum >= goal)
                result += count[sum - goal];
            count[sum]++;
        }

        return result;
    }

}


// O(n) Prefix Sums
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int[] prefixSums = new int[nums.length + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            prefixSums[i + 1] = sum;
        }

        int[] count = new int[sum + goal + 1];

        int result = 0;
        for (int preSum : prefixSums) {
            result += count[preSum];
            count[preSum + goal] += 1;
        }

        return result;
    }
}



// O(n) 1의 인덱스를 이용해서 경우의 수 계산, 0은 예외처리
class Solution2 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        // 1의 index, 양끝은 -1과 nums.length
        List<Integer> oneIndex = new ArrayList<>();
        oneIndex.add(-1);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 1) {
                oneIndex.add(i);
            }
        }
        oneIndex.add(nums.length);

        // System.out.println(oneIndex);

        int result = 0;
        if (goal == 0) {
            for (int i = 1; i < oneIndex.size(); ++i) {
                int countZero = oneIndex.get(i) - oneIndex.get(i - 1) - 1;
                result += (countZero * (countZero + 1)) / 2;
            }
            return result;
        }

        for (int i = goal; i < oneIndex.size() - 1; ++i) {
            int left = i - goal + 1;
            int right = i;
            int countLeftZero = oneIndex.get(left) - oneIndex.get(left - 1);
            int countRightZero = oneIndex.get(right + 1) - oneIndex.get(right);
            result += countLeftZero * countRightZero;
        }

        return result;
    }
}


// O(n^2) brute-force
class Solution1 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            int temp = 0;
            for (int j = i; j < nums.length; ++j) {
                temp += nums[j];
                if (temp == goal) {
                    count++;
                } else if (temp > goal) {
                    break;
                }
            }
        }
        return count;
    }
}


public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        testSol(List.of(new int[] {1, 0, 1, 0, 1}, 2), 4);
        testSol(List.of(new int[] {0, 0, 0, 0, 0}, 0), 15);
        testSol(List.of(new int[] {0, 0, 0, 0, 0, 1}, 0), 15);
        testSol(List.of(new int[] {0, 0, 1, 0, 0, 0}, 0), 9);
        testSol(List.of(new int[] {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0}, 0), 19);
        testSol(List.of(new int[] {1, 0, 1, 1, 0, 1}, 3), 4);
        testSol(List.of(new int[] {1, 0, 1, 0, 0, 1, 0, 1}, 2), 10);
        testSol(List.of(new int[] {1, 0, 1, 0, 1, 0, 1}, 3), 4);
        testSol(List.of(new int[] {1, 0, 1, 0, 1, 0, 1}, 2), 8);
        testSol(List.of(new int[] {1, 0, 1, 1, 0, 1}, 2), 6);
        testSol(List.of(new int[] {1, 0, 1, 1, 0, 0, 0, 1}, 2), 10);
        testSol(List.of(new int[] {1, 1, 0, 1, 1, 1}, 1), 7);
        testSol(List.of(new int[] {0, 0, 1, 0, 0, 0}, 1), 12);
        testSol(List.of(new int[] {1, 0, 0, 1, 1, 0, 0, 0, 1}, 2), 14);
    }

    static void testSol(List input, Object output) {
        // todo : input, output match
        int[] arg1 = (int[]) input.get(0);
        int arg2 = (int) input.get(1);
        int out = (int) output;
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.numSubarraysWithSum(arg1, arg2);
        if (res == out) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + out);
        }
    }
}
