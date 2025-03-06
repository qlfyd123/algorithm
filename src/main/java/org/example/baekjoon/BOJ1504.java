package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/1504">BOJ1504 특정한 최단 경로</a>
 */
public class BOJ1504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) graph[i][j] = 0;
                else graph[i][j] = Integer.MAX_VALUE;
            }
        }
        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start][end] = cost;
            graph[end][start] = cost;
        }
        st = new StringTokenizer(br.readLine());
        int V1 = Integer.parseInt(st.nextToken());
        int V2 = Integer.parseInt(st.nextToken());

        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    if (graph[start][mid] == Integer.MAX_VALUE | graph[mid][end] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (graph[start][end] > graph[start][mid] + graph[mid][end]) {
                        graph[start][end] = graph[start][mid] + graph[mid][end];
                    }
                }
            }
        }
        long minCost = Integer.MAX_VALUE;
        minCost = Math.min(minCost, graph[V1][V2] + Math.min((long) graph[V1][N] + (long) graph[1][V2], (long) graph[1][V1] + (long) graph[V2][N]));
        minCost = Math.min(minCost, 2L * graph[1][V1] + graph[1][V2] + graph[V2][N]);
        minCost = Math.min(minCost, 2L * graph[1][V2] + graph[1][V1] + graph[V1][N]);

        System.out.println(minCost == Integer.MAX_VALUE ? -1 : minCost);
    }
}
