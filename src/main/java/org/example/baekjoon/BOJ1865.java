package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);
            int W = Integer.parseInt(input[2]);
            List<int[]> roads = new ArrayList<>();
            while (M-- > 0) {
                String[] road = br.readLine().split(" ");
                int S = Integer.parseInt(road[0]);
                int E = Integer.parseInt(road[1]);
                int T = Integer.parseInt(road[2]);
                roads.add(new int[]{S, E, T});
                roads.add(new int[]{E, S, T});
            }
            while (W-- > 0) {
                String[] hole = br.readLine().split(" ");
                int S = Integer.parseInt(hole[0]);
                int E = Integer.parseInt(hole[1]);
                int T = Integer.parseInt(hole[2]);
                roads.add(new int[]{S, E, -T});
            }
            if (bellmanFord(roads, N)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean bellmanFord(List<int[]> roads, int N) {
        int[] dist = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            dist[i] = 0;
        }
        for (int i = 1; i < N; i++) {
            for (int[] road : roads) {
                int start = road[0];
                int next = road[1];
                int time = road[2];
                if (dist[next] > dist[start] + time) {
                    dist[next] = dist[start] + time;
                }
            }
        }
        for (int[] road : roads) {
            int start = road[0];
            int next = road[1];
            int time = road[2];
            if (dist[next] > dist[start] + time) {
                return true;
            }
        }
        return false;
    }
}
