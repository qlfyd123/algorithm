package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringJoiner;

/**
 * @see <a href="https://www.acmicpc.net/problem/2580">백준2580</a>
 */
public class baekjoon_2580 {
    static int[][] sudoku;
    static Stack<int[]> blanks;

    private static boolean contain(int number, int[] node) {
        int row = node[0];
        int column = node[1];
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][column] == number) return true;
            if (sudoku[row][i] == number) return true;
        }
        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
            for (int j = column / 3 * 3; j < column / 3 * 3 + 3; j++) {
                if (sudoku[i][j] == number) return true;
            }
        }
        return false;
    }

    private static boolean findNumber() {
        int[] node;
        if (!blanks.isEmpty()) {
            node = blanks.pop();
        } else {
            return true;
        }
        for (int i = 1; i < 10; i++) {
            if (!contain(i, node)) {
                sudoku[node[0]][node[1]] = i;
                if (findNumber()) {
                    return true;
                } else {
                    sudoku[node[0]][node[1]] = 0;
                }
            }
        }
        blanks.add(node);
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];
        blanks = new Stack<>();
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
        findNumber();
        for (int i = 0; i < 9; i++) {
            StringJoiner sj = new StringJoiner(" ");
            for (int j = 0; j < 9; j++) {
                sj.add(Integer.valueOf(sudoku[i][j]).toString());
            }
            System.out.println(sj);
        }
    }
}
