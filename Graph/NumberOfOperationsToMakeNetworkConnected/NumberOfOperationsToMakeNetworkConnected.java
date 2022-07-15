package Graph.NumberOfOperationsToMakeNetworkConnected;
// 1319. Number of Operations to Make Network Connected
// https://leetcode.com/problems/number-of-operations-to-make-network-connected/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// O(N) DFS
class Solution {
    boolean[] visited;
    List<List<Integer>> graph;

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        visited = new boolean[n];
        graph = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int[] conn : connections) {
            graph.get(conn[0]).add(conn[1]);
            graph.get(conn[1]).add(conn[0]);
        }

        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }

        return count - 1;
    }

    private void dfs(int i) {
        if (visited[i]) {
            return;
        }

        visited[i] = true;
        for (int next : graph.get(i)) {
            dfs(next);
        }
    }
}


// O(N) FindUnion
class Solution1 {
    int[] parent;

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }

        int count = 0;
        for (int[] edge : connections) {
            int p1 = findParent(edge[0]);
            int p2 = findParent(edge[1]);

            if (p1 != p2) {
                count++;
            }
            union(p1, p2);
            // System.out.println(Arrays.toString(parent));
        }

        return (n - 1) - count;
    }

    private int findParent(int i) {
        if (parent[i] == i) {
            return i;
        }

        parent[i] = findParent(parent[i]);
        return parent[i];
    }

    private void union(int i, int j) {
        parent[j] = i;
    }
}


public class NumberOfOperationsToMakeNetworkConnected {
    public static void main(String[] args) {
        testSol(4, new int[][] {{0, 1}, {0, 2}, {1, 2}}, 1);
        testSol(6, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}}, 2);
        testSol(6, new int[][] {{0, 1}, {0, 2}, {1, 2}}, -1);
    }

    static void testSol(int input1, int[][] input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.makeConnected(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
