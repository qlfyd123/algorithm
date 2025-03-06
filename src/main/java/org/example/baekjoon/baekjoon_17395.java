package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/17395
 * */
public class baekjoon_17395 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long log10x = (int) (Math.log10(x) / Math.log10(2));

        Queue<Long> factor = new PriorityQueue<>();
        long limit = (long) Math.pow(2, log10x);
        long temp = x;
        while (temp > 0) {
            if (temp >= limit) {
                temp -= limit;
                factor.add(limit);
            }
            limit /= 2;
        }

        if (factor.size() < N) {
            System.out.println(-1);
            return;
        }
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < N; i++) {
            temp = factor.poll();
            while (factor.size() > N - 1) {
                temp += factor.poll();
            }
            x -= temp;
            sj.add(String.valueOf(x));
        }
        System.out.println(sj);
    }
}