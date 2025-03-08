package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class BOJ10986 {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();
        long count = 0;
        long sum = 0;
        HashMap<Long, Long> modulus = new HashMap<>();
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            long mod = sum % M;
            if (mod == 0) {
                count++;
            }
            modulus.compute(mod, (k, v) -> v == null ? 1 : v + 1);
        }
        for (Entry<Long, Long> mod : modulus.entrySet()) {
            long value = mod.getValue();
            if (value > 1) {
                count += value * (value - 1) / 2;
            }
        }
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }
}
