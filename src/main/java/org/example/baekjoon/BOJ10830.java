package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/10830">BOJ10830 행렬 제곱</a>
 * */
public class BOJ10830 {

    private static int N;
    private static HashMap<Long, int[][]> cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int[][] a = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        cache = new HashMap<>();
        int[][] result = matrixPow(a, b);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j] % 1000);
                if (j != N - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] matrixPow(int[][] a, long B) {
        if (B == 1) {
            return a;
        } else if (cache.containsKey(B)) {
            return cache.get(B);
        }

        int[][] result;
        if ((B & 1) == 1) {
            int[][] half = matrixPow(a, B / 2);
            int[][] halfPlus = matrixPow(a, B / 2 + 1);
            result = matrixMultiply(half, halfPlus);
        } else {
            int[][] half = matrixPow(a, B / 2);
            result = matrixMultiply(half, half);
        }
        cache.put(B, result);
        return result;
    }

    private static int[][] matrixMultiply(int[][] a, int[][] b) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long temp = 0;
                for (int k = 0; k < N; k++) {
                    temp += (long) a[i][k] * b[k][j];
                }
                result[i][j] = (int) (temp % 1000);
            }
        }
        return result;
    }
}
