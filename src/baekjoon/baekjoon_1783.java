package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1783 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        if (x < 6 || y < 3) {
            int count = 1;
            if (y == 1) {
                System.out.println(1);
            } else if (y == 2) {
                int temp = Math.min(4, (x - 1) / 2 + count);
                System.out.println(temp);
            } else {
                int temp = Math.min(4, x);

                System.out.println(temp);
            }
        } else {
            int count = 5 + (x - 7);

            System.out.println(count);
        }
    }
}
