package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int greatestCommonFactor = 0;
        int greatestCommonMultiple = 0;

        int flag = 0;
        if (x > y)
            flag = y + 1;
        else
            flag = x + 1;
        while (flag-- > 0) {
            if ((x % flag == 0) && (y % flag == 0)) {
                greatestCommonFactor = flag;
                break;
            }
        }

        int i = 1;
        while (true) {
            int temp = greatestCommonFactor * i;
            if (temp % x == 0 & temp % y == 0) {
                greatestCommonMultiple = temp;
                break;
            }
            i++;
        }

        System.out.println(greatestCommonFactor);
        System.out.println(greatestCommonMultiple);
    }
}
