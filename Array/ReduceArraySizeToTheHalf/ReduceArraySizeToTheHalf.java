package Array.ReduceArraySizeToTheHalf;
// 1338. Reduce Array Size to The Half
// https://leetcode.com/problems/reduce-array-size-to-the-half/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSetSize(int[] arr) {
        int size = arr.length;

        Map<Integer, Integer> intMap = new HashMap<>();
        Arrays.stream(arr).forEach(i -> intMap.put(i, intMap.getOrDefault(i, 0) + 1));

        var entries = intMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .toList();

        int count = size;
        int i = 0;
        while (count > size / 2) {
            count -= entries.get(i).getValue();
            i++;
        }

        return i;
    }
}


public class ReduceArraySizeToTheHalf {
    public static void main(String[] args) {
        testSol(new int[] {3, 3, 3, 3, 5, 5, 5, 2, 2, 7}, 2);
        testSol(new int[] {7, 7, 7, 7, 7, 7}, 1);
    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.minSetSize(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
