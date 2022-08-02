package Array.KthSmallestElementInASortedMatrix;
// 378. Kth Smallest Element in a Sorted Matrix
// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

import java.util.PriorityQueue;

// O(N*logN) solution Binary Search
// 이게 뭐지?
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, lo = matrix[0][0], hi = matrix[n - 1][n - 1];

        while (lo <= hi) {
            int mi = lo + ((hi - lo) >> 1);
            int count = countNonBigger(mi, matrix);
            if (count < k) {
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }

        return lo;
    }

    private static int countNonBigger(int target, int[][] matrix) {
        int n = matrix.length, i = n - 1, j = 0, cnt = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > target) {
                i--;
            } else {
                cnt += i + 1;
                j++;
            }
        }

        return cnt;
    }
}


// O(N^2*log(N)) 공간 O(N^2) 요구조건 실패.
class Solution1 {
    public int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                heap.add(matrix[i][j]);
            }
        }

        int res = 0;
        for (int i = 0; i < k; ++i) {
            res = heap.poll();
        }

        return res;
    }
}


public class KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {
        testSol(new int[][] {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8, 13);
        testSol(new int[][] {{-5}}, 1, -5);
    }

    static void testSol(int[][] input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.kthSmallest(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
