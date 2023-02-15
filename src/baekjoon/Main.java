package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static Stack<int[]> bfs;
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
            for (int j = 0; j < endColumn; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(downHill(0, 0));
    }

    public static int downHill(int row, int column) {
        if (row == endRow & column == endColumn) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < 4; i++) {
            try {
                if ((map[row][column] > map[row + dx[i]][column + dy[i]])) {
                    count += downHill(row + dx[i], column + dy[i]);
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
        }
        return count;
    }
}
