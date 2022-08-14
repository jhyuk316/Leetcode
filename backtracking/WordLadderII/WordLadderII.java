package backtracking.WordLadderII;
// 126. Word Ladder II
// https://leetcode.com/problems/word-ladder-ii/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// solution TLE 32/35 뭐야? 솔루션도 안돼?
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return ans;

        Queue<List<String>> queue = new LinkedList<>(); // each element in queue is a path
        queue.offer(Arrays.asList(beginWord));
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            int sz = queue.size();
            while (sz-- > 0) {
                List<String> currPath = queue.poll();
                String lastWord = currPath.get(currPath.size() - 1);

                List<String> neighbors = getNeighbors(lastWord, wordSet);
                for (String neigh : neighbors) {
                    List<String> newPath = new ArrayList<>(currPath);
                    newPath.add(neigh);
                    visited.add(neigh);
                    if (neigh.equals(endWord)) {
                        ans.add(newPath);
                    } else {
                        queue.offer(newPath);
                    }
                }
            }
            for (String s : visited) // remove used words from wordSet to avoid going back
                wordSet.remove(s);
        }

        return ans;
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            char[] ch = word.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                ch[i] = c;
                String str = new String(ch);
                if (wordSet.contains(str)) // only get valid neighbors
                    neighbors.add(str);
            }
        }
        return neighbors;
    }
}


// BFS, TLE 32/35
class Solution2 {
    Set<String> visited;
    List<List<String>> answer;

    class Word {
        String word;
        List<String> answer;

        public Word(String word, List<String> answer) {
            this.word = word;
            this.answer = answer;
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        visited = new HashSet<>();
        answer = new ArrayList<>();

        Set<String> wordSet = new HashSet<>(wordList);

        // make graph
        Map<String, List<String>> graph = new HashMap<>();
        graph.put(beginWord, isTrans(beginWord, wordSet));

        // BFS
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(beginWord, List.of(beginWord)));
        visited.add(beginWord);

        while (!queue.isEmpty() && answer.isEmpty()) {
            int size = queue.size();

            Set<String> tempVisited = new HashSet<>();
            for (int i = 0; i < size; ++i) {
                Word cur = queue.poll();

                if (!graph.containsKey(cur.word)) {
                    graph.put(cur.word, isTrans(cur.word, wordSet));
                }

                for (String next : graph.get(cur.word)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    tempVisited.add(next);
                    var temp = new ArrayList<>(cur.answer);
                    temp.add(next);
                    queue.add(new Word(next, temp));
                    if (next.equals(endWord)) {
                        answer.add(temp);
                    }
                }
            }
            visited.addAll(tempVisited);
        }

        return answer;
    }

    private List<String> isTrans(String word, Set<String> wordSet) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < word.length(); ++i) {
            char temp = sb.charAt(i);
            for (char ch = 'a'; ch <= 'z'; ++ch) {
                if (temp == ch) {
                    continue;
                }
                sb.setCharAt(i, ch);
                if (wordSet.contains(sb.toString())) {
                    result.add(sb.toString());
                }
            }
            sb.setCharAt(i, temp);
        }
        return result;
    }
}


// DFS, TLE 19/35
class Solution1 {
    Set<String> visited;
    List<List<String>> answer;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        visited = new HashSet<>();
        answer = new ArrayList<>();

        // make graph
        Map<String, List<String>> graph = new HashMap<>();
        graph.put(beginWord, new ArrayList<>());
        for (var next : wordList) {
            if (beginWord.equals(next)) {
                continue;
            }

            if (isTrans(beginWord, next)) {
                graph.get(beginWord).add(next);
            }
        }

        for (var start : wordList) {
            graph.put(start, new ArrayList<>());
            for (var next : wordList) {
                if (start.equals(next)) {
                    continue;
                }
                if (isTrans(start, next)) {
                    graph.get(start).add(next);
                }
            }
        }

        dfs(beginWord, endWord, graph, new ArrayList<>());
        return answer;
    }

    private void dfs(String beginWord, String endWord, Map<String, List<String>> graph,
            List<String> result) {

        if (visited.contains(beginWord)) {
            return;
        }
        visited.add(beginWord);
        result.add(beginWord);

        if (beginWord.equals(endWord)) {
            // System.out.println(result);
            if (!answer.isEmpty() && answer.get(0).size() > result.size()) {
                answer.clear();
                answer.add(new ArrayList<>(result));
            } else if (answer.isEmpty() || answer.get(0).size() == result.size()) {
                answer.add(new ArrayList<>(result));
            }

            visited.remove(beginWord);
            result.remove(result.size() - 1);
            return;
        }

        for (String word : graph.get(beginWord)) {
            dfs(word, endWord, graph, result);
        }

        visited.remove(beginWord);
        result.remove(result.size() - 1);
    }

    private boolean isTrans(String a, String b) {
        int missCount = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                missCount++;
            }
        }
        return missCount == 1;
    }
}


public class WordLadderII {
    public static void main(String[] args) {
        testSol("hit", "cog",
                List.of("hot", "dot", "dog", "lot", "log", "cog"),
                List.of(List.of("hit", "hot", "dot", "dog", "cog"),
                        List.of("hit", "hot", "lot", "log", "cog")));
        testSol("hit", "cog",
                List.of("hot", "dot", "dog", "lot", "log"),
                List.of());
        testSol("hot", "dog",
                List.of("hot", "dog", "dot"),
                List.of(List.of("hot", "dot", "dog")));
    }

    static void testSol(String input1, String input2, List<String> input3,
            List<List<String>> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<List<String>> res = sol.findLadders(input1, input2, input3);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
