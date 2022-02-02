package String.CheckIfAllCharactersHaveEqualNumberOfOccurrences;
// 1941. Check if All Characters Have Equal Number of Occurrences
// https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/


// O(n) 문자 카운터 후 판단
class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] countChar = new int[26];
        int intA = 'a';

        for (int i = 0; i < s.length(); ++i) {
            countChar[s.charAt(i) - intA] += 1;
        }

        int count = countChar[s.charAt(0) - intA];
        for (int i = 0; i < 26; ++i) {
            if (countChar[i] != 0 && count != countChar[i]) {
                return false;
            }
        }

        return true;
    }
}


public class CheckIfAllCharactersHaveEqualNumberOfOccurrences {
    public static void main(String[] args) {
        testSol("abacbc", true);
        testSol("aaabb", false);
        testSol("kkedf", false);
        testSol("kkedde", true);
        testSol("z", true);
        testSol("zzzz", true);
    }

    static void testSol(String input, Boolean output) {
        Solution sol = new Solution();
        // todo : solution match
        boolean res = sol.areOccurrencesEqual(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}

