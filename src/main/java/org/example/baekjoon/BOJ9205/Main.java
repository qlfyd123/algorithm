package org.example.baekjoon.BOJ9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX_BEER = 20;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] cur = new int[2];
            cur[0] = Integer.parseInt(st.nextToken());
            cur[1] = Integer.parseInt(st.nextToken());
            List<int[]> store = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int[] loc = new int[2];
                loc[0] = Integer.parseInt(st.nextToken());
                loc[1] = Integer.parseInt(st.nextToken());
                store.add(loc);
            }
            int[] dest = new int[2];
            st = new StringTokenizer(br.readLine(), " ");
            dest[0] = Integer.parseInt(st.nextToken());
            dest[1] = Integer.parseInt(st.nextToken());

            boolean canReach = canReach(cur, dest);
            if (canReach) {
                sb.append("happy\n");
            } else {
                boolean[] visited = new boolean[n];
                Queue<int[]> queue = new LinkedList<>();
                queue.add(cur);
                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    if (canReach(current, dest)) {
                        canReach = true;
                        break;
                    }
                    for (int i = 0; i < n; i++) {
                        if (!visited[i] && canReach(current, store.get(i))) {
                            visited[i] = true;
                            queue.add(store.get(i));
                        }
                    }
                }
                if (canReach) {
                    sb.append("happy\n");
                } else {
                    sb.append("sad\n");
                }
            }
        }
        System.out.println(sb);
    }

    private static boolean canReach(int[] cur, int[] dest) {
        int distance = Math.abs(cur[0] - dest[0]) + Math.abs(cur[1] - dest[1]);
        return distance <= MAX_BEER * 50;
    }
}
