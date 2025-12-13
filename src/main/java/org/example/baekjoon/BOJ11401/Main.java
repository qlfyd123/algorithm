package org.example.baekjoon.BOJ11401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        long numerator = factorialModular(N);

        long denominator = powerModular((factorialModular(K) * factorialModular(N - K)) % MOD, MOD - 2);
        long result = (numerator * denominator) % MOD;

        System.out.println(result);
    }

    private static long factorialModular(long k) {
        long fact = 1;
        for (int i = 1; i <= k; i++) {
            fact *= i;
            fact %= MOD;
        }
        return fact % MOD;
    }

    private static long powerModular(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result % MOD;
    }
}
