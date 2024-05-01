package Graph.MinimumHeightTrees;

// 310. Minimum Height Trees
// https://leetcode.com/problems/minimum-height-trees/description/?envType=daily-question&envId=2024-04-23

import java.util.*;

class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // make map
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // BFS
        Deque<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        for (var edge : map.entrySet()) {
            if (edge.getValue().size() <= 1 && !visited[edge.getKey()]) {
                visited[edge.getKey()] = true;
                queue.add(edge.getKey());
            }
        }


        int depth = 0;
        while (!queue.isEmpty()) {
            Deque<Integer> newQueue = new LinkedList<>();

            System.out.println("result = " + queue);

            queue.forEach(node -> {
                        visited[node] = true;
                        for (Integer next : map.get(node)) {
                            map.get(next).remove(node);
                        }
                    }
            );
            queue.forEach(node -> {
                        for (Integer next : map.get(node)) {
                            if (map.get(next).size() <= 1 && !visited[next]) {
                                visited[next] = true;
                                newQueue.add(next);
                            }
                        }
                    }

            );

            if (newQueue.isEmpty()) {
                return queue.stream().toList();
            }

            queue = newQueue;
            depth++;
        }
        return null;
    }
}

public class MinimumHeightTrees {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("minHeightTrees = " + solution.findMinHeightTrees(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}}));
    }
}
