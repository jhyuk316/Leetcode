package String.LongestRepeatingCharacterReplacement;
// 424. Longest Repeating Character Replacement
// https://leetcode.com/problems/longest-repeating-character-replacement/

import java.util.Arrays;
import java.util.List;

// O(n)
class Solution {
    public int characterReplacement(String s, int k) {
        int maxLength = 0;
        int[] countChar = new int[26];

        int left = -1;
        int right = 0;
        int maxCount = 0;
        while (right < s.length()) {
            countChar[s.charAt(right) - 'A']++;
            // 현재 윈도우의 최빈값을 찾아야 함.
            // maxCount = Arrays.stream(countChar).max().orElse(0);
            // 하지만 최댓값이 갱신 되지 않으면 maxLength는 갱신 되지 않을 것이므로 굳이 검사할 필요가 없음.
            maxCount = Math.max(maxCount, countChar[s.charAt(right) - 'A']);
            while (right - left - maxCount > k) {
                left++;
                countChar[s.charAt(left) - 'A']--;
            }

            maxLength = Math.max(maxLength, right - left);
            right++;
        }

        return maxLength;
    }
}


public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        testSol(List.of("ABAB", 2), 4);
        testSol(List.of("AABABBA", 1), 4);
        testSol(List.of("ABBBABAAA", 2), 6);
    }

    static void testSol(List input, Object output) {
        // todo : input, output match
        String arg1 = (String) input.get(0);
        int arg2 = (int) input.get(1);
        int out = (int) output;
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.characterReplacement(arg1, arg2);
        if (res == out) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + out);
        }
    }

}
