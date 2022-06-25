package DynamicProgramming.MaximumProfitInJobScheduling;
// 1235. Maximum Profit in Job Scheduling
// https://leetcode.com/problems/maximum-profit-in-job-scheduling/
// Weighted_activity_selection_problem
// https://en.wikipedia.org/wiki/Activity_selection_problem#Weighted_activity_selection_problem

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

// O(N*logN) Heap을 이용한 DP (124ms)
class Solution5 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;
        List<int[]> jobs = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            jobs.add(new int[] {startTime[i], endTime[i], profit[i]});
        }
        // startTime sort
        jobs.sort(Comparator.<int[]>comparingInt(i -> i[0]));

        // [startTime, endTime, maxProfix]
        // startTime은 쓰이지 않으나 가독성을 위해 넣음.
        // endTime일때 가질 수 있는 최대값을 가지고 있고, endTime의 오름차순 유지.
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
        int curProfit = 0;
        int maxProfit = 0;

        for (int[] job : jobs) {
            while (!heap.isEmpty() && heap.peek()[1] <= job[0]) {
                curProfit = Math.max(curProfit, heap.poll()[2]);
            }
            heap.add(new int[] {job[0], job[1], curProfit + job[2]});
            maxProfit = Math.max(maxProfit, curProfit + job[2]);
        }

        return maxProfit;
    }
}


// O(N*logN) TreeMap을 이용한 DP (124ms)
class Solution4 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;
        List<int[]> jobs = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            jobs.add(new int[] {startTime[i], endTime[i], profit[i]});
        }
        jobs.sort(Comparator.<int[]>comparingInt(i -> i[1]));

        // key : endTime, value : maxProfix
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);

        for (int[] job : jobs) {
            int maxProfix = Math.max(dp.lastEntry().getValue(),
                    dp.floorEntry(job[0]).getValue() + job[2]);
            dp.put(job[1], maxProfix);
        }

        // System.out.println(dp);
        return dp.lastEntry().getValue();
    }
}


// O(N*logN) Weighted activity selection problem
// DP + Binary Search (57ms)
class Solution6 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;
        List<int[]> jobs = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            jobs.add(new int[] {startTime[i], endTime[i], profit[i]});
        }

        // jobs.sort(Comparator.<int[]>comparingInt(i -> i[1]));
        jobs.sort((i1, i2) -> i1[1] - i2[1]);
        // jobs.forEach(a -> System.out.println(Arrays.toString(a)));


        // key : endTime, value : maxProfix
        List<int[]> maxProfitList = new ArrayList<>();
        maxProfitList.add(new int[] {0, 0});

        for (int[] job : jobs) {
            int index = Collections.binarySearch(maxProfitList, new int[] {job[0] + 1, 0},
                    Comparator.comparingInt(j -> j[0]));

            // 못찾을 경우 위치 찾기.
            // System.out.println(index);
            if (index < -1) {
                index = -1 * index - 1;
            }

            index--;
            int temp = index < 0 ? 0 : maxProfitList.get(index)[1];
            int maxProfit = Math.max(maxProfitList.get(maxProfitList.size() - 1)[1], temp + job[2]);
            maxProfitList.add(new int[] {job[1], maxProfit});
        }

        // System.out.println(Arrays.toString(maxProfit));
        return maxProfitList.get(maxProfitList.size() - 1)[1];
    }
}


// O(N*logN) Weighted activity selection problem
// DP + Binary Search (57ms)
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;
        int[] maxProfit = new int[N];
        List<int[]> jobs = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            jobs.add(new int[] {startTime[i], endTime[i], profit[i]});
        }

        // jobs.sort(Comparator.<int[]>comparingInt(i -> i[1]));
        jobs.sort((i1, i2) -> i1[1] - i2[1]);
        jobs.forEach(a -> System.out.println(Arrays.toString(a)));

        maxProfit[0] = jobs.get(0)[2];
        for (int i = 1; i < N; ++i) {
            int index = Collections.binarySearch(jobs, new int[] {0, jobs.get(i)[0], 0},
                    Comparator.comparingInt(j -> j[1]));

            // 못찾을 경우 위치 찾기.
            System.out.println(index);
            if (index >= 0) {
                index = Collections.binarySearch(jobs, new int[] {0, jobs.get(i)[0] - 1, 0},
                        Comparator.comparingInt(j -> j[1])) + 1;
            } else {
                index = -1 * index - 1;
                index--;
            }

            int temp = index < 0 ? 0 : maxProfit[index];
            maxProfit[i] = Math.max(maxProfit[i - 1], temp + jobs.get(i)[2]);
        }

        System.out.println(Arrays.toString(maxProfit));
        return maxProfit[N - 1];
    }
}


// O(N*logN) Weighted activity selection problem
// DP + Binary Search (57ms)
class Solution2 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int N = startTime.length;
        int[] maxProfit = new int[N];
        List<int[]> jobs = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            jobs.add(new int[] {startTime[i], endTime[i], profit[i]});
        }

        // jobs.sort(Comparator.<int[]>comparingInt(i -> i[1]));
        jobs.sort((i1, i2) -> i1[1] - i2[1]);
        // jobs.forEach(a -> System.out.println(Arrays.toString(a)));

        maxProfit[0] = jobs.get(0)[2];
        for (int i = 1; i < N; ++i) {
            int index = findJobs(jobs, 0, i - 1, jobs.get(i)[0]);
            // System.out.println(index);

            int temp = index < 0 ? 0 : maxProfit[index];
            maxProfit[i] = Math.max(maxProfit[i - 1], temp + jobs.get(i)[2]);
        }

        // System.out.println(Arrays.toString(maxProfit));
        return maxProfit[N - 1];
    }

    private int findJobs(List<int[]> jobs, int left, int right, int startTime) {
        int index = -1;
        // System.out.println(left + " " + right + " " + startTime);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int target = jobs.get(mid)[1];
            if (target == startTime) {
                index = mid;
                left = mid + 1;
            }

            if (target > startTime) {
                right = mid - 1;
            } else if (target < startTime) {
                left = mid + 1;
            }
        }

        if (index == -1) {
            return left - 1;
        } else {
            return index;
        }
    }
}


// O(N^2) DP (19ms)
// dp[i] = max(dp[-1], temp + profit[i])
// temp는 겹치지 않는 최대값.
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
        // jobs.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        jobs.sort((i1, i2) -> i1[1] - i2[1]);
        jobs.forEach(a -> System.out.println(Arrays.toString(a)));

        // DP
        dp[0] = jobs.get(0)[2];
        for (int i = 1; i < N; ++i) {
            int j = 0;
            for (j = i; j >= 0; --j) {
                if (jobs.get(i)[0] >= jobs.get(j)[1]) {
                    break;
                }
            }

            int tempMax = j >= 0 ? dp[j] : 0;
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
        testSol(new int[] {4, 2, 4, 8, 2}, new int[] {5, 5, 5, 10, 8}, new int[] {1, 2, 8, 10, 4},
                18);
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
