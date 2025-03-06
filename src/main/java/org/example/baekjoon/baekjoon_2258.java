package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

https://www.acmicpc.net/problem/2258

*/
public class baekjoon_2258 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int needMeatWeight = Integer.parseInt(st.nextToken());


        int[][] meat = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            meat[i][0] = Integer.parseInt(st.nextToken());
            meat[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meat, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            } else {
                return o1[1] - o2[1];
            }
        });


        int totalWeight = 0;
        int price = Integer.MAX_VALUE;
        int priceTemp = 0;
        boolean possible = false;
        for (int i = 0; i < N; i++) {
            totalWeight += meat[i][0];

            if (i > 0 && meat[i][1] == meat[i - 1][1]) {
                priceTemp += meat[i][1];
            } else {
                priceTemp = meat[i][1];
            }
            if (totalWeight >= needMeatWeight) {
                price = Math.min(price, priceTemp);
            }
        }

        if (totalWeight < needMeatWeight) {
            System.out.println(-1);
        } else {
            System.out.println(price);
        }
    }
}
