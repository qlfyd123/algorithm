package org.example.baekjoon.BOJ11003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        Deque<Node> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < N + 1; i++) {

            int number = Integer.parseInt(st.nextToken());
            addNumber(deque, new Node(i, number));

            int standardIndex = (i - L + 1) <= 0 ? 1 : (i - L + 1);
            resizeDeque(deque, standardIndex);

            int min = deque.peekFirst().number;
            sb.append(min).append(" ");
        }
        System.out.println(sb);
    }

    private static void addNumber(Deque<Node> deque, Node input) {
        while (!deque.isEmpty()) {
            Node last = deque.peekLast();
            if (last.number > input.number) {
                deque.pollLast();
            } else {
                break;
            }
        }
        deque.addLast(input);
    }

    private static void resizeDeque(Deque<Node> deque, int index) {
        while (!deque.isEmpty()) {
            Node node = deque.peekFirst();
            if (node.index < index) {
                deque.pollFirst();
            } else {
                break;
            }
        }
    }

    private static class Node {
        int index;
        int number;

        public Node(int index, int number) {
            this.index = index;
            this.number = number;
        }

    }
}
