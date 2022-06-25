package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_1547 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        String location = "1";
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String x = st.nextToken();
            String y = st.nextToken();

            if (x.equals(location)) {
                location = y;
            } else if (y.equals(location)) {
                location = x;
            }
        }

        System.out.println(location);
    }
}
