package org.example.baekjoon.BOJ3079;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] time = new int[N];
        for (int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(br.readLine());
        }
        long left = 0;
        long right = Long.MAX_VALUE - 1;
        while (left < right) {
            long mid = (left + right) / 2;
            long maxProcess = 0;

            for (int j : time) {
                maxProcess += (mid / j);
                if (maxProcess >= M) {
                    right = mid;
                    break;
                }
            }

            if (maxProcess >= M) {
                right = mid;
            } else if (maxProcess < M) {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }


}
