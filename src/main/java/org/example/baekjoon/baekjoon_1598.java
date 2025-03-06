package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1598 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }
        int flag = 1;
        int distance = 0;

        while (x - flag > 3) {
            flag += 4;
        }
        int disX = x - flag;
        int flagX = flag;

        while (y - flag > 3) {
            flag += 4;
        }
        int disY = y - flag;
        int temp = flag - flagX;

        distance = (int) Math.abs(disX - disY) + temp / 4;

        System.out.println(distance);
    }
}
