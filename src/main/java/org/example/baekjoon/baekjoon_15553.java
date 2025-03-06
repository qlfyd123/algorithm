package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon_15553 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int friend = Integer.parseInt(st.nextToken());
        int matches = Integer.parseInt(st.nextToken());

        Queue<Integer> timeQueue = new PriorityQueue<>(Collections.reverseOrder());

        int temp = 0;
        while (friend-- > 0) {
            int time = Integer.parseInt(br.readLine());
            if (temp != 0) {
                timeQueue.add(time - temp);
            }
            temp = time;
        }

        int ans = matches - 1;
        while (matches-- > 1) {
            timeQueue.poll();
        }

        while (!timeQueue.isEmpty()) {
            ans += timeQueue.poll();
        }

        System.out.println(ans + 1);
    }
}

