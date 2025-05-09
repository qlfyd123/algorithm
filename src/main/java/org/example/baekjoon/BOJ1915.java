package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/1915">BOJ 1915 가장 큰 정사각형</a>
 * */
public class BOJ1915 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[][] map = new int[r + 1][c + 1];
        for (int i = 1; i < r + 1; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                map[i][j + 1] = Integer.parseInt(input[j]);
            }
        }
        int max = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = dp(map, i, j);
                    if (max < map[i][j]) {
                        max = map[i][j];
                    }
                }
            }
        }
        System.out.println(max * max);
    }

    private static int dp(int[][] map, int i, int j) {
        return Math.min(Math.min(map[i - 1][j], map[i][j - 1]), map[i - 1][j - 1]) + 1;
    }
}
