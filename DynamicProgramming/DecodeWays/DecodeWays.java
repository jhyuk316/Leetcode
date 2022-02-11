package DynamicProgramming.DecodeWays;
// 91. Decode Ways
// https://leetcode.com/problems/decode-ways/

import java.util.Arrays;


// O(n) DP tabulation 가능한 경우의 수 저장.
// dp[i] += dp[i-1] if 0 < s[i] <=9
// dp[i] += dp[i-2] if 10 <= s[i-1:i+1] <=26
class Solution {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;

        for (int i = 0; i < s.length(); ++i) {
            if (0 < strToInt(s, i, i + 1) && strToInt(s, i, i + 1) <= 9) {
                dp[i + 1] = dp[i];
            }
            if (i - 1 >= 0 && strToInt(s, i - 1, i + 1) <= 26 && strToInt(s, i - 1, i + 1) >= 10) {
                dp[i + 1] += dp[i - 1];
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[s.length()];
    }

    public int strToInt(String s, int left, int right) {
        int result = 0;
        for (int i = left; i < right; ++i) {
            result *= 10;
            result += s.charAt(i) - '0';
        }
        return result;
    }
}


public class DecodeWays {
    public static void main(String[] args) {
        testSol("12", 2);
        testSol("226", 3);
        testSol("06", 0);
        testSol("106", 1);
    }

    static void testSol(String input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.numDecodings(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
