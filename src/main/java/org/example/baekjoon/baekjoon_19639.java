package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
*https://www.acmicpc.net/problem/19639
* */
public class baekjoon_19639 {
    static int remainLife;
    static int maxLife;


    static class Object implements Comparable<Object> {
        int position;
        int life;


        @Override
        public int compareTo(Object o) {
            return this.life - o.life;
        }
    }

    static class HealPack extends Object {

        public HealPack(int position, int life) {
            this.position = position;
            this.life = life;
        }

        public int heal() {
            remainLife = Math.min(maxLife, remainLife + life);
            return position;
        }
    }

    static class Enemy extends Object {

        public Enemy(int position, int life) {
            this.position = position;
            this.life = life;
        }

        public int kill() {
            remainLife -= life;
            return -position;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        remainLife = Integer.parseInt(st.nextToken());
        maxLife = remainLife;
        Queue<Enemy> enemy = new PriorityQueue<>();
        Queue<HealPack> healPack = new PriorityQueue<>();

        for (int i = 1; i <= X; i++) {
            Enemy obj = new Enemy(i, Integer.parseInt(br.readLine()));
            enemy.add(obj);
        }

        for (int i = 1; i <= Y; i++) {
            HealPack obj = new HealPack(i, Integer.parseInt(br.readLine()));
            healPack.add(obj);
        }
        StringJoiner sj = new StringJoiner("\n");
        while (!enemy.isEmpty()) {
            if (enemy.peek().life >= remainLife) {
                try {
                    sj.add((String.valueOf(Objects.requireNonNull(healPack.poll()).heal())));
                } catch (NullPointerException e) {
                    System.out.println(0);
                    return;
                }
            } else {
                sj.add(String.valueOf(enemy.poll().kill()));
            }
        }
        while (!healPack.isEmpty()) {
            sj.add((String.valueOf(healPack.poll().heal())));
        }

        System.out.println(sj);
    }
}
