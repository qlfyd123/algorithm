package baekjoon;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * <a href="https://www.acmicpc.net/problem/1715">BOJ1715</a>
 * */
public class BOJ1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> card = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            card.add(Integer.parseInt(br.readLine()));
        }
        br.close();
        int compareCount = 0;
        while (card.size() > 1) {
            int a = card.poll();
            int b = card.poll();
            int cardBundleSize = a + b;
            compareCount += cardBundleSize;
            card.add(cardBundleSize);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(compareCount));
        bw.close();
    }
}
