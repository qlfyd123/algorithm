package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// TODO: 2023-01-16 아래 테스트 케이스 맞춰서 수정 
/*
10 10
##########
#.#....###
#........#
#........#
##B..#...#
#.#......#
#.#..R...#
#...O#...#
#.#....###
##########
* */
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
    boolean[][][] visited;

    public Board(String[][] board, int row, int column, Block hole) {
        this.board = board;
        this.row = row;
        this.column = column;
        this.hole = hole;
        visited = new boolean[2][row][column];
    }

    public void moveBall(Stack<Ball[]> stack) {
        Ball[] balls = stack.pop();
        Ball red = balls[0];
        Ball blue = balls[1];
        //상,하,좌,우 순서
        for (int i = 0; i < 4; i++) {
            Ball nextRedBall = getNextBallLocation(red, i);
            Ball nextBlueBall = getNextBallLocation(blue, i);
            boolean isSameLocation = nextRedBall.row == nextBlueBall.row & nextRedBall.column == nextBlueBall.column;
            boolean isRedHole = isHoleIn(red, nextRedBall);
            boolean isBlueHole = isHoleIn(blue, nextBlueBall);
            if (!isBlueHole) {
                if (isRedHole) {
                    count = Math.min(nextRedBall.count, count);
                } else {
                    if (isSameLocation) {
                        int redBallMoveDistance = Math.abs(nextRedBall.row - red.row + nextRedBall.column - red.column);
                        int blueBallMoveDistance = Math.abs(nextRedBall.row - blue.row + nextRedBall.column - blue.column);
                        int[] dx = {-1, 1, 0, 0};
                        int[] dy = {0, 0, -1, 1};
                        if (redBallMoveDistance > blueBallMoveDistance) {
                            nextRedBall.row -= dx[i];
                            nextRedBall.column -= dy[i];
                        } else {
                            nextBlueBall.row -= dx[i];
                            nextBlueBall.column -= dy[i];
                        }
                    }
                    if (visited[0][nextRedBall.row][nextRedBall.column] & visited[1][nextBlueBall.row][nextBlueBall.column]) {
                        continue;
                    } else {
                        visited[0][nextRedBall.row][nextRedBall.column] = true;
                        visited[1][nextBlueBall.row][nextBlueBall.column] = true;
                    }
                    stack.push(new Ball[]{nextRedBall, nextBlueBall});
                }
            }
        }
    }

    private boolean isHoleIn(Ball pre, Block next) {

        if (pre.row == hole.row) {
            int big = Math.max(pre.column, next.column);
            int small = Math.min(pre.column, next.column);
            return hole.column >= small & hole.column <= big;
        } else if (pre.column == hole.column) {
            int big = Math.max(pre.row, next.row);
            int small = Math.min(pre.row, next.row);
            return hole.row >= small & hole.row <= big;
        } else return false;
    }

    private Ball getNextBallLocation(Ball ball, int i) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int distance = 1;
        while (!board[ball.row + dx[i] * distance][ball.column + dy[i] * distance].equals("#")) {
            distance++;
        }
        distance--;
        return new Ball(ball.row + dx[i] * distance, ball.column + dy[i] * distance, ball.count + 1);
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
                    blue = new Ball(i, j, 0);
                    board[i][j] = ",";
                } else if (board[i][j].equals("O")) {
                    hole = new Block(i, j);
                }
            }
        }
        Board toy = new Board(board, row, column, hole);
        Stack<Ball[]> stack = new Stack<>();
        stack.push(new Ball[]{red, blue});
        toy.visited[0][red.row][red.column] = true;
        toy.visited[1][blue.row][blue.column] = true;
        while (!stack.isEmpty()) {
            toy.moveBall(stack);
        }

        System.out.println(toy.count == Integer.MAX_VALUE ? -1 : toy.count);
    }
}
