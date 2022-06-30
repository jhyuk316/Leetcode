package Math.MinimumMovesToEqualArrayElementsII;
// 462. Minimum Moves to Equal Array Elements II
// https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/

import java.util.Arrays;


// O(N) QuickSelect - 피벗 좌우는 정렬 되어 있음.
class Solution {
    public int minMoves2(int[] nums) {
        int N = nums.length;
        int median = quickSelect(nums, 0, N - 1, (N - 1) / 2);

        System.out.println(Arrays.toString(nums));

        int res = 0;
        for (int i = 0; i < N / 2; ++i) {
            res += nums[N - i - 1] - nums[i];
        }

        return res;
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        int pivotIndex = partition(nums, left, right, (left + right) / 2);

        if (pivotIndex < k) {
            return quickSelect(nums, pivotIndex + 1, right, k);
        } else if (pivotIndex > k) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return nums[k];
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];

        swap(nums, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; ++i) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, right, storeIndex);

        return storeIndex;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}


// O(N*logN) 절대 편차 최적화.
class Solution2 {
    public int minMoves2(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);

        int res = 0;
        for (int i = 0; i < N / 2; ++i) {
            res += nums[N - i - 1] - nums[i];
        }

        return res;
    }
}


// O(N*logN) 절대 편차를 최소화하는 곳은 중앙값.
class Solution1 {
    public int minMoves2(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);

        int median;
        if (N % 2 == 0) {
            median = Math.round((nums[N / 2] + nums[N / 2 - 1]) / 2);
        } else {
            median = nums[N / 2];
        }

        int res = 0;
        for (int num : nums) {
            res += Math.abs(median - num);
        }

        return res;
    }
}


public class MinimumMovesToEqualArrayElementsII {
    public static void main(String[] args) {
        testSol(new int[] {1, 2, 3}, 2);
        testSol(new int[] {1, 10, 2, 9}, 16);
        testSol(new int[] {1, 0, 0, 8, 6}, 14);
        testSol(new int[] {1, 0, 0, 1, 8, 6}, 14);
    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.minMoves2(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
