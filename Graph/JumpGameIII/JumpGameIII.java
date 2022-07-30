package Graph.JumpGameIII;
// 1306. Jump Game III
// https://leetcode.com/problems/jump-game-iii/

import java.util.LinkedList;
import java.util.Queue;

// O(N) DFS
class Solution {
    int N;
    boolean[] visited;

    public boolean canReach(int[] arr, int start) {
        N = arr.length;
        visited = new boolean[N];

        return dfs(arr, start);
    }

    private boolean dfs(int[] arr, int pos) {
        if (pos < 0 || pos >= N) {
            return false;
        }

        if (visited[pos]) {
            return false;
        }
        visited[pos] = true;

        if (arr[pos] == 0) {
            return true;
        }

        int diff = arr[pos];
        if (dfs(arr, pos - diff) || dfs(arr, pos + diff)) {
            return true;
        }
        return false;
    }
}


// O(N) BFS
class Solution1 {
    public boolean canReach(int[] arr, int start) {
        int N = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int pos = queue.poll();
                int diff = arr[pos];
                if (diff == 0) {
                    return true;
                }

                int right = pos + diff;
                int left = pos - diff;

                if (left >= 0 && !visited[left]) {
                    visited[left] = true;
                    queue.add(left);
                }

                if (right < N && !visited[right]) {
                    visited[right] = true;
                    queue.add(right);
                }
            }
        }

        return false;
    }
}


public class JumpGameIII {
    public static void main(String[] args) {
        testSol(new int[] {4, 2, 3, 0, 3, 1, 2}, 5, true);
        testSol(new int[] {4, 2, 3, 0, 3, 1, 2}, 0, true);
        testSol(new int[] {3, 0, 2, 1, 2}, 2, false);
    }

    static void testSol(int[] input1, int input2, boolean output) {
        Solution sol = new Solution();
        // todo : solution match
        boolean res = sol.canReach(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
