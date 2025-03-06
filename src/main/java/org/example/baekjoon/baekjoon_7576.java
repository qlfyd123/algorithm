package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/7576">백준 7576</a>
 */
public class baekjoon_7576 {
    static int x, y;
    static boolean[][] matrix;
    static int count = 0;
    static int maxTomato;
    static List<Location> location = new ArrayList<>();

    static class Location {
        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static boolean read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        matrix = new boolean[y][x];
        maxTomato = x * y;
        for (int i = 0; i < y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < x; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 1) {
                    count++;
                    location.add(new Location(j, i));
                    matrix[i][j] = true;
                } else if (temp == -1) {
                    matrix[i][j] = true;
                    maxTomato--;
                } else {
                    matrix[i][j] = false;
                }
            }
        }
        return count == maxTomato;
    }

    private static boolean checkRange(int xLo, int yLo) {
        if (xLo >= x | xLo < 0) return false;
        return !(yLo >= y | yLo < 0);
    }

    public static boolean grow() {
        List<Location> queue = new ArrayList<>();
        boolean bool = false;
        for (Location node : location) {
            for (int i = -1; i <= 1; i += 2) {
                if (checkRange(node.x, node.y + i)) {
                    if (!matrix[node.y + i][node.x]) {
                        matrix[node.y + i][node.x] = true;
                        queue.add(new Location(node.x, node.y + i));
                        count++;
                        bool = true;
                    }
                }
                if (checkRange(node.x + i, node.y)) {
                    if (!matrix[node.y][node.x + i]) {
                        matrix[node.y][node.x + i] = true;
                        queue.add(new Location(node.x + i, node.y));
                        count++;
                        bool = true;
                    }
                }
            }
        }
        location = queue;
        return bool;
    }

    public static void main(String[] args) throws IOException {
        if (read()) {
            System.out.println(0);
            return;
        }

        int loop = 0;
        while (!location.isEmpty()) {
            if (grow())
                loop++;
        }

        if (count == maxTomato) {
            System.out.println(loop);
        } else {
            System.out.println(-1);
        }
    }
}
