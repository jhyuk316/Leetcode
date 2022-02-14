package Binary.SingleNumberIII;
// 260. Single Number III
// https://leetcode.com/problems/single-number-iii/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


// O(n):time, O(1):space
// XOR의 결과가 1이라는 것은 서로 다른 비트라는 뜻이므로 그 중 1비트를 골라서 마스킹하면 서로 다른 숫자만 남게됨.
// 해답 비트 연산, 모든 수를 XOR한 뒤
// 결과값 중 1인 비트 하나를 골라 마스크를 만들고,
// 마스크와 AND시 0이 아닌 숫자들을 다시 XOR해서 한 수를 찾음.
class Solution {
    public int[] singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            result ^= nums[i];
        }
        int diff = result & (-result);

        int tempResult = result;
        for (int i = 0; i < nums.length; ++i) {
            if ((diff & nums[i]) != 0) {
                tempResult ^= nums[i];
            }
        }

        return new int[] {tempResult, result ^ tempResult};
    }
}


// O(n):time, O(n):space Set에 저장하고 겹치면 삭제.
class Solution1 {
    public int[] singleNumber(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();

        for (int i = 0; i < nums.length; ++i) {
            if (numsSet.contains(nums[i])) {
                numsSet.remove(nums[i]);
            } else {
                numsSet.add(nums[i]);
            }
        }

        return numsSet.stream().mapToInt(Integer::intValue).toArray();
    }
}


public class SingleNumberIII {
    public static void main(String[] args) {
        testSol(new int[] {1, 2, 1, 3, 2, 5}, new int[] {3, 5});
        testSol(new int[] {1, 2, 3, 5, 2, 1}, new int[] {3, 5});
        testSol(new int[] {2, 2, 3, 5}, new int[] {3, 5});
        testSol(new int[] {-1, 0}, new int[] {-1, 0});
        testSol(new int[] {1, 0}, new int[] {0, 1});
    }

    static void testSol(int[] input, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.singleNumber(input);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }
}
