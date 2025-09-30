package org.example.baekjoon.BOJ2615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAP_SIZE = 19;
    private static final int BLACK = 1;
    private static final int WHITE = 2;


    private static final int[][] directions = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[MAP_SIZE][MAP_SIZE];
        StringTokenizer st;
        for (int i = 0; i < MAP_SIZE; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < MAP_SIZE; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == BLACK) {
                    map[i][j] = BLACK;
                } else if (temp == WHITE) {
                    map[i][j] = WHITE;
                }
            }
        }

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                int color = map[i][j];
                for (int[] direction : directions) {
                    if ((color == BLACK || color == WHITE)) {
                        int prevColor;
                        if (inRange(i - direction[0], j - direction[1])) {
                            prevColor = map[i - direction[0]][j - direction[1]];
                            if (color == prevColor) {
                                continue;
                            }
                        }
                        int r = i;
                        int c = j;
                        int count = 0;
                        while (inRange(r, c) && map[r][c] == color) {
                            count++;
                            r += direction[0];
                            c += direction[1];
                        }
                        if (count == 5) {
                            System.out.println(color);
                            System.out.println((i + 1) + " " + (j + 1));
                            return;
                        }
                    }
                }
            }
        }

        System.out.println(0);

    }

    private static boolean inRange(int r, int c) {
        return (r >= 0 && r < MAP_SIZE && c >= 0 && c < MAP_SIZE);
    }

}
