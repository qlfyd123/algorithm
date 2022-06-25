package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int year = 1;
        int e = 1, s = 1, m = 1;

        while (e != E || s != S || m != M) {
            if (e < 15)
                e++;
            else
                e = 1;
            if (s < 28)
                s++;
            else
                s = 1;
            if (m < 19)
                m++;
            else
                m = 1;
            year++;
        }

        System.out.println(year);
    }
}
