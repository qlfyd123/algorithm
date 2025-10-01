package org.example.baekjoon.BOJ15486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] consult = new int[N + 1][2];
        for (int i = 1; i < N+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            //index 0-> 소요 시간 1->가격
            consult[i][0] = Integer.parseInt(st.nextToken());
            consult[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            int[] currentTimeConsult = consult[i];
            int cost = currentTimeConsult[1];
            int period = currentTimeConsult[0];

            dp[i] = Math.max(dp[i - 1], dp[i]);
            if (i - 1 + period <= N) {
                dp[i - 1 + period] = Math.max(dp[i - 1 + period], cost + dp[i - 1]);
            }
        }

        int maxBenefit = 0;
        for (int i = 1; i < N + 1; i++) {
            maxBenefit = Math.max(maxBenefit, dp[i]);
        }

        System.out.println(maxBenefit);
    }
}
