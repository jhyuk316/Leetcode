package Array.searchinrotatedsortedarray;
// 33. Search in Rotated Sorted Array
// https://leetcode.com/problems/search-in-rotated-sorted-array/


// O(logn) 바이너리 서치로 최소값 위치 찾고, 로테이션 보정 바이너리 서치
class Solution {
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
}


public class searchinrotatedsortedarray {

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(sol.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 5));
        System.out.println(sol.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(sol.search(new int[] {4, 5, 6, 8, 0, 1, 2}, 7));
        System.out.println(sol.search(new int[] {0, 1, 2, 4, 5, 6, 8}, 7));
        System.out.println(sol.search(new int[] {1}, 0));
        System.out.println(sol.search(new int[] {1}, 1));
        System.out.println(sol.search(new int[] {1}, 2));
        System.out.println(sol.search(new int[] {5, 1, 2, 3, 4}, 1));


    }
}
