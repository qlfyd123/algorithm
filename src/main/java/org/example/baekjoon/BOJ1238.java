package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1238 {

    private static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        /**
         * index 0: 도착점 index 1: 가중치
         */
        Map<Integer, List<int[]>> edges = new HashMap<>();
        Map<Integer, List<int[]>> reverseEdges = new HashMap<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int[] edge = new int[3];
            int start = Integer.parseInt(st.nextToken());
            edge[0] = start;
            edge[1] = Integer.parseInt(st.nextToken());
            edge[2] = Integer.parseInt(st.nextToken());
            edges.compute(start, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(edge);
                return v;
            });
            reverseEdges.compute(edge[1], (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(new int[]{edge[1], edge[0], edge[2]});
                return v;
            });
        }
        int[] party = new int[N + 1];
        Arrays.fill(party, Integer.MAX_VALUE);
        party[0] = 0;
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        sol(queue, party, edges);

        int[] reverseParty = new int[N + 1];
        Arrays.fill(reverseParty, Integer.MAX_VALUE);
        reverseParty[0] = 0;
        queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        sol(queue, reverseParty, reverseEdges);

        int max = 0;
        for (int i = 1; i < N + 1; i++) {
            if (i == X) {
                continue;
            }
            max = Math.max(max, party[i] + reverseParty[i]);
        }
        System.out.println(max);

    }

    private static void sol(Queue<int[]> queue, int[] arr, Map<Integer, List<int[]>> edges) {
        queue.add(new int[]{0, X, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentPoint = current[0];
            int nextPoint = current[1];
            int currentCost = current[2];
            if (arr[nextPoint] > currentCost + arr[currentPoint]) {
                arr[nextPoint] = currentCost + arr[currentPoint];
                if (edges.containsKey(nextPoint)) {
                    queue.addAll(edges.get(nextPoint));
                }
            }
        }
    }
}
