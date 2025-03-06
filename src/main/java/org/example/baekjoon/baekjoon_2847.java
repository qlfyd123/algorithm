package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_2847 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] level = new int[N];

        for (int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(br.readLine());
        }

        int temp = level[N - 1];
        int count = 0;
        for (int i = N - 2; i > -1; i--) {
            int score = level[i];
            if (temp <= score) {
                level[i] -= (score - temp + 1);
                count += (score - temp + 1);
            }
            temp = level[i];
        }

        System.out.println(count);
    }
}
