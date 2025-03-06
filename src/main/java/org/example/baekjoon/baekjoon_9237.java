package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_9237 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] growth = new int[N];
        for (int i = 0; i < N; i++) {
            growth[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(growth);

        int max = 0;
        for (int i = 0; i < growth.length; i++) {
            int temp = growth.length - i + growth[i] + 1;
            if (temp > max) {
                max = temp;
            }
        }

        System.out.println(max);
    }
}
