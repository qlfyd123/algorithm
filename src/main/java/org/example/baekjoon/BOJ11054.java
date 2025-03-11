package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BOJ11054 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();
        int[] dp_increase = new int[n];
        int[] dp_decrease = new int[n];

        for (int i = 0; i < n; i++) {
            dp_increase[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp_increase[i] = Math.max(dp_increase[i], dp_increase[j] + 1);
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            dp_decrease[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    dp_decrease[i] = Math.max(dp_decrease[i], dp_decrease[j] + 1);
                }
            }
        }

        int max = IntStream.range(0, n).mapToObj(i -> dp_increase[i] + dp_decrease[i] - 1).max(Integer::compareTo).get();
        System.out.println(max);
    }
}
