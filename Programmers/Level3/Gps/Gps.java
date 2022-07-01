package Programmers.Level3.Gps;
// 2017 카카오코드 본선 - GPS
// https://programmers.co.kr/learn/courses/30/lessons/1837

import java.util.*;

// dp[i][j]: 경로의 i번째 값이 j가 되는 경우,
// i번째까지의 경로가 valid하도록 고쳐야 하는 최소 횟수,
// 그러한 수정이 불가능하다면 INF
class Solution {
    int INF = 100001;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        // Graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edge_list) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // System.out.println(graph);

        // dp
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i < k + 1; ++i) {
            Arrays.fill(dp[i], INF);
        }

        dp[1][gps_log[0]] = 0;
        for (int i = 1; i < k + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j]);
                for (int privious : graph.get(j)) {
                    dp[i][j] = Math.min(dp[i - 1][privious], dp[i][j]);
                }
                dp[i][j] += gps_log[i - 1] == j ? 0 : 1;
            }
        }

        // print dp
        for (int i = 1; i < k + 1; ++i) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[k][gps_log[k - 1]] >= INF ? -1 : dp[k][gps_log[k - 1]];
    }
}


public class Gps {
    public static void main(String[] args) {
        testSol(7, 10, new int[][] {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6},
                {5, 7}, {6, 7}}, 6, new int[] {1, 2, 3, 3, 6, 7}, 1);
    }

    static void testSol(int n, int m, int[][] edge_list, int k, int[] gps_log, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.solution(n, m, edge_list, k, gps_log);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
