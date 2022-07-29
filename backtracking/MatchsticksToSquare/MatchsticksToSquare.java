package backtracking.MatchsticksToSquare;
// 473. Matchsticks to Square
// https://leetcode.com/problems/matchsticks-to-square/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import java.util.HashMap;


// soution DFS
class Solution2 {
    int length;
    int[] matchsticks;

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }

        if (sum % 4 != 0) {
            return false;
        }
        length = sum / 4;

        // 큰 수부터 처리.
        this.matchsticks = Arrays.stream(matchsticks).boxed().sorted(Comparator.reverseOrder())
                .mapToInt(i -> i).toArray();

        // 0번 변은 0번 성냥으로 고정.
        int[] sums = new int[4];
        sums[0] = matchsticks[0];

        return dfs(sums, 1);
    }

    private boolean dfs(int[] sums, int index) {
        if (index >= matchsticks.length) {
            return sums[0] == length && sums[1] == length && sums[2] == length;
        }

        for (int i = 0; i < 4; ++i) {
            if (sums[i] + matchsticks[index] > length) {
                continue;
            }

            // 중복 변 제거??
            if (i > 0 && sums[i] == sums[i - 1]) {
                continue;
            }

            sums[i] += matchsticks[index];
            if (dfs(sums, index + 1)) {
                return true;
            }
            sums[i] -= matchsticks[index];
        }
        return false;
    }
}


// brute-force
class Solution {
    Set<Integer> visited;
    int length;
    int[] matchsticks;

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }
        if (sum % 4 != 0) {
            return false;
        }

        length = sum / 4;

        this.matchsticks = Arrays.stream(matchsticks).boxed().sorted(Comparator.reverseOrder())
                .mapToInt(i -> i).toArray();

        // System.out.println(Arrays.toString(this.matchsticks));

        visited = new HashSet<>();
        return dfs(0, length, 4);
    }

    public boolean dfs(int index, int unitLength, int count) {
        visited.add(index);
        // System.out.println(count + ": " + index + visited);
        int j = index + 1;
        unitLength -= matchsticks[index];
        if (unitLength == 0) {
            count--;
            unitLength = length;
            j = 0;
            if (count == 0) {
                return true;
            }
        }

        for (; j < matchsticks.length; ++j) {
            if (visited.contains(j) || unitLength < matchsticks[j]) {
                continue;
            }

            if (dfs(j, unitLength, count)) {
                return true;
            }
        }

        visited.remove(index);
        return false;
    }
}


public class MatchsticksToSquare {
    public static void main(String[] args) {
        testSol(new int[] {1, 1, 2, 2, 2}, true);
        testSol(new int[] {3, 3, 3, 3, 4}, false);
        testSol(new int[] {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3}, true);
        testSol(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, true);
        testSol(new int[] {13, 11, 1, 8, 6, 7, 8, 8, 6, 7, 8, 9, 8}, true);
    }

    static void testSol(int[] input, boolean output) {
        Solution sol = new Solution();
        // todo : solution match
        boolean res = sol.makesquare(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
