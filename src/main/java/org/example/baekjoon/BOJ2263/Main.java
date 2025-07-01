package org.example.baekjoon.BOJ2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] inOrder = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
        int[] postOrder = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        findPreOrder(1, N, 1, N, inOrder, postOrder, sb);

        System.out.println(sb);
    }

    private static void findPreOrder(int inStart, int inEnd, int postStart, int postEnd, int[] inOrder, int[] postOrder, StringBuilder sb) {
        if (inStart > inEnd || postStart > postEnd) {
            return;
        }
        int root = postOrder[postEnd];
        sb.append(root).append(" ");
        int rootIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == root) {
                rootIndex = i;
                break;
            }
        }
        int leftSize = rootIndex - inStart;
        findPreOrder(inStart, rootIndex - 1, postStart, postStart + leftSize - 1, inOrder, postOrder, sb);
        findPreOrder(rootIndex + 1, inEnd, postStart + leftSize, postEnd - 1, inOrder, postOrder, sb);
    }
}
