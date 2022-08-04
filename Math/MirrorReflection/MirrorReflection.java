package Math.MirrorReflection;
// 858. Mirror Reflection
// https://leetcode.com/problems/mirror-reflection/

import java.sql.Time;

class Solution {
    int P;
    int Q;

    public int mirrorReflection(int p, int q) {
        P = p;
        Q = q;

        while (isMirror(p, q) == -1) {
            System.out.println(p + " " + q);
            // reflect
            p = p == P ? 0 : P;
            q = q + Q;
        }

        return isMirror(p, q);
    }

    private int isMirror(int p, int q) {
        if (p == P && q % (2 * P) == 0) {
            return 0;
        }

        if (p == 0 && q % P == 0) {
            return 2;
        }

        if (p == P && q % P == 0) {
            return 1;
        }
        return -1;
    }
}


public class MirrorReflection {
    public static void main(String[] args) {
        testSol(2, 1, 2);
        testSol(3, 1, 1);
        testSol(3, 2, 0);
        testSol(4, 3, 2);
    }

    static void testSol(int input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.mirrorReflection(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
