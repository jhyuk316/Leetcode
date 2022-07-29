package String.FindAndReplacePattern;
// 890. Find and Replace Pattern
// https://leetcode.com/problems/find-and-replace-pattern/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


// O(M*N) M : length of words, N : length of pattern
// Two Maps
class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (isMatch(word, pattern)) {
                result.add(word);
            }
        }
        return result;
    }

    public boolean isMatch(String word, String pattern) {
        Map<Character, Character> wordMap = new HashMap<>();
        Map<Character, Character> patternMap = new HashMap<>();

        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);

            if (!wordMap.containsKey(w)) {
                wordMap.put(w, p);
            }
            if (!patternMap.containsKey(p)) {
                patternMap.put(p, w);
            }

            if (wordMap.get(w) != p || patternMap.get(p) != w) {
                return false;
            }
        }
        return true;
    }
}


// O(M*N^2) M : length of words, N : length of pattern
// 완전탐색?
class Solution1 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (isMatch(word, pattern)) {
                result.add(word);
            }
        }

        return result;
    }

    private boolean isMatch(String word, String pattern) {
        Set<Character> wordSet = new HashSet<>();
        Set<Character> patternSet = new HashSet<>();
        char[] charArr = word.toCharArray();

        for (int i = 0; i < word.length(); ++i) {
            char origin = charArr[i];
            char target = pattern.charAt(i);
            if (patternSet.contains(target) || wordSet.contains(origin)) {
                continue;
            }

            patternSet.add(target);
            wordSet.add(origin);
            for (int j = i; j < word.length(); ++j) {
                if (charArr[j] == origin && pattern.charAt(j) == target) {
                    charArr[j] = target;
                }
            }
        }

        for (int i = 0; i < word.length(); ++i) {
            if (charArr[i] != pattern.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}


public class FindAndReplacePattern {
    public static void main(String[] args) {
        testSol(new String[] {"abc", "deq", "mee", "aqq", "dkd", "ccc"}, "abb",
                List.of("mee", "aqq"));

        testSol(new String[] {"a", "b", "c"}, "a", List.of("a", "b", "c"));


    }

    static void testSol(String[] input1, String input2, List<String> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<String> res = sol.findAndReplacePattern(input1, input2);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}

