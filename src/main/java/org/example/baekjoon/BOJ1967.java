package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/1967">1967. 트리의 지름</a>
 * */
public class BOJ1967 {

    private static final Map<Integer, List<int[]>> tree = new HashMap<>();
    private static int max = 0;
    private static boolean[] visited;
    private static int tempNodeNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            tree.compute(start, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(new int[]{end, cost});
                return v;
            });
            tree.compute(end, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(new int[]{start, cost});
                return v;
            });
        }
        br.close();
        visited = new boolean[n + 1];
        dfs(1, 0);
        dfs(tempNodeNumber, 0);
        System.out.println(max);
    }

    private static void dfs(int start, int sum) {
        List<int[]> edges = tree.get(start);
        visited[start] = true;
        boolean isLeaf = true;
        if (edges == null) {
            return;
        }
        for (int[] edge : edges) {
            if (!visited[edge[0]]) {
                isLeaf = false;
                dfs(edge[0], sum + edge[1]);
            }
        }
        if (isLeaf) {
            if (max < sum) {
                max = sum;
                tempNodeNumber = start;
            }
        }
        visited[start] = false;
    }


}
