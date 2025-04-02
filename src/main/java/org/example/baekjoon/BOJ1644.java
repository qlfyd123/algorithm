package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://www.acmicpc.net/problem/1644">1644. 소수의 연속합</a>
 */
public class BOJ1644 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] prime = new boolean[N + 1];
        Arrays.fill(prime, true);
        List<Integer> sum = new ArrayList<>();
        sum.add(0);
        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) {
                if (sum.isEmpty()) {
                    sum.add(i);
                } else {
                    sum.add(sum.get(sum.size() - 1) + i);
                }

                if (i * i <= N) {
                    int j = i << 1;
                    while (j <= N) {
                        prime[j] = false;
                        j += i;
                    }
                }
            }
        }
        int left = 0, right = 0;
        int count = 0;
        while (left <= right && right < sum.size()) {
            if (sum.get(right) - sum.get(left) == N) {
                count++;
                right++;
            } else if (sum.get(right) - sum.get(left) >= N) {
                left++;
            } else {
                right++;
            }
        }
        System.out.println(count);
    }
}
