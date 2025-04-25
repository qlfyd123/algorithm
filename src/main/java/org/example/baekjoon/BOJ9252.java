package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href="https://www.acmicpc.net/problem/9252">BOJ9252 LCS2</a>
 * */
public class BOJ9252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        String[] input2 = br.readLine().split("");
        int[][] dp = new int[input2.length + 1][input.length + 1];
        for (int i = 1; i <= input2.length; i++) {
            String x = input2[i - 1];
            for (int j = 1; j <= input.length; j++) {
                String y = input[j - 1];
                if (x.equals(y)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = input2.length, j = input.length;

        while (i >= 1 && j >= 1) {
            if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else {
                sb.append(input2[i - 1]);
                i--;
                j--;
            }
        }
        System.out.println(dp[input2.length][input.length]);
        System.out.println(sb.reverse());
    }
}
