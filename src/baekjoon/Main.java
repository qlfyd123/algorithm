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
        int max = 0;
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[i][j]);
            }
        }
        backTracking.push(new Board2048(board, 0, max));
        while (!backTracking.isEmpty()) {
            Board2048 board2048 = backTracking.pop();
            if (board2048.count == 5) {
                max = Math.max(max, board2048.max);
            } else {
                board2048.nextPlay(backTracking, board2048.count);
            }
        }
        System.out.println(max);
    }
}

class Board2048 {
    int[][] board;
    int count;
    int max;

    public Board2048(int[][] board, int count, int max) {
        this.count = count;
        this.board = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            this.board[i] = board[i].clone();
        }
        this.max = max;
    }

    public void nextPlay(Stack<Board2048> stack, int count) {
        for (int i = 0; i < 4; i++) {
            int[][] movedBoard = move(i);
            stack.push(new Board2048(movedBoard, count + 1, this.max));
        }
    }

    private int[][] move(int dir) {
        int[][] movedBoard;
        switch (dir) {
            case 0:
                movedBoard = moveVertical(true);
                break;
            case 1:
                movedBoard = moveVertical(false);
                break;
            case 2:
                movedBoard = moveHorizontal(true);
                break;
            case 3:
                movedBoard = moveHorizontal(false);
                break;
            default:
                movedBoard = new int[0][0];
        }

        return movedBoard;
    }

    private int[][] moveVertical(boolean isDirectionUp) {
        int movement = isDirectionUp ? 1 : -1;
        int[][] cloneBoard = getCloneBoard();
        boolean[][] isUnited = new boolean[cloneBoard.length][cloneBoard.length];
        for (int i = 0; i < board.length; i++) {
            searchRow(movement, i, cloneBoard, isUnited);
        }
        return cloneBoard;
    }

    private int[][] moveHorizontal(boolean isDirectionLeft) {
        int movement = isDirectionLeft ? 1 : -1;
        int[][] cloneBoard = getCloneBoard();
        boolean[][] isUnited = new boolean[cloneBoard.length][cloneBoard.length];
        for (int i = 0; i < board.length; i++) {
            searchColumn(movement, i, cloneBoard, isUnited);
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

    private void searchRow(int dir, int index, int[][] cloneBoard, boolean[][] isUnited) {
        int start = dir == 1 ? 0 : board.length - 1;
        int zeroIndex = -1;
        while (start < board.length & start >= 0) {
            if (zeroIndex == -1) {
                if (cloneBoard[start][index] == 0) {
                    zeroIndex = start;
                }
            } else {
                if (cloneBoard[start][index] != 0) {
                    cloneBoard[zeroIndex][index] = cloneBoard[start][index];
                    cloneBoard[start][index] = 0;
                    start = zeroIndex;
                    zeroIndex += dir;

                }
            }
            try {
                if (cloneBoard[start - dir][index] == cloneBoard[start][index] & !isUnited[start - dir][index]) {
                    cloneBoard[start - dir][index] *= 2;
                    cloneBoard[start][index] = 0;
                    zeroIndex = start;
                    this.max = Math.max(this.max, cloneBoard[start - dir][index]);
                    isUnited[start - dir][index] = true;
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
            start += dir;
        }
    }

    private void searchColumn(int dir, int index, int[][] cloneBoard, boolean[][] isUnited) {
        int start = dir == 1 ? 0 : board.length - 1;
        int zeroIndex = -1;
        while (start < board.length & start >= 0) {
            if (zeroIndex == -1) {
                if (cloneBoard[index][start] == 0) {
                    zeroIndex = start;
                }
            } else {
                if (cloneBoard[index][start] != 0) {
                    cloneBoard[index][zeroIndex] = cloneBoard[index][start];
                    cloneBoard[index][start] = 0;
                    zeroIndex += dir;
                }
            }
            try {
                if (cloneBoard[index][start - dir] == cloneBoard[index][start] & !isUnited[index][start - dir]) {
                    cloneBoard[index][start - dir] *= 2;
                    cloneBoard[index][start] = 0;
                    zeroIndex = start;
                    this.max = Math.max(this.max, cloneBoard[index][start - dir]);
                    isUnited[index][start - dir] = true;
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
            start += dir;
        }
    }

}
