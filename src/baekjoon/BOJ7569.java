package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * @see <a href="https://www.acmicpc.net/problem/7569">BOJ 7569 토마토</a>
* */
public class BOJ7569 {
    static final int[] dx = {0, 0, 1, -1, 0, 0};
    static final int[] dy = {1, -1, 0, 0, 0, 0};
    static final int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer init = new StringTokenizer(br.readLine());
        int column = Integer.parseInt(init.nextToken());
        int row = Integer.parseInt(init.nextToken());
        int layer = Integer.parseInt(init.nextToken());

        int[][][] storage = new int[layer][row][column];
        int totalTomato = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < layer; i++) {
            for (int j = 0; j < row; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < column; k++) {
                    int tomato = Integer.parseInt(st.nextToken());
                    storage[i][j][k] = tomato;
                    if (tomato == 1) {
                        totalTomato++;
                        queue.add(new int[]{i, j, k});
                    } else if (tomato == -1) {
                        totalTomato++;
                    }
                }
            }
        }
        Queue<int[]> nextProcess = new LinkedList<>();
        int count = 0;
        do {
            nextProcess.clear();
            while (!queue.isEmpty()) {
                int[] ripeTomato = queue.poll();
                for (int i = 0; i < 6; i++) {
                    try {
                        int[] nextTomato = {ripeTomato[0] + dz[i], ripeTomato[1] + dx[i], ripeTomato[2] + dy[i]};
                        if (storage[nextTomato[0]][nextTomato[1]][nextTomato[2]] == 0) {
                            storage[nextTomato[0]][nextTomato[1]][nextTomato[2]] = 1;
                            totalTomato++;
                            nextProcess.add(nextTomato);
                        }
                    } catch (ArrayIndexOutOfBoundsException ignored) {

                    }
                }
            }
            if (!nextProcess.isEmpty()) {
                count++;
            }
            queue.addAll(nextProcess);
        } while (!nextProcess.isEmpty());
        if (totalTomato == row * column * layer) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }
}
