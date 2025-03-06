package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2293">BOJ2293 동전1</a>
 * */
public class BOJ2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] count = new int[k + 1];
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }
        br.close();
        count[0] = 1;
        for (int i = 0; i < n; i++) {
            int coin = coins[i];
            for (int j = 1; j <= k; j++) {
                if (j - coin >= 0) {
                    count[j] += count[j-coin];
                }
            }
        }
        System.out.println(count[k]);
    }
}