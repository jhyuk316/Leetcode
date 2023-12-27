package Uncategorized.MinimumTimeToMakeRopeColorful;
// 1578. Minimum Time to Make Rope Colorful
// https://leetcode.com/problems/minimum-time-to-make-rope-colorful/?envType=daily-question&envId=2023-12-27

import java.util.*;;;

// O(N)
class Solution {
    public int minCost(String colors, int[] neededTime) {
        int N = colors.length();
        char[] colorArr = colors.toCharArray();

        int answer = 0;

        int max = neededTime[0];
        int temp = neededTime[0];
        for (int i = 1; i < N; ++i) {
            if (colorArr[i - 1] == colorArr[i]) {
                max = Math.max(max, neededTime[i]);
                temp += neededTime[i];
            } else {
                answer += temp - max;
                temp = neededTime[i];
                max = neededTime[i];
            }
        }
        answer += temp - max;

        return answer;
    }
}


// O(N)
class Solution2 {
    public int minCost(String colors, int[] neededTime) {
        int N = colors.length();
        char[] colorArr = colors.toCharArray();

        int total = Arrays.stream(neededTime).reduce(0, Integer::sum);

        int max = neededTime[0];
        for (int i = 1; i < N; ++i) {
            if (colorArr[i - 1] == colorArr[i]) {
                max = Math.max(max, neededTime[i]);
            } else {
                total -= max;
                max = neededTime[i];
            }
        }

        return total - max;
    }
}


public class MinimumTimeToMakeRopeColorful {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCost("abaac", new int[] {1, 2, 3, 4, 5}));
        System.out.println(solution.minCost("abc", new int[] {1, 2, 3}));
        System.out.println(solution.minCost("aabaa", new int[] {1, 2, 3, 4, 1}));
        System.out.println(solution.minCost("cddcdcae", new int[] {4, 8, 8, 4, 4, 5, 4, 2}));

        assert solution.minCost("abaac", new int[] {1, 2, 3, 4, 5}) == 3;
        assert solution.minCost("abc", new int[] {1, 2, 3}) == 0;
        assert solution.minCost("aabaa", new int[] {1, 2, 3, 4, 1}) == 2;
    }

}
