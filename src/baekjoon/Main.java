package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] bag = new int[K + 1];
        for (int j = 0; j < N; j++) {
            st = new StringTokenizer(br.readLine(), " ");
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            bag[weight] = Math.max(bag[weight - 1], cost);
        }
        for (int i = 1; i < K; i++) {
            if (bag[i + 1] == 0) {
                bag[i + 1] = bag[i];
            } else {
                if ((i << 1) + 1 <= K) {
                    bag[2 * i + 1] = bag[i + 1] + bag[i];
                }
            }
        }
        System.out.println(bag[K]);
    }
}