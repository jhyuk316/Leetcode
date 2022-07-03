package CodeTEST.ShowMeTheCode20223.Drop7;

import java.io.*;
import java.util.*;

class Solution {
    int[][] originMat = new int[7][7];
    int[][] mat = new int[7][7];
    int N = 7;
    int ball;

    public void solution() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            int totalBall = 1;
            for (int i = 0; i < N; ++i) {
                String[] line = in.readLine().split(" ");
                for (int j = 0; j < N; ++j) {
                    originMat[i][j] = Integer.parseInt(line[j]);
                    if (originMat[i][j] != 0) {
                        totalBall++;
                    }
                }
            }
            ball = Integer.parseInt(in.readLine());

            //
            int maxCount = 0;
            for (int j = 0; j < N; ++j) {
                // copy
                for (int k = 0; k < N; ++k) {
                    mat[k] = originMat[k].clone();
                }

                mat[0][j] = ball;
                moveDown();

                // 시뮬레이션
                int totalCount = 0;
                int count = deleteBall();
                while (count != 0) {
                    totalCount += count;
                    count = deleteBall();
                    // System.out.println(count);
                }
                maxCount = Math.max(maxCount, totalCount);
            }
            System.out.println(totalBall - maxCount);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return;
    }

    private void moveDown() {
        for (int i = N - 2; i >= 0; --i) {
            for (int j = 0; j < N; ++j) {
                if (mat[i][j] != 0 && mat[i + 1][j] == 0) {
                    int k = i + 1;
                    while (k < N && mat[k][j] == 0) {
                        k++;
                    }
                    mat[k - 1][j] = mat[i][j];
                    mat[i][j] = 0;
                }
            }
        }
    }

    private int deleteBall() {
        int delCount = 0;

        int[][] rowCount = countRows();
        int[] colCount = countCols();

        // for (int i = 0; i < N; ++i) {
        // System.out.println(Arrays.toString(rowCount[i]));
        // }
        // System.out.println(Arrays.toString(colCount));

        // del row
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (mat[i][j] != 0 && rowCount[i][j] == mat[i][j]) {
                    mat[i][j] = 0;
                    delCount++;
                }
            }
        }

        // del col
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (mat[i][j] != 0 && colCount[j] == mat[i][j]) {
                    mat[i][j] = 0;
                    delCount++;
                }
            }
        }
        moveDown();

        // printMat();

        return delCount;
    }

    private int[][] countRows() {
        int[][] rowCount = new int[N][N];
        for (int i = 0; i < N; ++i) {
            int count = 0;
            for (int j = 0; j < N; ++j) {
                if (mat[i][j] != 0) {
                    count++;
                }
                if (mat[i][j] == 0) {
                    count = 0;
                }
                rowCount[i][j] = count;
            }
        }
        for (int i = 0; i < N; ++i) {
            for (int j = N - 2; j >= 0; --j) {
                if (rowCount[i][j] != 0 && rowCount[i][j + 1] > rowCount[i][j]) {
                    rowCount[i][j] = rowCount[i][j + 1];
                }
            }
        }
        return rowCount;
    }

    private int[] countCols() {
        int[] colCount = new int[N];
        for (int i = 0; i < N; ++i) {
            int count = 0;
            for (int j = 0; j < N; ++j) {
                if (mat[j][i] != 0) {
                    count++;
                }
            }
            colCount[i] = count;
        }
        return colCount;
    }

    private void printMat() {
        System.out.println();
        for (int i = 0; i < N; ++i) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }
}


public class Drop7 {
    public static void main(String[] args) {
        testSol("0 0 0 0 0 0 0\n0 0 0 0 0 0 0\n0 0 0 0 0 0 0\n0 0 0 0 0 0 0\n0 0 0 4 0 2 0\n0 0 0 5 0 6 0\n3 3 0 7 5 6 7\n3",
                0);

    }

    static void testSol(String inputString, int output) {
        System.out.println("testSol\n" + inputString + "\nexpect : " + output);
        // String inputString = Integer.toString(input); // 4
        // ByteArrayOutputStream out = new ByteArrayOutputStream();
        // System.setOut(new PrintStream(out));
        InputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        Solution sol = new Solution();
        sol.solution();
    }
}
