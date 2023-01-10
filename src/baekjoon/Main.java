package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Ball {
    int row, column;

    public Ball(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

class Board {

    final String[][] board;
    boolean[][] visited;
    Ball red, blue;

    public Board(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row, column;
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        board = new String[row][column];
        visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            for (int j = 0; j < column; j++) {
                String token = line.nextToken();
                if (token.equals(",")) {
                    board[i][j] = token;
                } else {
                    if (token.equals("R")) {
                        red = new Ball(i, j);
                    } else if (token.equals("B")) {
                        blue = new Ball(i, j);
                    }
                    board[i][j] = ",";
                }
            }
        }
    }

    private void move(Ball red, Ball blue, int count) {
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int distance = 1;
            while (board[red.row + x[i] * distance][red.column + y[i] * distance].equals(",")) {
                distance++;
            }
            Ball nextRedBall = new Ball(red.row + (distance - 1) * x[i], red.column + y[i] * (distance - 1));
            Ball nextBlueBall = new Ball(blue.row + (distance - 1) * x[i], blue.column + y[i] * (distance - 1));
            move(nextRedBall, nextBlueBall, count + 1);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    }
}
