package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1062 {


    private static boolean[] mask;
    private static int N, K;
    private static List<Integer> words;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= (1 << (c - 'a'));
            }
            words.add(mask);
        }
        br.close();
        mask = new boolean[26];
        mask[0] = true;
        int bitmask = 0;
        mask['n' - 'a'] = true;
        mask['t' - 'a'] = true;
        mask['i' - 'a'] = true;
        mask['c' - 'a'] = true;
        bitmask |= (1 << (0));
        bitmask |= (1 << ('n' - 'a'));
        bitmask |= (1 << ('t' - 'a'));
        bitmask |= (1 << ('i' - 'a'));
        bitmask |= (1 << ('c' - 'a'));
        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }
        dfs(0, 0, bitmask);
        System.out.println(max);
    }

    private static void dfs(int index, int k, int bitMask) {
        if (k == K - 5) {
            int count = 0;
            for (int wordMask : words) {
                if ((bitMask & wordMask) == wordMask) {
                    count++;
                }
            }
            max = Math.max(count, max);
        } else {
            for (int i = index; i < 26; i++) {
                if (!mask[i]) {
                    dfs(i + 1, k + 1, bitMask | (1 << i));
                }
            }
        }
    }
}
