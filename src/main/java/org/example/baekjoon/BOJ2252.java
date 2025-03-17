package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252 {

    private static int N, M;
    private static Map<Integer, List<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int[] arr = new int[N + 1];
        map = new HashMap<>();
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[B]++;
            map.computeIfAbsent(A, k -> new ArrayList<>()).add(B);
        }
        br.close();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (arr[i] == 0) {
                queue.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            List<Integer> children = map.get(current);
            if (children != null) {
                for (int next : children) {
                    arr[next]--;
                    if (arr[next] == 0) {
                        queue.add(next);
                    }
                }
            }
            sb.append(current).append(" ");
        }
        System.out.println(sb);
    }
}
