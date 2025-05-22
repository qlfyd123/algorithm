package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * <a href="https://www.acmicpc.net/problem/2448">BOJ2448 별 찍기 - 11</a>
 * */
public class BOJ2448 {

    private static final String[] STARS = {"*", "* *", "*****"};
    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = (int) Math.log((double) N / 3);

        StringBuilder sb = new StringBuilder();
        String[] stars = makeStars(N);
        N -= 1;
        for (String star : stars) {
            sb.append(SPACE.repeat(N));
            sb.append(star);
            sb.append(SPACE.repeat(N));
            sb.append("\n");
            N--;
        }
        System.out.println(sb);
    }

    private static String[] makeStars(int n) {
        if (n == 3) {
            return STARS;
        } else {
            String[] stars = new String[n];
            String[] temp = makeStars(n / 2);

            for (int i = 0; i < temp.length; i++) {
                stars[i] = temp[i];
            }
            for (int i = temp.length; i < stars.length; i++) {
                stars[i] = temp[i - temp.length] + SPACE.repeat(temp[stars.length - 1 - i].length()) + temp[i - temp.length];
            }
            return stars;
        }
    }
}
