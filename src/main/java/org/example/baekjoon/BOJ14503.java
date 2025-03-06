package org.example.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/14503">BOJ 14503 로봇청소기</a>
 */
public class BOJ14503 {
    private final static int NORTH = 0;
    private final static int EAST = 1;
    private final static int SOUTH = 2;
    private final static int WEST = 3;
    private static int X;
    private static int Y;
    private static int direction;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());
        int[][] room = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        int cleanedTile = 0;
        outer:
        while (true) {
            cleanedTile = cleanCurrDirection(room, cleanedTile);
            int nextTile = isDirtyTile(room);
            switch (nextTile) {
                case NORTH:
                    X -= 1;
                    direction = NORTH;
                    break;
                case EAST:
                    Y += 1;
                    direction = EAST;
                    break;
                case SOUTH:
                    X += 1;
                    direction = SOUTH;
                    break;
                case WEST:
                    Y -= 1;
                    direction = WEST;
                    break;
                case -1:
                    boolean moved = moveBackward(room);
                    if (!moved) {
                        break outer;
                    }
                    break;
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(cleanedTile));
        bw.close();
    }

    /**
     * @param room 방 구조 배열
     * @return 뒤로 이동했는지의 여부
     */
    private static boolean moveBackward(int[][] room) {
        int x, y;
        switch (direction) {
            case NORTH:
                x = X + 1;
                y = Y;
                break;
            case EAST:
                x = X;
                y = Y - 1;
                break;
            case SOUTH:
                x = X - 1;
                y = Y;
                break;
            case WEST:
                x = X;
                y = Y + 1;
                break;
            default:
                return false;
        }
        if (inBound(room, x, y)) {
            if (room[x][y] != 1) {
                X = x;
                Y = y;
                return true;
            }
        }
        return false;
    }

    /**
     * @return 주변 4칸의 더러운 타일의 위치 더러운 타일이 없다면 -1을 반환
     */
    private static int isDirtyTile(int[][] room) {
        for (int i = 3; i >= 0; i--) {
            int dir = (direction + i) % 4;
            if (checkTile(dir, room)) {
                return dir;
            }
        }
        return -1;
    }

    /**
     * @param dir 청소기의 방향
     * @return 해당 방향의 타일이 더러운 타일인지 반환
     */
    private static boolean checkTile(int dir, int[][] room) {
        int x, y;
        switch (dir) {
            case 0:
                x = X - 1;
                y = Y;
                break;
            case 1:
                x = X;
                y = Y + 1;
                break;
            case 2:
                x = X + 1;
                y = Y;
                break;
            case 3:
                x = X;
                y = Y - 1;
                break;
            default:
                x = X;
                y = Y;
        }
        if (inBound(room, x, y)) {
            return room[x][y] == 0;
        }
        return false;
    }

    /**
     * @return 해당 위치가 방의 범위 안에 속해있는지를 반환
     */
    private static boolean inBound(int[][] room, int X, int Y) {
        if (X < 0 | X >= room.length) {
            return false;
        }
        return Y >= 0 & Y < room[0].length;
    }

    /**
     * @param cleanedTile 청소한 타일의 갯수
     */
    private static int cleanCurrDirection(int[][] room, int cleanedTile) {
        if (room[X][Y] == 0) {
            room[X][Y] = 2;
            cleanedTile++;
        }
        return cleanedTile;
    }
}
