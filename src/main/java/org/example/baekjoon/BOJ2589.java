package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2589">백준 2589번: 보물섬</a>
 * */
public class BOJ2589 {

    private final static int[] dx = {0, 0, -1, 1};
    private final static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[][] map = new String[N][K];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().split("");
        }

        int maxDistance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                if (map[i][j].equals("L")) {
                    Queue<int[]> queue = new LinkedList<>();
                    int[][] visited = new int[N][K];
                    queue.add(new int[]{i, j});
                    visited[i][j] = 1;
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if (nx < 0 || nx >= N || ny < 0 || ny >= K || visited[nx][ny] != 0 || map[nx][ny].equals("W")) {
                                continue;
                            }

                            visited[nx][ny] = visited[x][y] + 1;
                            queue.add(new int[]{nx, ny});
                            maxDistance = Math.max(maxDistance, visited[nx][ny]);
                        }
                    }
                }
            }
        }
        System.out.println(maxDistance - 1);
    }
}
