package DynamicProgramming.InterleavingString;
// 97. Interleaving String
// https://leetcode.com/problems/interleaving-string/

import java.util.Arrays;


// 솔루션 DP 6ms
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int N = s1.length();
        int M = s2.length();

        if (N + M != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][0] = true;

        for (int i1 = 0; i1 < N + 1; ++i1) {
            for (int i2 = 0; i2 < M + 1; ++i2) {
                if (i1 > 0 && s1.charAt(i1 - 1) == s3.charAt(i1 + i2 - 1)) {
                    dp[i1][i2] = dp[i1][i2] || true && dp[i1 - 1][i2];
                }
                if (i2 > 0 && s2.charAt(i2 - 1) == s3.charAt(i1 + i2 - 1)) {
                    dp[i1][i2] = dp[i1][i2] || true && dp[i1][i2 - 1];
                }
            }
        }

        for (boolean[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return dp[N][M];
    }
}


// 솔루션, DFS, 완전 탐색 5ms
// visited는 중요하다!!!
class Solution2 {
    boolean[][] visited;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        visited = new boolean[s1.length() + 1][s2.length() + 1];
        return dfs(s1, s2, s3, 0, 0, 0);
    }

    private boolean dfs(String s1, String s2, String s3, int i, int j, int k) {
        if (s3.length() == k) {
            return true;
        }

        if (visited[i][j]) {
            return false;
        }
        visited[i][j] = true;

        boolean res = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            res |= dfs(s1, s2, s3, i + 1, j, k + 1);
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            res |= dfs(s1, s2, s3, i, j + 1, k + 1);
        }
        return res;
    }
}


// 완전 탐색 Time Limit Exceeded
class Solution1 {
    public boolean isInterleave(String s1, String s2, String s3) {
        return dfs(s1, s2, s3, 0, 0, "");
    }

    private boolean dfs(String s1, String s2, String s3, int indexS1, int indexS3,
            String result) {
        if (s1.length() == indexS1) {
            String temp = s3.substring(indexS3, s3.length());
            result += temp;
            return result.toString().equals(s2);
        }

        boolean res = false;
        for (int j = indexS3; j < s3.length(); ++j) {
            if (s1.charAt(indexS1) == s3.charAt(j)) {
                res |= dfs(s1, s2, s3, indexS1 + 1, j + 1, result);
            }
            result += s3.charAt(j);
        }
        return res;
    }
}


public class InterleavingString {
    public static void main(String[] args) {
        testSol("bc", "bcb", "bbcbc", true);
        testSol("aabcc", "dbbca", "aadbbcbcac", true);
        testSol("aabcc", "dbbca", "aadbbbaccc", false);
        testSol("", "", "", true);
        testSol("ab", "bc", "babc", true);
    }

    static void testSol(String input1, String input2, String input3, boolean output) {
        Solution sol = new Solution();
        // todo : solution match
        boolean res = sol.isInterleave(input1, input2, input3);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
