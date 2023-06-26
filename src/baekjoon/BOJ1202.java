package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * <a href="https://www.acmicpc.net/problem/1202">BOJ 1202 보석 도둑</a>
 * */
public class BOJ1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] jewels = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            jewels[i][0] = Integer.parseInt(st.nextToken());
            jewels[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(jewels, Comparator.comparingInt(o -> o[0]));
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(bufferedReader.readLine());
        }
        Arrays.sort(bags);
        Queue<int[]> jewellQueue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        long sum = 0;
        int jewelIndex = 0;
        for (int bag : bags) {
            for (; jewelIndex < jewels.length; jewelIndex++) {
                if (jewels[jewelIndex][0] > bag) break;
                jewellQueue.add(jewels[jewelIndex]);
            }
            if (!jewellQueue.isEmpty()) {
                sum += jewellQueue.poll()[1];
            }
        }
        System.out.println(sum);
    }
}
