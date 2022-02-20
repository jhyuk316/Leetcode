package Array.LeastNumberOfUniqueIntegersAfterKRemovals;
// 1481. Least Number of Unique Integers after K Removals
// https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


// O(n*logn)
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> sortCounts = new PriorityQueue<>(counts.values());

        System.out.println(sortCounts);

        while (!sortCounts.isEmpty() && sortCounts.peek() <= k) {
            k -= sortCounts.poll();
        }

        return sortCounts.size();
    }
}


public class LeastNumberOfUniqueIntegersAfterKRemovals {

    public static void main(String[] args) {
        testSol(List.of(new int[] {5, 5, 4}, 1), 1);
        testSol(List.of(new int[] {4, 3, 1, 1, 3, 3, 2}, 3), 2);
        testSol(List.of(new int[] {2, 1, 1, 3, 3, 3}, 3), 1);
        testSol(List.of(new int[] {1, 2, 3}, 3), 0);
    }

    static void testSol(List input, Object output) {
        // todo : input, output match
        int[] arg1 = (int[]) input.get(0);
        int arg2 = (int) input.get(1);
        int out = (int) output;
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.findLeastNumOfUniqueInts(arg1, arg2);
        if (res == out) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + out);
        }
    }
}
