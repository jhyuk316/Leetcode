package String.MinimumWindowSubstring;
// 76. Minimum Window Substring
// https://leetcode.com/problems/minimum-window-substring/

import java.util.HashMap;
import java.util.Map;

// solution two-pointer array 4ms
// space 최적화
class Solution {
    char[] str;
    int[] target = new int[128];

    public String minWindow(String s, String t) {
        String res = "";
        int maxLength = 100001;

        str = s.toCharArray();

        int targetCount = 0;
        for (char c : t.toCharArray()) {
            if (target[c] == 0) {
                targetCount++;
            }
            target[c]++;
        }

        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char c1 = str[right];
            target[c1]--;

            if (target[c1] == 0) {
                targetCount--;
            }

            if (0 == targetCount) {
                while (0 == targetCount) {
                    char c2 = str[left];
                    target[c2]++;
                    if (target[c2] > 0) {
                        targetCount++;
                    }
                    left++;
                }

                if (maxLength > (right - left)) {
                    maxLength = right - left;
                    res = s.substring(left - 1, right + 1);
                }
            }
            right++;
        }
        return res;
    }
}


// solution two-pointer array 2ms
class Solution4 {
    char[] str;
    int[] target = new int[128];

    public String minWindow(String s, String t) {
        String res = "";
        int maxLength = 100001;

        str = s.toCharArray();

        int targetCount = 0;
        for (char c : t.toCharArray()) {
            if (target[c] == 0) {
                targetCount++;
            }
            target[c]++;
        }

        int matchCount = 0;
        int left = 0;
        int right = 0;
        int[] map = new int[128];
        while (right < s.length()) {
            char c1 = str[right];
            map[c1]++;

            if (target[c1] > 0 && target[c1] == map[c1]) {
                matchCount++;
            }

            if (matchCount == targetCount) {
                while (left <= right && matchCount == targetCount) {
                    char c2 = str[left];
                    map[c2]--;
                    if (target[c2] > 0 && map[c2] < target[c2]) {
                        matchCount--;
                    }
                    left++;
                }

                if (maxLength > (right - left)) {
                    maxLength = right - left;
                    res = s.substring(left - 1, right + 1);
                }
            }
            right++;
        }
        return res;
    }
}


// solution two-pointer hashmap 26ms
class Solution3 {
    char[] str;
    Map<Character, Integer> target = new HashMap<>();

    public String minWindow(String s, String t) {
        String res = "";
        int maxLength = 100001;

        str = s.toCharArray();

        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int matchCount = 0;
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char c1 = str[right];
            map.put(c1, map.getOrDefault(c1, 0) + 1);

            // if (target.containsKey(c1) && map.get(c1) == target.get(c1)) { //ERROR 왜?
            if (target.containsKey(c1) && map.get(c1).equals(target.get(c1))) {
                matchCount++;
            }

            if (matchCount == target.size()) {
                // System.out.println(map);
                while (left <= right && matchCount == target.size()) {
                    char c2 = str[left];
                    map.put(c2, map.getOrDefault(c2, 0) - 1);
                    if (target.containsKey(c2) && map.get(c2) < target.getOrDefault(c2, 0)) {
                        matchCount--;
                    }
                    left++;
                }

                if (maxLength > (right - left)) {
                    maxLength = right - left;
                    res = s.substring(left - 1, right + 1);
                }
            }
            right++;
        }
        return res;
    }
}


// two-pointer
// check가 너무 느림. 89ms
class Solution2 {
    char[] str;
    Map<Character, Integer> target = new HashMap<>();

    public String minWindow(String s, String t) {
        str = s.toCharArray();

        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        String res = "";
        int maxLength = 100001;
        while (right < s.length()) {
            map.put(str[right], map.getOrDefault(str[right], 0) + 1);
            if (check(map)) {
                System.out.println(map);
                while (left <= right) {
                    map.put(str[left], map.getOrDefault(str[left], 0) - 1);
                    left++;
                    if (map.get(str[left - 1]) < target.getOrDefault(str[left - 1], 0)) {
                        break;
                    }
                }
                if (maxLength > (right - left)) {
                    maxLength = right - left;
                    res = s.substring(left - 1, right + 1);
                }
            }
            right++;
        }
        return res;
    }

    private boolean check(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : target.entrySet()) {
            if (!map.containsKey(entry.getKey()) || map.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}


// brute-force + 약간의 dp?
// 모든 윈도우 사이즈별로 체크
class Solution1 {
    char[] str;
    Map<Character, Integer> target = new HashMap<>();

    public String minWindow(String s, String t) {
        int m = t.length();
        str = s.toCharArray();

        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        for (int i = m; i <= s.length(); ++i) {
            // System.out.println(i);
            Map<Character, Integer> map = new HashMap<>();
            for (int j = 0; j < i; ++j) {
                map.put(str[j], map.getOrDefault(str[j], 0) + 1);
            }

            for (int j = 0; j < s.length() - i + 1; ++j) {
                System.out.println(map);
                if (check(map)) {
                    return s.substring(j, j + i);
                }
                map.put(str[j], map.getOrDefault(str[j], 0) - 1);
                if (j + i >= s.length()) {
                    break;
                }
                map.put(str[j + i], map.getOrDefault(str[j + i], 0) + 1);
            }
        }
        return "";
    }

    private boolean check(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : target.entrySet()) {
            if (!map.containsKey(entry.getKey()) || map.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}


public class MinimumWindowSubstring {
    public static void main(String[] args) {
        testSol("BANC", "ABC", "BANC");
        testSol("ADOBECODEBANC", "ABC", "BANC");
        testSol("a", "a", "a");
        testSol("a", "aa", "");
        testSol("bbaa", "aba", "baa");
    }

    static void testSol(String input1, String input2, String output) {
        Solution sol = new Solution();
        // todo : solution match
        String res = sol.minWindow(input1, input2);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
