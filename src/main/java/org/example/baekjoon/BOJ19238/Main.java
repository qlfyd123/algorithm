package org.example.baekjoon.BOJ19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int flag = Integer.parseInt(st.nextToken());
                if (flag == 1) {
                    map[i][j] = -1;
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        Taxi taxi = new Taxi(fuel, x, y);
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int[] source = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
            int[] dest = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
            customers.add(new Customer(source, dest));
        }

        while (M-- > 0) {

            Customer customer = findNextCustomer(customers, taxi, map);

            if (customer == null) {
                System.out.println(-1);
                return;
            }

            taxi.x = customer.source[0];
            taxi.y = customer.source[1];
            taxi.fuel -= customer.dis;

            if (taxi.fuel < 0) {
                System.out.println(-1);
                return;
            }
            int costToDest = findMinDistanceToDest(taxi, map, customer.dest);

            if (costToDest > taxi.fuel || costToDest == -1) {

                System.out.println(-1);
                return;
            } else {
                taxi.x = customer.dest[0];
                taxi.y = customer.dest[1];
                taxi.fuel += costToDest;
            }
            customers.remove(customer);
        }

        System.out.println(taxi.fuel);
    }

    private static class Customer {

        int[] source;
        int[] dest;
        int dis;

        public Customer(int[] source, int[] dest) {
            this.source = source;
            this.dest = dest;
        }

    }

    private static class Taxi {

        int fuel;
        int x, y;

        public Taxi(int fuel, int x, int y) {
            this.fuel = fuel;
            this.x = x;
            this.y = y;
        }

    }

    private static Customer findNextCustomer(List<Customer> customers, Taxi taxi, int[][] map) {

        //dp로 최단거리 탐색
        int[][] cost = findMinDistance(taxi, map);

        //실제 택시를 태울 승객 결정
        int minDistance = Integer.MAX_VALUE;
        Customer customer = null;
        for (Customer temp : customers) {
            int dis = cost[temp.source[0]][temp.source[1]];
            if (dis < minDistance) {
                if (dis == 0) {
                    if (taxi.x != temp.source[0] || taxi.y != temp.source[1]) {
                        continue;
                    }
                }
                minDistance = dis;
                customer = temp;
            } else if (dis == minDistance) {
                if (customer.source[0] == temp.source[0]) {
                    customer = customer.source[1] > temp.source[1] ? temp : customer;
                } else {
                    customer = customer.source[0] > temp.source[0] ? temp : customer;
                }
            }
        }

        if (customer != null) {
            customer.dis = minDistance;
        }
        return customer;
    }

    private static int findMinDistanceToDest(Taxi taxi, int[][] map, int[] dest) {
        final int[] dx = {0, 0, 1, -1};
        final int[] dy = {1, -1, 0, 0};
        int[][] cost = new int[map.length][map.length];

        if (taxi.x == dest[0] && taxi.y == dest[1]) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(taxi.x * map.length + taxi.y);

        while (!queue.isEmpty() && cost[dest[0]][dest[1]] == 0) {
            int pos = queue.poll();
            int currentX = pos / map.length;
            int currentY = pos % map.length;
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];
                if (inRange(map.length, nextX, nextY)) {
                    if (cost[nextX][nextY] == 0 && map[nextX][nextY] != -1) {
                        cost[nextX][nextY] = cost[currentX][currentY] + 1;
                        queue.add(nextX * map.length + nextY);
                    }
                }
            }
        }
        return cost[dest[0]][dest[1]] == 0 ? -1 : cost[dest[0]][dest[1]];
    }

    private static int[][] findMinDistance(Taxi taxi, int[][] map) {
        final int[] dx = {0, 0, 1, -1};
        final int[] dy = {1, -1, 0, 0};
        int[][] cost = new int[map.length][map.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(taxi.x * map.length + taxi.y);

        while (!queue.isEmpty()) {
            int pos = queue.poll();
            int currentX = pos / map.length;
            int currentY = pos % map.length;
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];
                if (inRange(map.length, nextX, nextY)) {
                    if (cost[nextX][nextY] == 0 && map[nextX][nextY] != -1) {
                        cost[nextX][nextY] = cost[currentX][currentY] + 1;
                        queue.add(nextX * map.length + nextY);
                    }
                }
            }
        }
        cost[taxi.x][taxi.y] = 0;
        return cost;
    }

    private static boolean inRange(int N, int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
