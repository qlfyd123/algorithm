package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class baekjoon_11508 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] price = new Integer[N];

        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            price[i] = temp;
        }

        Arrays.sort(price, Collections.reverseOrder());

        int totalPrice = 0;
        for (int i =0; i <N; i++) {
            if (i % 3 != 2) {
                totalPrice += price[i];
            }
        }

        System.out.println(totalPrice);


    }
}
