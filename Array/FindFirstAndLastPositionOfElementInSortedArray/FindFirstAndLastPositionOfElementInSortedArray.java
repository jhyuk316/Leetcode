package Array.FindFirstAndLastPositionOfElementInSortedArray;
// 34. Find First and Last Position of Element in Sorted Array
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

import java.util.Arrays;
import java.util.List;


// O(logn) 시작과 끝을 찾기
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums, int target) {
        int first = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                first = mid;
            }
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return first;
    }

    private int findLast(int[] nums, int target) {
        int last = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                last = mid;
            }
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return last;
    }
}


// O(logn) target을 찾고 좌우로 분리해서 시작과 끝 찾기.
class Solution1 {
    public int[] searchRange(int[] nums, int target) {
        int res = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                res = mid;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (res == -1) {
            return new int[] {-1, -1};
        }

        left = 0;
        right = res;
        while (left < right) {
            // System.out.println("find left " + left + " " + right);
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int leftRes = left;

        left = res;
        right = nums.length - 1;
        while (left < right) {
            // System.out.println("find right " + left + " " + right);
            int mid = left + (right - left) / 2 + 1;

            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        int rightRes = right;

        return new int[] {leftRes, rightRes};
    }
}


public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        testSol(List.of(new int[] {5, 7, 7, 8, 10, 10, 10}, 8), new int[] {3, 3});
        testSol(List.of(new int[] {5, 7, 7, 8, 8, 10}, 8), new int[] {3, 4});
        testSol(List.of(new int[] {5, 7, 7, 8, 8, 8, 10}, 8), new int[] {3, 5});
        testSol(List.of(new int[] {5, 7, 7, 8, 8, 10}, 6), new int[] {-1, -1});
        testSol(List.of(new int[] {}, 0), new int[] {-1, -1});
    }

    static void testSol(List input, Object output) {
        // todo : input, output match
        int[] arg1 = (int[]) input.get(0);
        int arg2 = (int) input.get(1);
        int[] out = (int[]) output;
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.searchRange(arg1, arg2);
        if (Arrays.equals(res, out)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out
                    .println("x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(out));
        }
    }

}
