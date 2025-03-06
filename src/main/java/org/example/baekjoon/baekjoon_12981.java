package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_12981 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int temp = Math.min(R, Math.min(G, B));
        int result = temp;

        R -= temp;
        G -= temp;
        B -= temp;

        result += R / 3;
        R = R % 3;
        result += G / 3;
        G = G % 3;
        result += B / 3;
        B = B % 3;

        System.out.println(result + partition(R, G, B));
    }

    private static int partition(int R, int G, int B) {
        int x, y;
        int count = 0;
        if (R == 0) {
            count++;
        }
        if (G == 0) {
            count++;
        }
        if (B == 0) {
            count++;
        }

        if (count == 3) {
            return 0;
        } else if (count == 2) {
            return 1;
        } else {
            if (R == 0) {
                x = G;
                y = B;
            } else if (G == 0) {
                x = R;
                y = B;
            } else {
                x = R;
                y = G;
            }

            if (x == 1 & y == 1) {
                return 1;
            } else {
                return 2;
            }
        }

    }
}
