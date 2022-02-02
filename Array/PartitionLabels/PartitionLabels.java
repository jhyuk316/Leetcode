package Array.PartitionLabels;
// 763. Partition Labels
// https://leetcode.com/problems/partition-labels/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


// O(n) 해답 Two-Pointer
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] endChar = new int[26];

        for (int i = 0; i < s.length(); ++i) {
            endChar[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();

        int end = -1;
        int start = 0;
        for (int i = 0; i < s.length(); ++i) {
            end = Math.max(end, endChar[s.charAt(i) - 'a']);

            if (i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }

        return result;
    }
}


// O(n) Intervals를 만들고 합쳐서 그 결과를 반환.
class Solution1 {
    public List<Integer> partitionLabels(String s) {
        int[] startChar = new int[26];
        int[] endChar = new int[26];
        int intA = 'a';

        Arrays.fill(startChar, -1);

        for (int i = 0; i < s.length(); ++i) {
            if (startChar[s.charAt(i) - intA] == -1) {
                startChar[s.charAt(i) - intA] = i;
            }
            endChar[s.charAt(i) - intA] = i;
        }

        List<List<Integer>> intervals = new ArrayList<>();

        for (int i = 0; i < 26; ++i) {
            if (startChar[i] != -1) {
                intervals.add(List.of(startChar[i], endChar[i]));
            }
        }

        intervals.sort(Comparator.comparing((x) -> x.get(0)));

        List<List<Integer>> compressInterval = new ArrayList<>();

        int i = 0;
        while (i < intervals.size()) {
            int start = intervals.get(i).get(0);
            int end = intervals.get(i).get(1);

            ++i;
            while (i < intervals.size() && end >= intervals.get(i).get(0)) {
                end = Math.max(end, intervals.get(i).get(1));
                ++i;
            }

            compressInterval.add(List.of(start, end));
        }

        List<Integer> result = new ArrayList<>();
        for (i = 0; i < compressInterval.size(); ++i) {
            result.add(compressInterval.get(i).get(1) - compressInterval.get(i).get(0) + 1);
        }

        return result;
    }
}



public class PartitionLabels {
    public static void main(String[] args) {
        testSol("ababcbacadefegdehijhklij", List.of(9, 7, 8));
        testSol("hijhklijababcbacadefegde", List.of(8, 9, 7));
        testSol("eccbbbbdec", List.of(10));
        testSol("abc", List.of(1, 1, 1));
        testSol("abca", List.of(4));
    }

    static void testSol(String input, List<Integer> output) {
        Solution sol = new Solution();
        // todo : solution match
        List<Integer> res = sol.partitionLabels(input);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
