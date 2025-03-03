package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {

    static int N, M, H;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        boolean[][] ladder = new boolean[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }
        br.close();
        if (simulate(ladder)) {
            System.out.println(0);
            return;
        }
        dfs(0, 0, ladder);
        if (answer > 3) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void dfs(int pos, int cnt, boolean[][] ladder) {
        if (cnt >= answer) return;
        if (simulate(ladder)) {
            answer = cnt;
            return;
        }
        if (cnt == 3) return;
        int total = H * (N - 1);
        for (int idx = pos; idx < total; idx++) {
            int i = idx / (N - 1) + 1;
            int j = idx % (N - 1) + 1;
            if (ladder[i][j]) continue;
            if (j > 1 && ladder[i][j - 1]) continue;
            if (j < N - 1 && ladder[i][j + 1]) continue;
            ladder[i][j] = true;
            dfs(idx + 1, cnt + 1, ladder);
            ladder[i][j] = false;
        }
    }

    private static boolean simulate(boolean[][] ladder) {
        for (int start = 1; start <= N; start++) {
            int pos = start;
            for (int i = 1; i <= H; i++) {
                if (pos > 1 && ladder[i][pos - 1]) {
                    pos--;
                } else if (pos <= N - 1 && ladder[i][pos]) {
                    pos++;
                }
            }
            if (pos != start) return false;
        }
        return true;
    }
}
