package org.example.baekjoon;

import java.io.*;
/**
 *<a href="https://www.acmicpc.net/problem/10026">BOJ10026</a>
 * */
public class BOJ10026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        br.close();
        boolean[][] visited = new boolean[N][N];
        int nonBlindGridCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    nonBlindGridCount++;
                    nonBlindSearch(i, j, visited, grid);
                }
            }
        }

        visited = new boolean[N][N];
        int blindGridCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    blindGridCount++;
                    BlindSearch(i, j, visited, grid);
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(nonBlindGridCount + " " + blindGridCount);
        bw.close();

    }

    /**
     *
     * 색맹이있는 사람이 보는 구역을 분할
     * @param i 현재 ROW
     * @param j 현재 COLUMN
     * @param visited 방문 배열
     * @param grid  구역을 나눌 그림
     */
    private static void BlindSearch(int i, int j, boolean[][] visited, char[][] grid) {
        visited[i][j] = true;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (inBound(visited.length, x, y)) {
                if (grid[i][j] == 'R' || grid[i][j] == 'G') {
                    if (!visited[x][y] & (grid[x][y] == 'R' || grid[x][y] == 'G')) {
                        BlindSearch(x, y, visited, grid);
                    }
                } else {
                    if (!visited[x][y] & grid[i][j] == grid[x][y]) {
                        BlindSearch(x, y, visited, grid);
                    }
                }
            }
        }
    }

    /**
     * 색약이 없는 사람이 보는 구역을 분할
     */
    private static void nonBlindSearch(int i, int j, boolean[][] visited, char[][] grid) {
        visited[i][j] = true;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (inBound(visited.length, x, y)) {
                if (!visited[x][y] & grid[i][j] == grid[x][y]) {
                    nonBlindSearch(x, y, visited, grid);
                }
            }
        }
    }

    /**
     * 현재 ROW와 COLUMN이 그림 안에 포안되는지 판단
     * */
    private static boolean inBound(int length, int x, int y) {
        if (x < 0 | x >= length) {
            return false;
        }
        return (y >= 0 & y < length);
    }

}
