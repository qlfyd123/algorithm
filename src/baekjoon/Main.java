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
        backTracking.push(new Board2048(board, 0, 0));
        int max = 0;
        while (!backTracking.isEmpty()) {
            Board2048 board2048 = backTracking.pop();
            if (board2048.count == 5) {
                max = Math.max(max, board2048.max);
            } else {
                board2048.nextPlay(backTracking, board2048.count);
            }
        }
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
                movedBoard = up();
                break;
            case 1:
                movedBoard = down();
                break;
            case 2:
                movedBoard = right();
                break;
            case 3:
                movedBoard = left();
                break;
            default:
                movedBoard = new int[0][0];
        }

        return movedBoard;
    }

    private int[][] down() {
        int[][] cloneBoard = getCloneBoard();
        for (int i = board.length - 1; i > 0; i--) {
            searchRow(-1, i, cloneBoard);
        }
        return cloneBoard;
    }

    private int[][] up() {
        int[][] cloneBoard = getCloneBoard();
        for (int i = 0; i < board.length; i++) {
            searchRow(1, i, cloneBoard);
        }
        return cloneBoard;
    }


    private int[][] right() {
        int[][] cloneBoard = getCloneBoard();
        for (int i = 0; i < board.length; i++) {
            searchColumn(1, i, cloneBoard);
        }
        return cloneBoard;
    }

    private int[][] left() {
        int[][] cloneBoard = getCloneBoard();
        for (int i = 0; i < board.length; i++) {
            searchColumn(-1, i, cloneBoard);
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

    private void searchRow(int dir, int index, int[][] cloneBoard) {
        int start = dir == 1 ? 0 : board.length - 1;
        int end = dir == 1 ? board.length - 1 : 0;
        int zeroIndex = -1;
        while (start != end) {
            if (zeroIndex == -1) {
                if (cloneBoard[start][index] == 0) {
                    zeroIndex = start;
                }
            } else {
                if (cloneBoard[start][index] != 0) {
                    cloneBoard[zeroIndex][index] = cloneBoard[start][index];
                    cloneBoard[start][index] = 0;
                    try {
                        if (cloneBoard[zeroIndex - dir][index] == cloneBoard[zeroIndex][index]) {
                            cloneBoard[zeroIndex - dir][index] *= 2;
                            this.max = Math.max(this.max, cloneBoard[zeroIndex - dir][index]);
                        } else {
                            zeroIndex += dir;
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            }
            start += dir;
        }
    }

    private void searchColumn(int dir, int index, int[][] cloneBoard) {
        int start = dir == 1 ? board.length - 1 : 0;
        int end = dir == 1 ? 0 : board.length - 1;
        int zeroIndex = -1;
        while (start != end) {
            if (zeroIndex == -1) {
                if (cloneBoard[index][start] == 0) {
                    zeroIndex = start;
                }
            } else {
                if (cloneBoard[index][start] != 0) {
                    cloneBoard[index][zeroIndex] = cloneBoard[index][start];
                    cloneBoard[start][index] = 0;
                    try {
                        if (cloneBoard[index][zeroIndex - dir] == cloneBoard[index][zeroIndex]) {
                            cloneBoard[index][zeroIndex - dir] *= 2;
                        } else {
                            zeroIndex += dir;
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            }
            start += dir;
        }
    }


}
