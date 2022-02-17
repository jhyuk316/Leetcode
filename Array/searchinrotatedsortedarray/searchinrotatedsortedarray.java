package Array.searchinrotatedsortedarray;
// 33. Search in Rotated Sorted Array
// https://leetcode.com/problems/search-in-rotated-sorted-array/

import java.util.List;

// O(logn) One-pass Binary Search
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // System.out.println(left + " " + right);
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 순차인 부분을 찾고 그 부분 안에 타겟이 있으면 보통 이진탐색
            // 없으면 반대로 이진탐색
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}


// O(logn) 바이너리 서치로 최소값 위치 찾고, 로테이션 보정 바이너리 서치
class Solution2 {
    public int search(int[] nums, int target) {
        int rotation = findMin(nums);
        return binarySearch(nums, target, rotation);
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int binarySearch(int[] nums, int target, int rotation) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int rotMid = (mid + rotation) % nums.length;

            if (target == nums[rotMid]) {
                return rotMid;
            } else if (target > nums[rotMid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}


// O(logn) 바이너리 서치로 최소값 위치를 찾고 좌우 분할해서 바이너리 서치
class Solution1 {
    public int search(int[] nums, int target) {
        int minPos = findMin(nums);

        int leftTemp = binarySearch(nums, target, 0, minPos - 1);
        int rightTemp = binarySearch(nums, target, minPos, nums.length - 1);
        if (leftTemp != -1) {
            return leftTemp;
        }
        if (rightTemp != -1) {
            return rightTemp;
        }

        return -1;
    }

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public int binarySearchRecursion(int[] nums, int target, int left, int right) {
        int mid = (left + right) / 2;
        if (left > right) {
            return -1;
        }

        int res = -1;
        if (target == nums[mid]) {
            return mid;
        } else if (target > nums[mid]) {
            res = binarySearchRecursion(nums, target, mid + 1, right);
        } else {
            res = binarySearchRecursion(nums, target, left, mid - 1);
        }

        return res;
    }
}


public class searchinrotatedsortedarray {

    public static void main(String[] args) {
        testSol(List.of(new int[] {4, 5, 6, 7, 0, 1, 2}, 0), 4);
        testSol(List.of(new int[] {4, 5, 6, 7, 0, 1, 2}, 3), -1);
        testSol(List.of(new int[] {4, 5, 6, 8, 0, 1, 2}, 4), 0);
        testSol(List.of(new int[] {4, 5, 6, 8, 0, 1, 2}, 2), 6);
        testSol(List.of(new int[] {4, 5, 0, 1, 2, 3}, 4), 0);
        testSol(List.of(new int[] {4, 5, 0, 1, 2, 3}, 2), 4);
        testSol(List.of(new int[] {4, 5, 0, 1, 2, 3}, 3), 5);
        testSol(List.of(new int[] {1}, 1), 0);
        testSol(List.of(new int[] {1}, 0), -1);
        testSol(List.of(new int[] {1}, 2), -1);
    }

    static void testSol(List input, Object output) {
        // todo : input, output match
        int[] arg1 = (int[]) input.get(0);
        int arg2 = (int) input.get(1);
        int out = (int) output;
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.search(arg1, arg2);
        if (res == out) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + out);
        }
    }
}
