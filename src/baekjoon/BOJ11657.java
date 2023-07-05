package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/11657">BOJ 11657 타임머신</a>
 * */
public class BOJ11657 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[1] = 0L;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new int[]{start, end, cost});
        }
        br.close();
        resetDistance(distance, edges);
        boolean isNegativeCycle = checkCycle(edges, distance);

        if (isNegativeCycle) {
            System.out.println(-1);
        } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int i = 2; i < distance.length; i++) {
                if (distance[i] == Long.MAX_VALUE) {
                    bw.write("-1\n");
                } else {
                    bw.write(distance[i] + "\n");
                }
            }
            bw.close();
        }
    }

    /**
     * @param distance 거리 저장 배열
     * @param edges    노드의 노선 집합
     */
    private static void resetDistance(long[] distance, List<int[]> edges) {
        for (int i = 1; i < distance.length; i++) {
            for (int[] edge : edges) {
                int start = edge[0];
                int end = edge[1];
                int cost = edge[2];
                if (distance[start] != Long.MAX_VALUE & distance[end] > distance[start] + cost) {
                    distance[end] = distance[start] + cost;
                }
            }
        }
    }

    /**
     * @return 그래프 내에서 음수의 사이클 여부를 반환
     */
    private static boolean checkCycle(List<int[]> edges, long[] distance) {
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int cost = edge[2];
            if (distance[start] != Long.MAX_VALUE & distance[end] > distance[start] + cost) {
                return true;
            }
        }
        return false;
    }
}