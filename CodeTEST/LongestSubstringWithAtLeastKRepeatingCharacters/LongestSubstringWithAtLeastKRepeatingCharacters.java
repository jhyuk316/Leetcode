package CodeTEST.LongestSubstringWithAtLeastKRepeatingCharacters;
// 395. Longest Substring with At Least K Repeating Characters
// https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/

import java.util.Arrays;
import java.util.List;

class Solution {
    public int longestSubstring(String s, int k) {
        int[] countChar = new int[26];
        int intA = 'a';

        int max = 0;
        for (int i = 0; i < s.length(); ++i) {
            max = Math.max(max, _longestSubstring(s, i, k) - i);
        }

        return max;
    }

    private int _longestSubstring(String s, int left, int k) {
        int[] countChar = new int[26];
        int intA = 'a';

        for (int i = left; i < s.length(); ++i) {
            countChar[s.charAt(i) - intA] += 1;
        }

        // System.out.println(Arrays.toString(countChar));

        for (int i = s.length() - 1; i >= left; --i) {
            if (validString(countChar, k) == true) {
                // System.out.println((i + 1) + " " + Arrays.toString(countChar));
                return i + 1;
            }
            countChar[s.charAt(i) - intA] -= 1;
        }

        return -1;
    }

    private boolean validString(int[] countChar, int k) {
        for (int i = 0; i < 26; ++i) {
            if (countChar[i] != 0 && countChar[i] < k) {
                return false;
            }
        }
        return true;
    }
}


public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        testSol(List.of("a", 1), 1);
        testSol(List.of("aaabb", 3), 3);
        testSol(List.of("ababbc", 2), 5);
        testSol(List.of("kababbcababbab", 2), 7);
        testSol(List.of("kababbcbabbb", 2), 5);
    }


    static void testSol(List input, Object output) {
        // todo : input, output match
        String arg1 = (String) input.get(0);
        int arg2 = (int) input.get(1);
        int out = (int) output;
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.longestSubstring(arg1, arg2);
        if (res == out) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + out);
        }
    }
}
