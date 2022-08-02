package DynamicProgramming.JumpGameV;
// 1340. Jump Game V
// https://leetcode.com/problems/jump-game-v/

import java.util.Arrays;

class Solution {
    int[] jumpCount;

    public int maxJumps(int[] arr, int d) {
        jumpCount = new int[arr.length];

        int result = 0;
        for (int i = 0; i < arr.length; ++i) {
            result = Math.max(result, dfs(arr, d, i));
        }

        System.out.println(Arrays.toString(jumpCount));
        return result;
    }

    private int dfs(int[] arr, int d, int i) {
        if (jumpCount[i] != 0) {
            return jumpCount[i];
        }
        jumpCount[i] = 1;

        int depth = 0;
        for (int j = i - 1; j >= i - d && j >= 0; --j) {
            if (arr[i] <= arr[j]) {
                break;
            }
            depth = Math.max(depth, dfs(arr, d, j));
        }
        for (int j = i + 1; j <= i + d && j < arr.length; ++j) {
            if (arr[i] <= arr[j]) {
                break;
            }
            depth = Math.max(depth, dfs(arr, d, j));
        }

        jumpCount[i] = depth + 1;
        return jumpCount[i];
    }
}


public class JumpGameV {
    public static void main(String[] args) {
        testSol(new int[] {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12}, 2, 4);
        testSol(new int[] {3, 3, 3, 3, 3}, 3, 1);
        testSol(new int[] {7, 6, 5, 4, 3, 2, 1}, 1, 7);
    }

    static void testSol(int[] input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.maxJumps(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
