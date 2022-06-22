package Tree.TimeNeededToInformAllEmployees;
// 1376. Time Needed to Inform All Employees
// https://leetcode.com/problems/time-needed-to-inform-all-employees/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// solution
// O(N) bottom-up DFS
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res = Math.max(res, dfs(i, manager, informTime));
            System.out.println(Arrays.toString(manager));
            System.out.println(Arrays.toString(informTime));
        }
        return res;
    }

    public int dfs(int i, int[] manager, int[] informTime) {
        if (manager[i] != -1) {
            informTime[i] += dfs(manager[i], manager, informTime);
            manager[i] = -1;
        }
        return informTime[i];
    }
}


// O(N) dfs make graph
class Solution2 {
    int N;
    Map<Integer, List<Integer>> graph = new HashMap<>();

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        N = n;

        // Make Graph
        for (int i = 0; i < N; ++i) {
            if (!graph.containsKey(manager[i])) {
                graph.put(manager[i], new ArrayList<>());
            }
            graph.get(manager[i]).add(i);
        }

        return dfs(headID, informTime);
    }

    public int dfs(int id, int[] informTime) {
        if (!graph.containsKey(id)) {
            return informTime[id];
        }

        int res = 0;
        for (int employee : graph.get(id)) {
            res = Math.max(res, dfs(employee, informTime));
        }

        res += informTime[id];
        return res;
    }
}


// O(N^2) DFS brute-force
class Solution1 {
    int N;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        N = n;
        return dfs(headID, manager, informTime);
    }

    public int dfs(int id, int[] manager, int[] informTime) {
        int res = 0;
        for (int i = 0; i < N; ++i) {
            if (manager[i] == id) {
                res = Math.max(res, dfs(i, manager, informTime));
            }
        }

        res += informTime[id];
        return res;
    }
}


public class TimeNeededToInformAllEmployees {
    public static void main(String[] args) {
        testSol(1, 0, new int[] {-1}, new int[] {0}, 0);
        testSol(6, 2, new int[] {2, 2, -1, 2, 2, 2}, new int[] {0, 0, 1, 0, 0, 0}, 1);
        testSol(6, 2, new int[] {2, 2, -1, 2, 2, 2}, new int[] {0, 0, 1, 1, 2, 0}, 3);

    }

    static void testSol(int input1, int input2, int[] input3, int[] input4, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.numOfMinutes(input1, input2, input3, input4);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
