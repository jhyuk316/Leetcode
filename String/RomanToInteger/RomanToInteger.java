package String.RomanToInteger;
// 13. Roman to Integer
// https://leetcode.com/problems/roman-to-integer/

import java.util.HashMap;
import java.util.Map;

// O(N) 단순 무식 매칭, TC가 적은지 이것도 효과적ㅎ
class Solution {
    public int romanToInt(String s) {
        Map<String, Integer> sMap = new HashMap<>();
        Map<Character, Integer> cMap = new HashMap<>();

        sMap.put("CM", 900);
        sMap.put("CD", 400);
        sMap.put("XC", 90);
        sMap.put("XL", 40);
        sMap.put("IX", 9);
        sMap.put("IV", 4);

        cMap.put('M', 1000);
        cMap.put('D', 500);
        cMap.put('C', 100);
        cMap.put('L', 50);
        cMap.put('X', 10);
        cMap.put('V', 5);
        cMap.put('I', 1);

        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i + 1 < s.length() && sMap.containsKey(s.substring(i, i + 2))) {
                res += sMap.get(s.substring(i, i + 2));
                i++;
            } else {
                res += cMap.get(s.charAt(i));
            }
        }
        return res;
    }
}


// O(N) Greedy 앞글자는 무조건 뒷글자보다 크다.
class Solution1 {
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
