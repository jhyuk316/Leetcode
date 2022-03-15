package Programmers.Level4.TrainerLion;
// 몸짱 트레이너 라이언의 고민
// https://programmers.co.kr/learn/courses/30/lessons/1838

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// 해결 못함
class Solution {
    public int solution(int n, int m, int[][] timetable) {
        int answer = 0;

        Arrays.sort(timetable, ((o1, o2) -> o1[0] - o2[0]));

        int maxMember = 0;
        for (int i = 0; i < timetable.length; ++i) {
            int count = 1;
            for (int j = i + 1; j < timetable.length; ++j) {
                if (timetable[i][1] >= timetable[j][0]) {
                    count++;
                }
            }
            maxMember = Math.max(maxMember, count);
        }

        if (maxMember == 1) {
            return 0;
        }

        // 배치 문제

        return position(n, maxMember, 0, 0, new ArrayList<>());
    }

    private int position(int n, int k, int r, int c, List<int[]> res) {
        if (k == 0) {
            int minDist = 101;
            res.forEach(o -> System.out.print(Arrays.toString(o)));
            System.out.println();

            for (int i = 0; i < res.size(); ++i) {
                for (int j = i + 1; j < res.size(); ++j) {
                    int tempDist = distance(res.get(i), res.get(j));
                    if (minDist > tempDist) {
                        minDist = tempDist;
                        // System.out.print(minDist + " ");
                    }
                }
            }

            return minDist;
        }

        int maxDist = 0;
        for (int i = r; i < n; ++i) {
            for (int j = c; j < n; ++j) {
                int[] tempPos = new int[] {i, j};
                res.add(tempPos);
                int tempDist = position(n, k - 1, 0, 0, res);
                System.out.println(tempDist);
                maxDist = Math.max(maxDist, tempDist);
                res.remove(tempPos);
            }
        }
        return maxDist;
    }

    private int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}


public class TrainerLion {

    public static void main(String[] args) {
        testSol(3, 2, new int[][] {{1170, 1210}, {1200, 1260}}, 4);
        testSol(2, 1, new int[][] {{840, 900}}, 0);
        testSol(2, 2, new int[][] {{600, 630}, {630, 700}}, 2);
        testSol(4, 5,
                new int[][] {{1140, 1200}, {1150, 1200}, {1100, 1200}, {1210, 1300}, {1220, 1280}},
                4);
    }

    static void testSol(int input1, int input2, int[][] input3, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.solution(input1, input2, input3);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
