package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*boj  1987*/
public class BOJ_1987 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        String[][] board = new String[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().split("");
        }
        Set<String> linkedHashSet = new LinkedHashSet<>();
        int count = move(0, 0, board, linkedHashSet);
        System.out.println(count);
    }

    private static boolean isOutOfRange(int x, int y, int R, int C) {
        return (x >= R | x < 0) | (y >= C | y < 0);
    }

    private static int move(int x, int y, String[][] board, Set<String> linkedHashSet) {

        if (isOutOfRange(x, y, board.length, board[0].length)) return linkedHashSet.size();
        else if (!linkedHashSet.add(board[x][y])) return linkedHashSet.size();
//        linkedHashSet.add(board[x][y]);
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int maxMovement = 0;
        for (int i = 0; i < 4; i++) {
            maxMovement = Math.max(maxMovement, move(x + dx[i], y + dy[i], board, linkedHashSet));
        }
        linkedHashSet.remove(board[x][y]);

        return maxMovement;
    }
}
