package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2096">BOJ2096 내려가기</a>
 * */
public class BOJ2096 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] minDp = new int[3];
        int[] maxDp = new int[3];

        int[] minDpPrev = new int[3];
        int[] maxDpPrev = new int[3];

        Arrays.fill(minDp, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            if (i != 0) {
                for (int j = 0; j < 3; j++) {
                    minDpPrev[j] = minDp[j];
                    maxDpPrev[j] = maxDp[j];
                }
            }
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    minDp[j] = num;
                    maxDp[j] = num;
                } else {
                    boolean isFirst = true;
                    for (int k = -1; k <= 1; k++) {
                        if (j + k >= 0 && j + k < 3) {
                            if (isFirst) {
                                minDp[j] = minDpPrev[j + k] + num;
                                maxDp[j] = maxDpPrev[j + k] + num;
                                isFirst = false;
                            } else {
                                minDp[j] = Math.min(minDp[j], minDpPrev[j + k] + num);
                                maxDp[j] = Math.max(maxDp[j], maxDpPrev[j + k] + num);
                            }
                        }
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, minDp[i]);
            max = Math.max(max, maxDp[i]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(max).append(" ").append(min);
        System.out.println(sb);
    }
}
