package Graph.ColoringABorder;
// 1034. Coloring A Border
// https://leetcode.com/problems/coloring-a-border/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


// O(M*N) DFS solution
class Solution {
    int N;
    int M;
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        N = grid.length;
        M = grid[0].length;
        visited = new boolean[N][M];

        dfs(grid, row, col, color, grid[row][col]);

        return grid;
    }

    private boolean dfs(int[][] grid, int i, int j, int color, int target) {
        if (i < 0 || i >= N || j < 0 || j >= M) {
            return false;
        }

        // 비지티드 검사를 먼저해야 함.
        if (visited[i][j]) {
            return true;
        }

        if (grid[i][j] != target) {
            return false;
        }

        visited[i][j] = true;
        boolean inner = true;
        for (int k = 0; k < 4; ++k) {
            int x = i + dir[k][0];
            int y = j + dir[k][1];

            inner &= dfs(grid, x, y, color, target);
        }
        System.out.println(i + " " + j + " " + inner);

        if (!inner) {
            grid[i][j] = color;
        }
        return true;
    }
}


// O(M*N) BFS
class Solution1 {
    int N;
    int M;
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        N = grid.length;
        M = grid[0].length;

        int target = grid[row][col];

        visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        visited[row][col] = true;
        queue.add(new int[] {row, col});

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] pos = queue.poll();
                int count = 0;
                for (int d = 0; d < 4; ++d) {

                    int x = pos[0] + dir[d][0];
                    int y = pos[1] + dir[d][1];

                    if (x < 0 || x >= N || y < 0 || y >= M) {
                        continue;
                    }
                    if (visited[x][y]) {
                        count++;
                        continue;
                    }
                    if (grid[x][y] == target) {
                        count++;
                        visited[x][y] = true;
                        queue.add(new int[] {x, y});
                    }
                }
                if (count != 4) {
                    grid[pos[0]][pos[1]] = color;
                }
            }
        }

        return grid;
    }
}


public class ColoringABorder {
    public static void main(String[] args) {
        testSol(new int[][] {{1, 1}, {1, 2}}, 0, 0, 3, new int[][] {{3, 3}, {3, 2}});
        testSol(new int[][] {{1, 2, 2}, {2, 3, 2}}, 0, 1, 3, new int[][] {{1, 3, 3}, {2, 3, 3}});
        testSol(new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 1, 1, 2,
                new int[][] {{2, 2, 2}, {2, 1, 2}, {2, 2, 2}});
    }

    static void testSol(int[][] input1, int input2, int input3, int input4, int[][] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[][] res = sol.colorBorder(input1, input2, input3, input4);
        if (Arrays.deepEquals(res, output)) {
            System.out.println("O : " + matToString(res));
        } else {
            System.out.println("x : " + matToString(res) + "	expect : " + matToString(output));
        }
    }

    public static String matToString(int[][] mat) {
        String res = "\n";
        for (int[] row : mat) {
            res += Arrays.toString(row);
            res += "\n";
        }
        return res;
    }
}
