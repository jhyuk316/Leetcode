package Array.Candy;
// 135. Candy
// https://leetcode.com/problems/candy/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// O(N) greedy
class Solution {
    public int candy(int[] ratings) {
        int N = ratings.length;
        int[] count = new int[N];

        // 시작 위치
        for (int i = 1; i < N; ++i) {
            if (ratings[i - 1] < ratings[i]) {
                count[i] = count[i - 1] + 1;
            }
        }
        for (int i = N - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                count[i] = Math.max(count[i], count[i + 1] + 1);
            }
        }

        System.out.println(Arrays.toString(count));
        int res = 0;
        for (int c : count) {
            res += c + 1;
        }

        return res;
    }
}


// O(N)
class Solution2 {
    public int candy(int[] ratings) {
        int N = ratings.length;
        if (N <= 1) {
            return 1;
        }

        int[] count = new int[N];

        // 시작 위치
        Queue<Integer> queue = new LinkedList<>();
        if (ratings[0] <= ratings[1]) {
            queue.add(0);
            count[0] = 1;
        }
        for (int i = 1; i < N - 1; ++i) {
            if (ratings[i - 1] >= ratings[i] && ratings[i] <= ratings[i + 1]) {
                queue.add(i);
                count[i] = 1;
            }
        }
        if (ratings[N - 1] <= ratings[N - 2]) {
            queue.add(N - 1);
            count[N - 1] = 1;
        }

        // BFS
        int depth = 2;
        while (!queue.isEmpty()) {
            System.out.println(queue);
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int pos = queue.poll();

                if (pos + 1 < ratings.length && count[pos + 1] <= depth
                        && ratings[pos] < ratings[pos + 1]) {
                    queue.add(pos + 1);
                    count[pos + 1] = depth;
                }
                if (pos - 1 >= 0 && count[pos - 1] <= depth
                        && ratings[pos] < ratings[pos - 1]) {
                    queue.add(pos - 1);
                    count[pos - 1] = depth;
                }
            }
            depth++;
        }

        // System.out.println(Arrays.toString(count));

        int res = 0;
        for (int c : count) {
            res += c;
        }

        return res;
    }
}


// O(N)
class Solution1 {
    public int candy(int[] ratings) {
        int N = ratings.length;
        int[] count = new int[N];

        Map<Integer, List<Integer>> ratingsMap = new HashMap<>();

        for (int i = 0; i < N; ++i) {
            if (!ratingsMap.containsKey(ratings[i])) {
                ratingsMap.put(ratings[i], new ArrayList<>());
            }
            ratingsMap.get(ratings[i]).add(i);
        }

        List<Map.Entry<Integer, List<Integer>>> ratingsList =
                new ArrayList<>(ratingsMap.entrySet());

        ratingsList.sort((o1, o2) -> (o1.getKey() - o2.getKey()));

        // System.out.println(ratingsList);

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, List<Integer>> rate : ratingsList) {
            for (int pos : rate.getValue()) {
                if (count[pos] == 0) {
                    queue.add(pos);
                    count[pos] = 1;
                }
            }

            int depth = 2;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; ++i) {
                    int pos = queue.poll();

                    if (pos + 1 < ratings.length && count[pos + 1] <= depth
                            && ratings[pos] < ratings[pos + 1]) {
                        queue.add(pos + 1);
                        count[pos + 1] = depth;
                    }
                    if (pos - 1 >= 0 && count[pos - 1] <= depth
                            && ratings[pos] < ratings[pos - 1]) {
                        queue.add(pos - 1);
                        count[pos - 1] = depth;
                    }
                }
                depth++;
            }
        }

        // System.out.println(Arrays.toString(count));
        int res = 0;
        for (int c : count) {
            res += c;
        }

        return res;
    }
}


public class Candy {
    public static void main(String[] args) {
        testSol(new int[] {1, 0, 2}, 5);
        testSol(new int[] {1, 2, 2}, 4);
        testSol(new int[] {1, 6, 10, 8, 7, 3, 2}, 18);
    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.candy(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
