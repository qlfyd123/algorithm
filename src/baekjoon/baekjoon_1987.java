package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1987 {
    static int x, y;
    static int[][] range = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static char[][] matrix;
    static boolean[] visited = new boolean[26];
    static int ans = 0;

    private static boolean inRange(int x, int y) {
        if (x < 0 | x >= baekjoon_1987.x) return false;
        else return !(y < 0 | y >= baekjoon_1987.y);
    }

    private static void dfs(int x, int y, int count) {
        for (int i = 0; i < 4; i++) {
            int[] location = range[i];
            int curX = x + location[0];
            int curY = y + location[1];
            if (inRange(curX, curY)) {
                char curChar = (char) (matrix[curY][curX] - 'A');
                if (!visited[curChar]) {
                    visited[curChar] = true;
                    dfs(curX, curY, count + 1);
                } else {
                    ans = Math.max(count, ans);
                }
            }
        }
        visited[matrix[y][x] - 'A'] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer lo = new StringTokenizer(br.readLine());
        y = Integer.parseInt(lo.nextToken());
        x = Integer.parseInt(lo.nextToken());
        matrix = new char[y][x];
        for (int i = 0; i < y; i++) {
            char[] chars = br.readLine().toCharArray();
            System.arraycopy(chars, 0, matrix[i], 0, x);
        }
        visited[matrix[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(ans);

    }
}
