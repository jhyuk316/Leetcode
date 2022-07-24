package Array.SearchA2DMatrixII;
// 240. Search a 2D Matrix II
// https://leetcode.com/problems/search-a-2d-matrix-ii/


// O(M+N) Solution Greedy
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        int N = matrix[0].length;

        int i = 0;
        int j = N - 1;
        while (j >= 0 && i < M) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}


// O(n^log3) Solution Divide and Conquer
class Solution3 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        int N = matrix[0].length;
        return find(matrix, target, 0, 0, M - 1, N - 1);
    }

    private boolean find(int[][] matrix, int target, int startX, int startY, int endX, int endY) {
        if (startX > endX || startY > endY) {
            return false;
        }

        if (startX == endX && startY == endY) {
            if (matrix[startX][startY] == target) {
                return true;
            }
            return false;
        }

        int midX = startX + (endX - startX) / 2;
        int midY = startY + (endY - startY) / 2;

        if (matrix[midX][midY] == target) {
            return true;
        }

        if (matrix[midX][midY] < target) {
            if (find(matrix, target, startX, midY + 1, midX, endY)
                    || find(matrix, target, midX + 1, startY, endX, midY)
                    || find(matrix, target, midX + 1, midY + 1, endX, endY)) {
                return true;
            }
        } else {
            if (find(matrix, target, startX, startY, midX, midY)
                    || find(matrix, target, startX, midY + 1, midX, endY)
                    || find(matrix, target, midX + 1, startY, endX, midY)) {
                return true;
            }
        }
        return false;
    }
}


// DFS 느림. 116ms
class Solution2 {
    int M;
    int N;
    boolean[][] visited;

    public boolean searchMatrix(int[][] matrix, int target) {
        M = matrix.length;
        N = matrix[0].length;
        visited = new boolean[M][N];

        return dfs(matrix, target, 0, 0);
    }

    private boolean dfs(int[][] matrix, int target, int i, int j) {
        if (i < 0 || i >= M || j < 0 || j >= N) {
            return false;
        }

        if (visited[i][j]) {
            return false;
        }
        visited[i][j] = true;

        if (matrix[i][j] > target) {
            return false;
        } else if (matrix[i][j] == target) {
            return true;
        }

        if (dfs(matrix, target, i + 1, j) || dfs(matrix, target, i, j + 1)) {
            return true;
        }
        return false;
    }
}


// O(M*logN) binarySearch
class Solution1 {
    int M;
    int N;

    public boolean searchMatrix(int[][] matrix, int target) {
        M = matrix.length;
        N = matrix[0].length;

        for (int[] row : matrix) {
            if (binarySearch(row, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] row, int target) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                right = mid - 1;
            } else if (row[mid] < target) {
                left = mid + 1;
            }
        }
        return false;
    }
}


public class SearchA2DMatrixII {
    public static void main(String[] args) {
        testSol(new int[][] {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5, true);
        testSol(new int[][] {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20, false);

    }

    static void testSol(int[][] input1, int input2, Boolean output) {
        Solution sol = new Solution();
        // todo : solution match
        Boolean res = sol.searchMatrix(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
