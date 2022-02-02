package Math.CountPrimes;
// 204. Count Primes
// https://leetcode.com/problems/count-primes/


import java.util.Arrays;

// O(n*logn) 에라토스테네스의 체
class Solution {
    public int countPrimes(int n) {
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);

        for (int i = 2; i < (int) Math.sqrt(n) + 1; ++i) {
            if (primes[i] == true) {
                for (int j = i + i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }

        // System.out.println(Arrays.toString(primes));

        int count = 0;
        // 0과 1은 소수가 아니므로 2부터 카운트.
        for (int i = 2; i < n; ++i) {
            if (primes[i] == true) {
                ++count;
            }
        }

        return count;
    }
}


public class CountPrimes {
    public static void main(String[] args) {
        testSol(10, 4);
        testSol(0, 0);
        testSol(1, 0);
        testSol(2, 0);
        testSol(3, 1);
        testSol(4, 2);
    }

    static void testSol(int input, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.countPrimes(input);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }

}
