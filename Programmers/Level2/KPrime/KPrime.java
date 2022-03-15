package Programmers.Level2.KPrime;
// k진수에서 소수 개수 구하기
// https://programmers.co.kr/learn/courses/30/lessons/92335


import java.util.Arrays;

class Solution {
    public int solution(int n, int k) {
        StringBuilder kStr = new StringBuilder();
        while (n != 0) {
            kStr.append(n % k);
            n /= k;
        }
        kStr.reverse();
        // System.out.println(kStr);

        String[] numbers = kStr.toString().split("0");
        System.out.println(Arrays.toString(numbers));

        int answer = 0;
        for (String number : numbers) {
            if (!number.equals("") && isPrime(Long.parseLong(number))) {
                answer++;
            }
        }

        return answer;
    }

    // long이 아니면 런타임에러
    private boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }

        for (long i = 2; i < Math.sqrt(n) + 1; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}


public class KPrime {
    public static void main(String[] args) {
        testSol(437674, 3, 3);
        testSol(110011, 10, 2);
        testSol(4, 10, 0);
        testSol(3, 10, 1);
        testSol(5, 10, 1);
        testSol(6, 10, 0);
        testSol(101210, 10, 0);
        testSol(1000000, 10, 0);
        testSol(1000000, 3, 2);
        testSol(1000000, 5, 0);
    }

    static void testSol(int input1, int input2, int output) {
        Solution sol = new Solution();
        // todo : solution match
        int res = sol.solution(input1, input2);
        if (res == output) {
            System.out.println("O : " + res);
        } else {
            System.out.println("x : " + res + "	expect : " + output);
        }
    }
}
