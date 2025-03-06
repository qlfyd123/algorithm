package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2110">BOJ2110 공유기 설치</a>
 * */
public class BOJ2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);
        int start = 1, end = houses[N - 1] - houses[0];
        while (start - end != 1) {
            int midValue = (start + end) / 2;
            if (getRouterNum(midValue, houses) >= C) {
                start = midValue + 1;
            } else {
                end = midValue-1;
            }
        }
        System.out.println(end);
    }

    public static int getRouterNum(int disBetween, int[] houses) {
        int routerCount = 1;
        int router = houses[0];
        for (int house : houses) {
            if (house - router >= disBetween) {
                routerCount++;
                router = house;
            }
        }
        return routerCount;
    }
}
