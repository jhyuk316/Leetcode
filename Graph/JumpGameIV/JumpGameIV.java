package Graph.JumpGameIV;
// 1345. Jump Game IV
// https://leetcode.com/problems/jump-game-iv/

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// visited 개선
class Solution {
    public int minJumps(int[] arr) {
        int N = arr.length;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = N - 1; i >= 0; --i) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        boolean[] visited = new boolean[N];
        Set<Integer> visitedNum = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int pos = queue.poll();
                // System.out.println(depth + " " + pos);

                if (visited[pos]) {
                    continue;
                }
                visited[pos] = true;

                if (pos == N - 1) {
                    return depth;
                }

                if (pos - 1 >= 0) {
                    queue.add(pos - 1);
                }

                if (pos + 1 < N) {
                    queue.add(pos + 1);
                }

                if (!visitedNum.contains(arr[pos])) {
                    for (int index : graph.get(arr[pos])) {
                        queue.add(index);
                    }
                }
                visitedNum.add(arr[pos]);
            }
            depth++;
        }
        return -1;
    }
}


// O(N) Solution. 왜 그래프를 제거할 생각을 못했을까?
class Solution1 {
    public int minJumps(int[] arr) {
        int N = arr.length;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = N - 1; i >= 0; --i) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];

        queue.add(0);
        visited[0] = true;

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int pos = queue.poll();
                // System.out.println(depth + " " + pos);

                if (pos == N - 1) {
                    return depth;
                }

                if (pos - 1 >= 0 && !visited[pos - 1]) {
                    visited[pos - 1] = true;
                    queue.add(pos - 1);
                }

                if (pos + 1 < N && !visited[pos + 1]) {
                    visited[pos + 1] = true;
                    queue.add(pos + 1);
                }

                for (int index : graph.get(arr[pos])) {
                    if (visited[index]) {
                        continue;
                    }
                    visited[index] = true;
                    queue.add(index);
                }

                // 핵심 그래프 끊기
                // 비지티드로 사이클이 발생하진 않지만 계속 검사함.
                graph.get(arr[pos]).clear();
            }
            depth++;
        }
        return -1;
    }
}


public class JumpGameIV {
    public static void main(String[] args) {
        testSol(new int[] {100, -23, -23, 404, 100, 23, 23, 23, 3, 404}, 3);
        testSol(new int[] {7}, 0);
        testSol(new int[] {7, 6, 9, 6, 9, 6, 9, 7}, 1);

    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.minJumps(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }


}
