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
/**
 * <a href="https://www.acmicpc.net/problem/11779">최소비용 구하기 2</a>
 * */
public class BOJ11779 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(start, k -> new ArrayList<>()).add(new int[]{end, weight});
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        boolean[] visited = new boolean[n + 1];
        int[] weights = new int[n + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[start] = 0;
        int[] previous = new int[n + 1];
        queue.add(new int[]{0, start});
        while (!queue.isEmpty()) {
            int[] currentNode = queue.poll();
            int current = currentNode[1];
            int weight = currentNode[0];
            if (visited[current]) {
                continue;
            } else {
                visited[current] = true;
            }
            List<int[]> nextNodes = map.getOrDefault(current, new ArrayList<>());
            for (int[] next : nextNodes) {
                int nextNode = next[0];
                int nextWeight = next[1] + weight;

                if (!visited[nextNode] && weights[nextNode] > nextWeight) {
                    previous[nextNode] = current;
                    weights[nextNode] = nextWeight;
                    previous[nextNode] = current;
                    queue.add(new int[]{nextWeight, nextNode});
                }
            }
        }

        int node = target;
        List<Integer> route = new ArrayList<>();
        route.add(target);
        while (previous[node] != 0) {
            node = previous[node];
            route.add(node);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(weights[target]).append("\n");
        sb.append(route.size()).append("\n");
        for (int i = route.size() - 1; i >= 0; i--) {
            sb.append(route.get(i)).append(" ");
        }

        System.out.print(sb.toString().trim());
    }
}
