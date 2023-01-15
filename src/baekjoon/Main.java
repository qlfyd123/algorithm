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

    public Ball(Block block, int count) {
        super(block.row, block.column);
        this.count = count;
    }

}

class Board {
    final private String[][] board;
    final private int row, column;
    final private Block hole;
    int count = Integer.MAX_VALUE;

    public Board(String[][] board, int row, int column, Block hole) {
        this.board = board;
        this.row = row;
        this.column = column;
        this.hole = hole;
    }

    public void moveBall(Ball[] balls, Stack<Ball[]> stack) {
        Ball red = balls[0];
        Ball blue = balls[1];
        for (int i = 0; i < 4; i++) {
            Block nextRedBall = getNextBallLocation(red, i);
            Block nextBlueBall = getNextBallLocation(blue, i);
            boolean isSameLocation = nextRedBall.row == nextBlueBall.row & nextRedBall.column == nextBlueBall.column;
            boolean isRedHole = isHoleIn(red, nextRedBall);
            boolean isBlueHole = isHoleIn(blue, nextBlueBall);
            if (!isBlueHole) {
                if (isRedHole) {
                    count = Math.min(red.count, count);
                } else {
                    if (isSameLocation) {
                        int redBallMoveDistance = Math.abs(nextRedBall.row - red.row + nextRedBall.column - red.column);
                        int blueBallMoveDistance = Math.abs(nextRedBall.row - blue.row + nextRedBall.column - blue.column);
                        if (redBallMoveDistance > blueBallMoveDistance) {
                            int[] dx = {1, -1, 0, 0};
                            int[] dy = {0, 0, -1, 1};
                            nextRedBall.row -= dx[i];
                            nextRedBall.column -= dy[i];
                        }
                        stack.push(new Ball[]{new Ball(nextRedBall, red.count + 1), new Ball(nextBlueBall, blue.count + 1)});
                    }
                }
            }
        }
    }

    private boolean isHoleIn(Ball pre, Block next) {
        if (pre.row == hole.row) {
            return hole.column > pre.column & hole.column <= next.column;
        } else if (pre.column == hole.column) {
            return hole.row > pre.row & hole.row <= next.row;
        } else return false;
    }

    private Block getNextBallLocation(Ball red, int i) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int distance = 1;
        while (!board[red.row + dx[i] * distance][red.column + dy[i] * distance].equals("#")) {
            distance++;
        }
        Block nextBallLocation = new Block(red.row + dx[i] * distance, red.column + dy[i] * distance);
        return nextBallLocation;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] board;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());
        Ball red = null, blue = null;
        Block hole = null;
        board = new String[row][column];
        for (int i = 0; i < row; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < column; j++) {
                board[i][j] = line[j];
                if (board[i][j].equals("R")) {
                    red = new Ball(i, j, 0);
                    board[i][j] = ",";
                } else if (board[i][j].equals("B")) {
                    board[i][j] = ",";
                } else if (board[i][j].equals("O")) {
                    hole = new Block(i, j);
                }
            }
        }
        Board toy = new Board(board, row, column, hole);
        Stack<Ball[]> stack = new Stack<>();
        stack.push(new Ball[]{red, blue});
        while (!stack.isEmpty()) {

        }
    }
}
