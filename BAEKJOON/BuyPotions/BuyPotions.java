package CodeTEST.BAEKJOON.BuyPotions;
// 물약 구매
// https://www.acmicpc.net/problem/24954

import java.io.*;
import java.util.*;

class Solution {

    List<Map<Integer, Integer>> discountList;
    boolean[] visited;
    int N;
    int answer = 10001;

    public void solution() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        // System.out.println(N);

        visited = new boolean[N];

        // List<Integer> c = Arrays.stream(in.readLine().split(" ")).map(Integer::parseInt).collect(
        // Collectors.toList());

        String[] cString = in.readLine().split(" ");
        int[] coin = Arrays.stream(cString).mapToInt(Integer::parseInt).toArray();

        // System.out.println(Arrays.toString(coin));

        discountList = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            discountList.add(new HashMap<>());
            int n = Integer.parseInt(in.readLine());
            for (int j = 0; j < n; ++j) {
                String[] p = in.readLine().split(" ");
                int[] potion = Arrays.stream(p).mapToInt(Integer::parseInt).toArray();
                discountList.get(i).put(potion[0] - 1, potion[1]);
            }
        }

        // for (Map<Integer, Integer> dis : discountList) {
        // System.out.println(dis);
        // }

        dfs(coin, N, 0);

        System.out.println(answer);
    }

    private void dfs(int[] coin, int depth, int cost) {
        if (depth == 0) {
            // System.out.println("answer " + cost);
            if (answer > cost) {
                answer = cost;
            }
            return;
        }

        for (int i = 0; i < N; ++i) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;

            int tmpCost = cost;
            if (coin[i] <= 0) {
                tmpCost += 1;
            } else {
                tmpCost += coin[i];
            }
            // System.out.print(i + ", " + cost + ", ");

            int[] tempCoin = Arrays.copyOf(coin, N);
            // System.out.println(Arrays.toString(tempCoin));
            Map<Integer, Integer> disMap = discountList.get(i);
            for (Map.Entry<Integer, Integer> dis : disMap.entrySet()) {
                tempCoin[dis.getKey()] -= dis.getValue();
            }

            dfs(tempCoin, depth - 1, tmpCost);
            visited[i] = false;
        }
    }
}


public class BuyPotions {
    public static void main(String[] args) throws Exception {
        String input = "4\n" + "10 15 20 25\n" + "2\n" + "3 10\n" + "2 20\n" + "0\n" + "1\n"
                + "4 10\n" + "1\n" + "1 10";
        // OutputStream out = new ByteArrayOutputStream();
        // System.setOut(new PrintStream(out));
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Solution sol = new Solution();
        sol.solution();
    }
}
