package DynamicProgramming.PascalsTriangle;
// 118. Pascal's Triangle
// https://leetcode.com/problems/pascals-triangle/

import java.util.ArrayList;
import java.util.List;

// O(N^2) DP
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));

        for (int i = 1; i < numRows; ++i) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for (int j = 1; j < i; ++j) {
                temp.add(result.get(i - 1).get(j) + result.get(i - 1).get(j - 1));
            }
            temp.add(1);
            result.add(temp);
        }

        // for (List<Integer> temp : result) {
        // System.out.println(temp);
        // }

        return result;
    }
}


public class PascalsTriangle {

    public static void main(String[] args) {
        testSol(5, List.of(List.of(1), List.of(1, 1), List.of(1, 2, 1), List.of(1, 3, 3, 1),
                List.of(1, 4, 6, 4, 1)));
        testSol(1, List.of(List.of(1)));
    }

    static void testSol(int input, List<List<Integer>> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<List<Integer>> res = sol.generate(input);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
