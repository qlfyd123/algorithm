package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Meat {
        int weight;
        int price;
        Queue<Integer> weightQueue = new PriorityQueue<>(Collections.reverseOrder());


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


        Map<Integer, Meat> meat = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            if (!meat.containsKey(price)) {
                meat.put(price, Meat.MeetOf(price, weight));
            } else {
                meat.get(price).add(weight);
            }
        }
        int totalWeight = 0;
        int price = -1;
        for (Map.Entry<Integer, Meat> entry : meat.entrySet()) {
            Meat meatTemp = entry.getValue();
            int priceTemp = 0;
            while (!meatTemp.weightQueue.isEmpty()) {
                totalWeight += meatTemp.weightQueue.poll();
                priceTemp += meatTemp.price;
                if (totalWeight >= needMeatWeight) {
                    if (price == -1) {
                        price = priceTemp;
                    } else {
                        price = Math.min(price, priceTemp);
                    }
                    break;
                }
            }
        }
        System.out.println(price);
    }
}
