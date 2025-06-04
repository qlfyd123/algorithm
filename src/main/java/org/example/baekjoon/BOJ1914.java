package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

/**
 * <a href="https://www.acmicpc.net/problem/1914">백준 1914번: 하노이 탑</a>
 * */
public class BOJ1914 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (N <= 20) {
            StringBuilder sb = new StringBuilder();
            hanoi(N, 1, 3, 2, sb);

            bw.write((int) (Math.pow(2, N) - 1) + "\n");
            bw.write(sb.toString());
            bw.flush();
        } else {
            BigInteger count = BigInteger.valueOf(2).pow(N).subtract(BigInteger.ONE);
            bw.write(String.valueOf(count));
            bw.flush();
        }
        bw.close();

    }

    private static void hanoi(int n, int from, int to, int other, StringBuilder sb) {
        if (n == 1) {
            sb.append(from).append(" ").append(to).append("\n");
            return;
        }

        hanoi(n - 1, from, other, to, sb);
        sb.append(from).append(" ").append(to).append("\n");
        hanoi(n - 1, other, to, from, sb);
    }
}
