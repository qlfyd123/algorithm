package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_15904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String result = findCapital(br.readLine());
        System.out.println(result);
    }

    public static String findCapital(String str) {
        char[] ucpc = { 'U', 'C', 'P', 'C' };
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ucpc[index]) {
                index++;
            }

            if (index == 4) {
                return "I love UCPC";
            }

        }

        return "I hate UCPC";
    }
}
