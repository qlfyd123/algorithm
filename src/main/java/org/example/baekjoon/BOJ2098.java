package org.example.baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2098 {

    private static final int INF = Integer.MAX_VALUE;
    private static int N;
    private static int[][] city;
    private static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        city = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(input[j]);
            }
        }

        cost = new int[N][(int) Math.pow(2, N) + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[i], -1);
        }
        int result = dfs(0, 1);
        System.out.println(result);
    }

    private static int dfs(int start, int bit) {
        if (bit == (1 << N) - 1) {
            if (city[start][0] != 0) {
                return city[start][0];
            }
            return INF;
        }

        if (cost[start][bit] != -1) {
            return cost[start][bit];
        }

        cost[start][bit] = INF;
        for (int i = 0; i < N; i++) {
            int nextBit = bit | 1 << i;
            if (city[start][i] != 0 && (bit & 1 << i) == 0) {
                int temp = dfs(i, nextBit);
                if (temp != INF) {
                    cost[start][bit] = Math.min(cost[start][bit], temp + city[start][i]);
                }
            }
        }
        return cost[start][bit];
    }

}
