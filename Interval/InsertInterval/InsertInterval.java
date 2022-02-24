package Interval.InsertInterval;
// 57. Insert Interval
// https://leetcode.com/problems/insert-interval/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// O(N) 해답
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> leftResult = new ArrayList<>();
        List<int[]> rightResult = new ArrayList<>();

        int start = newInterval[0];
        int end = newInterval[1];
        for (int i = 0; i < intervals.length; ++i) {
            if (intervals[i][1] < start) {
                leftResult.add(intervals[i]);
            } else if (end >= intervals[i][0]) {
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
            } else {
                rightResult.add(intervals[i]);
            }
        }

        leftResult.add(new int[] {start, end});
        leftResult.addAll(rightResult);
        return leftResult.toArray(new int[leftResult.size()][2]);
    }
}


// O(N) 삽입 후 머지
class Solution1 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 삽입
        int start = newInterval[0];
        int[][] temps = new int[intervals.length + 1][2];
        int i = 0;
        while (i < intervals.length && start > intervals[i][0]) {
            i++;
        }

        System.arraycopy(intervals, 0, temps, 0, i);
        temps[i] = newInterval;
        System.arraycopy(intervals, i, temps, i + 1, intervals.length - i);
        intervals = temps;

        // 병합
        List<int[]> result = new ArrayList<>();
        for (i = 0; i < intervals.length; ++i) {
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


public class InsertInterval {
    public static void main(String[] args) {
        testSol(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5}, new int[][] {{1, 5}, {6, 9}});
        testSol(new int[][] {{1, 3}, {6, 9}}, new int[] {0, 5}, new int[][] {{0, 5}, {6, 9}});
        testSol(new int[][] {}, new int[] {0, 5}, new int[][] {{0, 5}});
        testSol(new int[][] {{1, 3}, {6, 9}}, new int[] {4, 5},
                new int[][] {{1, 3}, {4, 5}, {6, 9}});
        testSol(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8},
                new int[][] {{1, 2}, {3, 10}, {12, 16}});


    }

    static void testSol(int[][] input1, int[] input2, int[][] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[][] res = sol.insert(input1, input2);
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
