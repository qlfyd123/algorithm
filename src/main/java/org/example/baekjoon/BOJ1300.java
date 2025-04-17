package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * <a href="https://www.acmicpc.net/problem/1300">1300. K번째 수</a>
 * */
public class BOJ1300 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N, k;
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        long left = 1, right = N * N;
        while (left < right) {
            long mid = (left + right) / 2;
            long count = 0;
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println((left + right) / 2);
    }
}
