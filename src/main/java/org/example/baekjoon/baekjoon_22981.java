package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
* https://www.acmicpc.net/problem/22981
* */
public class baekjoon_22981 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long boxNum = Long.parseLong(st.nextToken());
        Queue<Integer> efficiency = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            efficiency.add(Integer.parseInt(st.nextToken()));
        }

        int flag = 1;

        long max = 0;
        long minWorkEff = efficiency.poll();
        while (!efficiency.isEmpty()) {
            long temp = efficiency.poll();
            long maxTemp = (long) flag * minWorkEff + (N - flag) * temp;
            if (max < maxTemp) {
                max = maxTemp;
            }
            flag++;
        }
        boolean isDivedTwo = boxNum % max == 0;
        long ans = isDivedTwo ? boxNum / max : boxNum / max + 1;

        System.out.println(ans);

    }
}
