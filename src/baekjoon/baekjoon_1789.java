package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());
        int i = 0;
        long sum = 0;
        while (true) {
            sum += i;
            if (i >= (N - sum)) {
                break;
            }
            i++;
        }

        System.out.println(i);
    }
}
