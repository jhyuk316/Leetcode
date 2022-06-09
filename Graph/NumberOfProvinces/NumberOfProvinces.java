package Graph.NumberOfProvinces;
// 547. Number of Provinces
// https://leetcode.com/problems/number-of-provinces/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// DFS
class Solution {
    Set<Integer> visited = new HashSet<>();
    int N;

    public int findCircleNum(int[][] isConnected) {
        N = isConnected.length;

        int count = 0;
        for (int i = 0; i < N; ++i) {
            if (!visited.contains(i)) {
                dfs(isConnected, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] isConnected, int node) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        for (int i = 0; i < N; ++i) {
            if (isConnected[node][i] == 1) {
                dfs(isConnected, i);
            }
        }
        return;
    }
}


// Union Find
class Solution1 {
    int M;
    int N;
    int[] parents;

    public int findCircleNum(int[][] isConnected) {
        M = isConnected[0].length;
        N = isConnected.length;
        parents = new int[N];

        for (int i = 0; i < N; ++i) {
            parents[i] = i;
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        System.out.println(Arrays.toString(parents));

        // count
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; ++i) {
            set.add(find(i));
        }
        System.out.println(Arrays.toString(parents));
        return set.size();
    }

    private int find(int node) {
        if (parents[node] != node) {
            parents[node] = find(parents[node]);
        }
        return parents[node];
    }

    private void union(int node1, int node2) {
        int p1 = find(node1);
        int p2 = find(node2);

        if (p1 != p2) {
            parents[p2] = p1;
        }
    }
}


public class NumberOfProvinces {
    public static void main(String[] args) {
        testSol(new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}, 2);
        testSol(new int[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}, 3);
        testSol(new int[][] {{1, 1, 1, 0, 1, 1, 1, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 0, 0, 0, 1, 0},
                {1, 0, 0, 1, 1, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0}, {0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 0, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1}}, 1);
    }

    static void testSol(int[][] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.findCircleNum(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
