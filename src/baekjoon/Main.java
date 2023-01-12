package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Block {
    int row, column;

    public Block(int r, int c) {
        row = r;
        column = c;
    }
}

class Ball extends Block {
    int count;

    public Ball(int r, int c, int count) {
        super(r, c);
        this.count = count;
    }
}

class Board {
    final private String[] board;
    final private int row, column;

    public Board(String[][] board, int row, int column) {
        this.board = board;
        this.row = row;
        this.column = column;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] board;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());
        Ball red = null, blue;
        Block hole;
        board = new String[row][column];
        for (int i = 0; i < row; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < column; j++) {
                board[i][j] = line[j];
                if (board[i][j].equals("R")) {
                    red = new Ball(i, j, 0);
                } else if (board[i][j].equals("B")) {
                    blue = new Ball(i, j, 0);
                } else if (board[i][j].equals("O")) {
                    hole = new Block(i, j);
                }
            }
        }
        Board toy = new Board(board, row, column);
        Stack<Ball> stack = new Stack<>();
        stack.push(red);
        while (!stack.isEmpty()) {

        }
    }
}
