package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1817 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        if (N == 0) {
            System.out.println(0);
            return;
        }
        st = new StringTokenizer(br.readLine());
        int temp = M;
        int box = 1;

        while (st.hasMoreTokens()) {
            int size = Integer.parseInt(st.nextToken());
            if (temp < size) {
                box++;
                temp = M - size;
            } else {
                temp -= size;
            }
        }

        System.out.println(box);
    }
}
