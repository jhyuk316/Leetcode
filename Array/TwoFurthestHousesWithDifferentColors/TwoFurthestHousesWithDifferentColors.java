package Array.TwoFurthestHousesWithDifferentColors;
// 2078. Two Furthest Houses With Different Colors
// https://leetcode.com/problems/two-furthest-houses-with-different-colors/


// O(N)
class Solution {
    public int maxDistance(int[] colors) {
        int maxLength = 0;

        for (int i = colors.length - 1; i >= 0; --i) {
            if (colors[i] != colors[0]) {
                maxLength = Math.max(maxLength, i);
                break;
            }
        }

        for (int i = 0; i < colors.length; ++i) {
            if (colors[i] != colors[colors.length - 1]) {
                maxLength = Math.max(maxLength, colors.length - 1 - i);
                break;
            }
        }

        return maxLength;
    }
}


// O(N^2) brute-force
class Solution1 {
    public int maxDistance(int[] colors) {
        int maxLength = 0;

        for (int i = 0; i < colors.length; ++i) {
            maxLength = Math.max(maxLength, maxDistance(colors, i));
        }

        return maxLength;
    }

    private int maxDistance(int[] colors, int index) {
        int i = 0;
        while (i < index && colors[i] == colors[index]) {
            i++;
        }

        return index - i;
    }
}


public class TwoFurthestHousesWithDifferentColors {
    public static void main(String[] args) {
        testSol(new int[] {1, 1, 1, 1, 1, 1, 1}, 0);
        testSol(new int[] {1, 1, 1, 6, 1, 1, 1}, 3);
        testSol(new int[] {1, 1, 1, 6, 1, 1, 1, 1}, 4);
        testSol(new int[] {1, 8, 3, 8, 3}, 4);
        testSol(new int[] {0, 1}, 1);
    }

    static void testSol(int[] input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.maxDistance(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
