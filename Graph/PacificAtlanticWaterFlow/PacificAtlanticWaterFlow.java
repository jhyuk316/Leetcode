package Graph.PacificAtlanticWaterFlow;
// 417. Pacific Atlantic Water Flow
// https://leetcode.com/problems/pacific-atlantic-water-flow/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int M;
    int N;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        M = heights.length;
        N = heights[0].length;

        boolean[][] isPacific = new boolean[M][N];
        boolean[][] isAtlantic = new boolean[M][N];

        for (int i = 0; i < M; ++i) {
            dfs(heights, i, 0, isPacific);
            dfs(heights, i, N - 1, isAtlantic);
        }
        for (int j = 0; j < N; ++j) {
            dfs(heights, 0, j, isPacific);
            dfs(heights, M - 1, j, isAtlantic);
        }


        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (isPacific[i][j] && isAtlantic[i][j]) {
                    answer.add(List.of(i, j));
                }
            }
        }

        return answer;
    }

    private void dfs(int[][] heights, int r, int c, boolean[][] map) {
        if (map[r][c] == true) { // visited를 겸함
            return;
        }

        map[r][c] = true;
        if (r - 1 >= 0 && heights[r - 1][c] >= heights[r][c]) {
            dfs(heights, r - 1, c, map);
        }
        if (r + 1 < M && heights[r + 1][c] >= heights[r][c]) {
            dfs(heights, r + 1, c, map);
        }
        if (c - 1 >= 0 && heights[r][c - 1] >= heights[r][c]) {
            dfs(heights, r, c - 1, map);
        }
        if (c + 1 < N && heights[r][c + 1] >= heights[r][c]) {
            dfs(heights, r, c + 1, map);
        }
    }
}


class Solution1 {
    int M;
    int N;

    boolean[][] visited;
    boolean[][] isPacific;
    boolean[][] isAtlantic;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        M = heights.length;
        N = heights[0].length;

        visited = new boolean[M][N];
        isPacific = new boolean[M][N];
        isAtlantic = new boolean[M][N];

        // isPacific
        for (int i = 0; i < M; ++i) {
            dfs(heights, i, 0, true, false);
        }
        for (int j = 0; j < N; ++j) {
            dfs(heights, 0, j, true, false);
        }

        // isAtlantic
        visited = new boolean[M][N];
        for (int i = 0; i < M; ++i) {
            dfs(heights, i, N - 1, false, true);
        }
        for (int j = 0; j < N; ++j) {
            dfs(heights, M - 1, j, false, true);
        }


        List<List<Integer>> answer = new ArrayList<>();

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (isPacific[i][j] && isAtlantic[i][j]) {
                    answer.add(List.of(i, j));
                }
            }
        }

        return answer;
    }

    private void dfs(int[][] heights, int r, int c, boolean isPac, boolean isAtl) {
        // if (r == 0 || c == 0) {
        // isPacific[r][c] = true;
        // }
        // if (r == M - 1 || c == N - 1) {
        // isAtlantic[r][c] = true;
        // }

        if (visited[r][c] == true) {
            return;
        }

        visited[r][c] = true;
        isPacific[r][c] |= isPac;
        isAtlantic[r][c] |= isAtl;
        if (r - 1 >= 0 && heights[r - 1][c] >= heights[r][c]) {
            dfs(heights, r - 1, c, isPacific[r][c], isAtlantic[r][c]);
        }
        if (r + 1 < M && heights[r + 1][c] >= heights[r][c]) {
            dfs(heights, r + 1, c, isPacific[r][c], isAtlantic[r][c]);
        }
        if (c - 1 >= 0 && heights[r][c - 1] >= heights[r][c]) {
            dfs(heights, r, c - 1, isPacific[r][c], isAtlantic[r][c]);
        }
        if (c + 1 < N && heights[r][c + 1] >= heights[r][c]) {
            dfs(heights, r, c + 1, isPacific[r][c], isAtlantic[r][c]);
        }
        // visited[r][c] = false;
    }
}


public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {

        int[][] res1 = new int[][] {{0, 4}, {1, 3}, {1, 4}, {2, 2}, {3, 0}, {3, 1}, {4, 0}};
        List<List<Integer>> resList = new ArrayList<>();
        for (int[] arr : res1) {
            resList.add(Arrays.stream(arr).boxed().toList());
        }

        testSol(new int[][] {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}}, resList);

        List<List<Integer>> resList1 = new ArrayList<>();
        resList1.add(List.of(0, 0));
        resList1.add(List.of(0, 1));
        resList1.add(List.of(1, 0));
        resList1.add(List.of(1, 1));


        testSol(new int[][] {{2, 1}, {1, 2}}, resList1);
    }

    static void testSol(int[][] input, List<List<Integer>> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<List<Integer>> res = sol.pacificAtlantic(input);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
