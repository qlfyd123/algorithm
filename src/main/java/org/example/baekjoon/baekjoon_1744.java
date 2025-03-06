package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
/*
* https://www.acmicpc.net/problem/1744
* */
public class baekjoon_1744 {
    public static int queueSum(Queue<Integer> que) {
        int sum = 0;
        if (que.size() % 2 != 0) {
            sum += que.poll();
        }

        while (!que.isEmpty()) {
            int temp = que.poll() * que.poll();
            sum += temp;
        }

        return sum;
    }

    public static int queueSum(Queue<Integer> que, int zeroCount) {
        int sum = 0;
        if (que.size() % 2 != 0) {
            if (zeroCount > 0) {
                que.poll();
            } else {
                sum += que.poll();
            }
        }

        while (!que.isEmpty()) {
            int temp = que.poll() * que.poll();
            sum += temp;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int arrSize = Integer.parseInt(br.readLine());

        Queue<Integer> positive = new PriorityQueue<>();
        Queue<Integer> negative = new PriorityQueue<>(Collections.reverseOrder());
        int zeroCount = 0;
        int sum = 0;

        while (arrSize-- > 0) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                zeroCount++;
            } else if (num > 1) {
                positive.add(num);
            } else if (num == 1) {
                sum++;
            } else {
                negative.add(num);
            }
        }
        sum += queueSum(positive);
        sum += queueSum(negative, zeroCount);
        System.out.println(sum);
    }
}
