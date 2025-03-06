package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_12931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int increaseCount = 0;
        int maxCount = 0;
        while (st.hasMoreTokens()) {
            double num = Integer.parseInt(st.nextToken());
            int count = 0;
            while (num > 0) {
                if (num / 2 % 1 == 0) {
                    num /= 2;
                    count++;
                } else {
                    num--;
                    increaseCount++;
                }
            }
            if (maxCount < count) {
                maxCount = count;
            }
        }
        System.out.println(increaseCount + maxCount);
    }
}
