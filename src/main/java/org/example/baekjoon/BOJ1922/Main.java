package org.example.baekjoon.BOJ1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        Queue<Link> queue = new PriorityQueue<>(Comparator.comparingInt(link -> link.cost));
        for (int i = 0; i < M; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            queue.add(new Link(start, end, cost));
        }

        int[] union = new int[N + 1];
        int totalCost = 0;

        while (!queue.isEmpty()) {
            Link link = queue.poll();
            int rootStart = findRoot(union, link.start);
            int rootEnd = findRoot(union, link.end);

            if (rootStart != rootEnd) {
                union[rootEnd] = rootStart;
                totalCost += link.cost;
            }
        }

        System.out.println(totalCost);
    }

    private static int findRoot(int[] union, int node) {
        if (union[node] == 0) {
            return node;
        }
        return findRoot(union, union[node]);
    }

    private static class Link {

        int start;
        int end;
        int cost;

        public Link(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

}
