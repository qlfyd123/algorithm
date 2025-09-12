package org.example.baekjoon.BOJ2749;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private final static int MOD = 1000000;
    private final static int PISANO_PERIOD = 1500000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        long remainder = 0;
        for (int i = 0; i < N.length(); i++) {
            remainder = (remainder * 10 + (N.charAt(i) - '0')) % PISANO_PERIOD;
        }

        int[] Fibonacci = new int[PISANO_PERIOD + 1];
        Fibonacci[0] = 0;
        Fibonacci[1] = 1;

        for (int i = 2; i <= PISANO_PERIOD; i++) {
            Fibonacci[i] = (Fibonacci[i - 1] + Fibonacci[i - 2]) % MOD;
        }

        System.out.println(Fibonacci[(int) remainder]);
    }
}