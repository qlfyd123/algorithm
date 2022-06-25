package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_2891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        boolean[] boat = new boolean[N];
        Arrays.fill(boat, true);
        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            int brokenBoat = Integer.parseInt(st.nextToken());
            boat[brokenBoat - 1] = false;
        }

        st = new StringTokenizer(br.readLine());
        int count = S;
        while (st.hasMoreTokens()) {
            int spare = Integer.parseInt(st.nextToken()) - 1;

            if (!boat[spare]) {
                boat[spare] = true;
                count--;
            } else if (spare > 0 && !boat[spare - 1]) {
                boat[spare - 1] = true;
                count--;
            } else if (spare < N - 1 && !boat[spare + 1]) {
                boat[spare + 1] = true;
                count--;
            }
        }
        System.out.println(count);
    }
}
