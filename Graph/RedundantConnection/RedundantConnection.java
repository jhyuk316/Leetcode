package Graph.RedundantConnection;
// 684. Redundant Connection
// https://leetcode.com/problems/redundant-connection/
// [Algorithm] 유니온 파인드(Union - Find) - <https://ssungkang.tistory.com/198>

import java.util.Arrays;

class Solution {
    int[] parents;

    public int[] findRedundantConnection(int[][] edges) {
        parents = new int[edges.length + 1];

        int[] result = new int[] {};

        for (int[] edge : edges) {
            int p0 = findParent(edge[0]);
            int p1 = findParent(edge[1]);

            if (p0 == p1) {
                result = edge;
            }
            union(p0, p1);
            // union(edge[0], edge[1]);

            // System.out.println(Arrays.toString(parents));
        }
        return result;
    }

    private int findParent(int node) {
        if (parents[node] == 0) {
            parents[node] = node;
        }

        if (parents[node] != node) {
            parents[node] = findParent(parents[node]);
        }
        return parents[node];
    }

    private void union(int node1, int node2) {
        parents[node2] = node1;
    }
}


public class RedundantConnection {

    public static void main(String[] args) {

        testSol(new int[][] {{1, 2}, {1, 3}, {2, 3}}, new int[] {2, 3});
        testSol(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}, new int[] {1, 4});
        testSol(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}, new int[] {1, 4});
        testSol(new int[][] {{1, 4}, {3, 4}, {1, 3}, {1, 2}, {4, 5}}, new int[] {1, 3});
    }

    static void testSol(int[][] input, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.findRedundantConnection(input);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }

}
