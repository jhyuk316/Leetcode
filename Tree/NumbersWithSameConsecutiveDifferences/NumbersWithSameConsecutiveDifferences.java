package Tree.NumbersWithSameConsecutiveDifferences;
// 967. Numbers With Same Consecutive Differences
// https://leetcode.com/problems/numbers-with-same-consecutive-differences/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// O(2^N) dfs
class Solution {
    List<Integer> answer = new ArrayList<>();

    public int[] numsSameConsecDiff(int n, int k) {
        for (int i = 1; i < 10; ++i) {
            dfs(n - 1, k, i);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(int n, int k, int num) {
        if (n == 0) {
            answer.add(num);
            return;
        }

        int last = num % 10;

        if (last - k >= 0) {
            dfs(n - 1, k, num * 10 + last - k);
        }
        if (last + k < 10 && k != 0) {
            dfs(n - 1, k, num * 10 + last + k);
        }
    }
}


public class NumbersWithSameConsecutiveDifferences {
    public static void main(String[] args) {
        testSol(3, 7, new int[] {181, 292, 707, 818, 929});
        testSol(2, 1,
                new int[] {10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98});
        testSol(2, 0, new int[] {11, 22, 33, 44, 55, 66, 77, 88, 99});

    }

    static void testSol(int input1, int input2, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.numsSameConsecDiff(input1, input2);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }
}
