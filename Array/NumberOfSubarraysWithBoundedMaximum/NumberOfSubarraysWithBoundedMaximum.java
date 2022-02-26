package Array.NumberOfSubarraysWithBoundedMaximum;
// 795. Number of Subarrays with Bounded Maximum
// https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/


// O(N) 해답
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return count(nums, right) - count(nums, left - 1);
    }

    int count(int[] nums, int bound) {
        int ans = 0, cnt = 0;
        for (int x : nums) {
            cnt = x <= bound ? cnt + 1 : 0;
            ans += cnt;
        }
        return ans;
    }
}


// O(N) right 보다 작은 구간의 조합 수 - left 보다 작은 구간의 조합 수
class Solution2 {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int count = 0;
        count = lessCombi(nums, right);
        count -= lessCombi(nums, left - 1);

        return count;
    }

    private int lessCombi(int[] nums, int limit) {
        int l = 0;
        int r = 0;
        int count = 0;

        while (l < nums.length) {
            while (l < nums.length && nums[l] > limit) {
                l++;
            }

            r = l;
            while (r < nums.length && nums[r] <= limit) {
                r++;
            }

            int length = r - l;
            if (length % 2 == 0) {
                count += (length + 1) * (length >> 1);
            } else {
                count += ((length + 1) >> 1) * (length);
            }
            l = r + 1;
        }
        return count;
    }
}


// O(N^2) brute-force 아마도 맞겠지?
class Solution1 {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            boolean isValid = false;
            int j = i;
            while (j < nums.length && nums[j] <= right) {
                if (left <= nums[j]) {
                    isValid = true;
                }
                if (isValid) {
                    count++;
                }
                j++;
            }
        }
        return count;
    }
}


public class NumberOfSubarraysWithBoundedMaximum {
    public static void main(String[] args) {
        testSol(new int[] {2, 1, 4, 3}, 2, 3, 3);
        testSol(new int[] {2, 9, 2, 5, 6}, 2, 8, 7);

        testSol(new int[] {2, 1, 2}, 1, 2, 6);
        testSol(new int[] {2, 1, 2}, 2, 3, 5);
        testSol(new int[] {2, 1, 2}, 2, 2, 5);
        testSol(new int[] {2, 1}, 1, 2, 3);
        testSol(new int[] {1, 2}, 2, 2, 2);
        testSol(new int[] {2, 1}, 2, 2, 2);
        testSol(new int[] {1}, 2, 2, 0);
        testSol(new int[] {1, 2}, 2, 3, 2);
        testSol(new int[] {2}, 2, 2, 1);
        testSol(new int[] {73, 55, 36, 5, 55, 14, 9, 7, 72, 52}, 32, 69, 22);
    }

    static void testSol(int[] input1, int input2, int input3, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.numSubarrayBoundedMax(input1, input2, input3);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
