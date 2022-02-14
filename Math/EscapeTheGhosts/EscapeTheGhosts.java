package Math.EscapeTheGhosts;
// 789. Escape The Ghosts
// https://leetcode.com/problems/escape-the-ghosts/

import java.util.List;

// O(n)
// 유령이 먼저 target에 도달하면 실패이므로
// 모든 유령과 거리를 비교해서 짧으면 성공.
// 알고리즘이 아니라 영어 + 수학 문제 ㅋㅋㅋ.
class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int[] origin = {0, 0};
        int distanceTarget = distance(origin, target);

        for (int i = 0; i < ghosts.length; ++i) {
            if (distanceTarget >= distance(target, ghosts[i])) {
                return false;
            }
        }
        return true;
    }

    private int distance(int[] a, int[] b) {
        int x = Math.abs(b[0] - a[0]);
        int y = Math.abs(b[1] - a[1]);
        return x + y;
    }
}


public class EscapeTheGhosts {
    public static void main(String[] args) {
        testSol(List.of(new int[][] {{1, 0}, {0, 3}}, new int[] {0, 1}), true);
        testSol(List.of(new int[][] {{1, 0}}, new int[] {2, 0}), false);
        testSol(List.of(new int[][] {{2, 0}}, new int[] {1, 0}), false);
    }

    static void testSol(List input, Boolean output) {
        // todo : input, output match
        int[][] arg1 = (int[][]) input.get(0);
        int[] arg2 = (int[]) input.get(1);
        boolean out = (boolean) output;
        Solution sol = new Solution();
        // todo : solution match
        boolean res = sol.escapeGhosts(arg1, arg2);
        if (res == out) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + out);
        }
    }

}
