package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
/*
*https://www.acmicpc.net/problem/10427
* */
public class baekjoon_10427 {
    static int[] S;

    static long liquidation(int index) {
        int minLiquidation = Integer.MAX_VALUE;
        for (int i = 0; i < S.length - index + 1; i++) {
            int liquidation = 0;
            for (int j = i; j <= i + index - 1; j++) {
                liquidation += S[i + index - 1] - S[j];
            }
            minLiquidation = Math.min(liquidation, minLiquidation);
        }
        return minLiquidation;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            S = Collections.list(st).stream()
                    .map(token -> (String) token)
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();
            long sum = 0;
            for (int i = 2; i <= N; i++) {
                long temp = liquidation(i);
                sum += temp;
            }

            System.out.println(sum);
        }
    }
}
