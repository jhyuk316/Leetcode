package Graph.ZeroOneMatrix;
// 542. 01 Matrix
// https://leetcode.com/problems/01-matrix/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// O(M*N) DP?
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int M = mat.length;
        int N = mat[0].length;

        int[][] result = new int[M][N];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = 100000;
                }
            }
        }

        // left
        for (int i = 0; i < M; ++i) {
            for (int j = 1; j < N; ++j) {
                result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
            }
        }

        // right
        for (int i = 0; i < M; ++i) {
            for (int j = N - 2; j >= 0; --j) {
                result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
            }
        }

        // top
        for (int i = 1; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
            }
        }

        // bottom
        for (int i = M - 2; i >= 0; --i) {
            for (int j = 0; j < N; ++j) {
                result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
            }
        }

        for (int[] res : result) {
            System.out.println(Arrays.toString(res));
        }

        return result;
    }
}


// O(M*N) BFS
class Solution1 {
    int[][] result;
    int M;
    int N;

    public int[][] updateMatrix(int[][] mat) {
        M = mat.length;
        N = mat[0].length;

        result = new int[M][N];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                result[i][j] = -1;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                    queue.add(new int[] {i, j});
                }
            }
        }

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; ++k) {
                int[] pos = queue.poll();
                int x = pos[0];
                int y = pos[1];

                result[x][y] = depth;

                if (x > 0 && result[x - 1][y] == -1) {
                    result[x - 1][y] = 0;
                    queue.add(new int[] {x - 1, y});
                }
                if (x < M - 1 && result[x + 1][y] == -1) {
                    result[x + 1][y] = 0;
                    queue.add(new int[] {x + 1, y});
                }
                if (y > 0 && result[x][y - 1] == -1) {
                    result[x][y - 1] = 0;
                    queue.add(new int[] {x, y - 1});
                }
                if (y < N - 1 && result[x][y + 1] == -1) {
                    result[x][y + 1] = 0;
                    queue.add(new int[] {x, y + 1});
                }
            }
            depth++;
        }

        for (int[] res : result) {
            System.out.println(Arrays.toString(res));
        }

        return result;
    }
}


public class ZeroOneMatrix {
    public static void main(String[] args) {
        testSol(new int[][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
                new int[][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        testSol(new int[][] {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}},
                new int[][] {{0, 0, 0}, {0, 1, 0}, {1, 2, 1}});
        testSol(new int[][] {{0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 1, 0}, {1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1}},
                new int[][] {{0, 1, 0, 1, 2}, {1, 1, 0, 0, 1}, {0, 0, 0, 1, 0}, {1, 0, 1, 1, 1},
                        {1, 0, 0, 0, 1}});
    }

    static void testSol(int[][] input, int[][] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[][] res = sol.updateMatrix(input);
        if (Arrays.deepEquals(res, output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
