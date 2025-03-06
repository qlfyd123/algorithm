package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class baekjoon_1758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] tip = new Integer[N];
        for(int i=0;i<N;i++){
            tip[i]=Integer.parseInt(br.readLine());
        }

        Arrays.sort(tip, Collections.reverseOrder());

        long result = 0;
        for (int i = 0; i < N; i++) {
            result += Math.max((tip[i] - i),0);
        }

        System.out.println(result);
    }
}
