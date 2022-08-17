package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 *https://www.acmicpc.net/problem/23843
 * */
public class baekjoon_23843 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Integer[] chargingTime = new Integer[N];

        for (int i = 0; i < N; i++) {
            chargingTime[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(chargingTime,Collections.reverseOrder());
        Queue<Integer> timeQueue = new PriorityQueue<>();
        int max = 0;
        for (int time : chargingTime) {
            if (timeQueue.size() < M) {
                max = Math.max(max, time);
                timeQueue.add(time);
            } else {
                int temp = timeQueue.poll();
                max = Math.max(temp + time, max);
                timeQueue.add(temp + time);
            }
        }
        System.out.println(max);
    }
}
