package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;
/*
* https://www.acmicpc.net/problem/9082
* */
public class baekjoon_9082 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] mineCount = new int[N];
            int index = 0;
            for (char c : br.readLine().toCharArray()) {
                mineCount[index] = c - 48;
                index++;
            }

            boolean[] mine = new boolean[N];
            index = 0;
            for (char c : br.readLine().toCharArray()) {
                mine[index] = !(c == '#');
                index++;
            }

            for (int i = 0; i < N; i++) {
                if (!mine[i]) {
                    boolean isZero = false;
                    for (int j = Math.max(0, i - 1); j <= Math.min(N - 1, i + 1); j++) {
                        if (mineCount[j] == 0) {
                            isZero = true;
                            break;
                        }
                    }

                    if (!isZero) {
                        mine[i] = true;
                        for (int j = Math.max(0, i - 1); j <= Math.min(N - 1, i + 1); j++) {
                            mineCount[j]--;
                        }
                    }
                }
            }
            System.out.println(
                    IntStream.range(0, N)
                            .mapToObj(value -> mine[value])
                            .filter(value -> value)
                            .count());
        }
    }
}
