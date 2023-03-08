package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @see <a href="https://www.acmicpc.net/problem/2447">BOJ2447 별 찍기-10</a>
 */
public class BOJ2447 {
    static final String star = "*";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(countingStar(N));
    }

    public static StringBuilder countingStar(int N) {
        if (N == 1) {
            return new StringBuilder(star);
        } else {
            StringBuilder str = new StringBuilder();
            String[] star = countingStar(N / 3).toString().split("\n");
            for (int i = 0; i < 3; i++) {
                for (int index = 0; index < N / 3; index++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == 1 & j == 1) {
                            str.append(" ".repeat(N / 3));
                        } else {
                            str.append(star[index]);
                        }
                    }
                    str.append("\n");
                }
            }
            return str;
        }
    }
}