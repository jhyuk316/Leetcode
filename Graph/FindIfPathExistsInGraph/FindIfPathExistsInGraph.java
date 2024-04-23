package Graph.FindIfPathExistsInGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1971. Find if Path Exists in Graph
// https://leetcode.com/problems/find-if-path-exists-in-graph/

class Solution {

    int[] parent;
    Map<Integer, List<Integer>> map = new HashMap<>();
    boolean[] visit;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        parent = new int[n];
        visit = new boolean[n];

        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; ++i) {
            int startNode = edges[i][0];
            int endNode = edges[i][1];

            if (startNode > endNode) {
                map.get(startNode).add(endNode);
            } else {
                map.get(endNode).add(startNode);
            }
        }

        System.out.println(findParent(source));
        System.out.println(findParent(destination));
        return findParent(source) == findParent(destination);
    }

    private int findParent(int node) {
        if (visit[node]) {
            return parent[node];
        }
        visit[node] = true;

        int result = parent[node];
        for (int next : map.get(node)) {
            parent[node] = next;
            result = Math.min(result, findParent(next));
        }
        parent[node] = result;
        return parent[node];
    }

}


class Solution1 {

    Map<Integer, List<Integer>> map = new HashMap<>();

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        for (int i = 0; i < n; ++i) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.length; ++i) {
            int startNode = edges[i][0];
            int endNode = edges[i][1];

            map.get(startNode).add(endNode);
            map.get(endNode).add(startNode);
        }

        return dfs(source, new boolean[n], destination);
    }

    private boolean dfs(int node, boolean[] visit, int destination) {
        if (node == destination) {
            return true;
        }

        if (visit[node]) {
            return false;
        }
        visit[node] = true;

        boolean result = false;
        for (int next : map.get(node)) {
            result |= dfs(next, visit, destination);
        }
        return result;
    }

}


public class FindIfPathExistsInGraph {



}
