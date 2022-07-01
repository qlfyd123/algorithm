package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());

        int Tlength=T.length()-1;
        int Slength=S.length()-1;
        while (Slength < Tlength) {
            if (T.charAt(Tlength) == 'A') {
                T.deleteCharAt(Tlength);
            } else {
                T.deleteCharAt(Tlength);
                T.reverse();
            }
            Tlength--;
        }

        if (S.toString().equals(T.toString())) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
