package Array.SortColors;
// 75. Sort Colors
// https://leetcode.com/problems/sort-colors/
// Dutch national flag problem https://en.wikipedia.org/wiki/Dutch_national_flag_problem


import java.util.Arrays;

// O(n) three-pointer one-pass
class Solution {
    public void sortColors(int[] nums) {
        int red = 0;
        int white = 0;
        int blue = nums.length - 1;

        while (white <= blue) {
            if (nums[white] == 0) {
                nums[white] = nums[red];
                nums[red] = 0;
                red++;
                white++;
            } else if (nums[white] == 1) {
                white++;
            } else if (nums[white] == 2) {
                nums[white] = nums[blue];
                nums[blue] = 2;
                blue--;
            }
            // System.out.println(red + " " + white + " " + blue);
            // System.out.println(Arrays.toString(nums));
        }
    }
}


// O(n) in-place two-pass
class Solution3 {
    public void sortColors(int[] nums) {
        // 2를 오른쪽으로.
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] != 2) {
                left++;
            }
            if (nums[left] == 2) {
                nums[left] = nums[right];
                nums[right] = 2;
                right--;
            }
        }

        // 0을 왼쪽으로.
        left = 0;
        right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[right] != 0) {
                right--;
            }
            if (nums[right] == 0) {
                nums[right] = nums[left];
                nums[left] = 0;
                left++;
            }
        }
    }
}


// O(n*logn) in-place mergeSort
class Solution2 {
    public void sortColors(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);

    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int leftIndex = start;
        int rightIndex = mid + 1;

        while (leftIndex <= mid && rightIndex <= end) {
            if (nums[leftIndex] <= nums[rightIndex]) {
                leftIndex++;
            } else {
                int temp = nums[rightIndex];
                int i = rightIndex;

                // shift
                while (i != leftIndex) {
                    nums[i] = nums[i - 1];
                    --i;
                }
                nums[i] = temp;

                leftIndex++;
                rightIndex++;
                mid++;
            }
        }
    }
}


// O(n) 기수정렬
class Solution1 {
    public void sortColors(int[] nums) {
        int[] count = new int[3];

        for (int i = 0; i < nums.length; ++i) {
            count[nums[i]] += 1;
        }

        int k = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < count[i]; ++j) {
                nums[k] = i;
                k++;
            }
        }
    }
}


public class SortColors {
    public static void main(String[] args) {
        testSol(new int[] {0, 0}, new int[] {0, 0});
        testSol(new int[] {1, 1}, new int[] {1, 1});
        testSol(new int[] {2, 2}, new int[] {2, 2});
        testSol(new int[] {2, 0, 2, 1, 1, 0, 1, 0, 2}, new int[] {0, 0, 0, 1, 1, 1, 2, 2, 2});
        testSol(new int[] {2, 0, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 2, 1, 2},
                new int[] {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2});
    }

    static void testSol(int[] input, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        sol.sortColors(input);
        int[] res = input;
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }
}
