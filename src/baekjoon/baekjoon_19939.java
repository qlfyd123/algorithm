package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_19939 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int sum = (int) ((K * (K + 1)) / 2);

        if (N >= sum) {

            if ((N - sum) % K == 0) {
                System.out.println(K - 1);

            } else {
                System.out.println(K);
            }
        } else {

            System.out.println(-1);
        }
    }
}
