package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1049 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    StringTokenizer priceSt = new StringTokenizer(br.readLine());
    int minSetPrice = Integer.parseInt(priceSt.nextToken());
    int minPrice = Integer.parseInt(priceSt.nextToken());

    while (M-- > 1) {
      priceSt = new StringTokenizer(br.readLine());
      int setPrice = Integer.parseInt(priceSt.nextToken());
      int price = Integer.parseInt(priceSt.nextToken());

      minSetPrice = Math.min(minSetPrice, setPrice);
      minPrice = Math.min(minPrice, price);
    }

    if (minPrice * 6 < minSetPrice) {
      System.out.println(minPrice * N);
      return;
    } else {
      int remain = Math.min(minSetPrice, (N % 6) * minPrice);
      int result = (N / 6) * minSetPrice + remain;
      System.out.println(result);
    }
  }
}
