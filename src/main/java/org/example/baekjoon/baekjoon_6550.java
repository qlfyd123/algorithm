package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_6550 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        String str;
        while ((str = br.readLine()) != null) {

            st = new StringTokenizer(str);
            char[] s = st.nextToken().toCharArray();
            char[] t = st.nextToken().toCharArray();
            int flag = 0;

            for (int i = 0; i < t.length; i++) {

                if (t[i] == s[flag]) {

                    flag++;

                    if (flag == s.length) {

                        break;
                    }
                }
            }

            if (flag == s.length) {

                System.out.println("Yes");
            } else {

                System.out.println("No");
            }
        }

    }
}
