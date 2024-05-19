package Math.TotalNumberOfSquaresInGrid;

// Total Number of Squares in a nxn grid
// https://www.youtube.com/watch?v=VsBg2pioaoc

import java.util.Arrays;

// 실패한 솔루션

class Solution {

    int[] sq;
    int[] dia;
    int[] tilt;

    public int getNumberOfSquares(int n) {
        sq = new int[n + 1];
        dia = new int[n + 1];
        tilt = new int[n + 1];

        int result = getSquares(n) + getDia(n) + getTits(n);

        System.out.println("Arrays.toString(sq) = " + Arrays.toString(sq));
        System.out.println("Arrays.toString(dia) = " + Arrays.toString(dia));
        System.out.println("Arrays.toString(tits) = " + Arrays.toString(tilt));

        return result;
    }


    private int getSquares(int n) {
        if (n < 2) {
            return 0;
        }

        if (sq[n] > 0) {
            return sq[n];
        }

        sq[n] = getSquares(n - 1) + (n - 1) * (n - 1);
        return sq[n];
    }

    /*
    3 = 1
    4 = 2*2*1
    5 = 3*3*1 + 1
    6 = 4*4*1 + 2*2*1
    7 = 5*5*1 + 3*3*1 + 1
    8 = 6*6*1 + 4*4*1 + 2*2*1
    */
    private int getDia(int n) {
        if (n <= 2) {
            return 0;
        } else if (n == 3) {
            dia[3] = 1;
        }

        if (dia[n] > 0) {
            return dia[n];
        }

        dia[n] = getDia(n - 2) + (n - 2) * (n - 2);
        return dia[n];
    }

    /*
    4 = 2
    5 = 2*2*2 + 2
    6 =
     */
    private int getTits(int n) {
        if (n < 4) {
            return 0;
        }

        if (tilt[n] != 0) {
            return tilt[n];
        }

        tilt[n] = getTits(n - 1) + 2 * (n - 3) * (n - 3);
        return tilt[n];
    }

}

class SolutionRef {
    public int getNumberOfSquares(int n) {
        return (int) ((long) (Math.pow(n, 4) - Math.pow(n, 2)) / 12);
    }

}


public class TotalNumberOfSquaresInGrid {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("numberOfSquares = " + solution.getNumberOfSquares(3) + " " + 6);
        System.out.println("numberOfSquares = " + solution.getNumberOfSquares(4) + " " + 20);
        System.out.println("numberOfSquares = " + solution.getNumberOfSquares(5) + " " + 50);
        System.out.println("numberOfSquares = " + solution.getNumberOfSquares(6) + " " + 105);
        System.out.println("numberOfSquares = " + solution.getNumberOfSquares(7) + " " + 196);
        // System.out.println("numberOfSquares = " + solution.getNumberOfSquares(400) + " " + 2133320000);

        SolutionRef solutionRef = new SolutionRef();
        System.out.println("numberOfSquares1 = " + solutionRef.getNumberOfSquares(3));


    }

}
