package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142 {

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        List<int[]> virusList = new ArrayList<>();
        int blank = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int token = Integer.parseInt(st.nextToken());
                if (token == 2) {
                    virusList.add(new int[]{i, j});
                } else if (token == 0) {
                    blank++;
                }
                map[i][j] = token;
            }
        }
        List<List<Integer>> combinations = new ArrayList<>();
        combination(virusList, combinations, M, 0, new ArrayList<>());

        int minCycle = Integer.MAX_VALUE;
        for (List<Integer> combination : combinations) {
            Queue<int[]> virus = new LinkedList<>();
            virus.add(new int[]{-1, -1});
            for (int i : combination) {
                virus.add(virusList.get(i));
            }
            int result = bfs(virus, blank, N, map);
            if (result != -1) {
                minCycle = Math.min(minCycle, result);
            }
        }

        System.out.println(minCycle == Integer.MAX_VALUE ? -1 : minCycle);

    }

    private static void combination(List<int[]> virusList, List<List<Integer>> combinations, int M, int start, List<Integer> temp) {
        if (temp.size() == M) {
            combinations.add(new ArrayList<>(temp));
        } else {
            for (int i = start; i < virusList.size(); i++) {
                temp.add(i);
                combination(virusList, combinations, M, i + 1, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private static int bfs(Queue<int[]> virus, int blank, int N, int[][] map) {
        int cycle = 0;
        int[][] copiedMap = new int[N][N];
        for (int i = 0; i < map.length; i++) {
            int[] temp = map[i];
            System.arraycopy(temp, 0, copiedMap[i], 0, temp.length);
        }
        while (!virus.isEmpty() && blank > 0) {
            int[] curr = virus.poll();
            int r = curr[0];
            int c = curr[1];
            if (r == -1 && c == -1) {
                if (virus.isEmpty()) {
                    break;
                } else {
                    cycle++;
                    virus.add(curr);
                }
            } else {
                copiedMap[r][c] = 3;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        if (copiedMap[nr][nc] == 0) {
                            copiedMap[nr][nc] = 3;
                            blank--;
                            virus.add(new int[]{nr, nc});
                        } else if (copiedMap[nr][nc] == 2) {
                            copiedMap[nr][nc] = 3;
                            virus.add(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        return blank > 0 ? -1 : cycle;
    }
}
