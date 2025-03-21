package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1019 {

    private static long[] result;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        br.close();
        result = new long[10];
        int start = 1;
        int end = N;
        int digit = 1;
        while (start <= end) {
            while (start < 10 && start <= end) {
                int temp = start;
                while (temp > 0) {
                    result[temp % 10] += digit;
                    temp /= 10;
                }
                start++;
            }
            if (start > end) {
                break;
            }
            while (end % 10 != 9 && start <= end) {
                int temp = end;
                while (temp > 0) {
                    result[temp % 10]+=digit;
                    temp /= 10;
                }
                end--;
            }
            start /= 10;
            end /= 10;
            for (int i = 0; i < 10; i++) {
                result[i] += (long) (end - start + 1) * digit;
            }
            digit *= 10;
        }
        StringBuilder sb = new StringBuilder();
        for (long i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
