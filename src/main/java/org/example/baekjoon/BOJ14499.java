package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/14499">14499. 주사위 굴리기</a>
 * */
public class BOJ14499 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        Dice dice = new Dice(x, y);
        StringBuilder sb = new StringBuilder();
        while (k-- > 0) {
            int operation = Integer.parseInt(st.nextToken());
            dice.move(operation, map, sb);
        }
        System.out.println(sb);
    }

    private static class Dice {

        int x, y;

        int top, bottom, north, south, east, west;

        public Dice(int x, int y) {
            top = 0;
            bottom = 0;
            north = 0;
            south = 0;
            east = 0;
            west = 0;
            this.x = x;
            this.y = y;
        }

        public void move(int operation, int[][] map, StringBuilder sb) {

            switch (operation) {
                case 1:
                    if (check(map, x, y + 1)) {
                        moveEast();
                    } else {
                        return;
                    }
                    break;
                case 2:
                    if (check(map, x, y - 1)) {
                        moveWest();
                    } else {
                        return;
                    }
                    break;
                case 3:
                    if (check(map, x - 1, y)) {
                        moveNorth();
                    } else {
                        return;
                    }
                    break;
                case 4:
                    if (check(map, x + 1, y)) {
                        moveSouth();
                    } else {
                        return;
                    }
                    break;
            }
            copy(map);
            sb.append(top).append("\n");
        }

        private boolean check(int[][] map, int x, int y) {
            return x >= 0 && y >= 0 && x < map.length && y < map[0].length;
        }

        private void copy(int[][] map) {
            int value = map[x][y];
            if (value == 0) {
                map[x][y] = bottom;
            } else {
                bottom = map[x][y];
                map[x][y] = 0;
            }
        }

        private void moveEast() {
            y++;
            int temp = east;
            east = top;
            top = west;
            west = bottom;
            bottom = temp;
        }

        private void moveWest() {
            y--;
            int temp = west;
            west = top;
            top = east;
            east = bottom;
            bottom = temp;
        }

        private void moveNorth() {
            x--;
            int temp = north;
            north = top;
            top = south;
            south = bottom;
            bottom = temp;
        }

        private void moveSouth() {
            x++;
            int temp = south;
            south = top;
            top = north;
            north = bottom;
            bottom = temp;
        }
    }
}
