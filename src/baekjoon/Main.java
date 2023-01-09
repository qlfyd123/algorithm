package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

enum BALLCOLOR {
    RED, BLUE
}

// TODO: 2023-01-09 공 움직이는 함수 구현,play함수 재귀적 호출 
public class Main {
    static int[] redBall;
    static int[] blueBall;
    final static int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    enum DIRECTION {
        UP, DOWN, LEFT, RIGHT
    }

    public static void play(String[][] board, boolean[][] visited) {
        try {
            for (int i = 0; i < 4; i++) {
                int[] dir = direction[i];

            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    public static boolean move(BALLCOLOR color){
        
    }


    public static void main(String[] args) throws IOException {
        String[][] board;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row, column;
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        board = new String[row][column];
        boolean[][] visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());
            for (int j = 0; j < column; j++) {
                String token = line.nextToken();
                board[i][j] = token;
                if (token.equals("R")) {
                    redBall = new int[2];
                    redBall[0] = i;
                    redBall[1] = j;
                } else if (token.equals("B")) {
                    blueBall = new int[2];
                    blueBall[0] = i;
                    blueBall[1] = j;
                }
            }
        }


    }
}
