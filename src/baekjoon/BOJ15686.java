package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @see <a href="https://www.acmicpc.net/problem/15686">BOJ15686 치킨 배달</a>
 * */
public class BOJ15686 {
    static class Position {
        int row, column;

        public Position(int r, int c) {
            row = r;
            column = c;
        }
    }

    static int N, M;
    static int totalDistance = Integer.MAX_VALUE;
    static boolean[] visited;
    static Position[] houses;
    static Position[] chickens;
    static int[][] roadMap;
    static int housesSize = 0, chickensSize = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        houses = new Position[N * N];
        chickens = new Position[13];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    chickens[chickensSize++] = new Position(i, j);
                } else if (num == 1) {
                    houses[housesSize++] = new Position(i, j);
                }
            }
        }
        //각 가정집에서 치킨집으로의 거리
        roadMap = new int[housesSize][chickensSize];
        for (int i = 0; i < housesSize; i++) {
            Position house = houses[i];
            for (int j = 0; j < chickensSize; j++) {
                Position chicken = chickens[j];
                roadMap[i][j] = Math.abs(house.row - chicken.row) + Math.abs(house.column - chicken.column);
            }
        }
        visited = new boolean[chickensSize];
        combination(0, 0);
        System.out.println(totalDistance);
    }

    /**
     * 치킨집의 조합을 구하고 조합된 치킨집의 갯수가 M개라면 치킨 거리의 합을 구하고 이전에 구한 값과
     * 비교합니다
     *
     * @param combinationSize 현재 조합의 크기(처음 호출할 경우에는 0)
     * @param index           다음으로 탐색할 치킨집의 번호
     */
    public static void combination(int combinationSize, int index) {
        if (combinationSize == M) {
            int totalCickenRoad = 0;
            for (int i = 0; i < housesSize; i++) {
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
                combination(combinationSize + 1, index + 1);
                visited[i] = false;
            }
        }
    }
}
