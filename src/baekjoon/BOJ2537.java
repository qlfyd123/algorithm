package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * <a href="https://www.acmicpc.net/problem/2573">BOJ2537 빙산</a>
 * */
public class BOJ2537 {
    static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());
        int[][] northPole = new int[row][column];
        Queue<int[]> glaciers = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < column; j++) {
                int glacier = Integer.parseInt(st.nextToken());
                if (glacier > 0) glaciers.add(new int[]{i, j});
                northPole[i][j] = glacier;
            }
        }
        int year = 0;
        while (!glaciers.isEmpty()) {
            /*빙하가 녹는 과정*/
            int[][] nextNorthPole = new int[row][column];
            int loop = glaciers.size();
            while (loop-- > 0) {
                int[] glacier = glaciers.poll();
                int water = 0;
                for (int i = 0; i < 4; i++) {
                    if (northPole[glacier[0] + direction[i][0]][glacier[1] + direction[i][1]] == 0) {
                        water++;
                    }
                }
                int nextGlacierHeight = Math.max(northPole[glacier[0]][glacier[1]] - water, 0);
                if (nextGlacierHeight > 0) {
                    nextNorthPole[glacier[0]][glacier[1]] = nextGlacierHeight;
                    glaciers.add(new int[]{glacier[0], glacier[1]});
                }
            }
            /*빙하가 조각나지 않고 다 녹은 경우 0을 출력하고 종료*/
            if (glaciers.isEmpty()) {
                System.out.println(0);
                return;
            } else {
                int pieceNum = searchPiece(nextNorthPole, glaciers.peek(), new boolean[row][column]);
                year++;
                /*빙하가 녹은 이후 조각났는지를 판별*/
                if (pieceNum != glaciers.size()) {
                    System.out.println(year);
                    return;
                }
                northPole = nextNorthPole;
            }
        }
        System.out.println(0);
    }

    /**
     * @param nextNorthPole 빙하
     * @param glacier 탐색을 시작할 빙하
     * @param visited 중복탬색 방지용 방문 배열
     * @return 조각난 빙하의 빙하 갯수를 반환
     */
    private static int searchPiece(int[][] nextNorthPole, int[] glacier, boolean[][] visited) {
        int pieceNum = 1;
        visited[glacier[0]][glacier[1]] = true;
        for (int i = 0; i < 4; i++) {
            if (!visited[glacier[0] + direction[i][0]][glacier[1] + direction[i][1]] & nextNorthPole[glacier[0] + direction[i][0]][glacier[1] + direction[i][1]] != 0) {
                pieceNum += searchPiece(nextNorthPole, new int[]{glacier[0] + direction[i][0], glacier[1] + direction[i][1]}, visited);
            }
        }
        return pieceNum;
    }

}
