package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href="https://www.acmicpc.net/problem/5639">5639. 이진 검색 트리</a>
 */
public class BOJ5639 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        Node root = null;
        Node node;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            long val = Long.parseLong(input);
            node = new Node(val);
            if (root == null) {
                root = node;
            } else {
                insert(root, node);
            }
        }
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        System.out.println(sb);
    }

    private static void insert(Node current, Node node) {
        if (node.val < current.val) {
            if (current.left == null) {
                current.left = node;
            } else {
                insert(current.left, node);
            }
        } else {
            if (current.right == null) {
                current.right = node;
            } else {
                insert(current.right, node);
            }
        }
    }

    private static void dfs(Node node, StringBuilder sb) {
        if (node != null) {
            if (isLeaf(node)) {
                sb.append(node.val).append("\n");
            } else {
                dfs(node.left, sb);
                dfs(node.right, sb);
                sb.append(node.val).append("\n");
            }
        }
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private static class Node {

        long val;
        Node left;
        Node right;

        Node(long val) {
            this.val = val;
        }
    }
}
