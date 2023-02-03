package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] board = new int[size][size];
        int max = 0;
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[i][j]);
            }
        }
        Queue<Board> boards = new LinkedList<>();
        boards.add(new Board(max, 0, board));
        while (!boards.isEmpty()) {
            Board play = boards.poll();
            for (int i = 0; i < 4; i++) {
                move(i, play);
            }
        }
    }

    public static Board move(int direction, Board boardGame) {
        int start = 0, dir = 0;
        switch (direction) {
            case 0, 2:
                start = 0;
                dir = 1;
                searchHorizontally(start, dir, boardGame.board);
            case 1, 3:
                start = boardGame.board.length;
                dir = -1;
        }

    }

    public static int[][] searchHorizontally(int start, int dir, int[][] board) {
        int[][] nextBoard = new int[board.length][board.length];
        boolean[][] isUnited = new boolean[board.length][board.length];
        for (int i = 0; i < 4; i++) {
            int row = start;
            int index = start;
            while (row < board.length & row >= 0) {
                try {
                    if (board[row][i] != 0) {
                        nextBoard[index][i] = board[row][i];
                        if (nextBoard[index - dir][i] == nextBoard[index][i] & !isUnited[index - dir][i]) {
                            nextBoard[index - dir][i] *= 2;
                            isUnited[index - dir][i] = true;
                        } else {
                            index += dir;
                        }
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }
                row += dir;
            }
        }
    }

    static class Board {
        int max, count;
        int[][] board;

        public Board(int max, int count, int[][] board) {
            this.max = max;
            this.count = count;
            this.board = board;
        }
    }
}