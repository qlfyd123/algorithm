package org.example.baekjoon.BOJ2343;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] records = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sum = Arrays.stream(records).sum();
        int min = Arrays.stream(records).max().getAsInt();
        int sol = 0;
        while (min <= sum) {
            int blueRaySize = (min + sum) / 2;
            boolean canRecord = canRecord(records, blueRaySize, M);
            if (!canRecord) {
                min = blueRaySize + 1;
            } else {
                sol = blueRaySize;
                sum = blueRaySize - 1;
            }
        }
        System.out.println(sol);
    }

    private static boolean canRecord(int[] records, int blueRaySize, int M) {
        int count = 0;
        int blueRayTime = 0;
        for (int record : records) {
            blueRayTime += record;
            if (blueRaySize < blueRayTime) {
                blueRayTime = record;
                count++;
            }
        }
        return count + 1 <= M;
    }

}
