package DynamicProgramming.MaximumProfitInJobScheduling;
// 1235. Maximum Profit in Job Scheduling
// https://leetcode.com/problems/maximum-profit-in-job-scheduling/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// DP
// dp[i] = max(dp[-1], temp + profit[i])
// temp는 겹치지 않는 최대값.
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;
        List<int[]> jobs = new ArrayList<>();
        int[] dp = new int[N];

        for (int i = 0; i < N; ++i) {
            int[] temp = new int[] {startTime[i], endTime[i], profit[i]};
            jobs.add(temp);
        }

        // jobs.sort(Comparator.<int[]>comparingInt(i -> i[1]).thenComparing(i -> i[0]));
        jobs.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        jobs.forEach(a -> System.out.println(Arrays.toString(a)));

        // DP
        int tempMax = 0;
        int j = 0;
        dp[0] = jobs.get(0)[2];
        for (int i = 1; i < N; ++i) {
            j = i;
            while (j >= 0 && jobs.get(i)[0] < jobs.get(j)[1]) {
                j--;
            }
            tempMax = j >= 0 ? dp[j] : 0;
            dp[i] = Math.max(dp[i - 1], tempMax + jobs.get(i)[2]);
        }

        System.out.println(Arrays.toString(dp));
        return dp[N - 1];
    }
}


// time over
class Solution1 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;
        List<int[]> jobs = new ArrayList<>();
        int[] dp = new int[N];

        for (int i = 0; i < N; ++i) {
            int[] temp = new int[] {startTime[i], endTime[i], profit[i]};
            jobs.add(temp);
        }

        // jobs.sort(Comparator.<int[]>comparingInt(i -> i[1]).thenComparing(i -> i[0]));
        jobs.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        jobs.forEach(a -> System.out.println(Arrays.toString(a)));

        // DP
        int tempMax = 0;
        int j = 0;
        dp[0] = jobs.get(0)[2];
        for (int i = 1; i < N; ++i) {
            j = 0;
            while (j < i && jobs.get(i)[0] >= jobs.get(j)[1]) {
                j++;
            }
            tempMax = j > 0 ? dp[j - 1] : 0;
            dp[i] = Math.max(dp[i - 1], tempMax + jobs.get(i)[2]);
        }

        System.out.println(Arrays.toString(dp));
        return dp[N - 1];
    }
}


public class MaximumProfitInJobScheduling {
    public static void main(String[] args) {
        testSol(new int[] {1, 2, 3, 3}, new int[] {3, 4, 5, 6}, new int[] {50, 10, 40, 70}, 120);
        testSol(new int[] {1, 2, 3, 4, 6}, new int[] {3, 5, 10, 6, 9},
                new int[] {20, 20, 100, 70, 60}, 150);
        testSol(new int[] {1, 1, 1}, new int[] {2, 3, 4}, new int[] {5, 6, 4}, 6);
        testSol(new int[] {6, 15, 7, 11, 1, 3, 16, 2}, new int[] {19, 18, 19, 16, 10, 8, 19, 8},
                new int[] {2, 9, 1, 19, 5, 7, 3, 19}, 41);
    }

    static void testSol(int[] input1, int[] input2, int[] input3, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.jobScheduling(input1, input2, input3);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
