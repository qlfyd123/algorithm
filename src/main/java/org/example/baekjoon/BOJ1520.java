package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * @see <a href="https://www.acmicpc.net/problem/1520">BOJ1520 내리막길</a>
* */
public class BOJ1520 {
    static int[][] map;
    static int[][] routeCount;
    static int endRow, endColumn;
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        endRow = Integer.parseInt(st.nextToken());
        endColumn = Integer.parseInt(st.nextToken());
        map = new int[endRow][endColumn];
        routeCount = new int[endRow][endColumn];
        for (int i = 0; i < endRow; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(routeCount[i], -1);
            for (int j = 0; j < endColumn; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        downHill(0, 0);
        System.out.println(routeCount[0][0]);
    }

    public static int downHill(int row, int column) {
        if (row == endRow - 1 & column == endColumn - 1) {
            routeCount[row][column] = 1;
            return 1;
        }
        routeCount[row][column] = 0;
        int count = 0;
        for (int i = 0; i < 4; i++) {
            try {
                if (map[row][column] > map[row + dx[i]][column + dy[i]]) {
                    if (routeCount[row + dx[i]][column + dy[i]] == -1) {
                        count += downHill(row + dx[i], column + dy[i]);
                    } else {
                        count += routeCount[row + dx[i]][column + dy[i]];
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
        }
        return routeCount[row][column] += count;
    }
}
