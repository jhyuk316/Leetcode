package String.LongestSubstringWithoutRepeatingCharacters;
// 3. Longest Substring Without Repeating Characters
// https://leetcode.com/problems/longest-substring-without-repeating-characters/

import java.util.HashSet;
import java.util.Set;


// O(N) array
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] cCount = new int[256];

        int j = 0;
        int max = 0;
        for (int i = 0; i < s.length(); ++i) {
            while (j < i && cCount[s.charAt(i)] > 0) {
                cCount[s.charAt(j)]--;
                j++;
            }

            cCount[s.charAt(i)] = 1;
            max = Math.max(max, i - j + 1);
        }

        return max;
    }
}


// O(N) set
class Solution1 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> cSet = new HashSet<>();
        int j = 0;
        int max = 0;
        for (int i = 0; i < s.length(); ++i) {
            while (j < i && cSet.contains(s.charAt(i))) {
                cSet.remove(s.charAt(j));
                j++;
            }

            cSet.add(s.charAt(i));
            max = Math.max(max, cSet.size());
        }

        return max;

    }
}


public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        testSol("abcabcbb", 3);
        testSol("bbbbb", 1);
        testSol("pwwkew", 3);
    }

    static void testSol(String input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.lengthOfLongestSubstring(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
