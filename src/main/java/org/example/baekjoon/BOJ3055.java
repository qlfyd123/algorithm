package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ3055 {

    private static int R, C;
    private static String[][] map;
    // 인덱스0이 0일 경우 물, 1일경우 비버
    private static Queue<int[]> queue;

    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new String[R][C];
        Set<int[]> water = new HashSet<>();
        int[] beaver = new int[3];
        for (int i = 0; i < R; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                map[i][j] = input[j];
                if (map[i][j].equals("*")) {
                    water.add(new int[]{0, i, j});
                } else if (map[i][j].equals("S")) {
                    beaver = new int[]{1, i, j, 0};
                }
            }
        }
        br.close();

        queue = new LinkedList<>();
        queue.addAll(water);
        queue.add(beaver);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int type = current[0];
            int r = current[1];
            int c = current[2];
            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    continue;
                }
                if (type == 0) {
                    if (map[nr][nc].equals(".")) {
                        map[nr][nc] = "*";
                        queue.add(new int[]{0, nr, nc});
                    }
                } else {
                    int count = current[3];
                    if (map[nr][nc].equals("D")) {
                        sb.append(count + 1);
                        System.out.println(sb);
                        return;
                    } else if (map[nr][nc].equals(".")) {
                        map[nr][nc] = "S";
                        queue.add(new int[]{1, nr, nc, count + 1});
                    }
                }
            }
        }
        if (sb.isEmpty()) {
            System.out.println("KAKTUS");
        }
    }
}
