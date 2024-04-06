package DynamicProgramming.CountSubstringsThatDifferByOneCharacter;
// 1638. Count Substrings That Differ by One Character
// https://leetcode.com/problems/count-substrings-that-differ-by-one-character/

class Solution {

    public int countSubstrings(String s, String t) {
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();

        int answer = 0;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < t.length(); ++j) {
                if (sarr[i] != tarr[j]) {
                    int pI = i - 1;
                    int pJ = j - 1;
                    int countLeft = 1;
                    while (0 <= pI && pI < s.length() && 0 <= pJ && pJ < t.length()) {
                        if (sarr[pI] == tarr[pJ]) {
                            countLeft++;
                        } else {
                            break;
                        }
                        pI--;
                        pJ--;
                    }

                    pI = i + 1;
                    pJ = j + 1;
                    int countRight = 1;
                    while (0 <= pI && pI < s.length() && 0 <= pJ && pJ < t.length()) {
                        if (sarr[pI] == tarr[pJ]) {
                            countRight++;
                        } else {
                            break;
                        }
                        pI++;
                        pJ++;
                    }

                    answer += (countLeft * countRight);
                }
            }
        }

        return answer;
    }
}


class Solution1 {
    public int countSubstrings(String s, String t) {
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();

        int[][] mat = new int[s.length() + 2][t.length() + 2];


        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < t.length(); ++j) {
                if (sarr[i] == tarr[j]) {
                    mat[i + 1][j + 1] = mat[i][j] + 1;
                }
            }
        }

        for (int i = s.length() - 2; i >= 0; --i) {
            for (int j = t.length() - 2; j >= 0; --j) {
                if (sarr[i] == tarr[j] && mat[i + 2][j + 2] > mat[i + 1][j + 1]) {
                    mat[i + 1][j + 1] = mat[i + 2][j + 2];
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < t.length(); ++j) {
                if (sarr[i] != tarr[j]) {
                    answer += (mat[i][j] + 1) * (mat[i + 2][j + 2] + 1);
                }
            }
        }

        return answer;
    }
}


public class CountSubstringsThatDifferByOneCharacter {

    public static void main(String[] args) {

        Solution1 sol = new Solution1();

        System.out.println(sol.countSubstrings("aba", "baba") == 6);
        System.out.println(sol.countSubstrings("ab", "bb") == 3);
        System.out.println(sol.countSubstrings("abbab", "bbbbb") == 33);

    }

}
