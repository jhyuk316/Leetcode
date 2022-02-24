package Interval.MergeIntervals;
// 56. Merge Intervals
// https://leetcode.com/problems/merge-intervals/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// O(N*logN) 정렬하고 머지
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            int s = intervals[i][0];
            int e = intervals[i][1];

            while (i + 1 < intervals.length && e >= intervals[i + 1][0]) {
                e = Math.max(e, intervals[i + 1][1]);
                i++;
            }
            result.add(new int[] {s, e});
        }

        return result.toArray(new int[result.size()][2]);
    }
}


public class MergeIntervals {
    public static void main(String[] args) {
        testSol(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}},
                new int[][] {{1, 6}, {8, 10}, {15, 18}});
        testSol(new int[][] {{1, 4}, {4, 5}}, new int[][] {{1, 5}});
        testSol(new int[][] {{1, 4}, {1, 3}, {4, 5}}, new int[][] {{1, 5}});

    }

    static void testSol(int[][] input, int[][] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[][] res = sol.merge(input);
        if (Arrays.deepEquals(res, output)) {
            System.out.println("O : " + matToString(res));
        } else {
            System.out.println("x : " + matToString(res) + "	expect : " + matToString(output));
        }
    }

    public static String matToString(int[][] mat) {
        if (mat == null)
            return "null";
        int iMax = mat.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0;; i++) {
            b.append(Arrays.toString(mat[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
