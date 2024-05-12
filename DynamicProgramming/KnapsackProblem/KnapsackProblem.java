package DynamicProgramming.KnapsackProblem;
// 12865. 평범한 배낭 성공
// https://www.acmicpc.net/problem/12865

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KnapsackProblem {

    public static class Item {
        final int weight;
        final int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }
    }


    /*
    4 7
    6 13
    4 8
    3 6
    5 12
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N, K;
        N = sc.nextInt();
        K = sc.nextInt();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            int weight = sc.nextInt();
            int value = sc.nextInt();
            items.add(new Item(weight, value));
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i < N + 1; ++i) {
            for (int j = 1; j < K + 1; ++j) {
                Item item = items.get(i - 1);

                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (j - item.getWeight() >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - item.getWeight()] + item.getValue());
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
