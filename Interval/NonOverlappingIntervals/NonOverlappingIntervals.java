package Interval.NonOverlappingIntervals;
// 435. Non-overlapping Intervals
// https://leetcode.com/problems/non-overlapping-intervals/

import java.util.Arrays;
import java.util.Comparator;

// DP:
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.<int[]>comparingInt(i -> i[1]));

        int[] dp = new int[intervals.length + 1];

        int j = 0;
        for (int i = 0; i < intervals.length; ++i) {
            while (j < intervals.length && intervals[i][0] >= intervals[j][1]) {
                j++;
            }
            dp[i + 1] = dp[j] + 1;
        }

        // System.out.println(Arrays.toString(dp));
        return intervals.length - dp[dp.length - 1];
    }
}


// greedy
class Solution1 {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,
                Comparator.<int[]>comparingInt(i -> i[1]).thenComparingInt(i -> i[0]));

        int last = Integer.MIN_VALUE;
        int count = 0;
        for (int[] interval : intervals) {
            if (interval[0] >= last) {
                last = interval[1];
                count++;
            }
        }

        return intervals.length - count;
    }
}


public class NonOverlappingIntervals {
    public static void main(String[] args) {
        testSol(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 3}}, 1);
        testSol(new int[][] {{1, 2}, {1, 2}, {1, 2}}, 2);
        testSol(new int[][] {{1, 2}, {2, 3}}, 0);
    }

    static void testSol(int[][] input, Integer output) {
        Solution sol = new Solution();
        // todo : solution match
        Integer res = sol.eraseOverlapIntervals(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
