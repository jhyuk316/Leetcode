package DynamicProgramming.ChampagneTower;
// 799. Champagne Tower
// https://leetcode.com/problems/champagne-tower/

import java.util.Arrays;

// O(N^2)
class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] glass = new double[query_row + 1][query_row + 1];

        glass[0][0] = poured;
        for (int i = 0; i < query_row; ++i) {
            for (int j = 0; j < query_row; ++j) {
                if (glass[i][j] <= 1) {
                    continue;
                }
                double tick = (glass[i][j] - 1) / 2;
                glass[i + 1][j] += tick;
                glass[i + 1][j + 1] += tick;
            }
        }

        for (int i = 0; i < query_row + 1; ++i) {
            // System.out.println(Arrays.toString(glass[i]));
        }

        return glass[query_row][query_glass] > 1 ? 1 : glass[query_row][query_glass];
    }
}


public class ChampagneTower {
    public static void main(String[] args) {
        testSol(1, 1, 1, 0);
        testSol(2, 1, 1, 0.5);
        testSol(3, 1, 1, 1.0);
        testSol(4, 2, 0, 0.25);
        testSol(4, 2, 1, 0.5);
        testSol(5, 2, 0, 0.5);
        testSol(5, 2, 1, 1.0);

        testSol(100000009, 33, 17, 1);
    }

    static void testSol(int input1, int input2, int input3, double output) {
        Solution sol = new Solution();
        // todo : solution match
        double res = sol.champagneTower(input1, input2, input3);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
