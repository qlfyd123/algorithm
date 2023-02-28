package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int totalDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] cityMap = new int[N][N];
        ArrayList<int[]> chickens = new ArrayList<>();
        List<int[]> houses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cityMap[i][j] = Integer.parseInt(st.nextToken());
                if (cityMap[i][j] == 2) {
                    chickens.add(new int[]{i, j, 0});
                } else if (cityMap[i][j] == 1) {
                    houses.add(new int[]{i, j});
                }
            }
        }

        List<int[]> selectedChicken = new LinkedList<>();
        for (int[] chicken : chickens) {
            selectedChicken.add(chicken);
            selectChicken(selectedChicken, chickens, houses);
            selectedChicken.remove(0);
        }
        System.out.println(totalDistance);
    }

    public static void selectChicken(List<int[]> selectedChicken, ArrayList<int[]> chickens, List<int[]> houses) {
        if (selectedChicken.size() == M) {
            int totalDis = 0;
            for (int[] house : houses) {
                int chickenRoad = Integer.MAX_VALUE;
                for (int[] chicken : selectedChicken) {
                    chickenRoad = Math.min(chickenRoad, Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]));
                }
                totalDis += chickenRoad;
            }
            totalDistance = Math.min(totalDis, totalDistance);
            selectedChicken.remove(M - 1);
        } else {
            for (int[] chicken : chickens) {
                selectedChicken.add(chicken);
                selectChicken(selectedChicken, chickens, houses);
            }
            selectedChicken.remove(selectedChicken.size() - 1);
        }
    }
}
