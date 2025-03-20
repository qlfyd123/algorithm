package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15683 {

    private static int N, M;
    private static String[][] map;
    private static List<int[]> cctvList;
    private static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cctvList = new ArrayList<>();
        map = new String[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = input[j];
                if (map[i][j].equals("1") || map[i][j].equals("2") || map[i][j].equals("3") || map[i][j].equals("4") || map[i][j].equals("5")) {
                    cctvList.add(new int[]{i, j, Integer.parseInt(map[i][j])});
                }
            }
        }
        br.close();
        dfs(0);
        System.out.println(minCount);
    }

    private static void dfs(int index) {
        if (index == cctvList.size()) {
            int count = check();
            minCount = Math.min(minCount, count);
        } else {
            int[] cctv = cctvList.get(index);
            List<int[]> mask = new ArrayList<>();
            switch (cctv[2]) {
                case 1 -> {
                    for (int i = 0; i < 4; i++) {
                        masking(i, cctv[0], cctv[1], mask);
                        dfs(index + 1);
                        unmasking(mask);
                    }
                }
                case 2 -> {
                    for (int i = 0; i < 2; i++) {
                        masking(i, cctv[0], cctv[1], mask);
                        masking(i + 2, cctv[0], cctv[1], mask);
                        dfs(index + 1);
                        unmasking(mask);
                    }
                }
                case 3 -> {
                    for (int i = 0; i < 4; i++) {
                        masking(i, cctv[0], cctv[1], mask);
                        masking((i + 1) % 4, cctv[0], cctv[1], mask);
                        dfs(index + 1);
                        unmasking(mask);
                    }
                }
                case 4 -> {
                    for (int i = 0; i < 4; i++) {
                        masking(i, cctv[0], cctv[1], mask);
                        masking((i + 1) % 4, cctv[0], cctv[1], mask);
                        masking((i + 2) % 4, cctv[0], cctv[1], mask);
                        dfs(index + 1);
                        unmasking(mask);
                    }
                }
                case 5 -> {
                    masking(0, cctv[0], cctv[1], mask);
                    masking(1, cctv[0], cctv[1], mask);
                    masking(2, cctv[0], cctv[1], mask);
                    masking(3, cctv[0], cctv[1], mask);
                    dfs(index + 1);
                }
            }
        }
    }

    private static void unmasking(List<int[]> mask) {
        for (int[] m : mask) {
            map[m[0]][m[1]] = "0";
        }
        mask.clear();
    }

    private static int check() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].equals("0")) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * cctv가 확인 가능한 부분을 마스킹하고 마스킹된 부분을 list에 저장합니다.
     *
     * @param dir 마스킹할 방향을 지정합니다. 0: 왼쪽, 1: 위쪽, 2: 오른쪽, 3: 아래쪽
     */
    private static void masking(int dir, int r, int c, List<int[]> mask) {
        switch (dir) {
            case 0 -> {
                for (int i = c; i >= 0; i--) {
                    if (map[r][i].equals("6")) {
                        break;
                    } else if (!map[r][i].equals("0")) {
                        continue;
                    }
                    map[r][i] = "#";
                    mask.add(new int[]{r, i});
                }
            }
            case 1 -> {
                for (int i = r; i >= 0; i--) {
                    if (map[i][c].equals("6")) {
                        break;
                    } else if (!map[i][c].equals("0")) {
                        continue;
                    }
                    map[i][c] = "#";
                    mask.add(new int[]{i, c});
                }
            }
            case 2 -> {
                for (int i = c; i < M; i++) {
                    if (map[r][i].equals("6")) {
                        break;
                    } else if (!map[r][i].equals("0")) {
                        continue;
                    }
                    map[r][i] = "#";
                    mask.add(new int[]{r, i});
                }
            }
            case 3 -> {
                for (int i = r + 1; i < N; i++) {
                    if (map[i][c].equals("6")) {
                        break;
                    } else if (!map[i][c].equals("0")) {
                        continue;
                    }
                    map[i][c] = "#";
                    mask.add(new int[]{i, c});
                }
            }
        }
    }

}
