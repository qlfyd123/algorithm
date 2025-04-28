package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * BOJ1937 욕심쟁이 판다
 * */
public class BOJ1937 {

    private final static int[] dx = {1, 0, -1, 0};
    private final static int[] dy = {0, 1, 0, -1};

    private static int N;
    private static int[][] map;
    private static int[][] dp;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == 0) {
                    dfs(i, j);
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.println(max);
    }

    private static int dfs(int r, int c) {
        dp[r][c] = 1;
        int bamboo = map[r][c];
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (map[nr][nc] > bamboo) {
                    int temp;
                    if (dp[nr][nc] == 0) {
                        temp = dfs(nr, nc);
                    } else {
                        temp = dp[nr][nc];
                    }
                    dp[r][c] = Math.max(dp[r][c], temp + 1);
                }
            }
        }
        return dp[r][c];
    }
}
