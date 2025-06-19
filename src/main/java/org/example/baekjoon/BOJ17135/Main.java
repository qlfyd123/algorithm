package org.example.baekjoon.BOJ17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        List<int[]> archers = combination(M);

        int maxKill = 0;
        for (int[] archer : archers) {
            int[][] copyMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                System.arraycopy(map[i], 0, copyMap[i], 0, M);
            }
            int killCount = 0;
            for (int round = 0; round < N; round++) {
                List<int[]> killedEnemies = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    int archerPos = archer[i];
                    int[] targetEnemy = findNearestEnemy(copyMap, archerPos);
                    if (targetEnemy[0] != -1 && targetEnemy[2] <= D) {
                        boolean alreadyKilled = false;
                        for (int[] enemy : killedEnemies) {
                            if (enemy[0] == targetEnemy[0] && enemy[1] == targetEnemy[1]) {
                                alreadyKilled = true;
                                break;
                            }
                        }
                        if (!alreadyKilled) {
                            killedEnemies.add(targetEnemy);
                        }

                    }
                }
                for (int[] killedEnemy : killedEnemies) {
                    copyMap[killedEnemy[0]][killedEnemy[1]] = 0;
                    killCount++;
                }
                moveEnemy(copyMap);
            }
            maxKill = Math.max(maxKill, killCount);
        }
        System.out.println(maxKill);
    }

    private static void moveEnemy(int[][] map) {
        int N = map.length;
        int M = map[0].length;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    if (i != N - 1) {
                        map[i + 1][j] = 1;
                    }
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int[] findNearestEnemy(int[][] map, int column) {
        int N = map.length;
        int M = map[0].length;
        int[] target = {-1, -1, -1};
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    int dis = Math.abs(N - i) + Math.abs(column - j);
                    if (dis < minDis || (dis == minDis && j < target[1])) {
                        minDis = dis;
                        target[0] = i;
                        target[1] = j;
                        target[2] = dis;
                    }
                }
            }
        }
        return target;
    }

    private static List<int[]> combination(int M) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                for (int k = j + 1; k < M; k++) {
                    result.add(new int[]{i, j, k});
                }
            }
        }
        return result;
    }
}
