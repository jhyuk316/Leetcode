package CodeTEST.ShowMeTheCode20223.ShowMeTheDungeon;

import java.io.*;
import java.util.*;

class Solution {
    int[] A;
    int[] P;
    int N;
    int K;
    boolean[] visited;

    public void solution() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] NK = in.readLine().split(" ");
            N = Integer.parseInt(NK[0]);
            K = Integer.parseInt(NK[1]);

            A = new int[N];
            P = new int[N];

            String[] temp = in.readLine().split(" ");
            for (int i = 0; i < N; ++i) {
                A[i] = Integer.parseInt(temp[i]);
            }
            temp = in.readLine().split(" ");
            for (int i = 0; i < N; ++i) {
                P[i] = Integer.parseInt(temp[i]);
            }
            // System.out.println(Arrays.toString(A));
            // System.out.println(Arrays.toString(P));

            visited = new boolean[N];

            int res = 0;
            for (int i = 0; i < N; ++i) {
                res = Math.max(res, dfs(i, K, 0));
                System.out.println(dfs(i, K, 0));
            }
            System.out.println(res);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return;
    }

    private int dfs(int node, int k, int cost) {
        cost = cost + A[node];
        if (k < cost) {
            return 0;
        }

        // System.out.println(node + " " + cost);

        visited[node] = true;
        int res = 0;
        for (int i = 0; i < N; ++i) {
            if (!visited[i]) {
                res = Math.max(res, dfs(i, k - cost, cost));
            }
        }
        visited[node] = false;

        return res + P[node];
    }
}


public class ShowMeTheDungeon {
    public static void main(String[] args) {

        testSol("5 4\n5 3 1 2 4\n10 10 10 10 10", 20);
        testSol("5 5\n5 3 1 2 4\n10 10 10 10 10", 20);
        testSol("5 10\n5 3 1 2 4\n10 10 10 10 10", 30);
        testSol("5 100\n1 1 1 1 1\n10 10 10 10 10", 50);
        testSol("5 1\n2 2 2 2 2\n2 2 2 2 2", 0);
    }

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
