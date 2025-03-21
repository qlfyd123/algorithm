package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/13460">백준13460</a>
 */
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

    public Ball(Ball ball) {
        super(ball.row, ball.column);
        this.count = ball.count + 1;
    }
}

class Board {
    final private String[][] board;
    final private Block hole;
    int count = Integer.MAX_VALUE;

    public Board(String[][] board, Block hole) {
        this.board = board;
        this.hole = hole;
    }


    public void moveBall(Queue<Ball[]> queue) {
        Ball[] balls = queue.poll();
        Ball red = balls[0];
        if (red.count > 10) {
            return;
        }
        Ball blue = balls[1];
        //상,하,좌,우 순서
        for (int i = 0; i < 4; i++) {
            Ball nextRedBall = getNextBallLocation(red, i);
            Ball nextBlueBall = getNextBallLocation(blue, i);
            if (nextRedBall == null & nextBlueBall == null) {
                continue;
            } else {
                if (nextBlueBall == null) {
                    nextBlueBall = blue;
                }
                if (nextRedBall == null) {
                    nextRedBall = new Ball(red);
                }
            }
            boolean isSameLocation = nextRedBall.row == nextBlueBall.row & nextRedBall.column == nextBlueBall.column;
            boolean isRedHole = isHoleIn(red, nextRedBall);
            boolean isBlueHole = isHoleIn(blue, nextBlueBall);
            if (!isBlueHole) {
                if (isRedHole) {
                    count = Math.min(nextRedBall.count, count);
                    queue.clear();
                    return;
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
//                    if (!(nextRedBall.visited[nextRedBall.row][nextRedBall.column] & nextBlueBall.visited[nextBlueBall.row][nextBlueBall.column])) {
//                        nextRedBall.visited[nextRedBall.row][nextRedBall.column] = true;
//                        nextBlueBall.visited[nextBlueBall.row][nextBlueBall.column] = true;
                    queue.add(new Ball[]{nextRedBall, nextBlueBall});
//                    }
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
        if (distance == 1) {
            return null;
        }
        distance--;
        return new Ball(ball.row + dx[i] * distance, ball.column + dy[i] * distance, ball.count + 1);
    }

}

public class baekjoon_13460 {
    public static void main(String[] args) throws Exception {
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
                switch (line[j]) {
                    case "R":
                        red = new Ball(i, j, 0);
                        board[i][j] = ".";
                        break;
                    case "B":
                        blue = new Ball(i, j, 0);
                        board[i][j] = ".";
                        break;
                    case "O":
                        hole = new Block(i, j);
                        board[i][j] = ".";
                    default:
                        board[i][j] = line[j];
                }
            }
        }
        Board toy = new Board(board, hole);
        Queue<Ball[]> queue = new LinkedList<>();
        queue.add(new Ball[]{red, blue});
        while (!queue.isEmpty()) {
            toy.moveBall(queue);
        }

        System.out.println(toy.count > 10 ? -1 : toy.count);
    }
}
