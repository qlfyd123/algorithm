package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] balloon = new int[1000002];

        int[] balloonHeight = new int[N];


        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            balloonHeight[i] = Integer.parseInt(st.nextToken());
        }


        int result = 0;
        for (int height : balloonHeight) {
            if (balloon[height + 1] == 0) {
                result++;
                balloon[height]++;
            } else {
                balloon[height]++;
                balloon[height + 1]--;
            }
        }
        System.out.println(result);
    }
}
