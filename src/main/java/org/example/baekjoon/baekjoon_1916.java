package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * @see <a href="https://www.acmicpc.net/problem/1916">백준1916</a>
* */
public class baekjoon_1916 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<int[]>> bus = new ArrayList<>();
        int[] costArr = new int[N + 1];
        int startPoint, endPoint;
        boolean[] visited = new boolean[N + 1];
        Queue<int[]> nodeQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        Arrays.fill(costArr, Integer.MAX_VALUE);
        for (int i = 0; i <= N; i++) {
            bus.add(i, new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            bus.get(start).add(new int[]{end, cost});
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        startPoint = Integer.parseInt(st.nextToken());
        endPoint = Integer.parseInt(st.nextToken());
        costArr[startPoint] = 0;
        nodeQueue.add(new int[]{startPoint, 0});
        while (!nodeQueue.isEmpty()) {
            int[] busStop = nodeQueue.poll();
            int busStopNo = busStop[0];
            if (visited[busStopNo]) {
                continue;
            }
            ArrayList<int[]> busStopList = bus.get(busStopNo);
            visited[busStopNo] = true;
            for (int[] busRoute : busStopList) {
                int end = busRoute[0];
                int routeCost = busRoute[1];
                costArr[end] = Math.min(costArr[end], costArr[busStopNo] + routeCost);
                nodeQueue.add(new int[]{end, costArr[end]});
            }
        }
        System.out.println(costArr[endPoint]);
    }
}
