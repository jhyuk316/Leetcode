package Interval.InsertInterval;
// 57. Insert Interval
// https://leetcode.com/problems/insert-interval/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;


// O(N) 해답2
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        int start = newInterval[0];
        int end = newInterval[1];

        int i = 0;
        while (i < intervals.length && intervals[i][1] < start) {
            result.add(intervals[i]);
            i++;
        }

        while (i < intervals.length && end >= intervals[i][0]) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        result.add(new int[] {start, end});

        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][2]);
    }
}


// O(N) 해답
class Solution3 {
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


// O(N) 삽입 후 머지 ArrayList 버전
class Solution2 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 삽입
        List<int[]> intervalList = Arrays.stream(intervals).collect(Collectors.toList());
        int index = Collections.binarySearch(intervalList, newInterval,
                Comparator.comparing((o) -> o[0]));
        intervalList.add(Math.abs(index) - 1, newInterval);
        // int[][] merged = intervalList.toArray(new int[intervals.length][2]);

        // 병합, 리스트 그대로 하는 건 너무 더럽다.
        List<int[]> result = new ArrayList<>();
        ListIterator<int[]> it = intervalList.listIterator();
        while (it.hasNext()) {
            int[] temp = it.next();
            int s = temp[0];
            int e = temp[1];

            ListIterator<int[]> itTemp = intervalList.listIterator(it.nextIndex());
            int[] nextTemp = itTemp.hasNext() ? itTemp.next() : null;
            while (nextTemp != null && e >= nextTemp[0]) {
                e = Math.max(e, nextTemp[1]);
                nextTemp = itTemp.hasNext() ? itTemp.next() : null;
                it.next();
            }
            result.add(new int[] {s, e});
        }

        return result.toArray(new int[result.size()][2]);
    }
}


// O(N) 삽입 후 머지 Array 버전
class Solution1 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 삽입
        int i = Arrays.binarySearch(intervals, newInterval, Comparator.comparing((o) -> o[0]));
        i = Math.abs(i) - 1;

        int[][] merged = new int[intervals.length + 1][2];
        System.arraycopy(intervals, 0, merged, 0, i);
        merged[i] = newInterval;
        System.arraycopy(intervals, i, merged, i + 1, intervals.length - i);

        // 병합
        List<int[]> result = new ArrayList<>();
        for (i = 0; i < merged.length; ++i) {
            int s = merged[i][0];
            int e = merged[i][1];

            while (i + 1 < merged.length && e >= merged[i + 1][0]) {
                e = Math.max(e, merged[i + 1][1]);
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
