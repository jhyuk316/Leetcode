package Graph.LongestConsecutiveSequence;
// 128. Longest Consecutive Sequence
// https://leetcode.com/problems/longest-consecutive-sequence/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


// O(n) DFS, memoization
class Solution {
    Set<Integer> nodes = new HashSet<>();
    Map<Integer, Integer> visited = new HashMap<>();

    public int longestConsecutive(int[] nums) {
        // make graph
        for (int num : nums) {
            nodes.add(num);
        }

        // dfs
        int maxAnswer = 0;
        for (int num : nums) {
            maxAnswer = Math.max(maxAnswer, dfs(num));
        }

        // System.out.println(visited);
        return maxAnswer;
    }

    private int dfs(int node) {
        if (!nodes.contains(node)) {
            return 0;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        int ans = dfs(node + 1) + 1;
        visited.put(node, ans);
        return ans;
    }
}



// O(n) dfs, memoization
class Solution1 {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> memo = new HashMap<>();

    public int longestConsecutive(int[] nums) {
        for (int num : nums) {
            if (graph.containsKey(num - 1)) {
                graph.get(num - 1).add(num);
            } else {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(num);
                graph.put(num - 1, tempList);
            }
        }

        System.out.println(graph);

        int maxDepth = 0;
        for (int num : nums) {
            maxDepth = Math.max(maxDepth, dfs(num));
        }

        System.out.println(memo);
        return maxDepth;
    }

    private int dfs(int node) {
        if (graph.containsKey(node) == false) {
            return 1;
        }
        if (memo.containsKey(node)) {
            return memo.get(node);
        }

        int result = dfs(node + 1) + 1;
        memo.put(node, result);
        return result;
    }
}



public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        testSol(new int[] {100, 4, 200, 1, 3, 2}, 4);
        testSol(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9);
    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.longestConsecutive(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
