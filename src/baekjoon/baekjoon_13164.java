package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int studentNum = Integer.parseInt(st.nextToken());
        int groupNum = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());

        int[] studentHeightDifference = new int[studentNum - 1];

        int studentHeightTemp = Integer.parseInt(st.nextToken());

        for (int i = 0; i < studentNum - 1; i++) {
            int temp = Integer.parseInt(st.nextToken());
            studentHeightDifference[i] = temp - studentHeightTemp;
            studentHeightTemp = temp;
        }

        Arrays.sort(studentHeightDifference);
        int cost = 0;
        for (int i = 0; i < studentHeightDifference.length - groupNum + 1; i++) {
            cost += studentHeightDifference[i];
        }

        System.out.println(cost);
    }
}
