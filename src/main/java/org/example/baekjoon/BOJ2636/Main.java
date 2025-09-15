package org.example.baekjoon.BOJ2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * <a {@link <a href="https://www.acmicpc.net/problem/2636">boj 2636</a>}
     *
     */

    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r, c;
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[][] map = new int[r][c];
        int remainCheese = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    remainCheese++;
                }
            }
        }

        int prevCheese = remainCheese;
        int cycle = 0;

        while (remainCheese > 0) {
            calcWall(map, 0, 0, new boolean[r][c]);

            remainCheese = meltCheese(map, remainCheese);
            cycle++;
            if (remainCheese == 0) {
                break;
            } else {
                prevCheese = remainCheese;
            }
        }

        System.out.println(cycle);
        System.out.println(prevCheese);
    }

    private static void calcWall(int[][] map, int r, int c, boolean[][] visited) {
        if (r < 0 || c < 0 || r >= map.length || c >= map[0].length) return;
        if (visited[r][c]) return;
        if (map[r][c] == 0 || map[r][c] == -1) {
            map[r][c] = -1;
            visited[r][c] = true;
            for (int i = 0; i < 4; i++) {
                calcWall(map, r + dx[i], c + dy[i], visited);
            }
        }
    }

    private static int meltCheese(int[][] map, int remainCheese) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int r = i + dx[k];
                        int c = j + dy[k];
                        if (map[r][c] == -1) {
                            map[i][j] = 0;
                            remainCheese--;
                            break;
                        }
                    }
                }
            }
        }
        return remainCheese;
    }
}
