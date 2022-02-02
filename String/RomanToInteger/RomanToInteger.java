package String.RomanToInteger;
// 13. Roman to Integer
// https://leetcode.com/problems/roman-to-integer/

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = romanMap.get(s.charAt(s.length() - 1));

        for (int i = 0; i < s.length() - 1; ++i) {
            if (romanMap.containsKey(s.charAt(i))) {
                if (romanMap.get(s.charAt(i)) < romanMap.get(s.charAt(i + 1))) {
                    result -= romanMap.get(s.charAt(i));
                } else {
                    result += romanMap.get(s.charAt(i));
                }
            }
        }

        return result;
    }
}


public class RomanToInteger {
    public static void main(String[] args) {
        testSol("X", 10);
        testSol("III", 3);
        testSol("LVIII", 58);
        testSol("MCMXCIV", 1994);
    }

    static void testSol(String input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.romanToInt(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
