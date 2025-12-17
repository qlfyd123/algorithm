package org.example.baekjoon.BOJ17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int currentIslandMarker = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    markIsland(currentIslandMarker, map, i, j);
                    currentIslandMarker++;
                }
            }
        }

        Queue<Bridge> bridgeQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dx[k];
                        int nc = j + dy[k];

                        while (isInRange(map, nr, nc) && map[nr][nc] == 0) {
                            nr += dx[k];
                            nc += dy[k];
                        }

                        if (isInRange(map, nr, nc)) {
                            if (map[nr][nc] != 0 && map[nr][nc] != map[i][j]) {
                                bridgeQueue.add(new Bridge(map[i][j], map[nr][nc], i == nr ? Math.abs(j - nc) - 1 : Math.abs(i - nr) - 1));
                            }
                        }
                    }
                }
            }
        }
        int[] union = new int[8];
        int totalBridgeDistance = 0;
        int roadCount = 0;
        while (!bridgeQueue.isEmpty()) {
            Bridge bridge = bridgeQueue.poll();
            int source = bridge.sourceIsland;
            int dest = bridge.destIsland;
            int distance = bridge.distance;
            if (distance < 2) {
                continue;
            }
            int sourceNodeRoot = findRootNode(source, union);
            int destNodeRoot = findRootNode(dest, union);
            if (sourceNodeRoot != destNodeRoot) {
                roadCount++;
                union[destNodeRoot] = sourceNodeRoot;
                totalBridgeDistance += distance;
            }
        }

        int islandCount = currentIslandMarker - 2;
        System.out.println(roadCount == islandCount - 1 ? totalBridgeDistance : -1);
    }

    private static void markIsland(int marker, int[][] map, int r, int c) {
        map[r][c] = marker;
        for (int i = 0; i < 4; i++) {
            int nextR = r + dx[i];
            int nextC = c + dy[i];

            if (isInRange(map, nextR, nextC) && map[nextR][nextC] == 1) {
                markIsland(marker, map, nextR, nextC);
            }
        }
    }

    private static boolean isInRange(int[][] map, int r, int c) {
        return (r >= 0 && r < map.length) && (c >= 0 && c < map[0].length);
    }

    private static int findRootNode(int node, int[] union) {
        if (union[node] == 0) {
            return node;
        }
        int root = findRootNode(union[node], union);
        union[node] = root;
        return root;
    }

    private static class Bridge {
        int sourceIsland;
        int destIsland;
        int distance;

        public Bridge(int sourceIsland, int destIsland, int distance) {
            this.destIsland = destIsland;
            this.sourceIsland = sourceIsland;
            this.distance = distance;
        }
    }
}
