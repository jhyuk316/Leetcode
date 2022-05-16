package Graph.CourseScheduleII;
// 210. Course Schedule II
// https://leetcode.com/problems/course-schedule-ii/
//
// Topological sorting - https://en.wikipedia.org/wiki/Topological_sorting
// 위상정렬 - https://ko.wikipedia.org/wiki/위상정렬

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 다시 푼것
class Solution {
    enum State {
        Yet, Visit, Finish
    }

    State[] visit;
    Map<Integer, List<Integer>> courseMap = new HashMap<>();;
    List<Integer> order = new ArrayList<>();;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        visit = new State[numCourses];

        // Making Graph
        for (int i = 0; i < numCourses; ++i) {
            courseMap.put(i, new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            courseMap.get(pre[0]).add(pre[1]);
            // courseMap.get(pre[1]).add(pre[0]);
        }

        // DFS
        for (int i = 0; i < numCourses; ++i) {
            if (dfs(i) == false) {
                return new int[] {};
            }
        }

        // check
        // if (order.size() != numCourses) {
        // return new int[] {};
        // }
        return order.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int index) {
        if (visit[index] == State.Finish) {
            return true;
        }

        if (visit[index] == State.Visit) {
            return false;
        }

        visit[index] = State.Visit;
        boolean res = true;
        for (int next : courseMap.get(index)) {
            res &= dfs(next);
        }
        order.add(index); // 바텀-업 왜지?
        visit[index] = State.Finish;
        return res;
    }
}


// O(n) 풀고 나니 Course Schedule 똑같은데 왜 이렇게 어렵게 생각했을까?
class Solution1 {
    enum Visit {
        before, doing, finish
    };

    private List<List<Integer>> graph;
    private Visit[] visited;
    private List<Integer> result = new ArrayList<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // list 초기화
        graph = new ArrayList<>();
        visited = new Visit[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            graph.add(new ArrayList<>());
            visited[i] = Visit.before;
        }

        // graph 생성
        for (int[] courses : prerequisites) {
            graph.get(courses[0]).add(courses[1]);
        }
        // System.out.println(graph);

        // 탐색
        for (int i = 0; i < numCourses; ++i) {
            if (hasCycle(i) == true) {
                return new int[] {};
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private boolean hasCycle(int node) {
        if (visited[node] == Visit.finish) {
            return false;
        }

        if (graph.get(node).isEmpty()) {
            visited[node] = Visit.finish;
            result.add(node);
            return false;
        }

        if (visited[node] == Visit.doing) {
            return true;
        }
        visited[node] = Visit.doing;
        for (int next : graph.get(node)) {
            if (hasCycle(next) == true) {
                return true;
            }
        }

        visited[node] = Visit.finish;
        result.add(node);
        return false;
    }
}



public class CourseScheduleII {
    public static void main(String[] args) {
        testSol(2, new int[][] {{1, 0}}, new int[] {0, 1});
        testSol(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}, new int[] {0, 2, 1, 3});
        testSol(1, new int[][] {}, new int[] {0});
        testSol(2, new int[][] {{1, 0}, {0, 1}}, new int[] {});
        testSol(4, new int[][] {{2, 1}, {1, 2}, {1, 0}, {3, 1}, {2, 0}, {3, 2}}, new int[] {});
        testSol(7, new int[][] {{3, 3}, {1, 2}}, new int[] {});
        testSol(4, new int[][] {{2, 1}, {1, 0}, {3, 1}, {2, 0}, {3, 2}}, new int[] {0, 1, 2, 3});
    }

    static void testSol(int input1, int[][] input2, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.findOrder(input1, input2);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }
}
