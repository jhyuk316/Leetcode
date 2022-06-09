package Graph.MostStonesRemovedWithSameRowOrColumn;
// 947. Most Stones Removed with Same Row or Column
// https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/

import java.util.HashSet;
import java.util.Set;

// O(M*N) Union Find
class Solution {
    int[] parents;

    public int removeStones(int[][] stones) {
        parents = new int[stones.length];
        for (int i = 0; i < stones.length; ++i) {
            parents[i] = i;
        }

        for (int i = 0; i < stones.length; ++i) {
            for (int j = i; j < stones.length; ++j) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    union(i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < stones.length; ++i) {
            set.add(find(i));
        }

        return stones.length - set.size();
    }

    private int find(int index) {
        if (parents[index] != index) {
            parents[index] = find(parents[index]);
        }
        return parents[index];
    }

    private void union(int node0, int node1) {
        int p0 = find(node0);
        int p1 = find(node1);

        if (p0 != p1) {
            parents[p1] = p0;
        }
    }
}


// O(M*N) DFS
class Solution1 {
    Set<int[]> visited = new HashSet<>();

    public int removeStones(int[][] stones) {
        int count = 0;
        for (int[] pos : stones) {
            if (!visited.contains(pos)) {
                dfs(stones, pos);
                count++;
            }
        }
        return stones.length - count;
    }

    private void dfs(int[][] stones, int[] pos) {
        if (visited.contains(pos)) {
            return;
        }

        visited.add(pos);

        for (int i = 0; i < stones.length; ++i) {
            if (stones[i][0] == pos[0] || stones[i][1] == pos[1]) {
                dfs(stones, stones[i]);
            }
        }
    }
}


public class MostStonesRemovedWithSameRowOrColumn {
    public static void main(String[] args) {
        testSol(new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}, 5);
        testSol(new int[][] {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}, 3);
        testSol(new int[][] {{0, 0}}, 0);
    }

    static void testSol(int[][] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.removeStones(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
