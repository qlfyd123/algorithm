package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon_13417 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char token = st.nextToken().charAt(0);
            StringBuffer sb = new StringBuffer();
            sb.append(token);
            while (N-- > 1) {
                char temp = st.nextToken().charAt(0);
                if (temp <= token) {
                    sb.insert(0, temp);
                    token=temp;
                }
                else sb.append(temp);
            }

            System.out.println(sb);
        }
    }
}