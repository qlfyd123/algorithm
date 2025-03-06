package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
* https://www.acmicpc.net/problem/24023
* */
public class baekjoon_24023 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int result = 0;
        int start = 1;
        int end = 0;
        for (int i = 1; i < N + 1; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp > K) {
                start = i + 1;
                result = 0;
                continue;
            }
            boolean flag = (K | temp) > K;
            if (!flag) {
                result = result | temp;
                if (result == K) {
                    end = i;
                    break;
                }
            } else {
                start = i + 1;
                result = 0;
            }
        }
        if (end == 0)
            System.out.println(-1);
        else
            System.out.println(start + " " + end);
    }
}
