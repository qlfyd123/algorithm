package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/13023">BOJ13023 ABCDE</a>
 * */
public class BOJ13023 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.compute(start, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(end);
                return v;
            });
            graph.compute(end, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(start);
                return v;
            });
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            max = Math.max(max, dfs(1, i, graph, visited));
            if (max >= 5) {
                break;
            }
        }
        if (max >= 5) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static int dfs(int max, int nodeNum, Map<Integer, List<Integer>> graph, boolean[] visited) {
        if (max >= 5) {
            return max;
        }
        visited[nodeNum] = true;
        int returnMax = 0;
        for (int nextNode : graph.getOrDefault(nodeNum, Collections.emptyList())) {
            if (!visited[nextNode]) {
                returnMax = Math.max(returnMax, dfs(max + 1, nextNode, graph, visited));
            }
        }
        visited[nodeNum] = false;
        return returnMax == 0 ? max : returnMax;
    }
}
