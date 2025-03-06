package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1526 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        do {
            int checkNum = N;
            boolean check = true;
            while (checkNum >= 1) {
                if (checkNum % 10 != 4 && checkNum % 10 != 7)
                    check = false;
                checkNum = checkNum / 10;
            }
            if (check == true)
                break;
        } while (N-- > 0);

        System.out.println(N);
    }
}
