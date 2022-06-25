package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_23028 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        while (N++ < 8) {
            st = new StringTokenizer(br.readLine());

            int major = Integer.parseInt(st.nextToken());
            int culture = Math.min(6 - major, Integer.parseInt(st.nextToken()));
            A += major * 3;
            B += major * 3 + culture * 3;
        }

        if (A >= 66 && B >= 130) {
            System.out.println("Nice");
        } else {
            System.out.println("Nae ga wae");
        }

    }
}
