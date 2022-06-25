package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] S = br.readLine().split("");

        String temp = S[0];
        int count = 1;
        for (String s : S) {
            if (!temp.equals(s)) {
                temp = s;
                count++;
            }
        }
        if (count == 1) {
            System.out.println(0);
        } else {
            System.out.println(count / 2);
        }

    }
}
