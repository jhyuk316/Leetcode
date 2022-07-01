package Programmers.Level3.FurthestNode;
// 가장 먼 노드
// https://programmers.co.kr/learn/courses/30/lessons/49189

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // Graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        System.out.println(graph);

        // BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        int answer = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            answer = count;

            count = 0;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int cur = queue.poll();
                for (int next : graph.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                        count++;
                    }
                }
            }
            // System.out.println(answer);
        }

        return answer;
    }
}


public class FurthestNode {

}
