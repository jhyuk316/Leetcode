package CodeTEST.RomanToInteger;
// 13. Roman to Integer
// https://leetcode.com/problems/roman-to-integer/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        Map<Character, List<Character>> romanPostfix = new HashMap<>();
        romanPostfix.put('I', List.of('V', 'X'));
        romanPostfix.put('X', List.of('L', 'C'));
        romanPostfix.put('C', List.of('D', 'M'));

        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);

        romanMap.put('V', 5);
        romanMap.put('X', 10);

        romanMap.put('L', 50);
        romanMap.put('C', 100);

        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (romanPostfix.containsKey(s.charAt(i))) {
                List<Character> checkList = romanPostfix.get(s.charAt(i));

                if (i + 1 < s.length() && checkList.contains(s.charAt(i + 1))) {
                    result -= romanMap.get(s.charAt(i));
                } else {
                    result += romanMap.get(s.charAt(i));
                }

            } else if (romanMap.containsKey(s.charAt(i))) {
                result += romanMap.get(s.charAt(i));
            }
        }

        return result;
    }
}


public class RomanToInteger {
    public static void main(String[] args) {
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
