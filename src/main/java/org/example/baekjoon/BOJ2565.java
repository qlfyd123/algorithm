package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2565">백준 2565번: 전깃줄</a>
 * */
public class BOJ2565 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<int[]> lines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new int[]{start, end});
        }

        lines.sort(Comparator.comparingInt(a -> a[0]));

        int[] dp = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < lines.size(); i++) {
            int[] line = lines.get(i);
            int start = line[0];
            int end = line[1];
            for (int j = i + 1; j < lines.size(); j++) {
                int[] nextLine = lines.get(j);
                int nextStart = nextLine[0];
                int nextEnd = nextLine[1];

                if (nextStart > start && nextEnd > end) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);
    }
}
