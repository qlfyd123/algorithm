package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2225">백준 2225번: 합분해</a>
 * */
public class BOJ2225 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            dp[i][1] = 1;
        }
        for (int i = 0; i <= K; i++) {
            dp[0][i] = 1;
        }

        for (int j = 2; j <= K; j++) {
            for (int i = 1; i <= N; i++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (dp[i][j] >= 1000000000) {
                    dp[i][j] -= 1000000000;
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
