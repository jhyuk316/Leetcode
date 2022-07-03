package CodeTEST.ShowMeTheCode20223.SumOfNum8;


import java.io.*;
import java.util.*;

class Solution {
    public void solution() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            int N = Integer.parseInt(in.readLine());
            int[] A = new int[N + 1];
            int[] B = new int[N + 1];

            String[] nums = in.readLine().split(" ");
            for (int i = 1; i < N + 1; ++i) {
                A[i] = Integer.parseInt(nums[i - 1]);
            }
            nums = in.readLine().split(" ");
            for (int i = 1; i < N + 1; ++i) {
                B[i] = Integer.parseInt(nums[i - 1]);
            }
            // System.out.println(Arrays.toString(A));
            // System.out.println(Arrays.toString(B));

            for (int i = 1; i < N + 1; ++i) {
                A[i] += A[i - 1];
                B[i] += B[i - 1];
            }
            // System.out.println(Arrays.toString(A));
            // System.out.println(Arrays.toString(B));

            for (int i = 0; i < N + 1; ++i) {
                A[i] -= B[i];
            }
            System.out.println(Arrays.toString(A));

            Map<Integer, Integer> map = new HashMap<>();
            for (int a : A) {
                map.put(a, map.getOrDefault(a, 0) + 1);
            }
            // System.out.println(map);

            long res = 0;
            for (long count : map.values()) {
                if (count % 2 == 0) {
                    res += (count - 1) * (count >> 1);
                } else {
                    res += count * ((count - 1) >> 1);
                }
            }
            System.out.println(res);

        } catch (Exception e) {
            // TODO: handle exception
        }
        return;
    }
}


// bruteforce
class Solution1 {
    public void solution() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            int N = Integer.parseInt(in.readLine());
            int[] A = new int[N + 1];
            int[] B = new int[N + 1];

            String[] nums = in.readLine().split(" ");
            for (int i = 1; i < N + 1; ++i) {
                A[i] = Integer.parseInt(nums[i - 1]);
            }
            nums = in.readLine().split(" ");
            for (int i = 1; i < N + 1; ++i) {
                B[i] = Integer.parseInt(nums[i - 1]);
            }
            // System.out.println(Arrays.toString(A));
            // System.out.println(Arrays.toString(B));

            for (int i = 1; i < N + 1; ++i) {
                A[i] += A[i - 1];
                B[i] += B[i - 1];
            }
            System.out.println(Arrays.toString(A));
            System.out.println(Arrays.toString(B));

            int count = 0;
            for (int i = 0; i < N + 1; ++i) {
                for (int j = i + 1; j < N + 1; ++j) {
                    if (A[j] - A[i] == B[j] - B[i]) {
                        // System.out.println(i + " " + j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return;
    }
}


public class SumOfNum8 {
    public static void main(String args[]) {
        testSol("3\n1 2 3\n1 3 2", 3);
        testSol("3\n1 3 2\n1 2 3", 3);
        testSol("3\n3 1 2\n1 2 3", 1);
        testSol("3\n3 10 2\n10 2 3", 1);
        testSol("5\n1 2 3 4 5\n4 5 6 7 8", 0);
        testSol("6\n23 13 31 17 29 19\n23 13 31 17 29 19", 21);
        testSol("3\n1 2 1\n1 1 1", 2);
        // Solution sol = new Solution();
        // sol.solution();
    }

    // public static void main(String args[]) {
    // Solution sol = new Solution();
    // sol.solution();
    // }

    static void testSol(String inputString, int output) {
        System.out.println("testSol\n" + inputString + "\nexpect : " + output);
        // String inputString = Integer.toString(input); // 4
        // ByteArrayOutputStream out = new ByteArrayOutputStream();
        // System.setOut(new PrintStream(out));
        InputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        Solution sol = new Solution();
        sol.solution();
    }
}
