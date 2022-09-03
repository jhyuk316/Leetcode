package Matrix.RotateImage;
// 48. Rotate Image
// https://leetcode.com/problems/rotate-image/


//
class Solution {
    public void rotate(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;

        for (int i = 0; i < M / 2; ++i) {
            for (int j = 0; j < (N + 1) / 2; ++j) {
                int temp = matrix[i][j];
                int x = i;
                int y = j;
                for (int k = 0; k < 3; ++k) {
                    System.out.print(x + " " + y + " -> ");
                    matrix[x][y] = matrix[N - 1 - y][x];
                    int tempX = x;
                    x = N - 1 - y;
                    y = tempX;
                    System.out.println(x + " " + y);
                }
                matrix[x][y] = temp;
            }
        }
    }
}


// 짜고 보니 반시계 젠장.
class Solution1 {
    public void rotate(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;

        for (int i = 0; i < M / 2; ++i) {
            for (int j = 0; j < (N + 1) / 2; ++j) {
                int temp = matrix[i][j];
                int x = i;
                int y = j;
                for (int k = 0; k < 3; ++k) {
                    System.out.print(x + " " + y + " -> ");
                    matrix[x][y] = matrix[y][N - 1 - x];
                    int tempY = y;
                    y = N - 1 - x;
                    x = tempY;
                    System.out.println(x + " " + y);
                }
                matrix[x][y] = temp;
            }
        }
    }
}


public class RotateImage {

}
