package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
* https://www.acmicpc.net/problem/14222
* */
public class baekjoon_14222 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] require = new boolean[N + 1];
        Arrays.fill(require, false);
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int value = Integer.parseInt(st.nextToken());
            boolean temp = false;
            for (int i = value; i < N + 1; i += K) {
                if (!require[i]) {
                    require[i] = true;
                    temp = true;
                    break;
                }
            }
            if (!temp) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }
}
