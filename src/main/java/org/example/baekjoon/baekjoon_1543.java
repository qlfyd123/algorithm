package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1543 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[] text = br.readLine().toCharArray();
    char[] target = br.readLine().toCharArray();

    System.out.println(ans(text, target));
  }

  public static int ans(char[] text, char[] target) {
    int result = 0;
    for (int i = 0; i < text.length; i++) {
      if (text[i] == target[0]) {
        boolean same = true;
        for (int j = 1; j < target.length; j++) {
          try {
            int x = text[i + j];
            int y = target[j];
            if (x != y) {
              same = false;
              break;
            }
          } catch (IndexOutOfBoundsException e) {
            return result;
          }
        }
        if (same == true) {
          i += target.length - 1;
          result++;
        }
      }
    }

    return result;
  }
}
