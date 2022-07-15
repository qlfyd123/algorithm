package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Meat {
        int weight = 0;
        int price = 0;
        Queue<Integer> weightQueue = new PriorityQueue<>();


        private Meat(int weight, int price) {
            this.weight = weight;
            this.price = price;
            weightQueue.add(weight);

        }

        public static Meat MeetOf(int price, int weight) {
            return new Meat(weight, price);
        }

        public void add(int weight) {
            if (this.weight < weight) {
                this.weight = weight;
            }
            weightQueue.add(weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int needMeatWeight = Integer.parseInt(st.nextToken());

        List<Meat> meat = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            if (meat[price] != null) {
                meat[price].add(weight);
            } else {
                meat[price] = Meat.MeetOf(weight);
            }
        }
        int totalWeight = 0;
        int price = 0;
        outer:
        for (int i = 0; i < 100000; i++) {
            if (meat[i] != null) {
                Meat meatTemp = meat[i];
                int priceTemp = 0;
                while (!meatTemp.weightQueue.isEmpty()) {
                    totalWeight += meatTemp.weightQueue.poll();
                    priceTemp += i;
                    if (totalWeight >= needMeatWeight) {
                        price = priceTemp;
                        break outer;
                    }
                }
            }
        }

        if (totalWeight >= needMeatWeight) {
            System.out.println(price);
        } else {
            System.out.println(-1);
        }
    }

}
