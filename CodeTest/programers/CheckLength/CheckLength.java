package CodeTEST.programers.CheckLength;
// 거리두기 확인하기
// https://programmers.co.kr/learn/courses/30/lessons/81302

import java.util.Arrays;

// DFS
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; ++i) {
            answer[i] = checkLength(places[i]);
        }
        return answer;
    }

    private int checkLength(String[] place) {
        char[][] placeArr = new char[5][5];
        for (int i = 0; i < place.length; ++i) {
            placeArr[i] = place[i].toCharArray();
        }

        int res = 1;
        for (int i = 0; i < place.length; ++i) {
            for (int j = 0; j < place.length; ++j) {
                if (placeArr[i][j] == 'P') {
                    res &= check(placeArr, i, j, 2);
                }
            }
        }

        return res;
    }

    private int check(char[][] place, int r, int c, int dist) {
        // P: 사람, O: 빈자리, X: 파티션
        if (r < 0 || r >= 5 || c < 0 || c >= 5) {
            return 1;
        }
        if (dist != 2 && place[r][c] == 'P') {
            return 0;
        }
        if (dist == 0 || place[r][c] == 'X') {
            return 1;
        }

        char temp = place[r][c];
        place[r][c] = 'X';

        int res = 1;
        res &= check(place, r + 1, c, dist - 1);
        res &= check(place, r - 1, c, dist - 1);
        res &= check(place, r, c + 1, dist - 1);
        res &= check(place, r, c - 1, dist - 1);
        place[r][c] = temp;
        return res;
    }
}


public class CheckLength {
    public static void main(String[] args) {
        testSol(new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},}, new int[] {1});
        testSol(new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}}, new int[] {1, 0, 1, 1, 1});
    }

    static void testSol(String[][] input, int[] output) {
        Solution sol = new Solution();
        // todo : solution match
        int[] res = sol.solution(input);
        if (Arrays.equals(res, output)) {
            System.out.println("O : " + Arrays.toString(res));
        } else {
            System.out.println(
                    "x : " + Arrays.toString(res) + "	expect : " + Arrays.toString(output));
        }
    }

}
