package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/13913">13913. 숨바꼭질 4</a>
 */
public class BOJ13913 {

    private static final Queue<Node> queue = new LinkedList<>();
    private static int N, K;
    private static int[] dp;
    private static Node answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        br.close();
        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }
        dp = new int[100001];
        queue.add(new Node(N));
        while (!queue.isEmpty() && dp[K] == 0) {
            bfs();
        }
        StringBuilder sb = new StringBuilder();
        calcDir(sb, answer);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(dp[K] + "\n");
        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();
    }

    private static void calcDir(StringBuilder sb, Node answer) {
        sb.insert(0, answer.position).insert(0, " ");
        if (answer.parent != null) {
            calcDir(sb, answer.parent);
        }
    }

    private static void bfs() {
        Node current = queue.poll();
        for (int i = -1; i <= 1; i += 2) {
            int nextPosition = current.position + i;
            teleport(current, nextPosition);
        }
        int nextPosition = current.position * 2;
        teleport(current, nextPosition);
    }

    private static void teleport(Node current, int nextPosition) {
        if (nextPosition >= 0 && nextPosition < dp.length) {
            if (dp[nextPosition] == 0) {
                if (nextPosition == K) {
                    dp[nextPosition] = dp[current.position] + 1;
                    answer = new Node(current, nextPosition);
                    return;
                }
                dp[nextPosition] = dp[current.position] + 1;
                queue.add(new Node(current, nextPosition));
            }
        }
    }

    private static class Node {

        public Node parent;
        public int position;

        public Node(Node parent, int position) {
            this.parent = parent;
            this.position = position;
        }

        public Node(int position) {
            this.position = position;
            this.parent = null;
        }
    }
}
