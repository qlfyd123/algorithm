package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_17521 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long money = Integer.parseInt(st.nextToken());

        int[] stock = new int[n];

        for (int i = 0; i < n; i++) {
            stock[i] = Integer.parseInt(br.readLine());
        }

        long coin = 0;
        int remain = 0;

        for (int i = 0; i < n - 1; i++) {

            if (stock[i] < stock[i + 1] && coin == 0) {

                coin = money / stock[i];
                remain = (int) money % stock[i];
            } else if (stock[i] > stock[i + 1] && coin != 0) {

                money = coin * stock[i] + remain;
                coin = 0;
            }

            if (i == n - 2 && coin != 0) {
                long temp = coin * stock[i + 1] + remain;
                if (money < temp) {
                    money = temp;
                }
            }

        }

        System.out.println(money);
    }
}
