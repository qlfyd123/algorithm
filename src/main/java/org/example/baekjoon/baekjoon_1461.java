package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon_1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> bookLocationPlus = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> bookLocationMinus = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            int token = Integer.parseInt(st.nextToken());
            if (token < 0) {
                bookLocationMinus.add(token);
            } else {
                bookLocationPlus.add(token);
            }
        }
        boolean compare;
        if (bookLocationMinus.isEmpty()) {
            compare = true;
        } else if (bookLocationPlus.isEmpty()) {
            compare = false;
        } else {
            compare = bookLocationPlus.peek() > Math.abs(bookLocationMinus.peek());
        }

        int plusDistance;
        int minusDistance;

        if (compare) {
            plusDistance = calDistance(bookLocationPlus, true, M);
            minusDistance = calDistance(bookLocationMinus, false, M);
        } else {
            plusDistance = calDistance(bookLocationPlus, false, M);
            minusDistance = calDistance(bookLocationMinus, true, M);
        }

        System.out.println(plusDistance + minusDistance);
    }

    private static int calDistance(PriorityQueue<Integer> queue, boolean compare, int M) {
        int result = 0;
        if (compare) {
            boolean first = true;
            while (!queue.isEmpty()) {
                if (first) {
                    result += Math.abs(queue.peek());
                    first = false;
                } else {
                    result += 2 * Math.abs(queue.peek());
                }

                for (int i = 0; i < M; i++) {
                    if (!queue.isEmpty()) {
                        queue.poll();
                    }
                }
            }
        } else {
            while (!queue.isEmpty()) {
                result += 2 * Math.abs(queue.peek());

                for (int i = 0; i < M; i++) {
                    if (!queue.isEmpty()) {
                        queue.poll();
                    }
                }
            }
        }
        return result;
    }
}
