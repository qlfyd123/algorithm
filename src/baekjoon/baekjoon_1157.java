package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine().toUpperCase();

        char[] str_split = str.toCharArray();
        str = null;
        int[] ascii = new int[26];

        for (char i : str_split) {
            int num = ((int) i) - 65;
            ascii[num]++;
        }
        int max = 0;
        int ch = 0;
        for (int i = 0; i < ascii.length; i++) {
            if (ascii[max] < ascii[i])
                max = i;
            else if (ascii[max] == ascii[i] && max != i) {
                ch = -2;
                break;
            }
        }
        for (int i = 0; i < ascii.length; i++) {
            if (ascii[max] == ascii[i] && max != i) {
                max = -2;
                break;
            }
        }
        System.out.println((char) (max + 65));

    }
}
