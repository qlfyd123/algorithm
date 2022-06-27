package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_1246 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] eggPrice = new int[M];

        for (int i = 0; i < M; i++) {
            eggPrice[i] = Integer.parseInt(br.readLine());
        }

        int maxSale = 0;
        int maxEggPrice = 0;

        Arrays.sort(eggPrice);

        for (int i = 0; i < M; i++) {
            int totalSale;
            if (M - i < N) totalSale = eggPrice[i] * (M - i);
            else totalSale = eggPrice[i] * N;

            if (maxSale < totalSale) {
                maxSale = totalSale;
                maxEggPrice = eggPrice[i];
            }
        }

        String sb = maxEggPrice +
                " " +
                maxSale;
        System.out.println(sb);
    }
}
