package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Board2048> backTracking = new Stack<>();
        int size = Integer.parseInt(br.readLine());
        int[][] board = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backTracking.push(new Board2048(board, 0));
        while (!backTracking.isEmpty()) {
            Board2048 board2048 = backTracking.pop();
        }
    }
}

class Board2048 {
    private int[][] board;
    int count;

    public Board2048(int[][] board, int count) {
        this.count = count;
        this.board = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            this.board[i] = board[i].clone();
        }
    }

    public void nextPlay(Stack<Board2048> stack) {
        for (int i = 0; i < 4; i++) {
            move(i);
        }
    }

    public int[][] move(int dir) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        switch (dir) {
            case 0:
                up(dir, dx);
                break;
            case 1:
                down(dir, dx);
                break;
            case 2:
                right(dir, dy);
                break;
            case 3:
                left(dir, dy);
                break;
        }
    }

    private void down(int dir, int[] dx) {
        for (int i = board.length - 1; i > 0; i--) {
            searchRow(dir, dx, i, getCloneBoard());
        }
    }

    private void up(int dir, int[] dx) {
        for (int i = 0; i < board.length; i++) {
            searchRow(dir, dx, i, getCloneBoard());
        }
    }


    private int[][] right(int dir, int[] dy) {
        int[][] cloneBoard = getCloneBoard();
        for (int i = 0; i < board.length; i++) {
            searchColumn(dir, dy, i, cloneBoard);
        }
        return cloneBoard;
    }

    private int[][] left(int dir, int[] dy) {
        int[][] cloneBoard = getCloneBoard();
        for (int i = 0; i < board.length; i++) {
            searchColumn(dir, dy, i, cloneBoard);
        }
        return cloneBoard;
    }

    private int[][] getCloneBoard() {
        int[][] cloneBoard = new int[board.length][board.length];
        for (int j = 0; j < board.length; j++) {
            cloneBoard[j] = board[j].clone();
        }
        return cloneBoard;
    }

    private void searchRow(int dir, int[] directionArray, int i, int[][] cloneBoard) {
        for (int j = 0; j < board.length; j++) {
            if (cloneBoard[i][j] != 0) {
                int dis = 1;
                try {
                    while (cloneBoard[i + directionArray[dir] * dis][j] == 0) {
                        dis++;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                } finally {
                    dis--;
                    cloneBoard[i + directionArray[dir] * dis][j] = cloneBoard[i][j];
                    cloneBoard[i][j] = 0;
                }
            }
        }
    }

    private void searchColumn(int dir, int[] directionArray, int i, int[][] cloneBoard) {
        for (int j = 0; j < board.length; j++) {
            if (board[i][j] != 0) {
                int dis = 1;
                try {
                    while (board[i][j + directionArray[dir] * dis] == 0) {
                        dis++;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                } finally {
                    dis--;
                    board[i][j + directionArray[dir] * dis] = board[i][j];
                    board[i][j] = 0;
                }
            }
        }
    }


}
