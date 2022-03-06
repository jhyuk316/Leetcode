package Heap.TopKFrequentElements;
// 347. Top K Frequent Elements
// https://leetcode.com/problems/top-k-frequent-elements/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


// O(N*logN) 원소수 카운트해서 Map에 저장, 결과를 Heap에 넣어서 추출.
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> sortedCounts =
                new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        sortedCounts.addAll(counts.entrySet());

        System.out.println(sortedCounts);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            result.add(sortedCounts.poll().getKey());
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}


public class TopKFrequentElements {
    public static void main(String[] args) {
        testSol(new int[] {1, 1, 1, 2, 2, 3}, 2, new int[] {1, 2});

    }

    static void testSol(int[] input1, int input2, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.topKFrequent(input1, input2);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }

}
