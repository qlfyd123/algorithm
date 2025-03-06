package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class baekjoon_2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuffer sb = new StringBuffer();
        ArrayList<Integer> al = new ArrayList<Integer>();

        for (int i = 0; i < N; i++) {
            al.add(Integer.parseInt(br.readLine()));
        }

        // sort(array);

        Collections.sort(al);

        for (int c : al) {
            sb.append(c).append("\n");
        }

        System.out.println(sb);
    }
}
