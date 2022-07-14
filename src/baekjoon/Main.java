package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Meat implements Comparable<Meat> {
        int price = 0;
        int weight = 0;

        private Meat(int weight, int price) {
            this.price = price;
            this.weight = weight;
        }

        public static Meat MeetOf(int weight, int price) {
            return new Meat(weight, price);
        }


        @Override
        public int compareTo(Meat meat) {
            if (meat.price == this.price) {
                return meat.weight - this.weight;
            } else {
                return this.price - meat.price;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int needMeatWeight = Integer.parseInt(st.nextToken());

        Meat[] meat = new Meat[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meat[i] = Meat.MeetOf(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(meat);

        int flag = 0;
        int totalWeight = 0;
        int price = -1;
        int priceTemp = 0;
        int weightTemp = 0;
        while (totalWeight <= needMeatWeight) {
            if (flag == meat.length ) {
                price = -1;
            }
            if (priceTemp == flag) {
                weightTemp += meat[flag].weight;
            }
            totalWeight += meat[flag].weight + weightTemp;
            flag++;
            price = meat[flag].price;
        }

        double sale = meat[flag].weight / meat[flag].price;
        System.out.println(price);
    }

}
