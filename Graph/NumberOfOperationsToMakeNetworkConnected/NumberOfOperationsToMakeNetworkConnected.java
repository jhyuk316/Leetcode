package Graph.NumberOfOperationsToMakeNetworkConnected;
// 1319. Number of Operations to Make Network Connected
// https://leetcode.com/problems/number-of-operations-to-make-network-connected/

import java.util.Arrays;

class Solution {
    int[] root;

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        root = new int[n];
        Arrays.fill(root, -1);

        int count = 0;
        for (int[] edge : connections) {
            int p1 = findRoot(edge[0]);
            int p2 = findRoot(edge[1]);

            if (p1 != p2) {
                count++;
            }
            union(p1, p2);
            // System.out.println(Arrays.toString(root));
        }

        return (n - 1) - count;
    }

    private int findRoot(int i) {
        if (root[i] == -1) {
            root[i] = i;
        }

        if (root[i] != i) {
            root[i] = findRoot(root[i]);
        }

        return root[i];
    }

    private void union(int i, int j) {
        root[j] = i;
    }
}


public class NumberOfOperationsToMakeNetworkConnected {
    public static void main(String[] args) {

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
