package org.example.baekjoon;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * <a href="https://www.acmicpc.net/problem/1655">BOJ 1655 가운데를 말해요</a>
 * */
public class BOJ1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> right = new PriorityQueue<>();
        boolean push = true;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (push) {
                left.add(num);
                push = false;
            } else {
                right.add(num);
                push = true;
            }
            if (!right.isEmpty()) {
                if (left.peek() > right.peek()) {
                    left.add(right.poll());
                    right.add(left.poll());
                }
            }
            bw.write(String.valueOf(left.peek()));
            bw.newLine();
        }
        bw.close();
    }
}