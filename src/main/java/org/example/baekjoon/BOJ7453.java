package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/7453">합이 0인 네 정수</a>
 * */
public class BOJ7453 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arrA, arrB, arrC, arrD;
        arrA = new int[n];
        arrB = new int[n];
        arrC = new int[n];
        arrD = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arrA[i] = Integer.parseInt(st.nextToken());
            arrB[i] = Integer.parseInt(st.nextToken());
            arrC[i] = Integer.parseInt(st.nextToken());
            arrD[i] = Integer.parseInt(st.nextToken());
        }
        int[] sumAB = new int[n * n];
        int[] sumCD = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumAB[i * n + j] = (arrA[i] + arrB[j]);
                sumCD[i * n + j] = (arrC[i] + arrD[j]);
            }
        }
        Arrays.sort(sumCD);
        Arrays.sort(sumAB);
        long count = 0;
        int left = 0, right = n * n - 1;
        while (left < n * n && right >= 0) {
            long sum = sumAB[left] + sumCD[right];
            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                long countAB = 0;
                int sumLeft = sumAB[left];
                while (left < n * n && sumAB[left] == sumLeft) {
                    left++;
                    countAB++;
                }
                long countCD = 0;
                int sumRight = sumCD[right];
                while (right >= 0 && sumRight == sumCD[right]) {
                    right--;
                    countCD++;
                }
                count += countAB * countCD;
            }

        }
        System.out.println(count);
    }
}
