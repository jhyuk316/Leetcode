package CodeTEST.BAEKJOON.MeetingRoom;
// 1931. 회의실 배정
// https://www.acmicpc.net/problem/1931
// Activity Selection Problem
// https://en.wikipedia.org/wiki/Activity_selection_problem

import java.io.*;
import java.util.*;


// 그리드 알고리즘.
// 끝나는 시간이 가장 빠른 회의를 찾고 그 이후에 가장 빠른 회의를 찾으면 최댓값을 알 수 있음.
// 증명 - 귀류법
// 가정 : 가장 짧은 A를 선택하면 나머지 부분(B)이 최댓값이 아니다.
// A보다 긴 A'를 선택하면 나머지 부분(B')이 최댓값이다.
// A' 대신 A를 선택해도 최댓값의 나머지 부분(B')를 취할 수 있다.
// 가장 짧은 A가 최댓값을 가질 수 있으므로 위 명제는 모순이다.
// 즉, 가장 짧은 A를 선택하면 최댓값이다.

// arrays버전
class Solution {
    int N;

    public void solution() {
        try {
            // input
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(in.readLine());

            int[][] timeArr = new int[N][2];

            for (int i = 0; i < N; ++i) {
                String[] timeStr = in.readLine().split(" ");
                timeArr[i][0] = Integer.parseInt(timeStr[0]);
                timeArr[i][1] = Integer.parseInt(timeStr[1]);
            }

            // solve
            Arrays.sort(timeArr,
                    Comparator.<int[]>comparingInt(t -> t[1]).thenComparingInt(t -> t[0]));

            int lastTime = 0;
            int count = 0;
            for (int[] time : timeArr) {
                if (time[0] >= lastTime) {
                    lastTime = time[1];
                    count++;
                }
            }
            System.out.println(count);
        } catch (Exception e) {
            System.out.println(e);
        }
        return;
    }
}


// list버전
class Solution1 {
    int N;

    public void solution() {
        try {
            // input
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(in.readLine());
            // System.out.println("N : " + N);

            List<int[]> timeList = new ArrayList<>();

            while (in.ready()) {
                String[] timeStr = in.readLine().split(" ");
                int[] tempTime = Arrays.stream(timeStr).mapToInt(Integer::parseInt).toArray();

                timeList.add(tempTime);
            }

            // solve
            timeList.sort(Comparator.comparingInt(t -> t[0]));
            timeList.sort(Comparator.comparingInt(t -> t[1]));
            // System.out.println("time List");
            // timeList.forEach(a -> System.out.println(Arrays.toString(a)));

            int lastTime = 0;
            int count = 0;
            for (int[] time : timeList) {
                if (time[0] >= lastTime) {
                    lastTime = time[1];
                    count++;
                }
            }
            System.out.println(count);

        } catch (Exception e) {
            System.out.println(e);
        }
        return;
    }
}


public class MeetingRoom {

    public static void main(String[] args) {
        String input = "11\n"
                + "1 4\n"
                + "3 5\n"
                + "0 6\n"
                + "5 7\n"
                + "3 8\n"
                + "5 9\n"
                + "6 10\n"
                + "8 11\n"
                + "8 12\n"
                + "2 13\n"
                + "12 14\n";


        // OutputStream out = new ByteArrayOutputStream();
        // System.setOut(new PrintStream(out));
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Solution sol = new Solution();
        sol.solution();
    }

}
