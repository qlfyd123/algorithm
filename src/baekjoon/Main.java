package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int x, y;
    static boolean[][] matrix;
    static boolean[][][] visited;
    static Queue<Node> nodes = new LinkedList<>();

    static class Node {
        int x;
        int y;
        int wall;

        public Node(int x, int y, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }
    }

    private static boolean checkRange(int xLo, int yLo) {
        if (xLo >= x | xLo < 0) return false;
        return !(yLo >= y | yLo < 0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        matrix = new boolean[y][x];
        visited = new boolean[y][x][2];

        for (int i = 0; i < y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < x; j++) {
                matrix[i][j] = Objects.equals(st.nextToken(), "0");
            }
        }

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    if (checkRange(node.x + i, node.y + j)) {
                        x = node.x + i;
                        y = node.y + j;
                        if (matrix[y][x]) {
                            nodes.add(new Node(x, y, node.wall));
                        }else{
                            if(){
                                nodes.
                            }
                        }
                    }
                }
            }
        }
    }

}
