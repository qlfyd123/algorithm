package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_11399 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] watingTime = new int[N];

    for (int i = 0; i < N; i++) {
      watingTime[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(watingTime);

    int result = 0;
    for (int i = 0; i < N; i++) {
      result += watingTime[i] * (N - i);
    }

    System.out.println(result);
  }
}
