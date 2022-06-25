package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_2828 {
    public static int catchApple(int left, int right, int line) {

        if (right < line) {

            return 0;
        } else if (line < left) {

            return -(left - line);
        } else {

            return line - right;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int left = 1;
        int right = left + M - 1;

        int range = 0;
        int loop = Integer.parseInt(br.readLine());
        while (loop-- > 0) {
            int line = Integer.parseInt(br.readLine());
            int count = catchApple(left, right, line);
            left += count;
            right += count;
            range += (int) Math.abs(count);
        }

        System.out.println(range);

    }
}
