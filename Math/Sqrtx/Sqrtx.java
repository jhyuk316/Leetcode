package Math.Sqrtx;
// 69. Sqrt(x)
// https://leetcode.com/problems/sqrtx/


class Solution {
    public int mySqrt(int x) {
        long left = 0;
        long right = x;
        long mid;

        if (x <= 1) {
            return x;
        }

        while (left < right) {
            mid = left + (right - left) / 2;
            long exp = mid * mid;
            if (exp == x) {
                return (int) mid;
            } else if (exp < x) {
                left = mid + 1;
            } else if (exp > x) {
                right = mid;
            }
        }

        return (int) left - 1;
    }
}


public class Sqrtx {
    public static void main(String[] args) {
        testSol(0, 0);
        testSol(1, 1);
        testSol(2, 1);
        testSol(4, 2);
        testSol(8, 2);
        testSol(9, 3);
        testSol(325732, 570);
        testSol(2147395599, 46339);
        testSol(2147483647, 46340);
        testSol(1073741823, 32767);
    }

    static void testSol(int input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.mySqrt(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
