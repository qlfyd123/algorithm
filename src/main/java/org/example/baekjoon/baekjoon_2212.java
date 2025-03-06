package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        if (K >= N) {
            System.out.println(0);
            return;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] sensor = new int[N];

        for (int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensor);

        int[] distance = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            distance[i] = (sensor[i + 1] - sensor[i]);
        }

        int result = 0;
        Arrays.sort(distance);
        for (int i = 0; i < N - K; i++) {
            result += distance[i];
        }
        System.out.println(result);
    }


}
