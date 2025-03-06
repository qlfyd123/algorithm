package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/1806">백준1806</a>
 */
public class baekjoon_1806 {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int[] segment = new int[N];
        for (int i = 0; i < N; i++) {
            segment[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0, right = 0;
        int sum = 0;
        int minCount = Integer.MAX_VALUE;
        while (left < N & right < N) {
            sum += segment[right];
            while (sum >= S) {
                minCount = Math.min(minCount, right - left + 1);
                sum -= segment[left];
                left++;
            }
            right++;
        }
        System.out.println(minCount == Integer.MAX_VALUE ? 0 : minCount);
    }
}