package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_1417 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int x = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }
        int[] vote = new int[N - 1];

        for (int i = 0; i < vote.length; i++) {

            vote[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        while (true) {
            Arrays.sort(vote);
            if (x <= vote[vote.length - 1]) {
                count++;
                x++;
                vote[vote.length - 1]--;
                continue;
            }
            break;
        }

        System.out.println(count);

    }
}
