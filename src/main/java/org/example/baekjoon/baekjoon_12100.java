package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @see <a href="https://www.acmicpc.net/problem/12100">백준 12100</a>
 * */
public class baekjoon_12100 {
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
                Board next = move(i, play);
                max = Math.max(max, next.max);
                if (next.count != 5) {
                    boards.add(next);
                }
            }
        }
        System.out.println(max);
    }

    public static Board move(int direction, Board boardGame) {
        int[][] board = new int[0][];
        switch (direction) {
            case 0:
                /* UP */
                return searchVertically(1, boardGame);
            case 1:
                /* DOWN */
                return searchVertically(-1, boardGame);
            case 2:
                /* RIGHT */
                return searchHorizontally(-1, boardGame);
            case 3:
                /* LEFT */
                return searchHorizontally(1, boardGame);
        }
        return new Board(boardGame.count + 1, boardGame.max, board);
    }

    public static Board searchVertically(int dir, Board boardGame) {
        int[][] nextBoard = new int[boardGame.board.length][boardGame.board.length];
        boolean[][] isUnited = new boolean[boardGame.board.length][boardGame.board.length];
        int max = boardGame.max;
        int start = dir > 0 ? 0 : boardGame.board.length - 1;
        for (int i = 0; i < boardGame.board.length; i++) {
            int row = start;
            int index = start;
            while (row <= boardGame.board.length - 1 & row >= 0) {
                try {
                    if (boardGame.board[row][i] != 0) {
                        nextBoard[index][i] = boardGame.board[row][i];
                        if (nextBoard[index - dir][i] == nextBoard[index][i] & !isUnited[index - dir][i]) {
                            nextBoard[index - dir][i] *= 2;
                            nextBoard[index][i] = 0;
                            isUnited[index - dir][i] = true;
                            max = Math.max(max, nextBoard[index - dir][i]);
                        } else {
                            index += dir;
                        }
                    }
                } catch (IndexOutOfBoundsException ignored) {
                    index += dir;
                }
                row += dir;
            }
        }
        return new Board(max, boardGame.count + 1, nextBoard);
    }

    public static Board searchHorizontally(int dir, Board boardGame) {
        int[][] nextBoard = new int[boardGame.board.length][boardGame.board.length];
        boolean[][] isUnited = new boolean[boardGame.board.length][boardGame.board.length];
        int start = dir > 0 ? 0 : boardGame.board.length - 1;
        int max = boardGame.max;
        for (int i = 0; i < boardGame.board.length; i++) {
            int column = start;
            int index = start;
            while (column <= boardGame.board.length - 1 & column >= 0) {
                try {
                    if (boardGame.board[i][column] != 0) {
                        nextBoard[i][index] = boardGame.board[i][column];
                        if (nextBoard[i][index - dir] == nextBoard[i][index] & !isUnited[i][index - dir]) {
                            nextBoard[i][index - dir] *= 2;
                            nextBoard[i][index] = 0;
                            isUnited[i][index - dir] = true;
                            max = Math.max(max, nextBoard[i][index - dir]);
                        } else {
                            index += dir;
                        }
                    }
                } catch (IndexOutOfBoundsException ignored) {
                    index += dir;
                }
                column += dir;
            }
        }
        return new Board(max, boardGame.count + 1, nextBoard);
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