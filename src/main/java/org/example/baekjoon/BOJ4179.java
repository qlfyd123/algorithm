package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ4179 {

    static int R, C;
    static char[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        R = Integer.parseInt(arr[0]);
        C = Integer.parseInt(arr[1]);
        map = new char[R][C];
        Queue<int[]> jQueue = new LinkedList<>();
        Queue<int[]> fQueue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    jQueue.add(new int[]{i, j});
                } else if (map[i][j] == 'F') {
                    fQueue.add(new int[]{i, j});
                }
            }
        }
        br.close();
        int count = 0;
        while (!jQueue.isEmpty()) {
            count++;
            Queue<int[]> nextFQueue = new LinkedList<>();
            Queue<int[]> nextJQueue = new LinkedList<>();
            while (!jQueue.isEmpty()) {
                int[] j = jQueue.poll();
                if (map[j[0]][j[1]] == 'F') {
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = j[0] + dx[i];
                    int ny = j[1] + dy[i];
                    if ((nx >= 0 && ny >= 0 && nx < R && ny < C) && map[nx][ny] == '.') {
                        nextJQueue.add(new int[]{nx, ny});
                        map[nx][ny] = 'J';
                    } else if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                        System.out.println(count);
                        return;
                    }
                }
            }
            while (!fQueue.isEmpty()) {
                int[] f = fQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = f[0] + dx[i];
                    int ny = f[1] + dy[i];
                    if ((nx >= 0 && ny >= 0 && nx < R && ny < C) && (map[nx][ny] == 'J' || map[nx][ny] == '.')) {
                        nextFQueue.add(new int[]{nx, ny});
                        map[nx][ny] = 'F';
                    }
                }
            }
            fQueue = nextFQueue;
            jQueue = nextJQueue;
        }
        System.out.println("IMPOSSIBLE");
    }
}
