package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int totalDistance = Integer.MAX_VALUE;
    static boolean[] visited;
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    static int[][] roadMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    chickens.add(new int[]{i, j, 0});
                } else if (num == 1) {
                    houses.add(new int[]{i, j});
                }
            }
        }
        roadMap = new int[houses.size()][chickens.size()];
        for (int i = 0; i < houses.size(); i++) {
            int[] house = houses.get(i);
            for (int j = 0; j < chickens.size(); j++) {
                int[] chicken = chickens.get(j);
                roadMap[i][j] = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
            }
        }
        visited = new boolean[chickens.size()];
        combination(0, 0);
        System.out.println(totalDistance);
    }

    public static void combination(int combinationSize, int index) {
        if (combinationSize == M) {
            int totalCickenRoad = 0;
            for (int i = 0, housesSize = houses.size(); i < housesSize; i++) {
                int chickenRoad = Integer.MAX_VALUE;
                for (int j = 0; j < visited.length; j++) {
                    if (visited[j]) {
                        chickenRoad = Math.min(chickenRoad, roadMap[i][j]);
                    }
                }
                totalCickenRoad += chickenRoad;
            }
            totalDistance = Math.min(totalCickenRoad, totalDistance);
        } else {
            for (int i = index; i < visited.length; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                combination(combinationSize + 1, index);
                visited[i] = false;
            }
        }
    }
}
