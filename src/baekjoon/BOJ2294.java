package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/2294">BOJ2294 동전2</a>
 */
public class BOJ2294 {
    static int n;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Set<Integer> coins = new HashSet<>();
        int[] count = new int[k + 1];
        Arrays.fill(count, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            if (coin <= k) {
                coins.add(coin);
                count[coin] = 1;
            }
        }

        int index = 0;
        while (++index < k) {
            if (count[index] == Integer.MAX_VALUE) {
                continue;
            }
            for (int coin : coins) {
                if (index + coin <= k) {
                    count[index + coin] = Math.min(count[index] + 1, count[index + coin]);
                }
            }
        }
        if (count[k] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(count[k]);
        }
    }
}
