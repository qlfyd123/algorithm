package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @see <a href="https://www.acmicpc.net/problem/7576">백준 7576</a>
 * */
public class baekjoon_7576 {
    static int x, y;
    static int[][] matrix;
    static int count = 0;
    static int maxTomato;
    static Queue<Location> location = new LinkedList<>();

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
        matrix = new int[y][x];
        maxTomato = x * y;
        for (int i = 0; i < y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < x; j++) {
                int temp = Integer.parseInt(st.nextToken());
                matrix[i][j] = temp;
                if (temp == 1) {
                    count++;
                    location.add(new Location(j, i));
                } else if (temp == -1) {
                    maxTomato--;
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
        Queue<Location> queue = new LinkedList<>();
        boolean bool = false;
        while (!location.isEmpty()) {
            Location node = location.poll();
            for (int i = -1; i <= 1; i += 2) {
                if (checkRange(node.x, node.y + i)) {
                    if (matrix[node.y + i][node.x] == 0) {
                        matrix[node.y + i][node.x] = 1;
                        queue.add(new Location(node.x, node.y + i));
                        count++;
                        bool = true;
                    }
                }
                if (checkRange(node.x + i, node.y)) {
                    if (matrix[node.y][node.x + i] == 0) {
                        matrix[node.y][node.x + i] = 1;
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
