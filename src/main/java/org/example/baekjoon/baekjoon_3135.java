package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_3135 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int N = Integer.parseInt(br.readLine());

        int min = Math.abs(A - B) - 1;
        for (int i = 0; i < N; i++) {

            int herz = Integer.parseInt(br.readLine());
            int temp = Math.abs(B - herz);

            if (temp < min) {
                min = temp;
            }
        }

        System.out.print(min + 1);

    }
}
