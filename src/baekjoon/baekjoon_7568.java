package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] bulk = new int[N][2];
        int[] score = new int[N];
        for (int[] i : bulk) {
            String[] bmi = br.readLine().split(" ");
            i[0] = Integer.parseInt(bmi[0]);
            i[1] = Integer.parseInt(bmi[1]);
        }
        Arrays.fill(score, 1);

        for (int i = 0; i < bulk.length; i++) {

            for (int j = i; j < bulk.length; j++) {

                int compare = compare(bulk[i][0], bulk[j][0], bulk[i][1], bulk[j][1]);

                if (compare == 0)
                    score[j]++;
                else if (compare == 1)
                    score[i]++;
                // else if(compare==2)

            }
        }

        for (int i : score) {
            System.out.print(i + " ");
        }

    }

    public static int compare(int x_weight, int y_weight, int x_height, int y_height) {
        if (x_weight > y_weight && x_height > y_height)
            return 0;
        else if (x_weight < y_weight && x_height < y_height)
            return 1;
        else if (x_weight == y_weight && x_height == y_height)
            return 2;

        return 3;
    }
}

/*
 * 5
 * 55 185
 * 58 183
 * 88 186
 * 60 175
 * 46 155
 */