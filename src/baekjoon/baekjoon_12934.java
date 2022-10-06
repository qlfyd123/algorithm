package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_12934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());
        double limit = ((-1 + Math.sqrt(1 + 8 * (x + y))) / 2) + 1;
        if (limit % 1 == 0) {
            int winCount = 0;
            while (limit-- > 1) {
                if (limit <= x) {
                    x -= limit;
                    winCount++;
                } else {
                    y -= limit;
                }
            }
            System.out.println(winCount);
        } else {
            System.out.println(-1);
        }
    }
}