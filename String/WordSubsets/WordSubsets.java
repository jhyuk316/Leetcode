package String.WordSubsets;
// 916. Word Subsets
// https://leetcode.com/problems/word-subsets/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


// O(M*N) solution
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> result = new ArrayList<>();

        int[] maxCount = new int[26];
        for (String word2 : words2) {
            int[] word2Count = count(word2);
            for (int i = 0; i < 26; ++i) {
                maxCount[i] = Math.max(maxCount[i], word2Count[i]);
            }
        }

        for (String word1 : words1) {
            int[] word1Count = count(word1);
            boolean match = true;
            for (int i = 0; i < 26; ++i) {
                if (word1Count[i] < maxCount[i]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                result.add(word1);
            }
        }

        return result;
    }

    private int[] count(String s) {
        int[] result = new int[26];
        for (char c : s.toCharArray()) {
            result[c - 'a']++;
        }
        return result;
    }
}



// O(M*N) 더럽게 느린데 이게 답이야?
class Solution1 {
    List<Map<Character, Integer>> word2List = new ArrayList<>();

    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> result = new ArrayList<>();

        for (String word2 : words2) {
            Map<Character, Integer> tempMap = new HashMap<>();
            for (char c : word2.toCharArray()) {
                tempMap.put(c, tempMap.getOrDefault(c, 0) + 1);
            }
            word2List.add(tempMap);
        }

        Map<Character, Integer> wordMap = new HashMap<>();
        for (var word2map : word2List) {
            for (var entry : word2map.entrySet()) {
                if (wordMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    wordMap.put(entry.getKey(), entry.getValue());
                }
            }
        }

        for (String word1 : words1) {
            Map<Character, Integer> tempMap = new HashMap<>();
            for (char c : word1.toCharArray()) {
                tempMap.put(c, tempMap.getOrDefault(c, 0) + 1);
            }

            boolean find = true;
            for (var entry : wordMap.entrySet()) {
                if (tempMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    find = false;
                    break;
                }
            }

            if (find) {
                result.add(word1);
            }
        }
        return result;
    }
}


public class WordSubsets {
    public static void main(String[] args) {
        testSol(new String[] {"amazon", "apple", "facebook", "google", "leetcode"},
                new String[] {"e", "o"}, List.of("facebook", "google", "leetcode"));
        testSol(new String[] {"amazon", "apple", "facebook", "google", "leetcode"},
                new String[] {"e", "l"}, List.of("apple", "google", "leetcode"));
        testSol(new String[] {"amazon", "apple", "facebook", "google", "leetcode"},
                new String[] {"lo", "eo"}, List.of("google", "leetcode"));
    }

    static void testSol(String[] input1, String[] input2, List<String> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<String> res = sol.wordSubsets(input1, input2);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
