package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_21557 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int left, right;

    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(st.nextToken());
    }

    left = array[0];
    right = array[N - 1];

    while (N > 2) {
      if (left > right) {
        N--;
        left--;
      } else if (N == 3) {
        left--;
        right--;
        N--;
      } else {
        N--;
        right--;
      }
    }

    System.out.print(Math.max(left, right));
  }
}
