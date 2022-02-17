package Array.FindMinimumInRotatedSortedArray;
// 153. Find Minimum in Rotated Sorted Array
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

// O(logn)
class Solution {
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
        return nums[left];
    }
}


// O(logn) 재귀함수
class Solution1 {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int left, int right) {
        if (left + 1 >= right) {
            return Math.min(nums[left], nums[right]);
        }

        int result = 0;
        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[right]) {
            result = findMin(nums, mid + 1, right);
        } else {
            result = findMin(nums, left, mid);
        }
        return result;
    }
}


// O(n) Brute-force
class Solution2 {
    public int findMin(int[] nums) {
        int min = 5001;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }
}



class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.findMin(new int[] {3, 4, 5, 1, 2}));
        System.out.println(sol.findMin(new int[] {3, 4, 5, 6, 7, 0, 1, 2}));
        System.out.println(sol.findMin(new int[] {11, 13, 15, 17}));
        System.out.println(sol.findMin(new int[] {3, 1, 2}));
        System.out.println(sol.findMin(new int[] {1, 2, 3}));
        System.out.println(sol.findMin(new int[] {2, 3, 1}));

    }
}
