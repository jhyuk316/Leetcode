package Array.MaximumProductSubarray;
// 152. Maximum Product Subarray
// https://leetcode.com/problems/maximum-product-subarray/

import java.util.ArrayList;
import java.util.List;

// O(n) 해답, 앞 뒤로 한번씩 곱해서 max 갱신
// 간단한 원리, for문을 두번 돌아도 가장 빠름.
class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];

        int temp = 0;
        for (int i = 0; i < nums.length; ++i) {
            temp = temp != 0 ? temp * nums[i] : nums[i];
            max = Math.max(max, temp);
        }

        temp = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            temp = temp != 0 ? temp * nums[i] : nums[i];
            max = Math.max(max, temp);
        }

        return max;
    }
}


// O(n) 해답, 양수와 음수로 나누어 음수가 곱해질때 서로 교체
// 예외가 존재하고, 간단해 보이는데 까다로운 디버깅이 필요함.
class Solution1 {
    public int maxProduct(int[] nums) {
        int plus = 1;
        int minus = 0;
        int max = nums[0];

        if (nums.length == 1) {
            return nums[0];
        }

        for (int i = 0; i < nums.length; ++i) {
            plus = plus == 0 ? nums[i] : plus * nums[i];
            minus = minus * nums[i];

            if (nums[i] < 0) {
                int temp = plus;
                plus = minus;
                minus = temp;
            }

            max = Math.max(max, plus);
            // System.out.println(i + ": " + max);
        }

        return max;
    }
}


public class MaximumProductSubarray {
    public static void main(String[] args) {
        testSol(new int[] {10, -2, 5, -3, 5, -2, 4}, 1500);

        testSol(new int[] {2, 3, -2, 4}, 6);
        testSol(new int[] {2, -3, 2, 4}, 8);

        testSol(new int[] {-2, 0, -1}, 0);
        testSol(new int[] {-2, 0, -3, -4}, 12);
        testSol(new int[] {-1, -3, -2, 0, -1}, 6);
        testSol(new int[] {-2, 0, 1}, 1);

        testSol(new int[] {-2}, -2);
        testSol(new int[] {-4, -2, -7}, 14);

        testSol(new int[] {2, 3, -2, 4, 0, 158, -2, 3, 4}, 158);
        testSol(new int[] {2, 0, -2, 0, 0, 158, -2, 0, 4}, 158);
        testSol(new int[] {0, 0, 0}, 0);
        testSol(new int[] {-1, -3, -7, 0}, 21);
        testSol(new int[] {-2, 3, 2, 4}, 24);
        testSol(new int[] {1, -2, 3, 2, 4}, 24);
    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.maxProduct(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}


// O(n) 0 분할 후 - 분할 하여 접근, 느림
// 잘못된 발상, 문제를 분할하면 쉬울 줄 알았으나, 더욱 많은 절차로 느리고 복잡함.
class Solution3 {
    public int maxProduct(int[] nums) {
        int result = nums[0];

        List<Integer> zeroPos = new ArrayList<>();
        zeroPos.add(-1);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                zeroPos.add(i);
                result = 0;
            }
        }
        zeroPos.add(nums.length);

        // System.out.println("zeroPos " + zeroPos);

        for (int i = 0; i < zeroPos.size() - 1; ++i) {
            result = Math.max(result, maxProduct(nums, zeroPos.get(i) + 1, zeroPos.get(i + 1)));
        }

        return result;
    }

    public int maxProduct(int[] nums, int start, int end) {
        // System.out.println("s " + start + " e " + end);
        if (end - start == 0) {
            return 0;
        } else if (end - start == 1) {
            return nums[start];
        }

        int prefix = 1;
        int full = 1;
        int suffix = 1;

        boolean firstMinus = false;
        for (int i = start; i < end; ++i) {
            full *= nums[i];

            if (!firstMinus) {
                prefix *= nums[i];
            }

            if (nums[i] < 0) {
                firstMinus = true;
                suffix = 1;
            }
            suffix *= nums[i];
        }

        // System.out.println("pre " + prefix + " mid " + full + " suf " + suffix);

        if (full < 0) {
            full = Math.max(full / prefix, full / suffix);
        }

        return full;
    }
}
