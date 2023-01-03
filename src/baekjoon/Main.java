package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;

    private static boolean[][] getEdge(int V, int E) throws IOException {
        boolean[][] edges = new boolean[V + 1][V + 1];
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            edges[left][right] = true;
            edges[right][left] = true;
        }
        return edges;
    }

    private static boolean search(boolean[][] edges, int nodeNo, boolean color, int[] coloredNode) {
        coloredNode[nodeNo] = color ? 2 : 1;
        for (int i = 1; i < edges.length; i++) {
            if (edges[nodeNo][i]) {
                if (coloredNode[i] == 0) {
                    if (search(edges, i, !color, coloredNode)) {
                        return true;
                    }
                }else{
                    if (coloredNode[nodeNo] == coloredNode[i] & i != nodeNo) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            boolean[][] edges = getEdge(V, E);
            int[] coloredNode = new int[V + 1];

            for (int i = 1; i < coloredNode.length; i++) {if (coloredNode[i] == 0) {
                    if (search(edges, i, false, coloredNode)) {
                        System.out.println("NO");
                        return;
                    }
                }
            }
            System.out.println("YES");
        }
    }
}
