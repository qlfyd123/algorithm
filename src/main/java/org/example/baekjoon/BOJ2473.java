package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2473">BOJ2473 세 용액</a>
 * */
public class BOJ2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long minDiff = Long.MAX_VALUE;
        long[] result = new long[3];
        for (int i = 0; i < N; i++) {
            long x = -arr[i];
            int left = i + 1, right = N - 1;
            while (left < right) {
                if (left == i) {
                    left++;
                } else if (right == i) {
                    right--;
                } else {
                    long sum = arr[left] + arr[right];
                    long diff = Math.abs(sum + arr[i]);
                    if (diff < minDiff) {
                        minDiff = diff;
                        result[0] = arr[left];
                        result[1] = arr[right];
                        result[2] = arr[i];
                    }
                    if (sum < x) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        Arrays.sort(result);
        StringBuilder sb = new StringBuilder();
        for (long num : result) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
