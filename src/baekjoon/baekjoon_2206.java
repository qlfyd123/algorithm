package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @see <a href="https://www.acmicpc.net/problem/2206">백준 2206</a>
* */
public class baekjoon_2206 {
    static int x, y;
    static int[][] range = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static boolean[][] matrix;
    static boolean[][][] visited;
    static Queue<Node> nodes = new LinkedList<>();

    static class Node {
        int x;
        int y;
        boolean canBreak;
        int cost;

        public Node(int x, int y, boolean canBreak, int cost) {
            this.x = x;
            this.y = y;
            this.canBreak = canBreak;
            this.cost = cost;
        }
    }

    private static int checkRange(int xLo, int yLo) {
        if (xLo >= x | xLo < 0 | yLo >= y | yLo < 0) return -1;
        else {
            if (matrix[yLo][xLo]) return 0;
            else return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        matrix = new boolean[y][x];
        visited = new boolean[2][y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                visited[0][i][j] = false;
                visited[1][i][j] = false;
            }
        }
        for (int i = 0; i < y; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < x; j++) {
                matrix[i][j] = chars[j] == '0';
            }
        }
        visited[0][0][0] = true;
        visited[1][0][0] = true;
        nodes.add(new Node(0, 0, true, 1));
        int ans = Integer.MAX_VALUE;
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (node.x == x - 1 & node.y == y - 1) {
                ans = Math.min(ans, node.cost);
            }
            for (int[] range : range) {
                int inRange = checkRange(node.x + range[1], node.y + range[0]);
                if (inRange != -1) {
                    int x = node.x + range[1];
                    int y = node.y + range[0];
                    int cost = node.cost + 1;
                    if (inRange == 0) {
                        if (node.canBreak & !visited[0][y][x]) {
                            nodes.add(new Node(x, y, true, cost));
                            visited[0][y][x] = true;
                        } else if (!node.canBreak & !visited[1][y][x]) {
                            nodes.add(new Node(x, y, false, cost));
                            visited[1][y][x] = true;
                        }
                    } else if (inRange == 1 & node.canBreak) {
                        nodes.add(new Node(x, y, false, cost));
                        visited[1][y][x] = true;
                    }
                }
            }
        }
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
}
