package String.ReverseWordsInAStringIII;
// 557. Reverse Words in a String III
// https://leetcode.com/problems/reverse-words-in-a-string-iii/


class Solution {
    public String reverseWords(String s) {
        String[] splitStr = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String str : splitStr) {
            StringBuilder tempStr = new StringBuilder(str);
            sb.append(tempStr.reverse().toString());
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}


public class ReverseWordsInAStringIII {
    public static void main(String[] args) {
        testSol("Let's take LeetCode contest", "s'teL ekat edoCteeL tsetnoc");
        testSol("God Ding", "doG gniD");
    }

    static void testSol(String input, String output) {
        Solution sol = new Solution();
        // todo : solution match
        String res = sol.reverseWords(input);
        if (res.equals(output)) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
