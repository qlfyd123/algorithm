package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_16435 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] fruit = new int[N];

        for (int i = 0; i < N; i++) {

            fruit[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(fruit);

        for (int i : fruit) {

            if (h >= i) {

                h++;
            } else {

                System.out.println(h);
                return;
            }

        }

        System.out.println(h);
    }
}
