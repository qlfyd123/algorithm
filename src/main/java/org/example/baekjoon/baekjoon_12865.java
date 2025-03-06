package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/12865"/>백준 12865</a>
 */
public class baekjoon_12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] bag = new int[N + 1][K + 1];
        for (int j = 1; j < N + 1; j++) {
            st = new StringTokenizer(br.readLine(), " ");
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            int[] dp = bag[j];
            for (int i = 0; i < K + 1; i++) {
                if (i < weight) dp[i] = bag[j - 1][i];
                else dp[i] = Math.max(bag[j - 1][i], bag[j - 1][i - weight] + cost);
            }
        }
        System.out.println(bag[N][K]);
    }
}