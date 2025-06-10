package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/1939">1939번 중량제한</a>
 * */
public class BOJ1939 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, List<int[]>> map = new HashMap<>();
        boolean[] visited = new boolean[N + 1];
        int[] weights = new int[N + 1];
        weights[1] = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            map.computeIfAbsent(start, k -> new ArrayList<>()).add(new int[]{end, weight});
            map.computeIfAbsent(end, k -> new ArrayList<>()).add(new int[]{start, weight});
        }

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        queue.add(new int[]{start, Integer.MAX_VALUE});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentWeight = current[1];
            if (visited[currentNode]) {
                continue;
            }
            visited[currentNode] = true;
            for (int[] next : map.getOrDefault(currentNode, new ArrayList<>())) {
                int nextNode = next[0];
                int nextWeight = next[1];
                int minWeight = Math.min(currentWeight, nextWeight);
                if (weights[nextNode] < minWeight) {
                    weights[nextNode] = minWeight;
                    queue.add(new int[]{nextNode, minWeight});
                }
            }
        }

        System.out.println(weights[target]);
    }
}
