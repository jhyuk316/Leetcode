package String.SubstringWithConcatenationOfAllWords;
// 30. Substring with Concatenation of All Words
// https://leetcode.com/problems/substring-with-concatenation-of-all-words/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// O(a+n⋅b) solution Sliding Window
// 뭔소린지 모르겠다.
class Solution {
    private HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
    private int n;
    private int wordLength;
    private int substringSize;
    private int k;

    public List<Integer> findSubstring(String s, String[] words) {
        n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength * k;

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < wordLength; i++) {
            slidingWindow(i, s, answer);
        }

        return answer;
    }

    private void slidingWindow(int left, String s, List<Integer> answer) {
        HashMap<String, Integer> wordsFound = new HashMap<>();
        int wordsUsed = 0;
        boolean excessWord = false;

        // Do the same iteration pattern as the previous approach - iterate
        // word_length at a time, and at each iteration we focus on one word
        for (int right = left; right <= n - wordLength; right += wordLength) {

            String sub = s.substring(right, right + wordLength);
            if (!wordCount.containsKey(sub)) {
                // Mismatched word - reset the window
                wordsFound.clear();
                wordsUsed = 0;
                excessWord = false;
                left = right + wordLength;
            } else {
                // If we reached max window size or have an excess word
                while (right - left == substringSize || excessWord) {
                    String leftmostWord = s.substring(left, left + wordLength);
                    left += wordLength;
                    wordsFound.put(leftmostWord, wordsFound.get(leftmostWord) - 1);

                    if (wordsFound.get(leftmostWord) >= wordCount.get(leftmostWord)) {
                        // This word was an excess word
                        excessWord = false;
                    } else {
                        // Otherwise we actually needed it
                        wordsUsed--;
                    }
                }

                // Keep track of how many times this word occurs in the window
                wordsFound.put(sub, wordsFound.getOrDefault(sub, 0) + 1);
                if (wordsFound.get(sub) <= wordCount.get(sub)) {
                    wordsUsed++;
                } else {
                    // Found too many instances already
                    excessWord = true;
                }

                if (wordsUsed == k && !excessWord) {
                    // Found a valid substring
                    answer.add(left);
                }
            }
        }
    }
}


// O(n*a*b - (a*b)^2) solution Check All Indices Using a Hash Table
// 길이가 같으니깐 되는건가?
class Solution3 {
    private HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
    private int wordLength;
    private int substringSize;
    private int k;

    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length();
        k = words.length;
        wordLength = words[0].length();
        substringSize = wordLength * k;

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n - substringSize + 1; i++) {
            if (check(i, s)) {
                answer.add(i);
            }
        }

        return answer;
    }

    private boolean check(int i, String s) {
        // Copy the original dictionary to use for this index
        HashMap<String, Integer> remaining = new HashMap<>(wordCount);
        int wordsUsed = 0;

        // Each iteration will check for a match in words
        for (int j = i; j < i + substringSize; j += wordLength) {
            String sub = s.substring(j, j + wordLength);
            if (remaining.getOrDefault(sub, 0) != 0) {
                remaining.put(sub, remaining.get(sub) - 1);
                wordsUsed++;
            } else {
                break;
            }
        }

        return wordsUsed == k;
    }
}


// O(A*N*B+N)모든 스타트 점을 찾아서 완전탐색, 겨우 통과
class Solution2 {
    int N;
    Map<String, Integer> wordMap = new HashMap<>();

    public List<Integer> findSubstring(String s, String[] words) {
        N = words.length;
        int wordsLength = Arrays.stream(words).mapToInt(String::length).sum();
        Map<String, Set<Integer>> wordStartPos = new HashMap<>();
        wordMap = new HashMap<>();

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            if (!wordStartPos.containsKey(word)) {
                wordStartPos.put(word, new HashSet<>());
            }
        }

        for (String word : wordMap.keySet()) {
            for (int i = 0; i < s.length(); ++i) {
                boolean find = true;
                for (int j = 0; j < word.length(); ++j) {
                    if (i + j >= s.length() || s.charAt(i + j) != word.charAt(j)) {
                        find = false;
                        break;
                    }
                }

                if (find) {
                    wordStartPos.get(word).add(i);
                }
            }
        }

        System.out.println(wordStartPos);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length() - wordsLength + 1; ++i) {
            if (isFind(wordStartPos, wordMap.keySet(), new HashMap<>(), i, 0)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isFind(Map<String, Set<Integer>> graph, Set<String> words,
            Map<String, Integer> visited,
            int sIndex, int depth) {

        if (depth == N) {
            return true;
        }

        for (String word : words) {
            if (visited.containsKey(word) && visited.get(word) >= wordMap.get(word)) {
                continue;
            }
            if (graph.get(word).contains(sIndex)) {
                visited.put(word, visited.getOrDefault(word, 0) + 1);
                if (isFind(graph, words, visited, sIndex + word.length(), depth + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}


// 모든 스타트 점을 찾아서 완전탐색, TLE 174/178
class Solution1 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<List<Integer>> wordStartPos = new ArrayList<>();

        for (int i = 0; i < words.length; ++i) {
            wordStartPos.add(new ArrayList<>());
        }

        for (int w = 0; w < words.length; ++w) {
            for (int i = 0; i < s.length(); ++i) {
                boolean find = true;
                for (int j = 0; j < words[w].length(); ++j) {
                    if (i + j >= s.length() || s.charAt(i + j) != words[w].charAt(j)) {
                        find = false;
                        break;
                    }
                }

                if (find) {
                    wordStartPos.get(w).add(i);
                }
            }
        }

        System.out.println(wordStartPos);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (isFind(wordStartPos, words, new boolean[words.length], i, 0)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isFind(List<List<Integer>> graph, String[] words, boolean[] visited,
            int sIndex, int depth) {

        if (depth == words.length) {
            return true;
        }

        for (int i = 0; i < words.length; ++i) {
            if (visited[i]) {
                continue;
            }

            for (int start : graph.get(i)) {
                if (sIndex == start) {
                    visited[i] = true;
                    if (isFind(graph, words, visited, sIndex + words[i].length(), depth + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        testSol("barfoothefoobarman", new String[] {"foo", "bar"}, List.of(0, 9));
        testSol("wordgoodgoodgoodbestword", new String[] {"word", "good", "best", "word"},
                List.of());
        testSol("barfoofoobarthefoobarman", new String[] {"bar", "foo", "the"}, List.of(6, 9, 12));
        testSol("aaaaaaaaaa", new String[] {"a", "a", "a", "a", "a"}, List.of(0, 1, 2, 3, 4, 5));

    }

    static void testSol(String input1, String[] input2, List<Integer> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<Integer> res = sol.findSubstring(input1, input2);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
