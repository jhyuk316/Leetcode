package Array.KthLargestElementInAnArray;
// 215. Kth Largest Element in an Array
// https://leetcode.com/problems/kth-largest-element-in-an-array/
// QuickSelect
// https://en.wikipedia.org/wiki/Quickselect
// https://modoocode.com/287


import java.util.Arrays;


// O(N) QuickSelect - QuickSort 변형
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
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


// sort
class Solution1 {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}


public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        testSol(new int[] {3, 2, 1, 5, 6, 4}, 2, 5);
        testSol(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4);
        testSol(new int[] {2, 1}, 1, 2);
    }

    static void testSol(int[] input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.findKthLargest(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
