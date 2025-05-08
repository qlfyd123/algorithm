package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2146">BOJ 2146 다리 만들기</a>
 * */
public class BOJ2146 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int N;
    private static int[][] map;
    private static int[][] label;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        label = new int[N][N];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int labelNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && label[i][j] == 0) {
                    label[i][j] = labelNum;
                    dfs(i, j, labelNum++);
                }
            }
        }

        boolean flag = false;
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if (map[nr][nc] == 0 && !flag) {
                        label[nr][nc] = label[r][c];
                        map[nr][nc] = map[r][c] + 1;
                        queue.add(new int[]{nr, nc});
                    } else if (label[nr][nc] != 0 && label[nr][nc] != label[r][c]) {
                        min = Math.min(map[r][c] + map[nr][nc] - 2, min);
                        flag = true;
                    }
                }
            }
        }
        System.out.println(min);
    }

    private static void dfs(int r, int c, int labelNum) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (map[nr][nc] == 1 && label[nr][nc] == 0) {
                    label[nr][nc] = labelNum;
                    dfs(nr, nc, labelNum);
                }
            }
        }
    }
}
