package org.example.baekjoon.BOJ14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            int[] temp = map[i];
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                temp[j] = Integer.parseInt(st.nextToken());
            }
        }
        int sum = 0;
        sum += checkHorizontalRoads(map, L);
        sum += checkVerticalRoads(map, L);

        System.out.println(sum);
    }

    private static int checkHorizontalRoads(int[][] map, int L) {
        int compatibleRoadCount = 0;
        for (int[] ints : map) {
            boolean isCompatibleRoad = isCompatibleRoad(ints, L);
            if (isCompatibleRoad) {
                compatibleRoadCount++;
            }
        }

        return compatibleRoadCount;
    }

    private static int checkVerticalRoads(int[][] map, int L) {
        int compatibleRoadCount = 0;

        for (int i = 0; i < map.length; i++) {

            int[] road = new int[map.length];

            // 2차원 배열을 세로 도로 배열로 매핑
            int index = 0;
            for (int[] ints : map) {
                road[index] = ints[i];
                index++;
            }

            boolean isCompatibleRoad = isCompatibleRoad(road, L);
            if (isCompatibleRoad) {
                compatibleRoadCount++;
            }
        }

        return compatibleRoadCount;
    }

    private static boolean isCompatibleRoad(int[] road, int L) {
        // 이전 경사로 상태 저장변수
        String prevRunWay = "init";
        for (int currentRoadXPosition = 0; currentRoadXPosition < road.length; currentRoadXPosition++) {

            // 경사로를 연속해서 설치하는 경우 내려가는 경사로를 설치한 이후 바로 올라가는 경사로를 설치하는 경우 경사로가 겹치기 때문에 설치 불가
            if (!prevRunWay.equals(DESC) && canBuildAscRunway(road, currentRoadXPosition, L)) {
                currentRoadXPosition += (L - 1);
                prevRunWay = ASC;
                continue;
            }

            if (canBuildDescRunway(road, currentRoadXPosition, L)) {
                currentRoadXPosition += (L - 1);
                prevRunWay = DESC;
                continue;
            }

            // 경사로를 설치하지 않은 경우 이전 경사로 상태 초기화
            prevRunWay = "init";
            int nextPosition = currentRoadXPosition + 1;
            if (nextPosition < road.length) {
                if (road[currentRoadXPosition] != road[nextPosition]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canBuildDescRunway(int[] road, int start, int L) {
        int endOfRunway = start + L;
        if (endOfRunway >= road.length) {
            return false;
        }

        if (road[start] - road[endOfRunway] == 1) {
            return isSmoothRange(road, start + 1, endOfRunway);
        } else {
            return false;
        }
    }

    private static boolean canBuildAscRunway(int[] road, int start, int L) {
        int endOfRunway = start + L;
        if (endOfRunway >= road.length) {
            return false;
        }

        if (road[endOfRunway] - road[start] == 1) {
            return isSmoothRange(road, start, endOfRunway - 1);
        } else {
            return false;
        }
    }

    private static boolean isSmoothRange(int[] road, int start, int end) {
        int prevRoadYPosition = road[start];
        for (int i = start; i <= end; i++) {
            if (prevRoadYPosition != road[i]) {
                return false;
            }
        }
        return true;
    }
}
