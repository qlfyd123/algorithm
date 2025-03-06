package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1167 {

    static int V;
    static List<List<int[]>> tree;
    static boolean[] visited;
    static int maxCost = 0;
    static int maxV = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        tree = new ArrayList<>(V + 1);
        for (int i = 0; i < V + 1; i++) {
            tree.add(i, new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            List<int[]> edges = tree.get(v);
            while (st.hasMoreTokens()) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new int[]{end, cost});
            }
        }

        visited = new boolean[V + 1];
        dfs(1, 0);
        visited = new boolean[V + 1];
        dfs(maxV, 0);
        System.out.println(maxCost);
    }

    private static void dfs(int v, int totalCost) {
        visited[v] = true;
        if (totalCost > maxCost) {
            maxCost = totalCost;
            maxV = v;
        }
        for (int[] edge : tree.get(v)) {
            int end = edge[0];
            int cost = edge[1];
            if (!visited[end]) {
                dfs(end, totalCost + cost);
            }
        }
    }
}
