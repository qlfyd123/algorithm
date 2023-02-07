package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int MAX = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int row, column;
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        int[][] tetromino = new int[row][column];
        boolean[][] visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < column; j++) {
                tetromino[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Queue<Polyiomino> polyiominos = new LinkedList<>();
                Polyiomino polyiomino = new Polyiomino(i, j, visited);
                int sum = tetromino[i][j];
                int finalI = i;
                int finalJ = j;
                polyiominos.add(new Polyiomino(finalI, finalJ, visited) {
                    {
                        setSum(sum);
                        setCount(1);
                        setVisited(finalI, finalJ);
                    }
                });
                do {
                    searchNext(tetromino, polyiominos.poll(), polyiominos);
                } while (!polyiominos.isEmpty());
            }
        }

        System.out.println(MAX);
    }

    public static void searchNext(int[][] tetromino, Polyiomino polyiomino, Queue<Polyiomino> polyiominos) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        boolean[] canVisited = new boolean[4];
        boolean isCountThree = polyiomino.getCount() == 3;
        for (int i = 0; i < 4; i++) {
            canVisited[i] = polyiomino.canVisited(i);
        }
        for (int i = 0; i < 4; i++) {
            if (canVisited[i]) {
                int newX = polyiomino.getX() + dx[i];
                int newY = polyiomino.getY() + dy[i];
                if (isCountThree) {
                    MAX = Math.max(MAX, polyiomino.getSum() + tetromino[newX][newY]);
                    return;
                }
                polyiominos.add(getNextPolyio(tetromino, polyiomino, newX, newY));
            }
        }

        if (canVisited[0] & canVisited[1]) {
            if (canVisited[2]) {
                int newX = polyiomino.getX() + dx[2];
                int newY = polyiomino.getY() + dy[2];
                if (isCountThree) {
                    MAX = Math.max(MAX, polyiomino.getSum() + tetromino[newX][newY]);
                    return;
                }
                polyiominos.add(getNextPolyio(tetromino, polyiomino, newX, newY));
            }
        } //else if (canVisited[2] & canVisited[3])
    }

    private static Polyiomino getNextPolyio(int[][] tetromino, Polyiomino polyiomino, int newX, int newY) {
        Polyiomino pm = new Polyiomino(newX, newY, polyiomino.cloneVisitedArray());
        pm.setCount(polyiomino.getCount() + 1);
        pm.setSum(polyiomino.getSum() + tetromino[newX][newY]);
        pm.setVisited(newX, newY);
        return pm;
    }
}

class Polyiomino {
    private boolean[][] visited;
    private int sum;
    private int count;
    private int x;
    private int y;

    public Polyiomino(int x, int y, boolean[][] visited) {
        setVisited(visited);
        this.setX(x);
        this.setY(y);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public boolean canVisited(int i) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        try {
            if (visited[x + dx[i]][y + dy[i]]) {
                return false;
            } else {
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean[][] cloneVisitedArray() {
        boolean[][] clone = new boolean[visited.length][];
        for (int i = 0; i < visited.length; i++) {
            clone[i] = visited[i].clone();
        }
        return clone;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisited(boolean[][] visited) {
        this.visited = visited;
    }

    public void setVisited(int i, int j) {
        visited[i][j] = true;
    }
}
