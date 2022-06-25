package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int maxScore = 0;

        for (int i = 0; i < score.length; i++) {
            int temp = Integer.parseInt(st.nextToken());
            score[i] = temp;

            if (temp > maxScore)
                maxScore = temp;
        }

        double sum = 0;

        for (int i : score) {
            double changedScore = (double) i / (double) maxScore * 100;
            sum = sum + changedScore;
        }

        System.out.println(sum / N);
    }
}
