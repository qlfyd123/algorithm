package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class baekjoon_15720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        Integer[] buger = new Integer[B];
        for (int i = 0; i < B; i++) {
            int price = Integer.parseInt(st.nextToken());
            buger[i] = price;
            sum += price;
        }

        st = new StringTokenizer(br.readLine());
        Integer[] sideMenu = new Integer[C];
        for (int i = 0; i < C; i++) {
            int price = Integer.parseInt(st.nextToken());
            sideMenu[i] = price;
            sum += price;
        }

        st = new StringTokenizer(br.readLine());
        Integer[] drink = new Integer[D];
        for (int i = 0; i < D; i++) {
            int price = Integer.parseInt(st.nextToken());
            drink[i] = price;
            sum += price;
        }

        Arrays.sort(buger, Comparator.reverseOrder());
        Arrays.sort(sideMenu, Comparator.reverseOrder());
        Arrays.sort(drink, Comparator.reverseOrder());
        int loop = min(B, C, D);
        int setPrice = 0;
        for (int i = 0; i < loop; i++) {
            setPrice += (buger[i] + sideMenu[i] + drink[i]) * 9 / 10;
        }
        for (int i = loop; i < B; i++) {
            setPrice += buger[i];

        }

        for (int i = loop; i < C; i++) {
            setPrice += sideMenu[i];

        }

        for (int i = loop; i < D; i++) {
            setPrice += drink[i];

        }

        System.out.println(sum);
        System.out.println(setPrice);
    }

    public static int min(int B, int C, int D) {
        int temp = Math.min(B, C);

        return Math.min(temp, D);
    }
}
