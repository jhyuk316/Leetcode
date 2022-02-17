package Array.FindAllNumbersDisappearedInAnArray;
// 448. Find All Numbers Disappeared in an Array
// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -1 * Math.abs(nums[index]);
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;
    }
}


public class FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        testSol(new int[] {4, 3, 2, 7, 8, 2, 3, 1}, List.of(5, 6));
    }

    static void testSol(int[] input, List<Integer> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<Integer> res = sol.findDisappearedNumbers(input);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
