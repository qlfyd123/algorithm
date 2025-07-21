package org.example.baekjoon.BOJ11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            List<Integer> files = new ArrayList<>();
            while (st.hasMoreTokens()) {
                files.add(Integer.parseInt(st.nextToken()));
            }
            /*구간합 저장 배열*/
            int[] range = new int[K];
            for (int i = 0; i < K; i++) {
                if (i == 0) {
                    range[i] = files.get(i);
                } else {
                    range[i] = range[i - 1] + files.get(i);
                }
            }
            int[][] cost = new int[K][K];
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < K; j++) {
                    int end = j + i;
                    if (end >= K) {
                        continue;
                    }
                    if (j != end) {
                        int rangeSum = j == 0 ? range[end] : range[end] - range[j - 1];
                        cost[j][end] = Integer.MAX_VALUE;
                        for (int mid = j; mid < end; mid++) {
                            int currCost = cost[j][mid] + cost[mid + 1][end] + rangeSum;
                            if (currCost < cost[j][end]) {
                                cost[j][end] = currCost;
                            }
                        }
                    }
                }
            }

            System.out.println(cost[0][K - 1]);

        }
    }

}
