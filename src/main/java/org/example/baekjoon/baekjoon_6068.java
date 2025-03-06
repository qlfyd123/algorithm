package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 *
 *
 * https://www.acmicpc.net/problem/6068
 *
 * */
public class baekjoon_6068 {
    static class Work implements Comparable<Work> {
        int startTime;
        int endTime;

        public Work(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Work w) {
            if (w.endTime == this.endTime) {
                return w.startTime - this.startTime;
            }
            return w.endTime - this.endTime;
        }
    }

    public static Work createWork(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        return new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Work> workQueue = new PriorityQueue<>();

        while (N-- > 0) {
            workQueue.add(createWork(br));
        }

        int deadLine = 0;
        if (workQueue.peek() != null) {
            deadLine = workQueue.peek().endTime;
        }

        while (!workQueue.isEmpty()) {
            Work workTemp = workQueue.poll();
            deadLine = Math.min(deadLine, workTemp.endTime);
            deadLine -= workTemp.startTime;

        }

        if (deadLine >= 0) {
            System.out.println(deadLine);
        } else {
            System.out.println(-1);
        }

    }
}
