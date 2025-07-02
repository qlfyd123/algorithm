package org.example.baekjoon.BOJ1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new int[]{a, b, c});
        }

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        queue.addAll(edges);
        int cost = 0;
        int maxWeight = 0;
        int[] unionFind = new int[N + 1];
        int count = 0;
        while (!queue.isEmpty()) {
            int[] edge = queue.poll();
            int start = edge[0];
            int end = edge[1];
            int weight = edge[2];

            int startRoot = findRoot(unionFind, start);
            int endRoot = findRoot(unionFind, end);

            if (startRoot != endRoot) {
                unionFind[endRoot] = startRoot;
                unionFind[end] = startRoot;
                cost += weight;
                maxWeight = Math.max(maxWeight, weight);
                count++;
            }

            if (count == N - 1) {
                break;
            }
        }
        System.out.println(cost - maxWeight);
    }

    private static int findRoot(int[] unionFind, int node) {
        while (unionFind[node] != 0) {
            node = unionFind[node];
        }

        return node;
    }
}
