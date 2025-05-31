package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/2467">용액</a>
 */
public class BOJ2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = N - 1;

        long maxLeft = 0;
        long maxRight = 0;
        long maxSum = Long.MAX_VALUE;

        while (left < right) {
            long sum = arr[left] + arr[right];

            if (Math.abs(sum) < maxSum) {
                maxSum = Math.abs(sum);
                maxLeft = arr[left];
                maxRight = arr[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxLeft).append(" ").append(maxRight);
        System.out.println(sb);
    }
}
