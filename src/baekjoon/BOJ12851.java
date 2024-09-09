package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851 {
    static int N, K;
    static int min;
    static int minFrequency = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N, 0});
        boolean[] visited = new boolean[100001];
        while (!queue.isEmpty()) {
            bfs(queue, visited);
        }
        if (N == 1) minFrequency++;
        System.out.println(min + "\n" + minFrequency);
    }

    private static void bfs(Queue<int[]> queue, boolean[] visited) {
        int position, count;
        int[] current = queue.poll();
        position = current[0];
        count = current[1];
        if (position == K) {
            if (count < min) {
                min = count;
                minFrequency = 1;
            } else if (count == min) {
                minFrequency++;
            }
        } else {
            visited[position] = true;
            for (int i = 0; i < 3; i++) {
                int nextPosition = switch (i) {
                    case 0 -> position + 1;
                    case 1 -> position - 1;
                    case 2 -> position * 2;
                    default -> 0;
                };
                if (nextPosition < visited.length && nextPosition >= 0 && !visited[nextPosition])
                    queue.add(new int[]{nextPosition, count + 1});
            }
        }
    }
}
