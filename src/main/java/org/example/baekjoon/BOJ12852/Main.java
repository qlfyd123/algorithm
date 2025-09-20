package org.example.baekjoon.BOJ12852;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();

        int[] dp = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        queue.add(N);
        while (!queue.isEmpty()) {
            int num = queue.poll();

            if (num == 1) {
                break;
            }
            if (num % 3 == 0) {
                if (!visited[num / 3]) {
                    visited[num / 3] = true;
                    dp[num / 3] = num;
                    queue.add(num / 3);
                }
            }

            if (num % 2 == 0) {
                if (!visited[num / 2]) {
                    visited[num / 2] = true;
                    dp[num / 2] = num;
                    queue.add(num / 2);
                }
            }

            if (num > 0) {
                if (!visited[num - 1]) {
                    visited[num - 1] = true;
                    dp[num - 1] = num;
                    queue.add(num - 1);
                }
            }
        }

        int index = 1;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        while (dp[index] != 0) {
            sb.insert(0, " ").insert(0, dp[index]);
            index = dp[index];
            count++;
        }

        System.out.println(count);
        System.out.println(sb);
    }

}
