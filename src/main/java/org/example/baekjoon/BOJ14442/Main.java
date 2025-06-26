package org.example.baekjoon.BOJ14442;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        final char ZERO = '0';

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            char[] chars = input.toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = chars[j] - ZERO;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, K});
        int[][][] dp = new int[K + 1][N][M];

        for (int i = 0; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                for (int l = 0; l < M; l++) {
                    dp[i][j][l] = Integer.MAX_VALUE;
                }
            }
        }
        dp[K][0][0] = 1;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int remainingK = current[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if (map[nx][ny] == 1 && remainingK > 0) {
                    if (dp[remainingK - 1][nx][ny] > dp[remainingK][x][y] + 1) {
                        dp[remainingK - 1][nx][ny] = dp[remainingK][x][y] + 1;
                        queue.add(new int[]{nx, ny, remainingK - 1});
                    }
                } else if (map[nx][ny] == 0) {
                    if (dp[remainingK][nx][ny] > dp[remainingK][x][y] + 1) {
                        dp[remainingK][nx][ny] = dp[remainingK][x][y] + 1;
                        queue.add(new int[]{nx, ny, remainingK});
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (dp[i][N - 1][M - 1] > 0) {
                min = Math.min(min, dp[i][N - 1][M - 1]);
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
}
