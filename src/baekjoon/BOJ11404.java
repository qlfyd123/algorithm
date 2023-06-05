package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/11404">BOJ 11404 플로이드</a>
 * */
public class BOJ11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] costs = new int[n + 1][n + 1];
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (costs[start][end] == 0) {
                costs[start][end] = cost;
            } else {
                costs[start][end] = Math.min(costs[start][end], cost);
            }
        }
        for (int mid = 1; mid < n + 1; mid++) {
            for (int start = 1; start < n + 1; start++) {
                if (start != mid & costs[start][mid] != 0) {
                    for (int end = 1; end < n + 1; end++) {
                        if (end != start & end != mid & costs[mid][end] != 0) {
                            if (costs[start][end] == 0) {
                                costs[start][end] = costs[start][mid] + costs[mid][end];
                            } else {
                                costs[start][end] = Math.min(costs[start][mid] + costs[mid][end], costs[start][end]);
                            }
                        }
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < n + 1; j++) {
                sb.append(costs[i][j]).append(" ");
            }
            result.append(sb).append("\n");
        }
        System.out.println(result);
    }
}
