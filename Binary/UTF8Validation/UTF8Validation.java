package Binary.UTF8Validation;
// 393. UTF-8 Validation
// https://leetcode.com/problems/utf-8-validation/


class Solution {
    public boolean validUtf8(int[] data) {
        int count = 0;
        for (int c : data) {
            // follow character
            if (count > 0) {
                if ((c & 0x80) != 0x80) {
                    return false;
                } else {
                    count--;
                }
            } else {
                if ((c & 0x80) == 0) {
                    continue;
                } else {
                    count = 0;
                    while ((c & 0x80) == 0x80) {
                        c = c << 1;
                        count++;
                    }
                    count--;
                    if (count == 0 || count >= 4) {
                        return false;
                    }
                }
            }
        }
        return count == 0;
    }
}


public class UTF8Validation {
    public static void main(String[] args) {
        // testSol(new int[] {197, 130, 1}, true);
        // testSol(new int[] {235, 140, 4}, false);
        testSol(new int[] {250, 145, 145, 145, 145}, false);
    }

    static void testSol(int[] input, boolean output) {
        Solution sol = new Solution();
        // todo : solution match
        boolean res = sol.validUtf8(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
