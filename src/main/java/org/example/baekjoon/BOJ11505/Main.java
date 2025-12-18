package org.example.baekjoon.BOJ11505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            numbers[i] = number;
        }

        SegmentTree tree = new SegmentTree(numbers);
        int TestCase = M + K;
        StringBuilder sb = new StringBuilder();
        while (TestCase-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int type = Integer.parseInt(st.nextToken());
            int source = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            if (type == 1) {
                tree.updateTree(source, dest);
            } else {
                sb.append(tree.query(source, dest)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static class SegmentTree {
        private static final int MOD = 1_000_000_007;
        long[] tree;
        int n;

        public SegmentTree(int[] numbers) {
            this.n = numbers.length;
            int size = 1;
            while (size < numbers.length) {
                size *= 2;
            }
            tree = new long[size * 2];
            Arrays.fill(tree, 1);
            buildTree(numbers, 1, 1, numbers.length);
        }

        private void buildTree(int[] numbers, int node, int start, int end) {
            if (start == end) {
                this.tree[node] = numbers[start - 1];
            } else {
                int mid = (start + end) / 2;
                buildTree(numbers, node * 2, start, mid);
                buildTree(numbers, node * 2 + 1, mid + 1, end);
                this.tree[node] = (this.tree[node * 2 + 1] * this.tree[node * 2]) % MOD;
            }
        }

        public void updateTree(int index, int value) {
            updateNode(1, 1, n, index, value);
        }

        private void updateNode(int node, int start, int end, int index, int value) {
            if (start == end) {
                this.tree[node] = value;
            } else {
                int mid = (start + end) / 2;

                if (index > mid) {
                    updateNode(node * 2 + 1, mid + 1, end, index, value);
                } else {
                    updateNode(node * 2, start, mid, index, value);
                }

                this.tree[node] = (this.tree[node * 2] * this.tree[node * 2 + 1]) % MOD;
            }
        }

        public long query(int start, int end) {
            return query(1, 1, this.n, start, end);
        }

        private long query(int node, int start, int end, int left, int right) {
            int mid = (start + end) / 2;
            if (left <= start && right >= end) {
                return this.tree[node];
            }
            if (left > mid) {
                return query(node * 2 + 1, mid + 1, end, left, right);
            } else if (right <= mid) {
                return query(node * 2, start, mid, left, right);
            } else {
                long leftRangeQuery = query(node * 2, start, mid, left, mid);
                long rightRangeQuery = query(node * 2 + 1, mid + 1, end, mid + 1, right);
                return (leftRangeQuery * rightRangeQuery) % MOD;
            }
        }
    }
}
