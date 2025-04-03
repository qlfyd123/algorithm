package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/17143">17143. 낚시왕</a>
 * */
public class BOJ17143 {

    private static final Set<Shark> sharks = new HashSet<>();
    private static Shark[][] map;
    private static int R, C, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Shark[R + 1][C + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r, c, s, d, z);
            sharks.add(shark);
            map[r][c] = shark;
        }

        int sum = 0;
        for (int i = 1; i <= C; i++) {
            sum += catchFish(i);
            moveSharks();
        }
        System.out.println(sum);
    }

    private static int catchFish(int i) {
        for (int j = 1; j <= R; j++) {
            if (map[j][i] != null) {
                Shark shark = map[j][i];
                sharks.remove(shark);
                map[j][i] = null;
                return shark.z;
            }
        }
        return 0;
    }

    private static void moveSharks() {
        Set<Shark> eaten = new HashSet<>();
        Shark[][] newMap = new Shark[R + 1][C + 1];
        for (Shark shark : sharks) {
            switch (shark.d) {
                case 1, 2 -> {
                    int dis = shark.s % (2 * (R - 1));
                    while (dis > 0) {
                        if (shark.r == 1 && shark.d == 1) {
                            shark.d = 2;
                        } else if (shark.r == R && shark.d == 2) {
                            shark.d = 1;
                        }
                        if (shark.d == 1) {
                            shark.r--;
                        } else {
                            shark.r++;
                        }
                        dis--;
                    }
                }
                case 3, 4 -> {
                    int dis = shark.s % (2 * (C - 1));
                    while (dis > 0) {
                        if (shark.c == 1 && shark.d == 4) {
                            shark.d = 3;
                        } else if (shark.c == C && shark.d == 3) {
                            shark.d = 4;
                        }
                        if (shark.d == 3) {
                            shark.c++;
                        } else {
                            shark.c--;
                        }
                        dis--;
                    }
                }
            }
            if (isBigger(shark, eaten, newMap)) {
                newMap[shark.r][shark.c] = shark;
            }
        }
        sharks.removeAll(eaten);
        map = newMap;
    }

    private static boolean isBigger(Shark shark, Set<Shark> eaten, Shark[][] newMap) {
        if (newMap[shark.r][shark.c] != null) {
            Shark other = newMap[shark.r][shark.c];
            if (shark.z > other.z) {
                eaten.add(other);
                return true;
            } else {
                eaten.add(shark);
                return false;
            }
        }
        return true;
    }

    private static class Shark {

        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
