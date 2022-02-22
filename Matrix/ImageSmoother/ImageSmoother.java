package Matrix.ImageSmoother;
// 661. Image Smoother
// https://leetcode.com/problems/image-smoother/

import java.util.Arrays;

// O(M*N) 해답
class Solution {
    int M;
    int N;

    public int[][] imageSmoother(int[][] img) {
        M = img.length;
        N = img[0].length;

        int[][] result = new int[M][N];

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                result[i][j] = smoother(img, i, j);
            }
        }

        return result;
    }

    private int smoother(int[][] img, int i, int j) {
        int result = 0;
        int count = 0;
        for (int di : new int[] {-1, 0, 1}) {
            for (int dj : new int[] {-1, 0, 1}) {
                if (isValid(i + di, j + dj)) {
                    result += img[i + di][j + dj];
                    count++;
                }
            }
        }
        return result / count;
    }

    private boolean isValid(int i, int j) {
        if (i < 0 || i >= M || j < 0 || j >= N) {
            return false;
        }
        return true;
    }
}


// O(M*N) 망할 1줄짜리 처리.
class Solution1 {
    public int[][] imageSmoother(int[][] img) {
        int M = img.length;
        int N = img[0].length;

        int[][] result2 = new int[M][N];
        if (M == 1 && N == 1) {
            return img;
        } else if (M == 1) {
            for (int j = 1; j < N - 1; ++j) {
                result2[0][j] = (img[0][j - 1] + img[0][j] + img[0][j + 1]) / 3;
            }
            result2[0][0] = (img[0][0] + img[0][1]) / 2;
            result2[0][N - 1] = (img[0][N - 2] + img[0][N - 1]) / 2;
        } else if (N == 1) {
            for (int i = 1; i < M - 1; ++i) {
                result2[i][0] = (img[i - 1][0] + img[i][0] + img[i + 1][0]) / 3;
            }
            result2[0][0] = (img[0][0] + img[1][0]) / 2;
            result2[M - 1][0] = (img[M - 2][0] + img[M - 1][0]) / 2;
        } else {
            int[][] rowResult = new int[M][N];
            for (int j = 0; j < N; ++j) {
                rowResult[0][j] = (img[0][j] + img[1][j]);
                rowResult[M - 1][j] = (img[M - 1][j] + img[M - 2][j]);
            }
            for (int i = 1; i < M - 1; ++i) {
                for (int j = 0; j < N; ++j) {
                    rowResult[i][j] = (img[i - 1][j] + img[i][j] + img[i + 1][j]);
                }
            }

            System.out.println("rowResult");
            for (int[] res : rowResult) {
                System.out.println(Arrays.toString(res));
            }

            int[][] result = new int[M][N];
            for (int i = 0; i < M; ++i) {
                result[i][0] = rowResult[i][0] + rowResult[i][1];
                result[i][N - 1] = rowResult[i][N - 1] + rowResult[i][N - 2];
            }
            for (int j = 1; j < N - 1; ++j) {
                for (int i = 0; i < M; ++i) {
                    result[i][j] = rowResult[i][j - 1] + rowResult[i][j] + rowResult[i][j + 1];
                }
            }

            System.out.println("result");
            for (int[] res : result) {
                System.out.println(Arrays.toString(res));
            }

            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    if ((i == 0 || i == M - 1) && (j == 0 || j == N - 1)) {
                        result2[i][j] = result[i][j] / 4;
                    } else if ((i == 0 || i == M - 1) || (j == 0 || j == N - 1)) {
                        result2[i][j] = (int) result[i][j] / 6;
                    } else {
                        result2[i][j] = (int) result[i][j] / 9;
                    }
                }
            }
        }

        return result2;
    }
}


public class ImageSmoother {
    public static void main(String[] args) {
        testSol(new int[][] {{10}}, new int[][] {{10}});
        testSol(new int[][] {{2, 3}}, new int[][] {{2, 2}});
        testSol(new int[][] {{2, 3, 2, 3, 2}}, new int[][] {{2, 2, 2, 2, 2}});
        testSol(new int[][] {{2}, {3}}, new int[][] {{2}, {2}});
        testSol(new int[][] {{1, 1}, {1, 0}}, new int[][] {{0, 0}, {0, 0}});
        testSol(new int[][] {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}},
                new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        testSol(new int[][] {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}},
                new int[][] {{137, 141, 137}, {141, 138, 141}, {137, 141, 137}});
        testSol(new int[][] {{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}, {14, 15, 16}},
                new int[][] {{4, 4, 5}, {5, 6, 6}, {8, 9, 9}, {11, 12, 12}, {13, 13, 14}});
        testSol(new int[][] {{2, 8, 6, 0, 4, 14}, {15, 3, 5, 0, 10, 12}, {10, 13, 14, 5, 11, 16},
                {7, 8, 16, 11, 15, 13}, {19, 10, 7, 13, 0, 11}, {16, 19, 7, 3, 6, 11},
                {7, 2, 5, 9, 0, 19}, {14, 11, 8, 8, 14, 11}, {4, 5, 10, 4, 2, 12}},
                new int[][] {{7, 6, 3, 4, 6, 10}, {8, 8, 6, 6, 8, 11}, {9, 10, 8, 9, 10, 12},
                        {11, 11, 10, 10, 10, 11}, {13, 12, 10, 8, 9, 9}, {12, 10, 8, 5, 8, 7},
                        {11, 9, 8, 6, 9, 10}, {7, 7, 6, 6, 8, 9}, {8, 8, 7, 7, 8, 9}});

    }

    static void testSol(int[][] input, int[][] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[][] res = sol.imageSmoother(input);
        if (Arrays.deepEquals(res, output)) {
            System.out.println("O : " + matToString(res));
        } else {
            System.out.println("x : " + matToString(res) + "	expect : " + matToString(output));
        }
    }

    private static String matToString(int[][] mat) {
        String res = "\n";
        for (int[] row : mat) {
            res += Arrays.toString(row);
            res += "\n";
        }
        return res;
    }

}
