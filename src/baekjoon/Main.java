package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] sudoku;
    static final int max = 45;

    private static int searchRow(int row) {
        int count = 0;
        int sum = 0;
        for (int i : sudoku[row]) {
            if (i == 0) {
                count++;
            }
            sum += i;
        }
        return count > 1 ? 0 : max - sum;
    }

    private static int searchColumn(int column) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][column] == 0) {
                count++;
            }
            sum += sudoku[i][column];
        }
        return count > 1 ? 0 : max - sum;
    }

    private static int searchBlock(int row, int column) {
        row /= 3;
        column /= 3;

        int sum = 0;
        int count = 0;
        for (int i = row * 3; i < row * 3 + 3; i++) {
            for (int j = column * 3; j < column * 3 + 3; j++) {
                if (sudoku[i][j] == 0) {
                    count++;
                }
                sum += sudoku[i][j];
            }
        }
        return count > 1 ? 0 : max - sum;
    }

    private static boolean findBlankNumber(int[] blank) {
        int row = blank[0];
        int column = blank[1];
        int searchRow = searchRow(row);
        if (searchRow != 0) {
            sudoku[row][column] = searchRow;
            return true;
        }
        int searchColumn = searchColumn(column);
        if (searchColumn != 0) {
            sudoku[row][column] = searchColumn;
            return true;
        }
        int searchBlock = searchBlock(row, column);
        if (searchBlock != 0) {
            sudoku[row][column] = searchBlock;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];
        Queue<int[]> blanks = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            char[] line = br.readLine().replace(" ", "").toCharArray();
            for (int j = 0; j < 9; j++) {
                if (line[j] == '0') {
                    sudoku[i][j] = 0;
                    blanks.add(new int[]{i, j});
                } else {
                    sudoku[i][j] = line[j] - '0';
                }
            }
        }
        while (!blanks.isEmpty()) {
            int[] blank = blanks.poll();
            if (!findBlankNumber(blank)) {
                blanks.add(blank);
            }
        }
        for (int i = 0; i < 9; i++) {
            StringJoiner sj = new StringJoiner(" ");
            for (int j = 0; j < 9; j++) {
                sj.add(Integer.valueOf(sudoku[i][j]).toString());
            }
            System.out.println(sj);
        }
    }
}
