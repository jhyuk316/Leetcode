package Math.ReverseInteger;
// 7. Reverse Integer
// https://leetcode.com/problems/reverse-integer/


// O(N)
class Solution {
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res *= 10;
            res += x % 10;
            x = x / 10;
        }

        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }
}


public class ReverseInteger {
    public static void main(String[] args) {
        testSol(123, 321);
        testSol(-123, -321);
        testSol(120, 21);
        testSol(1534236469, 0);

    }

    static void testSol(int input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.reverse(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
