package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/17144">BOJ17144 미세먼지 안녕!</a>
 * */
public class BOJ17144 {

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int R, C, T;
    private static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];
        int bottom = 0, top = 0;
        total = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp > 0) {
                    map[i][j] = temp;
                    total += temp;
                } else if (temp == -1) {
                    map[i][j] = temp;
                    if (top == 0) {
                        top = i;
                    } else {
                        bottom = i;
                    }
                } else {
                    map[i][j] = 0;
                }
            }
        }
        while (T-- > 0) {
            map = spreadDust(map, top, bottom);
            cleanAir(map, top, bottom);
        }
        System.out.println(total);
    }

    private static int[][] spreadDust(int[][] map, int top, int bottom) {
        int[][] newMap = new int[R][C];
        newMap[top][0] = -1;
        newMap[bottom][0] = -1;
        for (int i = 0; i < R; i++) {
            int[] row = map[i];
            for (int j = 0; j < C; j++) {
                if (row[j] == -1) {
                    continue;
                }
                int dust = row[j];
                int spread = dust / 5;
                if (spread != 0) {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                            if (map[nx][ny] != -1) {
                                newMap[nx][ny] += spread;
                                count++;
                            }
                        }
                    }
                    newMap[i][j] += dust - (spread * count);
                } else {
                    newMap[i][j] += dust;
                }
            }
        }
        return newMap;
    }

    private static void cleanAir(int[][] map, int top, int bottom) {
        int prev = 0;
        //위쪽 공기순환
        for (int i = 0; i < C; i++) {
            if (map[top][i] != -1) {
                int temp = map[top][i];
                map[top][i] = prev;
                prev = temp;
            }
        }
        for (int i = top - 1; i >= 0; i--) {
            if (map[i][C - 1] != -1) {
                int temp = map[i][C - 1];
                map[i][C - 1] = prev;
                prev = temp;
            }
        }
        for (int i = C - 2; i >= 0; i--) {
            if (map[0][i] != -1) {
                int temp = map[0][i];
                map[0][i] = prev;
                prev = temp;
            }
        }
        for (int i = 1; i < top; i++) {
            if (map[i][0] != -1) {
                int temp = map[i][0];
                map[i][0] = prev;
                prev = temp;
            }
        }
        total -= prev;
        //아래쪽 공기 순환
        prev = 0;

        for (int i = 0; i < C; i++) {
            if (map[bottom][i] != -1) {
                int temp = map[bottom][i];
                map[bottom][i] = prev;
                prev = temp;
            }
        }

        for (int i = bottom + 1; i < R; i++) {
            if (map[i][C - 1] != -1) {
                int temp = map[i][C - 1];
                map[i][C - 1] = prev;
                prev = temp;
            }
        }

        for (int i = C - 2; i >= 0; i--) {
            if (map[R - 1][i] != -1) {
                int temp = map[R - 1][i];
                map[R - 1][i] = prev;
                prev = temp;
            }
        }

        for (int i = R - 2; i >= bottom; i--) {
            if (map[i][0] != -1) {
                int temp = map[i][0];
                map[i][0] = prev;
                prev = temp;
            }
        }
        total -= prev;
    }
}
