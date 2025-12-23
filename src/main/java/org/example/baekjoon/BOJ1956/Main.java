package org.example.baekjoon.BOJ1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] map = new int[V][V];

        for (int i = 0; i < V; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            map[start][end] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < V; k++) {
                    int startToEnd = map[j][k];
                    int startToMiddle = map[j][i];
                    int middleToEnd = map[i][k];

                    if (startToMiddle != Integer.MAX_VALUE && middleToEnd != Integer.MAX_VALUE) {
                        if (startToEnd > startToMiddle + middleToEnd) {
                            map[j][k] = startToMiddle + middleToEnd;
                        }
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) {
            min = Math.min(map[i][i], min);
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}
