package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/16928">백준 16928번: 뱀과 사다리 게임</a>
 * */
public class BOJ16928 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] ladder = new int[101];
        int[] snake = new int[101];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ladder[start] = end;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snake[start] = end;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        int[] map = new int[101];
        Arrays.fill(map, Integer.MAX_VALUE);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0];
            int count = current[1];

            if (position == 100) {
                System.out.println(count);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPosition = position + i;
                if (nextPosition > 100) {
                    continue;
                }

                if (ladder[nextPosition] != 0) {
                    nextPosition = ladder[nextPosition];
                } else if (snake[nextPosition] != 0) {
                    nextPosition = snake[nextPosition];
                }

                if (map[nextPosition] > count + 1) {
                    map[nextPosition] = count + 1;
                    queue.add(new int[]{nextPosition, count + 1});
                }
            }
        }
    }
}
