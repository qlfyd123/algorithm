package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * <a href="https://www.acmicpc.net/problem/2166">BOJ 2166: 다각형의 면적</a>
 * */
public class BOJ2166 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<int[]> positions = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            positions.add(new int[]{x, y});
        }
        positions.add(new int[]{positions.get(0)[0], positions.get(0)[1]});

        long dis_1 = 0;
        long dis_2 = 0;
        for (int i = 0; i < N; i++) {
            int[] current = positions.get(i);
            int[] next = positions.get(i + 1);

            dis_1 += (long) current[0] * next[1];
            dis_2 += (long) current[1] * next[0];
        }

        long result = Math.abs(dis_1 - dis_2);

        if (result % 2 == 0) {
            System.out.println(result / 2 + ".0");
        } else {
            System.out.println(result / 2 + ".5");
        }
    }
}
