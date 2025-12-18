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
                sb.append(tree.getRangeMultiple(source, dest)).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static class SegmentTree {
        private static final int MOD = 1_000_000_007;
        long[] tree;

        public SegmentTree(int[] numbers) {
            int size = 1;
            while (size < numbers.length) {
                size *= 2;
            }
            tree = new long[size * 2];
            Arrays.fill(tree, -1);
            int index = tree.length / 2;
            for (int i = 0; i < numbers.length; i++, index++) {
                tree[index] = numbers[i];
            }
            buildTree(1);
        }

        private void buildTree(int node) {
            if (!isLeafNode(node)) {
                buildTree(node * 2);
                buildTree(node * 2 + 1);
                long left = this.tree[node * 2] == -1 ? 1 : this.tree[node * 2];
                long right = this.tree[node * 2 + 1] == -1 ? 1 : this.tree[node * 2 + 1];
                this.tree[node] = (left * right) % MOD;
            }
        }

        public void updateTree(int index, int value) {
            int targetNode = tree.length / 2 + index - 1;
            this.tree[targetNode] = value;
            updateNode(targetNode);
        }

        private void updateNode(int node) {
            if (node == 0) {
                return;
            }

            if (!isLeafNode(node)) {
                this.tree[node] = (this.tree[node * 2] * this.tree[node * 2 + 1]) % MOD;
            }
            updateNode(node / 2);
        }

        private boolean isLeafNode(int node) {
            return this.tree.length / 2 <= node;
        }

        public long getRangeMultiple(int start, int end) {
            return getFragmentValue(start, end, 1, tree.length / 2, 1);
        }

        private long getFragmentValue(int start, int end, int nodeRangeStart, int nodeRangeEnd, int node) {
            if (start == nodeRangeStart && end == nodeRangeEnd) {
                return this.tree[node];
            }
            int mid = (nodeRangeStart + nodeRangeEnd) / 2;
            if (end <= mid) {
                return getFragmentValue(start, end, nodeRangeStart, mid, node * 2);
            } else if (start > mid) {
                return getFragmentValue(start, end, mid + 1, nodeRangeEnd, node * 2 + 1);
            } else {
                return (getFragmentValue(start, mid, nodeRangeStart, mid, node * 2) *
                        getFragmentValue(mid + 1, end, mid + 1, nodeRangeEnd, node * 2 + 1)) % MOD;
            }
        }
    }
}
