package Graph.RottingOranges;
// 994. Rotting Oranges
// https://leetcode.com/problems/rotting-oranges/

import java.util.LinkedList;
import java.util.Queue;

// O(M*N) solution, count로 check를 대신.
class Solution {
    public int orangesRotting(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;

        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] {i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) {
            return 0;
        }

        int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int depth = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // System.out.println(size);
            // queue.forEach(i -> System.out.println(i[0] + " " + i[1]));
            for (int i = 0; i < size; ++i) {
                int[] pos = queue.poll();
                for (int[] direction : directions) {
                    int x = pos[0] + direction[0];
                    int y = pos[1] + direction[1];

                    if (x < 0 || x >= M || y < 0 || y >= N || grid[x][y] != 1) {
                        continue;
                    }

                    freshCount--;
                    grid[x][y] = 2;
                    queue.add(new int[] {x, y});
                }
            }
            depth++;
        }

        return freshCount == 0 ? depth : -1;
    }
}


// O(M*N) BFS check가 너무 많다.
class Solution1 {
    public int orangesRotting(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] {i, j});
                }
            }
        }

        // check
        if (queue.isEmpty()) {
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (grid[i][j] == 1) {
                        return -1;
                    }
                }
            }
            return 0;
        }

        int depth = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // System.out.println(size);
            // queue.forEach(i -> System.out.println(i[0] + " " + i[1]));
            for (int i = 0; i < size; ++i) {
                int[] pos = queue.poll();
                int x = pos[0];
                int y = pos[1];

                if (x > 0 && grid[x - 1][y] == 1) {
                    grid[x - 1][y] = 2;
                    queue.add(new int[] {x - 1, y});
                }
                if (x < M - 1 && grid[x + 1][y] == 1) {
                    grid[x + 1][y] = 2;
                    queue.add(new int[] {x + 1, y});
                }
                if (y > 0 && grid[x][y - 1] == 1) {
                    grid[x][y - 1] = 2;
                    queue.add(new int[] {x, y - 1});
                }
                if (y < N - 1 && grid[x][y + 1] == 1) {
                    grid[x][y + 1] = 2;
                    queue.add(new int[] {x, y + 1});
                }
            }
            depth++;
        }

        // check
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return depth;
    }
}


public class RottingOranges {
    public static void main(String[] args) {
        testSol(new int[][] {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}, 4);
        testSol(new int[][] {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}, -1);
        testSol(new int[][] {{0}}, 0);
        testSol(new int[][] {{2}}, 0);
        testSol(new int[][] {{1}}, -1);
        testSol(new int[][] {{0, 2}}, 0);
        testSol(new int[][] {{0, 2, 2}}, 0);
        testSol(new int[][] {{2, 2}, {1, 1}, {0, 0}, {2, 0}}, 1);
    }

    static void testSol(int[][] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.orangesRotting(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
