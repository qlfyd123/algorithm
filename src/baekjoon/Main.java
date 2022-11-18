package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Item {
        int weight, cost;

        public Item(int x, int y) {
            weight = x;
            cost = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Queue<Item> maxCost = new PriorityQueue<>((o1, o2) -> o2.cost - o1.cost);
        Item[] minWeight = new Item[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            Item item = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            maxCost.add(item);
            minWeight[i] = item;
        }
        Arrays.sort(minWeight, Comparator.comparingInt(o -> o.weight));
        int max = 0;
        int weight = 0;
        int cost = 0;
        while (!maxCost.isEmpty()) {
            Item maxCostItem = maxCost.peek();
            if (weight + maxCostItem.weight > K) {
                for (int i = 0; i < N; i++) {
                    int weightTemp = weight + minWeight[i].weight;
                    if (weightTemp < K) {
                        weight = weightTemp;
                        cost += minWeight[i].cost;
                    } else if (weightTemp == K) {
                        max = Math.max(max, cost + minWeight[i].cost);
                        cost = 0;
                        weight = 0;
                        break;
                    } else {
                        max = Math.max(max, cost);
                        cost = 0;
                        weight = 0;
                        break;
                    }
                }
            } else if (weight + maxCostItem.weight == K) {
                max = Math.max(max, cost + maxCostItem.cost);
                cost = 0;
                weight = 0;
                maxCost.poll();
            } else {
                cost += maxCostItem.cost;
                weight += maxCostItem.weight;
                maxCost.poll();
            }
        }
        System.out.println(max);
    }
}
