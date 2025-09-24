package org.example.baekjoon.BOJ2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        int[][] map = new int[N][M];
        int remainCheese = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) remainCheese++;
            }
        }

        int cycle = 0;
        while (remainCheese > 0) {

            markAirPocket(map, 0, 0, new boolean[N][M]);

            remainCheese = meltCheese(map, remainCheese);
            cycle++;
        }

        System.out.println(cycle);
    }

    private static void markAirPocket(int[][] map, int r, int c, boolean[][] visited) {
        if (map[r][c] == 1) return;

        map[r][c] = -1;
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (isInRange(nx, ny, map.length, map[0].length) && !visited[nx][ny]) {
                markAirPocket(map, nx, ny, visited);
            }
        }
    }

    private static boolean isInRange(int r, int c, int N, int M) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    private static int meltCheese(int[][] map, int remainCheese) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1 && isHaveMeltingCondition(map, i, j)) {
                    map[i][j] = 0;
                    remainCheese--;
                }
            }
        }

        return remainCheese;
    }

    private static boolean isHaveMeltingCondition(int[][] map, int r, int c) {
        int airPocketCount = 0;
        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (isInRange(nx, ny, map.length, map[0].length) && map[nx][ny] == -1) {
                airPocketCount++;
            }
        }

        return airPocketCount >= 2;
    }
}
