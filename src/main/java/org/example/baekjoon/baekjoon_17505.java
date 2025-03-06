package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
* https://www.acmicpc.net/problem/17505
* */
public class baekjoon_17505 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        double K = Double.parseDouble(st.nextToken());

        if (K > (double) N* (N - 1) / 2) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (K >= N - 1 && N > 0) {
            sb.append(N).append(" ");
            K -= N - 1;
            N--;
        }

        for (int i = 1; i <= N - 1; i++) {
            sb.append(i).append(" ");
            if (i == N - K - 1) {
                sb.append(N).append(" ");
            }
        }


        System.out.println(sb.toString().trim());

    }
}
