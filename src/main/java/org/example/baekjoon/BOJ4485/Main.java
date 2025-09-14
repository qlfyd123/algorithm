package org.example.baekjoon.BOJ4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] dist = new int[n][n];
            Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> dist[o[0]][o[1]]));
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            dist[0][0] = map[0][0];
            queue.add(new int[]{0, 0});

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    if (inRange(nx, ny, n)) {
                        int newCost = dist[x][y] + map[nx][ny];
                        if (newCost < dist[nx][ny]) {
                            dist[nx][ny] = newCost;
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            System.out.println("Problem " + testCase++ + ": " + dist[n-1][n-1]);

        }
        br.close();
    }

    private static boolean inRange(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
