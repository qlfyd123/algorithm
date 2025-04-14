package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/1976">1976. 여행 가자</a>
 */
public class BOJ1976 {

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        int[] union = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            union[i] = i;
        }
        for (int i = 1; i < N + 1; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < N + 1; j++) {
                int n = Integer.parseInt(input.nextToken());
                if (n == 1) {
                    int root2 = findRoot(j, union);
                    int root = findRoot(i, union);
                    if (root < root2) {
                        union[root2] = root;
                    } else if (root > root2) {
                        union[root] = root2;
                    }
                }
            }
        }
        String[] tour = br.readLine().split(" ");

        int root = findRoot(Integer.parseInt(tour[0]), union);
        for (int i = 1; i < tour.length; i++) {
            int n = Integer.parseInt(tour[i]);
            int root2 = findRoot(n, union);
            if (root != root2) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static int findRoot(int n, int[] union) {
        if (union[n] == n) {
            return n;
        } else {
            return findRoot(union[n], union);
        }
    }
}