package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_11047 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int money = Integer.parseInt(st.nextToken());

    int[] coin = new int[N];

    for (int i = 0; i < N; i++) {
      coin[i] = Integer.parseInt(br.readLine());
    }

    int result = 0;
    int flag = N - 1;
    while (money > 0) {
      int temp = coin[flag];
      if (money >= temp) {
        result += money / temp;
        money = money % temp;
      }
      flag--;
    }
    System.out.println(result);
  }
}
