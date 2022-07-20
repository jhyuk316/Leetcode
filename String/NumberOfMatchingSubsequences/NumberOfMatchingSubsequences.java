package String.NumberOfMatchingSubsequences;
// 792. Number of Matching Subsequences
// https://leetcode.com/problems/number-of-matching-subsequences/

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


// O(M*N) N : s.length(), M : words.length
// 78ms Solution StringCharacterIterator
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        List<Queue<StringCharacterIterator>> map = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            map.add(new LinkedList<>());
        }

        for (String word : words) {
            map.get(word.charAt(0) - 'a').add(new StringCharacterIterator(word));
        }

        int count = 0;
        for (char c : s.toCharArray()) {
            Queue<StringCharacterIterator> queue = map.get(c - 'a');
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                StringCharacterIterator it = queue.poll();
                if (it.next() == it.DONE) {
                    count++;
                } else {
                    map.get(it.current() - 'a').add(it);
                }
            }
        }
        return count;
    }
}


// O(M*N) N : s.length(), M : words.length
// 153ms Solution
class Solution3 {
    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, Queue<String>> map = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            map.put(c, new LinkedList<>());
        }

        for (String word : words) {
            map.get(word.charAt(0)).add(word);
        }

        int count = 0;
        for (char c : s.toCharArray()) {
            Queue<String> queue = map.get(c);
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String word = queue.poll();
                if (word.length() == 1) {
                    count++;
                } else {
                    map.get(word.charAt(1)).add(word.substring(1));
                }
            }
        }
        return count;
    }
}


// O(M*N*K) N : s.length(), M : words.length, K : max(words.[i].length)
// 133ms
class Solution2 {
    public int numMatchingSubseq(String s, String[] words) {
        int count = 0;
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        for (String word : wordMap.keySet()) {
            if (matchingSubseq(s, word)) {
                count += wordMap.get(word);
            }
        }
        return count;
    }

    private boolean matchingSubseq(String s, String word) {
        int j = 0;
        for (int i = 0; i < word.length(); ++i) {
            while (j < s.length() && s.charAt(j) != word.charAt(i)) {
                j++;
            }
            if (j == s.length()) {
                return false;
            }
            j++;
        }
        return true;
    }
}


// O(M*N*K) N : s.length(), M : words.length, K : max(words.[i].length)
// 276ms
class Solution1 {
    Map<String, Boolean> matchMap = new HashMap<>();

    public int numMatchingSubseq(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (matchingSubseq(s, word)) {
                count++;
            }
        }
        return count;
    }

    private boolean matchingSubseq(String s, String word) {
        if (matchMap.containsKey(word)) {
            return matchMap.get(word);
        }

        int j = 0;
        for (int i = 0; i < word.length(); ++i) {
            while (j < s.length() && s.charAt(j) != word.charAt(i)) {
                j++;
            }
            if (j == s.length()) {
                matchMap.put(word, false);
                return false;
            }
            j++;
        }
        matchMap.put(word, true);
        return true;
    }
}


public class NumberOfMatchingSubsequences {
    public static void main(String[] args) {
        testSol("abcde", new String[] {"a", "bb", "acd", "ace"}, 3);
        testSol("dsahjpjauf", new String[] {"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"}, 2);
    }

    static void testSol(String input1, String[] input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.numMatchingSubseq(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
