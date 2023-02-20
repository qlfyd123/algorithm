package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int[][] match = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    if (i == 0 & j != 0) {
                        match[i][j] = match[i][j - 1];
                    } else if (j == 0 & i != 0) {
                        match[i][j] = match[i - 1][j];
                    } else {
                        match[i][j] = Math.max(match[i - 1][j], match[i][j - 1]);
                    }
                } else {
                    if (i == 0 & j != 0) {
                        match[i][j] = match[i][j - 1];
                    } else if (j == 0 & i != 0) {
                        match[i][j] = match[i - 1][j];
                    } else {
                        match[i][j] = Math.max(match[i - 1][j], match[i][j - 1]);
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < B.length; i++) {
            max = Math.max(max, match[A.length - 1][i]);
        }
        System.out.println(max);
    }
}
