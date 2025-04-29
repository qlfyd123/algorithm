package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * <a href="https://www.acmicpc.net/problem/2981">BOJ2981 검문</a>
 * */
public class BOJ2981 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Long> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers.add(Long.parseLong(br.readLine()));
        }
        numbers.sort(Long::compareTo);

        long gcd = numbers.get(1) - numbers.get(0);
        for (int i = 1; i < N - 1; i++) {
            long diff = numbers.get(i + 1) - numbers.get(i);
            gcd = gcd(Math.max(gcd, diff), Math.min(gcd, diff));
        }

        List<Long> factors = new ArrayList<>();
        for (long i = 2; i <= Math.sqrt(gcd); i++) {
            if (gcd % i == 0) {
                factors.add(i);
                if (gcd / i != i) {
                    factors.add(gcd / i);
                }
            }
        }
        factors.add(gcd);

        factors.sort(Long::compareTo);
        StringBuilder sb = new StringBuilder();
        for (long factor : factors) {
            sb.append(factor).append(" ");
        }
        System.out.println(sb);
    }

    private static long gcd(long a, long b) {

        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
