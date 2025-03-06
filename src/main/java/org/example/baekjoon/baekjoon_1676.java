package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1676 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 1; i < N + 1; i++) {
            int temp = i;
            while (temp % 5 == 0) {
                count++;
                temp /= 5;
            }
        }

        System.out.println(count);
    }
}
