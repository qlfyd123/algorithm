package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/1600">BOJ 1600 말이 되고픈 원숭이</a>
 * */
public class BOJ1600 {
    private final static int[][] likeHorse = {
            {1, 2},
            {-1, 2},
            {1, -2},
            {-1, -2},
            {2, 1},
            {2, -1},
            {-2, 1},
            {-2, -1}};
    private final static int[][] likeMonkey = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][] world = new int[H][W];
        int[][][] visited = new int[K + 1][H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                boolean isWall = Integer.parseInt(st.nextToken()) == 1;
                if (isWall) world[i][j] = -1;
            }
        }
        Queue<int[]> sequence = new LinkedList<>();
        sequence.add(new int[]{0, 0, K});
        while (!sequence.isEmpty()) {
            int[] currLocation = sequence.poll();
            if (currLocation[2] > 0) {
                horse(currLocation, world, sequence, visited);
            }
            monkey(currLocation, world, sequence, visited);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (H == 1 & W == 1) {
            bw.write(String.valueOf(0));
        } else {
            int result = Integer.MAX_VALUE;
            for (int i = 0; i <= K; i++) {
                if (visited[i][H - 1][W - 1] == 0) {
                    continue;
                }
                result = Math.min(result, visited[i][H - 1][W - 1]);
            }
            if (result == Integer.MAX_VALUE) {
                result = -1;
            }
            bw.write(String.valueOf(result));
        }
        bw.close();
    }

    /**
     * 인접한 4개의 칸으로만 이동
     *
     * @param currLocation 현재 위치와 말처럼 이동할 수 있는 남은 횟수
     * @param world        입력받은 월드
     * @param sequence     넓이 탐색을 위한 큐
     * @param visited      방문 확인 배열
     */
    private static void monkey(int[] currLocation, int[][] world, Queue<int[]> sequence, int[][][] visited) {
        int cur_X = currLocation[0];
        int cur_Y = currLocation[1];
        int curHorseCount = currLocation[2];
        for (int i = 0; i < 4; i++) {
            int x = currLocation[0] + likeMonkey[i][0];
            int y = currLocation[1] + likeMonkey[i][1];
            if (inBound(world.length, world[0].length, x, y)) {
                if (visited[curHorseCount][x][y] == 0 & world[x][y] != -1) {
                    visited[curHorseCount][x][y] = visited[curHorseCount][cur_X][cur_Y] + 1;
                    sequence.add(new int[]{x, y, curHorseCount});
                }
            }
        }
    }

    /**
     * 체스의 나이트처럼 이동
     */
    private static void horse(int[] currLocation, int[][] world, Queue<int[]> sequence, int[][][] visited) {
        int cur_X = currLocation[0];
        int cur_Y = currLocation[1];
        int curHorseCount = currLocation[2];
        for (int i = 0; i < 8; i++) {
            int x = currLocation[0] + likeHorse[i][0];
            int y = currLocation[1] + likeHorse[i][1];
            if (inBound(world.length, world[0].length, x, y)) {
                if (visited[curHorseCount - 1][x][y] == 0 & world[x][y] != -1) {
                    visited[curHorseCount - 1][x][y] = visited[curHorseCount][cur_X][cur_Y] + 1;
                    sequence.add(new int[]{x, y, curHorseCount - 1});
                }
            }
        }
    }

    /**
     * 현재 ROW와 COLUMN이 유효한 위치인지 판단
     */
    private static boolean inBound(int h, int w, int x, int y) {
        return (x >= 0 & x < h) & (y >= 0 & y < w);
    }
}
