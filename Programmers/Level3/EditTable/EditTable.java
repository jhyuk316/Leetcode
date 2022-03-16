package Programmers.Level3.EditTable;
// 표 편집
// https://programmers.co.kr/learn/courses/30/lessons/81303

import java.util.*;

// 해답, 다음 위치와 이전 위치 배열을 통한 linked list
class Solution {
    char[] answer;
    int cur;
    int N;
    Stack<Integer> stack;
    int[] up;
    int[] down;

    public String solution(int n, int k, String[] cmd) {
        answer = new char[n];
        up = new int[n];
        down = new int[n];
        N = n;
        cur = k;

        for (int i = 0; i < n; ++i) {
            answer[i] = 'O';
            up[i] = i - 1;
            down[i] = i + 1;
        }

        stack = new Stack<>();
        for (int i = 0; i < cmd.length; ++i) {
            String[] cmdSplit = cmd[i].split(" ");
            int dist = 0;
            if (cmdSplit.length > 1) {
                dist = Integer.parseInt(cmdSplit[1]);
            }
            if (cmdSplit[0].equals("U")) {
                for (int j = 0; j < dist; ++j) {
                    up();
                }
            } else if (cmdSplit[0].equals("D")) {
                for (int j = 0; j < dist; ++j) {
                    down();
                }
            } else if (cmdSplit[0].equals("C")) {
                delete();
            } else if (cmdSplit[0].equals("Z")) {
                undo();
            }
            System.out.println(i + " " + new String(answer) + " " + cur + " " + cmd[i]);
        }
        return new String(answer);
    }

    private int up() {
        cur = up[cur];
        return cur;
    }

    private int down() {
        cur = down[cur];
        return cur;
    }

    private void delete() {
        stack.add(cur);
        answer[cur] = 'X';

        if (up[cur] != -1) {
            down[up[cur]] = down[cur];
        }
        if (down[cur] != N) {
            up[down[cur]] = up[cur];
        }

        if (down[cur] == N) {
            up();
        } else {
            down();
        }
    }

    private void undo() {
        int index = stack.pop();
        answer[index] = 'O';
        int upPos = up[index];
        int downPos = down[index];

        if (upPos != -1) {
            down[upPos] = index;
        }
        if (downPos != N) {
            up[downPos] = index;
        }
    }
}


// 효율성 절반
class Solution1 {
    char[] answer;
    int cur;
    int N;
    Stack<Integer> stack;
    int last;


    public String solution(int n, int k, String[] cmd) {
        answer = new char[n];
        N = n;
        cur = k;
        last = n - 1;

        for (int i = 0; i < n; ++i) {
            answer[i] = 'O';
        }

        System.out.println("S " + new String(answer) + " " + cur);
        stack = new Stack<>();
        for (int i = 0; i < cmd.length; ++i) {
            String[] cmdSplit = cmd[i].split(" ");
            int dist = 0;
            if (cmdSplit.length > 1) {
                dist = Integer.parseInt(cmdSplit[1]);
            }
            if (cmdSplit[0].equals("U")) {
                for (int j = 0; j < dist; ++j) {
                    up();
                }
            } else if (cmdSplit[0].equals("D")) {
                for (int j = 0; j < dist; ++j) {
                    down();
                }
            } else if (cmdSplit[0].equals("C")) {
                delete();
            } else if (cmdSplit[0].equals("Z")) {
                undo();
            }
            System.out.println(i + " " + new String(answer) + " " + cur + " " + cmd[i]);
        }
        return new String(answer);
    }

    private int up() {
        cur--;
        while (cur >= 0 && answer[cur] == 'X') {
            cur--;
        }
        return cur;
    }

    private void down() {
        cur++;
        while (cur < N && answer[cur] == 'X') {
            cur++;
        }
    }

    private void delete() {
        answer[cur] = 'X';
        stack.add(cur);
        if (cur == last) {
            last = up();
        } else {
            down();
        }
    }

    private void undo() {
        int index = stack.pop();
        answer[index] = 'O';
        if (index >= last) {
            last = index;
        }
    }
}


public class EditTable {
    public static void main(String[] args) {
        testSol(8, 2, new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"},
                "OOOOXOOO");
        testSol(8, 2,
                new String[] {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"},
                "OOXOXOOO");
        testSol(8, 2, new String[] {"C", "C", "U 1", "C", "Z"}, "OOXXOOOO");
    }

    private static void testSol(int input1, int input2, String[] input3, String out) {
        Solution sol = new Solution();
        String res = sol.solution(input1, input2, input3);
        if (res.equals(out)) {
            System.out.println("O: " + res);
        } else {
            System.out.println("X: " + res + "\t expect" + out);
        }
    }
}
