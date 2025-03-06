package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_2140 {
    static int[] directionX = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] directionY = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static boolean check(int x, int y, char[][] mine) {
        for (int i = 0; i < 8; i++) {
            int XLocation = x + directionX[i];
            int YLocation = y + directionY[i];
            if (mine[XLocation][YLocation] == '0') {
                return false;
            }

        }
        return true;
    }

    private static void insertMine(int x, int y, char[][] mine) {
        for (int i = 0; i < 8; i++) {
            int XLocation = x + directionX[i];
            int YLocation = y + directionY[i];

            if (mine[XLocation][YLocation] != '#') {
                mine[XLocation][YLocation]--;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N < 3) {
            System.out.println(0);
            return;
        }
        char[][] mine = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            mine[i] = str.toCharArray();
        }
        int count = 0;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (check(i, j, mine)) {
                    insertMine(i, j, mine);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
