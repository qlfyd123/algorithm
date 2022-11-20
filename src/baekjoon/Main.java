package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Item implements Comparable<Item> {
        int weight, cost;
        float efficiency;

        public Item(int x, int y) {
            weight = x;
            cost = y;
            efficiency = (float) cost / weight;
        }

        @Override
        public int compareTo(Item o) {
            if (this.efficiency == o.efficiency)
                return this.weight - o.weight;
            else if (this.efficiency > o.efficiency) return -1;
            else return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            Item item = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            items[i] = item;
        }
        Arrays.sort(items);
        int max = 0;
        int weight, cost;
        for (int i = 0; i < N; i++) {
            Item root = items[i];
            weight = root.weight;
            cost = root.cost;
            for (int j = i + 1; j < N; j++) {
                Item temp = items[j];
                if (weight + temp.weight <= K) {
                    cost += temp.cost;
                    weight += temp.weight;
                    if (weight == K) {
                        break;
                    }
                }
            }
            max = Math.max(max, cost);
        }

        System.out.println(max);
    }
}