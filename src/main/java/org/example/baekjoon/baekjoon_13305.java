package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_13305 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    StringTokenizer distanceSt = new StringTokenizer(br.readLine());
    StringTokenizer oilSt = new StringTokenizer(br.readLine());

    long min = Integer.parseInt(oilSt.nextToken());
    long totalDistance = 0;

    long money = 0;
    while (oilSt.hasMoreTokens()) {
      totalDistance += Long.parseLong(distanceSt.nextToken());
      long oilPrice = Long.parseLong(oilSt.nextToken());
      if (min > oilPrice || !oilSt.hasMoreTokens()) {
        money += min * totalDistance;
        totalDistance = 0;
        min = oilPrice;
      }
    }

    System.out.println(money);
  }
}
