package Graph.AllAncestorsOfANodeInADirectedAcyclicGraph;
// 2192. All Ancestors of a Node in a Directed Acyclic Graph
// https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// O(V+E) DFS 완전탐색
class Solution {
    List<Set<Integer>> graph;
    boolean[] visited;

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        graph = new ArrayList<>();
        visited = new boolean[n];

        for (int i = 0; i < n; ++i) {
            graph.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; ++i) {
            getAncestors(i);
        }

        return graph.stream().map(vertex -> {
            return new ArrayList<>(vertex).stream().sorted().toList();
        }).toList();
    }

    private Set<Integer> getAncestors(int index) {
        if (visited[index]) {
            return graph.get(index);
        }

        visited[index] = true;
        Set<Integer> result = graph.get(index);
        List<Integer> temp = result.stream().toList();
        for (int next : temp) {
            result.addAll(getAncestors(next));
        }

        return result;
    }
}


public class AllAncestorsOfANodeInADirectedAcyclicGraph {
    public static void main(String[] args) {
        testSol(8,
                new int[][] {{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7},
                        {3, 5}, {3, 6}, {3, 7}, {4, 6}},
                List.of(List.of(),
                        List.of(),
                        List.of(),
                        List.of(0, 1),
                        List.of(0, 2),
                        List.of(0, 1, 3),
                        List.of(0, 1, 2, 3, 4),
                        List.of(0, 1, 2, 3)));

        testSol(5,
                new int[][] {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 2},
                        {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}},
                List.of(List.of(),
                        List.of(0),
                        List.of(0, 1),
                        List.of(0, 1, 2),
                        List.of(0, 1, 2, 3)));
    }

    static void testSol(int input1, int[][] input2, List<List<Integer>> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<List<Integer>> res = sol.getAncestors(input1, input2);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
