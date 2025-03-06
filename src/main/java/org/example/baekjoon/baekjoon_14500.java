package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/14500">백준14500</a>
 */
public class baekjoon_14500 {
    static int MAX = 0;
    static boolean[][] visited;
    static int[][] tetromino;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int row, column;
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        tetromino = new int[row][column];
        visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < column; j++) {
                tetromino[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Polynomio polynomio = new Polynomio(i, j, 1, tetromino[i][j]);
                search(polynomio);
                searchTetromino_T(polynomio);
            }
        }
        System.out.println(MAX);
    }

    public static void search(Polynomio polynomio) {
        visited[polynomio.getRow()][polynomio.getColumn()] = true;
        if (polynomio.getCount() == 4) {
            MAX = Math.max(MAX, polynomio.getSum());
            visited[polynomio.getRow()][polynomio.getColumn()] = false;
            return;
        }
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            Polynomio nextBlock = new Polynomio(polynomio.getRow() + dx[i], polynomio.getColumn() + dy[i], polynomio.getCount() + 1);
            if (canVisited(nextBlock)) {
                nextBlock.setSum(polynomio.getSum() + tetromino[nextBlock.getRow()][nextBlock.getColumn()]);
                search(nextBlock);
            }
        }

        visited[polynomio.getRow()][polynomio.getColumn()] = false;
    }

    private static void searchTetromino_T(Polynomio polynomio) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int newX = polynomio.getRow() + dx[i];
            int newY = polynomio.getColumn() + dy[i];
            try {
                list.add(tetromino[newX][newY]);
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        if (list.size() >= 3) {
            list.sort(Collections.reverseOrder());
            int sum = polynomio.getSum();
            sum += (list.get(0) + list.get(1) + list.get(2));
            MAX = Math.max(MAX, sum);
        }
    }

    public static boolean canVisited(Polynomio nextBlock) {
        try {
            return !visited[nextBlock.getRow()][nextBlock.getColumn()];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}

class Polynomio {
    private final int row;
    private final int column;
    private int count;
    private int sum;

    public Polynomio(int row, int column, int count) {
        this.row = row;
        this.column = column;
        this.count = count;
    }

    public Polynomio(int row, int column, int count, int sum) {
        this.row = row;
        this.column = column;
        this.count = count;
        this.sum = sum;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }
}