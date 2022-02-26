package String.GroupAnagrams;
// 49. Group Anagrams
// https://leetcode.com/problems/group-anagrams/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// O(M*N*logN) M : number of strs, N : length of str 정렬한 문자열을 키로 사용.
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();

        for (String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String key = new String(temp);

            if (!group.containsKey(key)) {
                group.put(key, new ArrayList<>());
            }
            group.get(key).add(str);
        }

        System.out.println(group);
        return new ArrayList<>(group.values());
    }
}


// O(M*N) 문자를 소수에 대입하여 해쉬화. 해쉬 충돌을 피하긴 어려움.
class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> group = new HashMap<>();

        for (String str : strs) {
            int key = makeKey(str);

            if (!group.containsKey(key)) {
                group.put(key, new ArrayList<>());
            }
            group.get(key).add(str);
        }

        System.out.println(group);
        return new ArrayList<>(group.values());
    }

    private int makeKey(String str) {
        int[] prime = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
                67, 71, 73, 79, 83, 89, 97, 101};
        int mod = 2147483647;

        int res = 1;
        for (char c : str.toCharArray()) {
            res *= prime[c - 'a'];
            res %= mod;
        }

        return res;
    }
}


// O(M*N) 문자를 카운트한 배열을 키로 씀. 하지만 실제로는 느림.
class Solution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();

        for (String str : strs) {
            String key = Arrays.toString(makeKay(str));
            if (!group.containsKey(key)) {
                group.put(key, new ArrayList<>());
            }
            group.get(key).add(str);
        }

        System.out.println(group);
        return new ArrayList<>(group.values());
    }

    private int[] makeKay(String str) {
        int[] key = new int[26];
        for (char c : str.toCharArray()) {
            key[c - 'a']++;
        }
        return key;
    }
}


public class GroupAnagrams {
    public static void main(String[] args) {
        testSol(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"},
                List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea")));

    }

    static void testSol(String[] input, List<List<String>> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<List<String>> res = sol.groupAnagrams(input);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }


}
