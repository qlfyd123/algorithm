package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * <a href="https://www.acmicpc.net/problem/14002">14002. 가장 긴 증가하는 부분 수열 4</a>
 * */
public class BOJ14002 {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[N];
        int[] prev = new int[N];
        int lisLen = 0, lisEnd = 0;
        Arrays.fill(prev, -1);
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > lisLen) {
                lisLen = dp[i];
                lisEnd = i;
            }
        }

        List<Integer> lis = new ArrayList<>();
        for (int i = lisEnd; i != -1; i = prev[i]) {
            lis.add(arr[i]);
        }
        System.out.println(lisLen);
        StringBuilder sb = new StringBuilder();
        for (int j = lis.size() - 1; j >= 0; j--) {
            int i = lis.get(j);
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
