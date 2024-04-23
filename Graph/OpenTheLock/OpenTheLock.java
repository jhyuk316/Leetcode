package Graph.OpenTheLock;

// 752. Open the Lock
// https://leetcode.com/problems/open-the-lock/?envType=daily-question&envId=2024-04-22

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

class Solution {
    public int openLock(String[] deadends, String target) {
        Deque<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[10000];

        Arrays.stream(deadends).forEach(end -> visited[Integer.parseInt(end)] = true);

        queue.add(0);

        int depth = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Integer first = queue.pollFirst();
                if (Objects.equals(first, Integer.valueOf(target))) {
                    return depth + 1;
                }

                if (visited[first]) {
                    continue;
                }
                visited[first] = true;


                int cur1000 = (first / 1000) % 10;
                int cur100 = (first / 100) % 10;
                int cur10 = (first / 10) % 10;
                int cur1 = first % 10;

                if (cur1000 == 9) {
                    queue.add(first - 9000);
                } else {
                    queue.add(first + 1000);
                }
                if (cur1000 == 0) {
                    queue.add(first + 9000);
                } else {
                    queue.add(first - 1000);
                }

                if (cur100 == 9) {
                    queue.add(first - 900);
                } else {
                    queue.add(first + 100);
                }
                if (cur100 == 0) {
                    queue.add(first + 900);
                } else {
                    queue.add(first - 100);
                }

                if (cur10 == 9) {
                    queue.add(first - 90);
                } else {
                    queue.add(first + 10);
                }
                if (cur10 == 0) {
                    queue.add(first + 90);
                } else {
                    queue.add(first - 10);
                }

                if (cur1 == 9) {
                    queue.add(first - 9);
                } else {
                    queue.add(first + 1);
                }
                if (cur1 == 0) {
                    queue.add(first + 9);
                } else {
                    queue.add(first - 1);
                }
            }
            depth++;
        }

        return -1;
    }
}

public class OpenTheLock {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("i = " + solution.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
    }
}
