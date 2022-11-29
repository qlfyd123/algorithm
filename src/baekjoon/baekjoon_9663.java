package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see <a href="https://www.acmicpc.net/problem/9663"/>백준9663</a>
 */
public class baekjoon_9663 {
    static int[] visited;
    static int ans = 0;

    private static boolean isPossible(int Row, int location) {
        for (int i = 0; i < Row; i++) {
            if (visited[i] == location) {
                return false;
            } else if (Math.abs(i - Row) == Math.abs(visited[i] - location)) {
                return false;
            }
        }
        return true;
    }

    private static void queen(int Row, int index) {
        visited[Row] = index;
        if (Row == visited.length - 1) {
            ans++;
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (isPossible(Row + 1, i)) {
                queen(Row + 1, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        visited = new int[N];
        for (int i = 0; i < N; i++) {
            queen(0, i);
        }

        System.out.println(ans);
    }
}
